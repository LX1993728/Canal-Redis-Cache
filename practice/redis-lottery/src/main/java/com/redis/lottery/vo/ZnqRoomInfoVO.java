package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqRoomInfoVO {
    private Long masterId; // 主播Id
    private Integer firstCharms; // 第一阶段的魅力值
    private Integer firstShared; // 已经分享的次数
    private Set<Integer> firstBarrageMIds; // 不同用户发送弹幕的数量

    private Integer secondCakes; //已收到的蛋糕数量

    private Integer thirdCharms = 999; // 第三阶段的魅力值 初始值999

    @Data@ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{
        private int drawn; // 已经开奖次数
        private int type; // 当前直播间已经进入的阶段
        private boolean isCompleted; // 该直播将当前的任务是否完成
    }

    // 获取分析结果
    public Info acquireInfoResult(ZnqTaskConfigVO configVO){
        if (firstBarrageMIds == null){
            firstBarrageMIds = new HashSet<Integer>();
        }
        final Integer ftCharms = configVO.getFirstTotalCharms();
        final Integer ftShared = configVO.getFirstTotalShared();
        final Integer ftBarrs = configVO.getFirstTotalBarrages();
        final Integer stCakes = configVO.getSecondTotalCakes();
        final Integer ttCharms = configVO.getThirdTotalCharms();

        int firstCharmCount = Math.min(getFirstCharms(), ftCharms);
        int firstSharedCount = Math.min(getFirstShared(), ftShared);
        int firstBarrageCount = Math.min(firstBarrageMIds.size(), ftBarrs);
        int secondCakeCount = Math.min(secondCakes, stCakes);
        int thirdCharmCount = Math.min(thirdCharms, ttCharms);

        Info info = new Info(0, 1, false);
        // 第一阶段 未完成 不可抽奖
        if (firstCharmCount < ftCharms || firstSharedCount < ftShared || firstBarrageCount < ftBarrs){
            return info;
        }
        // 第一阶段已完成 自动开启第二阶段 开奖次数为 1
        if (secondCakeCount < stCakes){
            info.setType(2);
            info.setDrawn(1);
            return info;
        }
        // 第二阶段已完成 自动开启第三阶段 开奖次数为 2
        if (thirdCharmCount < ttCharms){
            info.setType(3);
            info.setDrawn(2);
            return info;
        }
        // 第三阶段已完成 开奖次数为3 结束
        info.setType(3);
        info.setDrawn(3);
        info.setCompleted(true);
        return info;
    }

}
