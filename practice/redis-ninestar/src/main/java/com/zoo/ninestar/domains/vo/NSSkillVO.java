package com.zoo.ninestar.domains.vo;

import com.zoo.ninestar.domains.entity.NSPKSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * PK 过程中需要某一技能的执行情况
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSSkillVO extends NSPKSkill{
    // 剩余可执行次数 为null或-1表示没有次数限制
    private Integer remainTimes;
    // 表示是否为激活状态
    private Boolean isActive;

    public NSSkillVO(NSPKSkill skill, Integer remainTimes, Boolean isActive) {
        super(skill.getId(), skill.getName(),skill.getIcon(), skill.getForMaster(), skill.getIngots(), skill.getPointNum(), skill.getFeelNum(),
                skill.getWealthNum(), skill.getMasterScore(),skill.getDelayMinute(), skill.getMaxTimes(), skill.getDescription(),skill.getHurting(),
                skill.getPlussing(), skill.getBuffDelaySecond(),skill.getBuffMaxCount(), skill.getBuffHurtingSecond(), skill.getBuffAdditionSecond(),
                skill.getClearSelfBuff(), skill.getRefSkillId(),skill.getRefSkillCount());
        this.remainTimes = remainTimes;
        this.isActive = isActive;
    }
}
