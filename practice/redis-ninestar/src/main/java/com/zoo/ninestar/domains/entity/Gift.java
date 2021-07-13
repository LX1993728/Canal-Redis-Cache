package com.zoo.ninestar.domains.entity;

import com.zoo.ninestar.domains.constants.Constants.ITEM_TYPE;
import com.zoo.ninestar.domains.constants.Constants.PROP_STATUS;
import com.zoo.ninestar.domains.constants.Constants.RANK_STATUS;
import com.zoo.ninestar.domains.Item;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * 礼物字典表
 * 
 * @author yongxueli (yongxueli@sohu-inc.com)
 */
@Entity
@Table(name = "t_gift")
public class Gift extends Item implements java.io.Serializable {

	/**
	 * 礼物类型——普通礼物
	 */
	public static final Long GIFT_TYPE_NORMAL = 0L;

	/**
	 * 礼物类型——高级礼物
	 */
	public static final Long GIFT_TYPE_SENIOR = 1702L;

	/**
	 * 礼物特效——普通礼物
	 */
	public static final Integer GIFT_EFFECT_NORMAL = 0;

	/**
	 * 礼物特效——高级礼物
	 */
	public static final Integer GIFT_EFFECT_SENIOR = 1500;

	// Fields

	private static final long serialVersionUID = 8656973582082762051L;
	private Long giftId;
	private String giftName;
	private Integer price;
	private Integer levelReq;
	private String smallPic;
	private String bigPic = "";
	private String bigPic1 = "";
	private Long giftType;
	private Integer showType;
	private PROP_STATUS status;
	private String param;
	private Integer group;
	private String identity;
	private String giftTypeName;
	private RANK_STATUS rankStatus;
	private Integer effect;
	private Integer maxMultiple;
	private String clientPic = ""; // 客户端所用礼物图片

	/* 移动端礼物需求 - field - wujianing */
	private String mGiftIcon = "";
	// 移动需求 - 赵天武
	// 0 普通礼物无效果的
	// 1 gif效果的
	// 2 gif效果+黑板效果的
	private Integer mGiftType = 0;
	private String mGifIos = "";
	private String mGifAndroid = "";
	/* 移动端礼物需求 - field - wujianing */

	// 动态礼物效果
	private String giftDyEffect;

	// 是否组图效果
	private Integer coeffectPower;

	private String desc;
	private Integer sort;

	private Integer point;// 积分

	// 礼物经验
	private Integer experience;

	private Integer cusMoney;// 观众财富值
	private Integer cusFavor;// 观众好感值

	private Integer webVersion;// pc版本号
	private Integer mobileVersion;// 手机版本号

	private Integer displayType;// 0-全平台/1-手机端/2-web端
	// 手机端礼物字段
	private String mobilePng;// 手机端礼物小图
	private String mobileZip;// 手机端礼物效果
	private String mobileSvga;//svga格式素材
	private Integer moneyType;// 礼物货币类型(0-元宝/1-星钻)

	private Integer starPrice;// 星钻价格
	
	private Integer pkValue; // pk 值
	private String pkDescription; // pk 描述
    //pc svga素材  add by wgh
	private String pcSvga;
	
	
	//是否是周星礼物  1是 xzz 2018/12/19
	private Integer weekStar = 0;
	
	//周星礼物描述  xzz 2018/12/19
	private String wsDesc;
	
	//心跳礼物-边框
	private String heartGiftBorder;
	//心跳礼物-文案
	private String heartGiftDocument;
	//心跳礼物-默认主播头像
	private String heartGiftAnchorIcon;
	//心跳礼物-默认玩家头像
	private String heartGiftUserIcon;
	//心跳礼物-默认直播图
	private String heartGiftAnchorLiveImg;
	private String logo;
	//心跳礼物-APP大边框
	private String appbigHeartGiftBorder;
	//心跳礼物-APP小边框
	private String appsmallHeartGiftBorder;
	//心跳-样式
	private String heartgiftstyle;
	//心跳-新版直播间svga
	private String newlivesvga;

	private String appGiftShelfType;//手机端礼物货架类型,可能存在多种类型,类型之间以逗号分隔  (1首屏货架 2心跳回忆 3周星 4个性)


	private Integer showCombo;//是否展示连击标识(0:否;1：是)


	private Integer minWealthLevel;//所需最低财富等级

	@Column(name="skill_id")
	private Long skillId; // 技能id

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}


	private NSPKSkill skill;

	@Transient
	public NSPKSkill getSkill() {
		return skill;
	}

	public void setSkill(NSPKSkill skill) {
		this.skill = skill;
	}

	@Column(name="Fminwealthlevel")
	public Integer getMinWealthLevel() {
		return minWealthLevel;
	}

	public void setMinWealthLevel(Integer minWealthLevel) {
		this.minWealthLevel = minWealthLevel;
	}

	@Column(name = "appgiftshelftype")
	public String getAppGiftShelfType() {
		return appGiftShelfType;
	}

	public void setAppGiftShelfType(String appGiftShelfType) {
		this.appGiftShelfType = appGiftShelfType;
	}

	@Column(name = "Fshowcombo")
	public Integer getShowCombo() {
		return showCombo;
	}

	public void setShowCombo(Integer showCombo) {
		this.showCombo = showCombo;
	}

	@Transient
	public String getWsDesc() {
		return wsDesc;
	}

	public void setWsDesc(String wsDesc) {
		this.wsDesc = wsDesc;
	}

	@Transient
	public Integer getWeekStar() {
		return weekStar;
	}

	public void setWeekStar(Integer weekStar) {
		this.weekStar = weekStar;
	}

	
	@Column(name = "FdisplayType")
	public Integer getDisplayType() {
		return displayType;
	}

	public void setDisplayType(Integer displayType) {
		this.displayType = displayType;
	}

	@Column(name = "Fdesc")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "Fcoeffectpower")
	public Integer getCoeffectPower() {
		return coeffectPower;
	}

	public void setCoeffectPower(Integer coeffectPower) {
		this.coeffectPower = coeffectPower;
	}

	@Column(name = "Fdy_effect")
	public String getGiftDyEffect() {
		return giftDyEffect;
	}

	public void setGiftDyEffect(String giftDyEffect) {
		this.giftDyEffect = giftDyEffect;
	}

	// Constructors
	@Column(name = "Feffect")
	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	@Column(name = "Fstatus")
	public PROP_STATUS getStatus() {
		return status;
	}

	public void setStatus(PROP_STATUS status) {
		this.status = status;
	}

	/** default constructor */
	public Gift() {
		this.param = "";
		this.group = 1;
	}

	/** minimal constructor */
	public Gift(Long giftId) {
		this.giftId = giftId;
	}

	/** full constructor */
	public Gift(Long giftId, String giftName, Integer price, Integer levelReq,
                String smallPic, String bigPic, Long giftType, String param,
                Integer group, String identity, Integer effect) {
		this.giftId = giftId;
		this.giftName = giftName;
		this.price = price;
		this.levelReq = levelReq;
		this.smallPic = smallPic;
		this.bigPic = bigPic;
		this.giftType = giftType;
		this.param = param;
		this.group = group;
		this.identity = identity;
		this.effect = effect;
	}

	// Property accessors
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Fgiftid")
	public Long getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	@Column(name = "Fgiftname")
	public String getGiftName() {
		return this.giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "Fprice")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "Flevelreq")
	public Integer getLevelReq() {
		return this.levelReq;
	}

	public void setLevelReq(Integer levelReq) {
		this.levelReq = levelReq;
	}

	@Column(name = "Fsmallpic")
	public String getSmallPic() {
		return this.smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	@Column(name = "Fbigpic")
	public String getBigPic() {
		return this.bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	@Column(name = "Fbigpic1")
	public String getBigPic1() {
		return this.bigPic1;
	}

	public void setBigPic1(String bigPic1) {
		this.bigPic1 = bigPic1;
	}

	@Column(name = "Fgifttype")
	public Long getGiftType() {
		return this.giftType;
	}

	public void setGiftType(Long giftType) {
		this.giftType = giftType;
	}

	@Column(name = "Fshowtype")
	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	@Column(name = "Fparam")
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Column(name = "Fgroup")
	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	@Transient
	public String getGiftTypeName() {
		return giftTypeName;
	}

	public void setGiftTypeName(String giftTypeName) {
		this.giftTypeName = giftTypeName;
	}

	@Override
	public boolean equals(Object obj) {
		Gift gift = (Gift) obj;
		return this.getGiftId().longValue() == gift.getGiftId().longValue();
	}

	@Override
	public int hashCode() {

		return this.getGiftId().intValue();
	}

	@Override
	@Transient
	public Long getItemId() {
		return this.giftId;
	}

	@Override
	@Transient
	public String getItemName() {
		return this.giftName;
	}

	@Override
	@Transient
	public Integer getItemPrice() {
		return this.price;
	}

	@Transient
	@Override
	public Integer getItemType() {
		if (this.price == 0) {
			return ITEM_TYPE.FREEGIFT.ordinal();
		} else {
			return ITEM_TYPE.GIFT.ordinal();
		}
	}

	@Override
	@Transient
	public boolean isUpgrade() {
		return true;
	}

	@Column(name = "Frankstatus")
	public RANK_STATUS getRankStatus() {
		return rankStatus;
	}

	public void setRankStatus(RANK_STATUS rankStatus) {
		this.rankStatus = rankStatus;
	}

	@Column(name = "Fidentity")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/* 客户端礼物图片 */
	@Column(name = "FclientPic")
	public String getClientPic() {
		return clientPic;
	}

	public void setClientPic(String clientPic) {
		this.clientPic = clientPic;
	}

	/* 移动端礼物需求 - getset - wujianing */

	@Column(name = "Fmgifticon")
	public String getmGiftIcon() {
		return mGiftIcon;
	}

	@Column(name = "Fmgifttype")
	public Integer getmGiftType() {
		return mGiftType;
	}

	@Column(name = "Fmgifios")
	public String getmGifIos() {
		return mGifIos;
	}

	@Column(name = "Fmgifandroid")
	public String getmGifAndroid() {
		return mGifAndroid;
	}

	public void setmGiftIcon(String mGiftIcon) {
		this.mGiftIcon = mGiftIcon;
	}

	public void setmGiftType(Integer mGiftType) {
		this.mGiftType = mGiftType;
	}

	public void setmGifIos(String mGifIos) {
		this.mGifIos = mGifIos;
	}

	public void setmGifAndroid(String mGifAndroid) {
		this.mGifAndroid = mGifAndroid;
	}

	@Transient
	public Integer getMaxMultiple() {
		return maxMultiple;
	}

	public void setMaxMultiple(Integer maxMultiple) {
		this.maxMultiple = maxMultiple;
	}

	@Column(name = "Fsort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "Fpoint")
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	@Column(name = "Fexperience")
	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Column(name = "Fcusmoney")
	public Integer getCusMoney() {
		return cusMoney;
	}

	public void setCusMoney(Integer cusMoney) {
		this.cusMoney = cusMoney;
	}

	@Column(name = "Fcusfavor")
	public Integer getCusFavor() {
		return cusFavor;
	}

	public void setCusFavor(Integer cusFavor) {
		this.cusFavor = cusFavor;
	}

	@Column(name = "Fwebversion")
	public Integer getWebVersion() {
		return webVersion;
	}

	public void setWebVersion(Integer webVersion) {
		this.webVersion = webVersion;
	}

	@Column(name = "Fmoblieversion")
	public Integer getMobileVersion() {
		return mobileVersion;
	}

	public void setMobileVersion(Integer mobileVersion) {
		this.mobileVersion = mobileVersion;
	}

	@Column(name = "Fmobliesvga")	
	public String getMobileSvga() {
		return mobileSvga;
	}

	public void setMobileSvga(String mobileSvga) {
		this.mobileSvga = mobileSvga;
	}

	@Column(name = "Fmobliepng")
	public String getMobilePng() {
		return mobilePng;
	}

	public void setMobilePng(String mobilePng) {
		this.mobilePng = mobilePng;
	}

	@Column(name = "Fmobliezip")
	public String getMobileZip() {
		return mobileZip;
	}

	public void setMobileZip(String mobileZip) {
		this.mobileZip = mobileZip;
	}

	@Column(name = "Fmoneytype")
	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}

	@Column(name = "Fstarprice")
	public Integer getStarPrice() {
		return starPrice;
	}
	public void setStarPrice(Integer starPrice) {
		this.starPrice = starPrice;
	}
	@Column(name = "Fpkvalue")
	public Integer getPkValue() {
		return pkValue;
	}

	public void setPkValue(Integer pkValue) {
		this.pkValue = pkValue;
	}
	@Column(name = "Fpkdescription")
	public String getPkDescription() {
		return pkDescription;
	}

	public void setPkDescription(String pkDescription) {
		this.pkDescription = pkDescription;
	}
	@Column(name = "Fpcsvga")
	public String getPcSvga() {
		return pcSvga;
	}

	public void setPcSvga(String pcSvga) {
		this.pcSvga = pcSvga;
	}

	// PC端的版本号是否改变
	public boolean isChangeWeb(Gift giftNew) {
		// 判断名字是否改变
		if ((StringUtils.isNotBlank(this.getGiftName()) && !this.getGiftName()
				.equals(giftNew.getGiftName()))
				|| (StringUtils.isNotBlank(giftNew.getGiftName()) && !giftNew
						.getGiftName().equals(this.getGiftName()))) {
			return true;
		}
		// 判断价格是否改变
		if (this.getPrice().intValue() != giftNew.getPrice().intValue()) {
			return true;
		}
		// 判断积分是否改变
		if (this.getPoint().intValue() != giftNew.getPoint().intValue()) {
			return true;
		}
		// 判断经验是否改变
		if (this.getExperience().intValue() != giftNew.getExperience()
				.intValue()) {
			return true;
		}
		// 判断财富值是否改变
		if (this.getCusMoney().intValue() != giftNew.getCusMoney().intValue()) {
			return true;
		}
		// 判断好感值是否改变
		if (this.getCusFavor().intValue() != giftNew.getCusFavor().intValue()) {
			return true;
		}
		// 判断pk 值是否改变
		if (this.getPkValue().intValue() != giftNew.getPkValue().intValue()) {
			return true;
		}
		// 判断pk 描述是否改变
		if ((StringUtils.isNotBlank(this.getPkDescription()) && !this.getPkDescription().equals(
				giftNew.getPkDescription()))
				|| (StringUtils.isNotBlank(giftNew.getPkDescription()) && !giftNew
						.getPkDescription().equals(this.getPkDescription()))) {
			return true;
		}
		// 判断排序是否改变
		if (giftNew.getSort() != null
				&& this.getSort().intValue() != giftNew.getSort().intValue()) {
			return true;
		}
		// 判断类别是否改变
		if (this.getGiftType().intValue() != giftNew.getGiftType().intValue()) {
			return true;
		}
		// 判断组图效果是否改变
		if (giftNew.getCoeffectPower() != null
				&& this.getCoeffectPower().intValue() != giftNew.getCoeffectPower()
				.intValue()) {
			return true;
		}
		// 判断显示类型是否改变
		if (this.getDisplayType().intValue() != giftNew.getDisplayType()
				.intValue()) {
			return true;
		}
		// 判断礼物货币类型是否改变
		if (this.getMoneyType().intValue() != giftNew.getMoneyType().intValue()) {
			return true;
		}
		// 判断星钻价格是否改变
		if (this.getStarPrice().intValue() != giftNew.getStarPrice().intValue()) {
			return true;
		}
		// 判断描述是否改变
		if ((StringUtils.isNotBlank(this.getDesc()) && !this.getDesc().equals(
				giftNew.getDesc()))
				|| (StringUtils.isNotBlank(giftNew.getDesc()) && !giftNew
						.getDesc().equals(this.getDesc()))) {
			return true;
		}
		// 判断小图是否改变
		if ((StringUtils.isNotBlank(this.getSmallPic()) && !this.getSmallPic()
				.equals(giftNew.getSmallPic()))
				|| (StringUtils.isNotBlank(giftNew.getSmallPic()) && !giftNew
						.getSmallPic().equals(this.getSmallPic()))) {
			return true;
		}
		// 判断大图是否改变
		if ((StringUtils.isNotBlank(this.getBigPic()) && !this.getBigPic()
				.equals(giftNew.getBigPic()))
				|| (StringUtils.isNotBlank(giftNew.getBigPic()) && !giftNew
						.getBigPic().equals(this.getBigPic()))) {
			return true;
		}
		// 判断动态图是否改变
		if ((StringUtils.isNotBlank(this.getGiftDyEffect()) && !this
				.getGiftDyEffect().equals(giftNew.getGiftDyEffect()))
				|| (StringUtils.isNotBlank(giftNew.getGiftDyEffect()) && !giftNew
						.getGiftDyEffect().equals(this.getGiftDyEffect()))) {
			return true;
		}
		//判断pc svga素材是否改变  add by wgh
		if ((StringUtils.isNotBlank(this.getPcSvga()) && !this
				.getPcSvga().equals(giftNew.getPcSvga()))
				|| (StringUtils.isNotBlank(giftNew.getPcSvga()) && !giftNew
						.getPcSvga().equals(this.getPcSvga()))) {
			return true;
		}
		return false;
	}

	// 手机端的版本号是否改变
	public boolean isChangeMobile(Gift giftNew) {
		// 判断名字是否改变
		if ((StringUtils.isNotBlank(this.getGiftName()) && !this.getGiftName()
				.equals(giftNew.getGiftName()))
				|| (StringUtils.isNotBlank(giftNew.getGiftName()) && !giftNew
						.getGiftName().equals(this.getGiftName()))) {
			return true;
		}
		// 判断价格是否改变
		if (this.getPrice().intValue() != giftNew.getPrice().intValue()) {
			return true;
		}
		// 判断积分是否改变
		if (this.getPoint().intValue() != giftNew.getPoint().intValue()) {
			return true;
		}
		// 判断经验是否改变
		if (this.getExperience().intValue() != giftNew.getExperience()
				.intValue()) {
			return true;
		}
		// 判断财富值是否改变
		if (this.getCusMoney().intValue() != giftNew.getCusMoney().intValue()) {
			return true;
		}
		// 判断好感值是否改变
		if (this.getCusFavor().intValue() != giftNew.getCusFavor().intValue()) {
			return true;
		}
		// 判断pk 值是否改变
		if (this.getPkValue().intValue() != giftNew.getPkValue().intValue()) {
			return true;
		}
		// 判断pk 描述是否改变
		if ((StringUtils.isNotBlank(this.getPkDescription()) && !this.getPkDescription().equals(
				giftNew.getPkDescription()))
				|| (StringUtils.isNotBlank(giftNew.getPkDescription()) && !giftNew
						.getPkDescription().equals(this.getPkDescription()))) {
			return true;
		}
		// 判断排序是否改变
		if (giftNew.getSort() != null
				&& this.getSort().intValue() != giftNew.getSort().intValue()) {
			return true;
		}
		// 判断类别是否改变
		if (this.getGiftType().intValue() != giftNew.getGiftType().intValue()) {
			return true;
		}
		// 判断组图效果是否改变
		if (giftNew.getCoeffectPower() != null
				&& this.getCoeffectPower().intValue() != giftNew.getCoeffectPower()
				.intValue()) {
			return true;
		}
		// 判断显示类型是否改变
		if (this.getDisplayType().intValue() != giftNew.getDisplayType()
				.intValue()) {
			return true;
		}
		// 判断礼物货币类型是否改变
		if (this.getMoneyType().intValue() != giftNew.getMoneyType().intValue()) {
			return true;
		}
		// 判断星钻价格是否改变
		if (this.getStarPrice().intValue() != giftNew.getStarPrice().intValue()) {
			return true;
		}
		// 判断描述是否改变
		if ((StringUtils.isNotBlank(this.getDesc()) && !this.getDesc().equals(
				giftNew.getDesc()))
				|| (StringUtils.isNotBlank(giftNew.getDesc()) && !giftNew
						.getDesc().equals(this.getDesc()))) {
			return true;
		}
		// 判断小图是否改变
		if ((StringUtils.isNotBlank(this.getMobilePng()) && !this
				.getMobilePng().equals(giftNew.getMobilePng()))
				|| (StringUtils.isNotBlank(giftNew.getMobilePng()) && !giftNew
						.getMobilePng().equals(this.getMobilePng()))) {
			return true;
		}
		// 判断动态图是否改变
		if ((StringUtils.isNotBlank(this.getMobileZip()) && !this
				.getMobileZip().equals(giftNew.getMobileZip()))
				|| (StringUtils.isNotBlank(giftNew.getMobileZip()) && !giftNew
						.getMobileZip().equals(this.getMobileZip()))) {
			return true;
		}
		if ((StringUtils.isNotBlank(this.getMobileSvga()) && !this
				.getMobileSvga().equals(giftNew.getMobileSvga()))
				|| (StringUtils.isNotBlank(giftNew.getMobileSvga()) && !giftNew
						.getMobileSvga().equals(this.getMobileSvga()))) {
			return true;
		}
		
		return false;
	}

	public String getHeartGiftBorder() {
		return heartGiftBorder;
	}

	public String getHeartGiftDocument() {
		return heartGiftDocument;
	}

	public String getHeartGiftAnchorIcon() {
		return heartGiftAnchorIcon;
	}

	public String getHeartGiftUserIcon() {
		return heartGiftUserIcon;
	}

	public void setHeartGiftBorder(String heartGiftBorder) {
		this.heartGiftBorder = heartGiftBorder;
	}

	public void setHeartGiftDocument(String heartGiftDocument) {
		this.heartGiftDocument = heartGiftDocument;
	}

	public void setHeartGiftAnchorIcon(String heartGiftAnchorIcon) {
		this.heartGiftAnchorIcon = heartGiftAnchorIcon;
	}

	public void setHeartGiftUserIcon(String heartGiftUserIcon) {
		this.heartGiftUserIcon = heartGiftUserIcon;
	}

	public String getHeartGiftAnchorLiveImg() {
		return heartGiftAnchorLiveImg;
	}

	public void setHeartGiftAnchorLiveImg(String heartGiftAnchorLiveImg) {
		this.heartGiftAnchorLiveImg = heartGiftAnchorLiveImg;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAppbigHeartGiftBorder() {
		return appbigHeartGiftBorder;
	}

	public String getAppsmallHeartGiftBorder() {
		return appsmallHeartGiftBorder;
	}

	public void setAppbigHeartGiftBorder(String appbigHeartGiftBorder) {
		this.appbigHeartGiftBorder = appbigHeartGiftBorder;
	}

	public void setAppsmallHeartGiftBorder(String appsmallHeartGiftBorder) {
		this.appsmallHeartGiftBorder = appsmallHeartGiftBorder;
	}

	public String getHeartgiftstyle() {
		return heartgiftstyle;
	}

	public void setHeartgiftstyle(String heartgiftstyle) {
		this.heartgiftstyle = heartgiftstyle;
	}

	public String getNewlivesvga() {
		return newlivesvga;
	}

	public void setNewlivesvga(String newlivesvga) {
		this.newlivesvga = newlivesvga;
	}
	
	
	
	/* 移动端礼物需求 - getset - wujianing */

	@Override
	public String toString() {
		return "Gift{" +
				"giftId=" + giftId +
				", giftName='" + giftName + '\'' +
				", price=" + price +
				", levelReq=" + levelReq +
				", smallPic='" + smallPic + '\'' +
				", bigPic='" + bigPic + '\'' +
				", bigPic1='" + bigPic1 + '\'' +
				", giftType=" + giftType +
				", showType=" + showType +
				", status=" + status +
				", param='" + param + '\'' +
				", group=" + group +
				", identity='" + identity + '\'' +
				", giftTypeName='" + giftTypeName + '\'' +
				", rankStatus=" + rankStatus +
				", effect=" + effect +
				", maxMultiple=" + maxMultiple +
				", clientPic='" + clientPic + '\'' +
				", mGiftIcon='" + mGiftIcon + '\'' +
				", mGiftType=" + mGiftType +
				", mGifIos='" + mGifIos + '\'' +
				", mGifAndroid='" + mGifAndroid + '\'' +
				", giftDyEffect='" + giftDyEffect + '\'' +
				", coeffectPower=" + coeffectPower +
				", desc='" + desc + '\'' +
				", sort=" + sort +
				", point=" + point +
				", experience=" + experience +
				", cusMoney=" + cusMoney +
				", cusFavor=" + cusFavor +
				", webVersion=" + webVersion +
				", mobileVersion=" + mobileVersion +
				", displayType=" + displayType +
				", mobilePng='" + mobilePng + '\'' +
				", mobileZip='" + mobileZip + '\'' +
				", mobileSvga='" + mobileSvga + '\'' +
				", moneyType=" + moneyType +
				", starPrice=" + starPrice +
				", pkValue=" + pkValue +
				", pkDescription='" + pkDescription + '\'' +
				", pcSvga='" + pcSvga + '\'' +
				", weekStar=" + weekStar +
				", wsDesc='" + wsDesc + '\'' +
				", heartGiftBorder='" + heartGiftBorder + '\'' +
				", heartGiftDocument='" + heartGiftDocument + '\'' +
				", heartGiftAnchorIcon='" + heartGiftAnchorIcon + '\'' +
				", heartGiftUserIcon='" + heartGiftUserIcon + '\'' +
				", heartGiftAnchorLiveImg='" + heartGiftAnchorLiveImg + '\'' +
				", logo='" + logo + '\'' +
				", appbigHeartGiftBorder='" + appbigHeartGiftBorder + '\'' +
				", appsmallHeartGiftBorder='" + appsmallHeartGiftBorder + '\'' +
				", heartgiftstyle='" + heartgiftstyle + '\'' +
				", newlivesvga='" + newlivesvga + '\'' +
				", appGiftShelfType='" + appGiftShelfType + '\'' +
				", showCombo=" + showCombo +
				", minWealthLevel=" + minWealthLevel +
				'}';
	}
}
