package com.zoo.ninestar.services;

import com.zoo.ninestar.domains.entity.NSPK;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.domains.vo.NSResultVO;
import com.zoo.ninestar.domains.vo.redis.NSConfigVO;

import java.util.List;

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
     * 获取指定skillId的skill技能
     * @param skillId
     * @param isLoad
     * @return
     */
    NSPKSkill getLoadSkill(Long skillId, boolean isLoad);

    NSConfigVO getInitGlobalTotal();

    NSResultVO<NSPK> invitePK();

    NSResultVO<NSPK> startPK();

    NSPK saveOrUpdateNsPK(NSPK nspk, boolean isReload);

    NSPK getLoadNSPK(Long pkId, boolean isLoad);
}
