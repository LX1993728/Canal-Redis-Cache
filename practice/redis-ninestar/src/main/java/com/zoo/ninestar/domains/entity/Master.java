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
@Table(name = "t_master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  id: 主键递增 `PKId`
    private Long masterId;

    @Column(name = "nick_name")
    private String nickName;
}
