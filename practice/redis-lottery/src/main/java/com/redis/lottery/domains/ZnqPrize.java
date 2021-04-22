package com.redis.lottery.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_znq_prize")
public class ZnqPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id: 奖品ID
    private Long id;

    //name: 奖品名称
    private String name;

    // description: 奖品描述-(特/一二三...、/等奖)
    private String description;

    //image: 奖品图片地址
    private String image;

    //broad: 是否开启广播 默认0
    private Integer broad;

    //total: **每天**发放奖品的总数量
    private Integer total;

    //probability: 中奖概率 百分比
    @Column(length = 10, precision = 2)
    private Double probability;

    @Column(name = "star_diamonds")
    // 星钻数量(谢谢参与:-2 IPAD: -1,牡丹特效:0, 星钻:大于0 )
    private Integer starDiamonds;

    //types：奖品类型数组 1,2,3 最多3项 (1-基础作战,2-进阶作战,3-彩蛋)
    private String types;
}
