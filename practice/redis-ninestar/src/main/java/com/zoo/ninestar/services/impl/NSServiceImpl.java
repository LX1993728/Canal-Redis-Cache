package com.zoo.ninestar.services.impl;

import com.zoo.ninestar.domains.constants.NSKeyConfig;
import com.zoo.ninestar.domains.entity.NSPK;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.domains.vo.NSResultVO;
import com.zoo.ninestar.domains.vo.redis.NSConfigVO;
import com.zoo.ninestar.services.NSService;
import com.zoo.ninestar.utils.JedisUtils;
import com.zoo.ninestar.utils.MapObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Service
public class NSServiceImpl implements NSService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JedisUtils jedisUtils;

    @Override
    public List<NSPKSkill> getLoadSkills(boolean isLoad) {
        String skillKeyPattern = String.format("%s%s", NSKeyConfig.getSKILL_PRE() , "_*");
        final Set<String> skillKeys = jedisUtils.action(jedis -> jedis.keys(skillKeyPattern));
        if (skillKeys == null || skillKeys.isEmpty() || isLoad){
            log.info("skills key not exists or isLoad, loading from db......");
            final List<NSPKSkill> skillRecords = entityManager.createQuery("select skill from NSPKSkill skill", NSPKSkill.class).getResultList();
            for (NSPKSkill skill : skillRecords){
                String sKey = NSKeyConfig.getSkillKey(skill.getId());
                final Map<String, String> map = jedisUtils.hmsetResetObj(skill, sKey);
                log.info("redis skill hash skillId={}\tsize={}\t ----", skill.getId(), map.size());
            }
            return skillRecords;
        }else {
            log.info("skills key exists and not isLoad, loading from redis");
            List<NSPKSkill> skills = new ArrayList<>();
            for (String skey : skillKeys){
                final Map<String, String> map = jedisUtils.action(jedis -> jedis.hgetAll(skey));
                final NSPKSkill skill = MapObjUtils.strMap2Obj(NSPKSkill.class, map);
                if (skill != null){
                    skills.add(skill);
                }
            }
            log.info("load skills from redis success count={}", skills.size());
            return skills;
        }
    }

    /**
     * key : id of M skill which be the refSkillId field of other skill
     * value: id of B skill
     * @return
     */
    private Map<Long, Long> getBeRefSkillIdRelationMap(boolean isLoad){
        final String beRefSkillKey = NSKeyConfig.getBeRefSkillKey();
        Map<Long, Long> relationMap = new HashMap<>();
        if (!jedisUtils.exists(beRefSkillKey) || isLoad){
            final List<NSPKSkill> loadSkills = getLoadSkills(isLoad);
            for (NSPKSkill skill : loadSkills){
                if (skill.getRefSkillId() != null){
                    relationMap.put(skill.getRefSkillId(), skill.getId());
                }
            }
            jedisUtils.hmsetResetObj(relationMap, beRefSkillKey);
        }else {
            final Map<String, String> map = jedisUtils.action(jedis -> jedis.hgetAll(beRefSkillKey));
            for (Map.Entry<String, String> entry : map.entrySet()){
                relationMap.put(Long.parseLong(entry.getKey()), Long.parseLong(entry.getValue()));
            }
        }
        return relationMap;
    }

    /**
     *  get be refed skillId
     * @param skillId
     * @param isLoad
     * @return
     */
    public Long getBeRefedSkillId(Long skillId, boolean isLoad){
        final Map<Long, Long> rMap = getBeRefSkillIdRelationMap(isLoad);
        if (rMap.containsKey(skillId)){
            return rMap.get(skillId);
        }

        return null;
    }

    @Override
    public NSPKSkill getLoadSkill(Long skillId, boolean isLoad) {
        assert  skillId != null;
        String skey = NSKeyConfig.getSkillKey(skillId);
        final Boolean exists = jedisUtils.exists(skey);
        if (!exists || isLoad){
            log.info("load a skill by skillId from db and store to redis skillId={}...", skillId);
            NSPKSkill skill = null;
            try {
                skill = entityManager.find(NSPKSkill.class, skillId);
                jedisUtils.hmsetResetObj(skill, skey);
            }catch (Exception ignored){}
            return skill;
        }else {
            log.info("load a skill by skillId only from redis and skillId={}", skillId);
            final Map<String, String> map = jedisUtils.action(jedis -> jedis.hgetAll(skey));
            final NSPKSkill skill = MapObjUtils.strMap2Obj(NSPKSkill.class, map);
            return skill;
        }
    }

    /**
     *  get skills and statuses
     * @return
     */
    @Override
    public List<NSPKSkill> getSkillAndStatuses(Long pkId, Long masterId){
        assert pkId != null && masterId != null;
        final NSConfigVO globalConfig = getInitGlobalConfig();
        assert globalConfig != null;
        final List<NSPKSkill> loadSkills = getLoadSkills(false);
        Map<Long, Integer> skillModMap = new HashMap<>();
        for (NSPKSkill skill : loadSkills){
            final Integer maxTimes = skill.getMaxTimes();
            final Long refSkillId = skill.getRefSkillId();
            final Integer refSkillCount = skill.getRefSkillCount();
            final String skillTimesKey = NSKeyConfig.getSkillTimesKey(pkId, masterId, skill.getId());
            final String skillIsActiveKey = NSKeyConfig.getSkillIsActiveKey(pkId, masterId, skill.getId());
            final Long skillTimes = jedisUtils.action(jedis -> jedis.incrBy(skillTimesKey, 0L));
            final Boolean existsIsActiveKey = jedisUtils.exists(skillIsActiveKey);
            if (existsIsActiveKey){
                final boolean b = Boolean.parseBoolean(jedisUtils.get(skillIsActiveKey));
                // redis priority highest
                skill.setIsActive(b);
            }

            if (refSkillId != null && refSkillCount != null && refSkillCount != -1){
                final String refSkillTimesKey = NSKeyConfig.getSkillTimesKey(pkId, masterId, refSkillId);
                final Long refSkillTimes = jedisUtils.action(jedis -> jedis.incrBy(refSkillTimesKey, 0L));
                if (!existsIsActiveKey || !skill.getIsActive()){ // false表示已经点击过了
                    skill.setIsActive(refSkillTimes > refSkillCount && (refSkillTimes - skillTimes * refSkillCount) % refSkillCount == 0);
                    jedisUtils.set(skillIsActiveKey, String.valueOf(skill.getIsActive()));
                }
                final String refSkillModTimesKey = NSKeyConfig.getSkillModTimesKey(pkId, masterId, refSkillId);
                if (!jedisUtils.exists(refSkillModTimesKey)){
                    if (skill.getIsActive()){
                        jedisUtils.set(refSkillModTimesKey, String.valueOf(0));
                        skillModMap.put(refSkillId, 0);
                    }else {
                        int initModTimes = refSkillTimes.intValue() % refSkillCount;
                        jedisUtils.set(refSkillModTimesKey, String.valueOf(initModTimes));
                        skillModMap.put(refSkillId, initModTimes);
                    }
                }else {
                    final int modTimes = Integer.parseInt(jedisUtils.get(refSkillModTimesKey));
                    skillModMap.put(refSkillId, modTimes);
                }
            }

            if (skill.getIsActive() == null){
                skill.setIsActive(true);
            }

            if (maxTimes == null || maxTimes <= 0){
                skill.setRemainTimes(-1); // -1 - NO times limit
            }else {
                int remainTimes = maxTimes -  Math.min(skillTimes.intValue(), maxTimes);
                skill.setRemainTimes(remainTimes);
                if (skill.getIsActive() && remainTimes <= 0){
                    skill.setIsActive(false);
                    jedisUtils.set(skillIsActiveKey, String.valueOf(false));
                }
            }
        }

        for (NSPKSkill skill2 : loadSkills){
            final Long skill2Id = skill2.getId();
            if (skillModMap.containsKey(skill2Id)){
                skill2.setModTimes(skillModMap.get(skill2Id));
            }
        }
        return loadSkills;
    }

    @Override
    public Map<Long, NSPKSkill> getSkillAndStatusesMap(Long pkId, Long masterId){
        final List<NSPKSkill> skillAndStatuses = getSkillAndStatuses(pkId, masterId);
        Map<Long, NSPKSkill> resultMap = new HashMap<>();
        for (NSPKSkill skill : skillAndStatuses){
            resultMap.put(skill.getId(), skill);
        }
        return resultMap;
    }

    /**
     * get config
     * @return
     */
    @Override
    public NSConfigVO getInitGlobalConfig(){
        final String key = NSKeyConfig.getINIT_GLOBAL_CONFIG_KEY();
        final Boolean exists = jedisUtils.action(jedis -> jedis.exists(key));
        if (exists){
            final Map<String, String> map = jedisUtils.action(jedis -> jedis.hgetAll(key));
            return MapObjUtils.strMap2Obj(NSConfigVO.class, map);
        }else {
            final NSConfigVO configVO = new NSConfigVO();
            jedisUtils.hmsetResetObj(configVO, key);
            return configVO;
        }
    }

    @Override
    public NSPK saveOrUpdateNsPK(NSPK nspk, boolean isReload){
        assert  nspk != null;
        if (nspk.getId() == null){
            log.info("------ insert NSPK={} -----", nspk);
            entityManager.persist(nspk);
            assert nspk.getId() != null;
            final String pkRecordKey = NSKeyConfig.getPKRecordKey(nspk.getId());
            if (jedisUtils.exists(pkRecordKey)){
                jedisUtils.del(pkRecordKey);
            }
            if (isReload){
                final Map<String, String> map = jedisUtils.hmsetResetObj(pkRecordKey, pkRecordKey);
                log.info("---- insert nspk to redis map={}", map);
            }
            return nspk;
        }else {
            log.info("---- update nspk to db nspk={} -----", nspk);
            final Long nspkId = nspk.getId();
            Map<String, Object> map = MapObjUtils.obj2OBJMap(nspk);
            map.remove("id");
            try {
                final String updateEntityHql = MapObjUtils.getUpdateEntityHql(nspkId, NSPK.class, map);
                log.info("---- update nspk hql={} -----", updateEntityHql);
                entityManager.createQuery(updateEntityHql).executeUpdate();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return null;
            }
            final NSPK nspkResult = entityManager.find(NSPK.class, nspkId);
            assert nspkResult != null;
            if (isReload){
                final String pkRecordKey = NSKeyConfig.getPKRecordKey(nspkId);
                if(!jedisUtils.exists(pkRecordKey)){
                    final Map<String, String> strMap = jedisUtils.hmsetResetObj(nspkResult, pkRecordKey);
                    log.info("---- init nspk to redis map={}", strMap);
                }else {
                    final Map<String, String> updateMap = jedisUtils.hmsetUpdateObj(nspk, pkRecordKey);
                    log.info("---- update nspk to redis map={}", updateMap);
                }
            }
            return nspkResult;
        }
    }

    @Override
    public NSPK getLoadNSPK(Long pkId, boolean isLoad) {
        assert  pkId != null;
        String skey = NSKeyConfig.getSkillKey(pkId);
        final Boolean exists = jedisUtils.exists(skey);
        if (!exists || isLoad){
            log.info("load a nspk by pkId from db and store to redis pkId={}...", pkId);
            NSPK nsPK = null;
            try {
                nsPK = entityManager.find(NSPK.class, pkId);
                jedisUtils.hmsetResetObj(nsPK, skey);
            }catch (Exception ignored){}
            return nsPK;
        }else {
            log.info("load a nspk by pkId only from redis and pkId={}", pkId);
            final Map<String, String> map = jedisUtils.action(jedis -> jedis.hgetAll(skey));
            return MapObjUtils.strMap2Obj(NSPK.class, map);
        }
    }

    @Override
    public NSResultVO<NSPK> invitePK(){
        //TODO:1 ——invitePK 5 step
        final NSResultVO<NSPK> nsResultVO = new NSResultVO<>();
        /**
         * step1: 检查当前主播 与对方主播是否处于连麦状态 && !主播当前是否正在开始或进行一场PK
         *   满足 step1 ——> to step2:
         * step2: 插入一条初始化状态的PK记录 status=0 && 同步PK记录同步到Redis
         * step3: 初始化 Redis数据配置->[各个技能相关的NSSkillStatusVO、双方总血量的TotalVO]
         * step4: 给双方分别发送消息通知 && 同时放入队列一个等待时间大小的延时任务
         *   step:4.1 延时任务用于检查PK记录的状态是否是已接受进行中的状态
         *       if status == 0 给主播双方发送超时未接受的通知 并结束PK相关逻辑 else 不做处理
         * step5: 返回包装NSPK相关的VO

         */
        return nsResultVO;
    }

    @Override
    public NSResultVO<NSPK> startPK(){
        //TODO:2—— startPK 5 step
        final NSResultVO<NSPK> nsResultVO = new NSResultVO<>();
        /**
         * step1: 检查当前主播与发起者主播是否处于连麦状态 && !主播当前是否正在开始或进行一场PK
         * 满足 step1 ——> to step2:
         * step2: 修改PK记录的状态 if 接受 status=1; if 拒绝 status=2 && 同步修改PK记录同步到Redis
         * step3: if 接受 发送开始PK的通知到双方直播间 (包括各个技能的状态以及双方总血量等相关信息)
         *    step3.1: 获取配置中的PK最大时间，并根据时间创建延时任务放入队列
         *        该任务首先检查该PK是否结束?
         *        如果未结束，检查双方血量大小并更新PK记录的winner && 同时结束PK修改status=4并同步到redis && 最后发送PK结束的通知到双方直播间
         *  最后根据每个粉丝的贡献，分别给予对应的称号，再次发送通知到双方直播间
         * step5: 返回包装NSPK相关的VO
         */
        return nsResultVO;
    }


    public NSResultVO<NSPKSkill> useSkill(Long pkId, Long targetMasterId, Long skillId, Integer count){
        //TODO:3—— 封装PK开始后, 使用对应技能的useSkill()方法
        assert pkId != null && targetMasterId != null && skillId != null;
        count = count == null ? 1 : Math.abs(count);
        final NSResultVO<NSPKSkill> resultVO = new NSResultVO<>();

        // check balance is enough
        final NSPKSkill loadSkill = getLoadSkill(skillId, false);
        /**
         * TODO:// 余额检查
         * if  用户的余额 < 该技能的元宝数 * count
         *      return resultVO
         */

        // check skill invalid
        final Map<Long, NSPKSkill> statusesMap = getSkillAndStatusesMap(pkId, targetMasterId);
        if (!statusesMap.containsKey(skillId)){
            resultVO.setSuccess(false);
            resultVO.setDescription("不存在此技能skill !!!");
            return resultVO;
        }


        // (add a remote lock for  skill)
        final NSPKSkill skill = statusesMap.get(skillId);
        resultVO.setObj(skill);
        if (!skill.getIsActive()){
            resultVO.setSuccess(false);
            resultVO.setDescription("不满足使用此技能的条件!!! ");
            return resultVO;
        }

        final Long refSkillId = skill.getRefSkillId();
        if (refSkillId != null){
            count = Math.min(count, 1);
        }

        if (skill.getMaxTimes() != null && (count + skill.getRemainTimes()) > skill.getMaxTimes()){
            count =  skill.getMaxTimes() - skill.getRemainTimes();
        }

        final String skillTimesKey = NSKeyConfig.getSkillTimesKey(pkId, targetMasterId, skillId);
        Integer finalCount = count;
        final Long newTimes = jedisUtils.action(jedis -> jedis.incrBy(skillTimesKey, finalCount));
        // TODO: print log

        // if have max times
        if (skill.getMaxTimes() != null){
            skill.setRemainTimes(skill.getMaxTimes() - newTimes.intValue());
        }

        // if is a B skill
        if (refSkillId != null){
            final String refSkillModTimesKey = NSKeyConfig.getSkillModTimesKey(pkId, targetMasterId, refSkillId);
            jedisUtils.set(refSkillModTimesKey, String.valueOf(0));
            final String skillIsActiveKey = NSKeyConfig.getSkillIsActiveKey(pkId, targetMasterId, skillId);
            jedisUtils.set(skillIsActiveKey, String.valueOf(false));
        }

        // if is a M skill
        final Long beRefedSkillId = getBeRefedSkillId(skillId, false);
        if (beRefedSkillId != null){
            final NSPKSkill beRefSkill = statusesMap.get(beRefedSkillId);
            assert beRefSkill != null;
            if (!beRefSkill.getIsActive()){
                final String selfModTimesKey = NSKeyConfig.getSkillModTimesKey(pkId, targetMasterId, skillId);
                final Long selfModTimes = jedisUtils.action(jedis -> jedis.incrBy(selfModTimesKey, 0));
                long selfNewModTimes = Math.min(selfModTimes + finalCount, beRefSkill.getRefSkillCount());
                jedisUtils.set(selfModTimesKey, String.valueOf(selfNewModTimes));
                if (selfNewModTimes == beRefSkill.getRefSkillCount()){
                    beRefSkill.setIsActive(true);
                }
            }
        }

        // TODO: 处理技能的各项功能参数

        return resultVO;
    }
}
