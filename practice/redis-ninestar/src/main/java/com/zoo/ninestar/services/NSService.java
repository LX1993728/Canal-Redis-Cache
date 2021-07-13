package com.zoo.ninestar.services;

import com.zoo.ninestar.domains.entity.Gift;
import com.zoo.ninestar.domains.entity.NSPK;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.domains.vo.NSResultVO;
import com.zoo.ninestar.domains.vo.redis.NSConfigVO;
import com.zoo.ninestar.domains.vo.redis.NSPKTotalVO;

import java.util.List;
import java.util.Map;

/**
 * 九星神器 相关的service操作
 */
public interface NSService {
    /**
     * 获取所有的skill技能
     * @param isLoad 是否重新刷新加载到redis
     * @return
     */
    List<NSPKSkill> getLoadSkills(boolean isLoad);

    /**
     * get nine star pk total
     * @param pkId
     * @return
     */
    NSPKTotalVO getNSPKTotalVO(Long pkId);

    Long getBeRefedSkillId(Long skillId, boolean isLoad);

    /**
     * 获取指定skillId的skill技能
     * @param skillId
     * @param isLoad
     * @return
     */
    NSPKSkill getLoadSkill(Long skillId, boolean isLoad);

    Gift getLoadSkillGiftBySkillId(Long skillId, boolean includeSkill, boolean isLoad);

    Gift getLoadSkillGiftByGiftId(Long giftId, boolean includeSkill, boolean isLoad);

    List<Gift> getSkillGiftStatuses(Long pkId, Long masterId);

    List<NSPKSkill> getSkillAndStatuses(Long pkId, Long masterId);

    Map<Long, NSPKSkill> getSkillAndStatusesMap(Long pkId, Long masterId);

    NSConfigVO getInitGlobalConfig();

    NSResultVO<NSPK> invitePK();

    NSResultVO<NSPK> startPK();

    NSPK saveOrUpdateNsPK(NSPK nspk, boolean isReload);

    NSPK getLoadNSPK(Long pkId, boolean isLoad);
}
