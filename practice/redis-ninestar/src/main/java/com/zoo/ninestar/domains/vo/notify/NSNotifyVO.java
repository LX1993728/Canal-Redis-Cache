package com.zoo.ninestar.domains.vo.notify;

import lombok.*;

/**
 * 包装统一 关于九星神器的通知
 * 暂定协议号只使用一个，使用notifyType区分通知内容
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSNotifyVO<T> {
    // 协议号 通知的协议行为
    private Integer action;
    // 关于通知的类型
    private NSNotifyType notifyType;
    // 关于此通知的描述信息
    private String description;
    //关于此通知的内容对象 T: 对象类型
    private T obj;

    {
        if (notifyType != null){
            description = notifyType.getDescription();
        }
    }
}
