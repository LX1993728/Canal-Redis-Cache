package com.zoo.ninestar.domains.vo.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 全局配置
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSConfigVO {
    private Integer total = 30000; // 双方初始总血量
    private Long maxPKSeconds = 60 * 60L; // 双方PK的最大时间
    private Long maxWaitAccept = 60L; // 对方等待的最大时间
}
