package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 抽奖结果
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqLotteryVO {
    private Long masterId; // 粉丝ID
    private Long targetId; // 中奖所在直播间对应的主播ID
    private Long prizeId;
    private String masterName; // 粉丝名称
    private String targetName; // 主播名称
    private String prizeName;  // 奖品名称
    private boolean isWin; // 是否中奖
}
