package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqRoomInfoVO {
    private Long masterId; // 主播Id
    private String masterName; // 主播名称

    private Integer firstCharms; // 第一阶段的魅力值
    private Integer firstShared; // 已经分享的次数
    private Set<Integer> firstBarrageMIds; // 不同用户发送弹幕的数量

    private Integer secondCakes; //已收到的蛋糕数量

    private Integer thirdCharms = 999; // 第三阶段的魅力值 初始值999

    private Integer drawn =0; // 已经抽奖次数

    // 获取响应结果
    public Map<String, Object> getResult(){
        if (firstBarrageMIds == null){
            firstBarrageMIds =new HashSet<>();
        }
        Map<String, Object> resultMap = new HashMap<>();
        int firstCharmCount = Math.min(getFirstCharms(), 999);
        int firstSharedCount = Math.min(getFirstShared(), 14);
        int firstBarrageCount = Math.min(firstBarrageMIds.size(), 14);
        int secondCakeCount = Math.min(secondCakes, 9);
        int thirdCharmCount = Math.min(thirdCharms, 9999);
        // 第一阶段 未完成 不可抽奖
        if (firstCharmCount < 999 || firstSharedCount < 14 || firstBarrageCount < 14){
            resultMap.put("type", 1); // 任务阶段
            resultMap.put("isCompleted", false); // 该直播间任务是否完成
            resultMap.put("canDrawn", false); // 当前是否可以开启抽奖任务
            resultMap.put("charms", firstCharmCount);  // 已收获魅力值
            resultMap.put("shared", firstSharedCount); // 已被分享的次数
            resultMap.put("barrages", firstBarrageCount); // 已发送弹幕
            return resultMap;
        }
        // 第一阶段 已完成 可抽奖
        if (drawn == 0){
            resultMap.put("type", 1); // 任务阶段
            resultMap.put("isCompleted", false); // 该直播间任务是否完成
            resultMap.put("canDrawn", true); // 当前是否可以开启抽奖任务
            resultMap.put("charms", 999);  // 已收获魅力值
            resultMap.put("shared", 14); // 已被分享的次数
            resultMap.put("barrages", 14); // 已发送弹幕
            return resultMap;
        }
        // 第一阶段已完成抽奖 进入第二阶段
        if (drawn == 1 && secondCakeCount < 9){
            resultMap.put("type", 2);
            resultMap.put("isCompleted", false);
            resultMap.put("canDrawn", false);
            resultMap.put("cakes", secondCakeCount);
            return resultMap;
        }
        // 第二阶段已完成 等待抽奖
        if (secondCakeCount == 9 && drawn == 1){
            resultMap.put("type", 2);
            resultMap.put("isCompleted", false);
            resultMap.put("canDrawn", true);
            resultMap.put("cakes", 9);
            return resultMap;
        }
        // 第二阶段抽奖完毕 进入第三阶段
        if (drawn ==2 && thirdCharmCount < 9999){
            resultMap.put("type", 3);
            resultMap.put("isCompleted", false);
            resultMap.put("canDrawn", false);
            resultMap.put("cakes", 9);
            return resultMap;
        }
        // 第二阶段任务完成， 等待抽奖
        if (thirdCharmCount == 9999 && drawn == 2){
            resultMap.put("type", 3);
            resultMap.put("isCompleted", false);
            resultMap.put("canDrawn", true);
            resultMap.put("charms", 9999);
            return resultMap;
        }else if (drawn == 3){ // 第三阶段抽奖完毕 结束
            resultMap.put("type", 3);
            resultMap.put("isCompleted", true);
            resultMap.put("canDrawn", false);
            resultMap.put("charms", 9999);
            return resultMap;
        }
        log.error("exists  current Exception");
        return null;
    }

    public int getType(){
        final Map<String, Object> result = getResult();
        Assert.notNull(result, "result cannot be null !!!!");
        return (int) result.get("type");
    }

    public boolean isCompleted(){
        return drawn == 3;
    }

}
