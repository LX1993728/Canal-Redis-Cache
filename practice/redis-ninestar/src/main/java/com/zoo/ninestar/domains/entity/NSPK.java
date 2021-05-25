package com.zoo.ninestar.domains.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 九星神器 PK赛记录表
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_ns_pk")
public class NSPK {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  id: 主键递增 `PKId`
    private Long id;

    //  master_id: PK发起者masterId
    @Column(name = "master_id")
    private Long masterId;

    //  target_master_id: PK接受方的masterId
    @Column(name = "target_master_id")
    private Long targetMasterId;

    //  start_time: PK 发起时间(时间戳)
    @Column(name = "start_time")
    private Date startTime;

    //  end_time: PK结束时间(时间戳)
    @Column(name = "end_time")
    private Date endTime;

    //  total_buff: 双方的初始总血量
    @Column(name = "total_buff")
    private Integer totalBuff;

    //  winner_master_id: PK获胜方的主播masterId (`为-1表示平局`)
    @Column(name = "winner_master_id")
    private Long winnerMasterId;

    //  status: PK赛的状态(0- 发起PK邀请 1- 接受PK邀请进行PK中 2- 拒绝PK 3- PK已结束)
    private Integer status;


}
