package com.zoo.ninestar.domains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 九星神器 PK记录对应的详情表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_ns_pk_detail")
public class NSPKDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  id: 主键递增 `detailId`
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

    //    skill_id: 粉丝使用的技能Id
    @Column(name = "skill_id")
    private Long skillId;

    //    hurting: 粉丝使用技能为对方造成的伤害
    private Integer hurting;

    //    plussing: 粉丝使用技能为己方增加的生命值或者减少的伤害值
    private Integer plussing;

    //    release_time: 技能释放时间
    @CreatedDate
    @Column(name = "release_time")
    private Date releaseTime;

    //    end_time: 技能被终止的时间
    @Column(name = "end_time")
    private Date endTime;
}
