package com.zoo.ninestar.domains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 九星神器 PK技能表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_ns_pk_skill")
public class NSPKSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id: 主键递增 `skillId`
    private Long id;

    //  name: 技能名称
    private String name = "未知技能";

    //  icon：技能对应的图片地址
    private String icon;

    //  for_master: 是否是主播端技能(1-是主播侧技能 0-用户侧技能)
    @Column(name = "for_master")
    private Integer forMaster = 0;

    //  ingots: 使用改技能需要扣除的元宝值
    private Long ingots;

    //  point_num: 增加心意币数量`送礼的粉丝用户`
    @Column(name = "point_num")
    private Long pointNum;

    //  feel_num: 增加的好感值`送礼的粉丝用户`
    @Column(name = "feel_num")
    private Long feelNum;

    //  wealth_num: 增加的财富值`送礼的粉丝用户`
    @Column(name = "wealth_num")
    private Long wealthNum;

    //  master_score: 增加的主播经验值`当前的主播`
    @Column(name = "master_score")
    private Long masterScore;

    //  delay_minute: 在PK开始后至少多长时间(分钟)后才可释放此技能
    @Column(name = "delay_minute")
    private Integer delayMinute;

    //  max_times: 在一场PK中可以使用的最大次数
    @Column(name = "max_times")
    private Integer maxTimes;

    //  description: 技能描述
    private String description;

    //  hurting: PK中减少对方的生命值
    private Integer hurting;

    //  plussing: PK中恢复己方的生命值
    private Integer plussing;

    //  buff_time: 血条延时伤害时间(单位:秒)
    @Column(name = "buff_delay_second")
    private Integer buffDelaySecond;

    //  buff_max_count: 血条的最多条数
    @Column(name = "buff_max_count")
    private Integer buffMaxCount;

    // buff_hurting_second 一个buff每秒的基础伤害
    @Column(name = "buff_hurting_second")
    private Integer buffHurtingSecond;

    //  buff_addition: 重叠后每条buff除基础伤害外增加的附加伤害。
    @Column(name = "buff_addition_sencond")
    private Integer buffAdditionSecond;

    //  clear_self_buff: 表示清除己方血条(1-表示清除己方血条 0/NULL-不具备该项功能)
    @Column(name = "clear_self_buff")
    private Integer clearSelfBuff;

    //  ref_skill_id: 此技能的关联技能ID (表示该技能是辅助节能(大招))
    @Column(name = "ref_skill_id")
    private Long refSkillId;

    //  ref_skill_count: 表示关联技能的释放次数阈值(方可激活此技能)
    @Column(name = "ref_skill_count")
    private Integer refSkillCount;

    // allow_batter: 表示是否允许连击 1 表示允许连击 0或null表示不允许连击
    @Column(name = "allow_batter")
    private Integer allowBatter;

    // svga: 特效连接
    @Column(name = "svga")
    private String svga;

    // 剩余可执行次数 为null或-1表示没有次数限制
    @Transient
    private Integer remainTimes;

    // 有效累计次数
    @Transient
    private Integer modTimes;

    // 表示是否为激活状态
    @Transient
    private Boolean isActive;
}
