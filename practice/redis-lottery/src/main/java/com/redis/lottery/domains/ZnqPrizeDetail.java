package com.redis.lottery.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_znq_prize_detail")
public class ZnqPrizeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id: 详情表ID
    private Long id;

    //masterid：直播间粉丝的masterId
    private Long masterid;

    //roleid: 粉丝的角色id
    private Long roldid;

    // roomId：中奖所在的直播间的主播ID
    private Long targetid;

    //prizeid: 中奖的奖品id
    private Long prizeid;

    //time: 中奖时间
    private Date time;

    //date: 中奖日期(yyyy-MM-dd)
    private Integer date;

    //terminal: 设备类型 (0-PC端,1-App端)
    private Integer terminal;

    //nature：是否是自然人 (0-非自然人,1-是自然人)
    private Integer nature;

    //received: 是否已经领取 (0-已领取,1- 未领取)
    private Integer received;

    //code: 中奖码
    private String code;

    //stage: 活动的阶段类型 (1-基础阶段,2-进阶阶段,3-彩蛋阶段)
    private Integer type;

    //allow_edit: 是否允许编辑
    @Column(name = "allow_edit")
    private Integer allowEdit;
}
