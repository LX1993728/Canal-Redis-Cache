package com.zoo.ninestar.domains.constants;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class NSKeyConfig {
    // 所有九星神器 相关redis 所有key的前缀
    @Getter
    private static final String NS_PRE = "NINE_STAR";
    // 分隔符
    private static final String SPLIT_FLAG = "_";

    @Getter
    private static final String SKILL_PRE = NS_PRE + SPLIT_FLAG + "SKILL";

    /**
     * 获取技能key
     * @param skillId
     * @return
     */
    public static String getSkillKey(Long skillId){
        assert skillId != null;
        return String.format("%s%s%s", SKILL_PRE, SPLIT_FLAG, skillId);
    }
}
