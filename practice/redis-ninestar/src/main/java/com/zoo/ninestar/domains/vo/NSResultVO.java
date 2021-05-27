package com.zoo.ninestar.domains.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * service 执行是否成功 以及原因
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NSResultVO<T> {
    private boolean isSuccess;
    private String description;
    private T obj;
}
