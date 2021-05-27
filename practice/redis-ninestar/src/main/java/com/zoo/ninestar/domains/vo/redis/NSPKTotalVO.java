package com.zoo.ninestar.domains.vo.redis;

import com.zoo.ninestar.domains.entity.NSPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 状态：一场PK赛中PK双方PK情况
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSPKTotalVO {
    private Integer masterTotal; // 发起者剩余血量
    private Integer targetMasterTotal; // 接受者剩余血量
}
