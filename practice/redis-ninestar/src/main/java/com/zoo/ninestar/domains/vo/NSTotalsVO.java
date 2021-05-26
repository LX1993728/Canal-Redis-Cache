package com.zoo.ninestar.domains.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 一场PK赛中PK双方总血量
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSTotalsVO {
    Long masterTotal;
    Long targetMasterTotal;
}
