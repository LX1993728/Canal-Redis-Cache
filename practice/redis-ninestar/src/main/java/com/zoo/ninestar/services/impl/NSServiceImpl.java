package com.zoo.ninestar.services.impl;

import com.zoo.ninestar.domains.constants.NSKeyConfig;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.services.NSService;
import com.zoo.ninestar.utils.JedisUtils;
import com.zoo.ninestar.utils.MapObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
