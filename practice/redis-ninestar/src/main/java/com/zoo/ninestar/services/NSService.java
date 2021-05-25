package com.zoo.ninestar.services;

import com.zoo.ninestar.domains.entity.NSPKSkill;

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
}
