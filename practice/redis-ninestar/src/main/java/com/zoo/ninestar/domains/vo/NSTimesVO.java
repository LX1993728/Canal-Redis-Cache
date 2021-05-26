package com.zoo.ninestar.domains.vo;

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
public class NSTimesVO {
    Long skillId;
    Integer times; // 已经执行的次数
    Integer maxTimes; // 单场PK的最大可用次数
}
