package com.zoo.ninestar.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Item implements Serializable {

    private static final long serialVersionUID = -3286467135089759767L;

    public abstract Long getItemId();

    public abstract String getItemName();

    public abstract Integer getItemPrice();

    public abstract Integer getItemType();

    public abstract boolean isUpgrade();
    
    public abstract Integer getPoint();//积分    
    
    public abstract Integer getCusMoney();//观众财富值

    @Getter
    @Setter
    private Long deadLine = null;

    @Getter
    @Setter
    private Boolean canUse = false;

}
