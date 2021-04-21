package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqPrizeVO {
    private Long id;
    private String name;
    // 百分比
    private Double probability;
    private Integer total;
    // 已经发放的数量
    private Integer issued;
    private Integer broad;
    private Integer type;
}
