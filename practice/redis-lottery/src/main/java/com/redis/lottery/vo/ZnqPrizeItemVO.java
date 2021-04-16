package com.redis.lottery.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZnqPrizeItemVO {
    private Long prizeId;
    private int begin;
    private int end;

    public boolean drawn(int random){
        return random >= begin && random <= end;
    }
}
