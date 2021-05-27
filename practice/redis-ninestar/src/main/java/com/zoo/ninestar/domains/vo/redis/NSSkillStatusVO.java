package com.zoo.ninestar.domains.vo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * PK 过程中需要某一技能的执行次数
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSSkillStatusVO {
    private Long skillId;
    private Integer times = 0; // 已经执行的次数
    private Integer maxTimes = Integer.MAX_VALUE; // 单场PK的最大可用次数
    private Boolean isActive = true;
}
