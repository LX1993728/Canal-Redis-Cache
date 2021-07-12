package com.zoo.ninestar.domains.vo.notify;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public enum NSNotifyType {
    /**
     * -------------- 所有的通知类型在此处定义，code值依次递增 ------------------------------
     */
    SKILL_ACTIVE(1, "技能激活通知"),
    BUFF_CHANGE(2, "血量变化通知"),
    END_PK(3, "结束PK的通知"),
    BATTLE_REPORT(4, "战报信息通知"),
    DPS(5, "DPS排行榜通知"),
    HERO_TITLE(6, "英雄称号相关的通知"),
    SKILLS_STATUSES(7, "技能最新状态的通知");
    // TODO: 在此处以下依次增加通知类型
    ;

    @Setter @Getter
    private int code;
    @Setter @Getter
    private String description;

    private NSNotifyType(int code, String description) {
        this.description = description;
        this.code = code;
    }

    /**
     * 根据code获取通知类型的名称
     * @param code
     * @return
     */
    public static String getDescription(int code){
        for (NSNotifyType notifyType : NSNotifyType.values()){
            if (notifyType.getCode() == code){
                return notifyType.getDescription();
            }
        }

        return null;
    }

    /**
     * 根据code获取通知类型
     * @param code
     * @return
     */
    public static NSNotifyType getNotifyType(int code){
        for (NSNotifyType notifyType : NSNotifyType.values()){
            if (notifyType.getCode() == code){
                return notifyType;
            }
        }

        return null;
    }

}
