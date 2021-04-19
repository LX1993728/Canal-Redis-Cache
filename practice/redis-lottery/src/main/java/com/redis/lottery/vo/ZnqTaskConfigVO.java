package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 周年庆活动的任务配置参数
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqTaskConfigVO {
    private Integer firstTotalCharms = 999; // 第一阶段最大的魅力值
    private Integer firstTotalShared = 14; // 第一阶段最大分享次数
    private Integer firstTotalBarrages = 14; // 第一阶段不同用户发送弹幕的最大总数量

    private Integer secondTotalCakes = 9; //第二阶段收到蛋糕的最大数量

    private Integer thirdInitCharms = 999;  // 第三阶段收到魅力值的初始值 999
    private Integer thirdTotalCharms = 9999; // 第三阶段收到魅力值的最大值9999
}
