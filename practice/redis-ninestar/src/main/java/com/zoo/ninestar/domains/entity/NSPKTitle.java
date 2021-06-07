package com.zoo.ninestar.domains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 九星神器 PK记录对应的粉丝英雄称号
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_ns_pk_title")
public class NSPKTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  id: 主键递增 `titleId`
    private Long id;

    //    pk_id: 关联PK赛的ID
    @Column(name = "pk_id")
    private Long pkId;

    //    master_id: 粉丝所在直播间对应主播的masterId
    @Column(name = "master_id")
    private Long masterId;

    //    fan_master_id: 粉丝的masterId
    @Column(name = "fan_master_id")
    private Long fanMasterId;

    //    fan_role_id: 粉丝用户当前登录的角色ID
    @Column(name = "fan_role_id")
    private Long fanRoleId;

    // 粉丝用户当前登录用户的昵称
    @Column(name = "fan_nickname")
    private String fanNickName;

    // 称号名称
    private String title;

    // 1- 最强DPS，2-一击必杀，3-医学圣手，4，-先发制人
    private Integer type;

    // dps
    @Column(name = "dps_total")
    private Integer dpsTotal;

    // 蓄力技释放次数
    @Column(name = "b_skill_times")
    private Integer bSkillTimes;

    // 回血
    @Column(name = "add_buff")
    private Integer addBuff;

}
