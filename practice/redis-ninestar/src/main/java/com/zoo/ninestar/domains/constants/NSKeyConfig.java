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

    @Getter
    private static final String INIT_GLOBAL_CONFIG_KEY = NS_PRE + SPLIT_FLAG + "GLOBALCONFIG";

    private static final String PK_PRE = NS_PRE + SPLIT_FLAG + "PK";


    /**
     * 获取技能key
     * @param skillId
     * @return
     */
    public static String getSkillKey(Long skillId){
        assert skillId != null;
        return String.format("%s%s%s", SKILL_PRE, SPLIT_FLAG, skillId);
    }

    /**
     *
     * @return
     */
    public static String getBeRefSkillKey(){
        return String.format("%s%s%s", SKILL_PRE, SPLIT_FLAG, "BEREFRELATION");
    }

    /**
     * 获取指定PK赛的统一前缀
     * @param pkId
     * @return
     */
    public static String getPkPre(Long pkId){
        assert pkId != null;
        return String.format("%s%s%s", PK_PRE, SPLIT_FLAG, pkId);
    }

    /**
     * 获取指定PK赛中某个主播对应直播间的统一前缀
     * @param pkId
     * @param masterId
     * @return
     */
    public static String getPKMasterPre(Long pkId, Long masterId){
        assert masterId != null;
        final String pkPre = getPkPre(pkId);
        return String.format("%s%s%s%s%s", pkPre, SPLIT_FLAG,"Master", SPLIT_FLAG, masterId);
    }

    /**
     * Hash 结构 获取PK双方主播剩余总血量的key
     * @param pkId
     * @return
     */
    public static String getMastersTotalHashKey(Long pkId){
        final String pkPre = getPkPre(pkId);
        return String.format("%s%s%s", pkPre, SPLIT_FLAG, "TOTAL");
    }


    /**
     * 存储PK记录的Key
     * @param pkId
     * @return
     */
    public static String getPKRecordKey(Long pkId){
        assert pkId != null;
        final String pre = getPkPre(pkId);
        return String.format("%s%s%s", pre, SPLIT_FLAG,"RECORD");
    }

    // --------------------------------------【以下是对应于一场PK赛中某个主播对应直播间内相应的key】---------------------------------------------
    /**
     * 获取DPS(贡献排行榜) zset结构的key
     * @param pkId
     * @param masterId
     * @return
     */
    public static String getDPSZsetKey(Long pkId, Long masterId){
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s", pre, SPLIT_FLAG, "DPS");
    }

    /**
     * 获取detail战报的前缀
     * @param pkId
     * @param masterId
     * @return
     */
    public static String getPKDetailPre(Long pkId,Long masterId){
        final String pkPre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s", pkPre, SPLIT_FLAG, "DETAIL");
    }

    /**
     * 获取detail战报的key
     * @param pkId
     * @param masterId
     * @param detailId
     * @return
     */
    public static String getPKDetailKey(Long pkId,Long masterId, Long detailId){
        assert detailId != null;
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s", pre, SPLIT_FLAG, detailId);
    }

    /**
     * 获取技能血条相关的前缀
     * @param pkId
     * @param skillId
     * @return
     */
    public static String getSkillBuffPre(Long pkId,Long masterId, Long skillId){
        assert skillId != null;
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s%s%s", pre, SPLIT_FLAG, "BUFF", SPLIT_FLAG, skillId);
    }

    /**
     * 获取技能血条的key
     * @param pkId
     * @param skillId
     * @return
     */
    public static String generateSkillBuffKey(Long pkId, Long masterId, Long skillId){
        final String pre = getSkillBuffPre(pkId, masterId, skillId);
        return String.format("%s%s%s", pre, SPLIT_FLAG, System.currentTimeMillis());
    }

    /**
     * 获取一场PK中某个主播直播间内某个技能的触发使用次数相关的key
     * @param pkId
     * @param skillId
     * @return
     */
    public static String getSkillTimesKey(Long pkId, Long masterId, Long skillId){
        assert skillId != null;
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s%s%s", pre, SPLIT_FLAG, "SKILLTIMES", SPLIT_FLAG, skillId);
    }

    /**
     *
     * @param pkId
     * @param masterId
     * @param skillId
     * @return
     */
    public static String getSkillModTimesKey(Long pkId, Long masterId, Long skillId){
        assert skillId != null;
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s%s%s", pre, SPLIT_FLAG, "SKILLMODTIMES", SPLIT_FLAG, skillId);
    }

    /**
     *
     * @param pkId
     * @param masterId
     * @param skillId
     * @return
     */
    public static String getSkillIsActiveKey(Long pkId, Long masterId, Long skillId){
        assert skillId != null;
        final String pre = getPKMasterPre(pkId, masterId);
        return String.format("%s%s%s%s%s", pre, SPLIT_FLAG, "SKILLISACTIVE", SPLIT_FLAG, skillId);
    }

}
