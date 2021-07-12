package com.zoo.ninestar.domains.constants;

import com.zoo.ninestar.utils.Loader;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static String SHOW_TOKEN = "E524130E54FA88D2C6B4A3CA509BCCA0";

	// 直播间冠名榜条数
	public static final int LIVE_MOST_GIVE_COUNT = 30;

	// 直播间送礼TOP条数（直播间重构修改为：5） 2020-04-29
	public static final int LIVE_GIFT_TOP_COUNT = 5;

	// 直播间PK记录榜单条数
	public static final int PK_RECORD_COUNT = 5;

	// 直播间PK记录榜单条数 APP
	public static final int APP_PK_RECORD_COUNT = 30;

	// 赛事宝贝活动
	public static final String GAME_GIRL_ACTIVITY = "赛事宝贝";
	// 赛事宝贝礼物总数
	public static final int GAME_GIRL_GIFT_COUNT = 6;
	// 赛事宝贝1阶段礼物数量
	public static final int GAME_GIRL_GIFT_1 = 1;
	// 赛事宝贝2阶段礼物数量
	public static final int GAME_GIRL_GIFT_2 = 2;
	// 赛事宝贝3阶段礼物数量
	public static final int GAME_GIRL_GIFT_3 = 3;
	// 赛事宝贝完成动画pc
	public static final String GAME_GIRL_FINISH_PC = "http://f.cdn.tllm.cxg.changyou.com/show/20180517/images/gamegirl/finish_pc.swf";
	// 赛事宝贝完成动画app
	public static final String GAME_GIRL_FINISH_APP = "http://image.cdn.tllm.cxg.changyou.com/show/20180517/images/gamegirl/finish_app.zip";
	// 赛事宝贝完成目标
	public static final int GAME_GIRL_GOAL = 50;
	// 赛事宝贝礼物ID
	public static final Long GAME_GIRL_GIFT_ID = -100l;
	// 赛事宝贝PC礼物URL
	public static final String GAME_GIRL_GIFT_URL_PC = "http://image.cdn.tllm.cxg.changyou.com/show/20180517/images/gamegirl/gift_pc.png";
	// 赛事宝贝APP礼物URL
	public static final String GAME_GIRL_GIFT_URL_APP = "http://image.cdn.tllm.cxg.changyou.com/show/20180517/images/gamegirl/gift_app.png";
	// 赛事宝贝礼物名称
	public static final String GAME_GIRL_GIFT_NAME = "打CALL";
	// 赛事宝贝礼物备注
	public static final String GAME_GIRL_GIFT_DESC = "战队宝贝";
	
	// 情人节活动名称 added by xzz 2019/12/18
	public static final String LOVE_ACTIVITY_NAME = "情人节2020";
	// 情人节活动排行榜名称 added by xzz 2019/12/18
	public static final String LOVE_ACTIVITY_RANK_NAME = "情人节2020排行榜";
	// 情人节活动英文名称 added by xzz 2019/12/18
	public static final String LOVE_ACTIVITY_ENGLISH_NAME = "love";

	// 赛事宝贝礼物类型
	public static enum GAME_GIRL_GIFT_TYPE {
		PC, APP

	}

	// 数据源
	public static final String DS_MASTER = "sohushowdb";
	public static final String DS_SLAVE = "sohushowdbslave";
	public static final String DS_ACTIVITY = "sohuactivitydb";
	public static final String DS_STATISTICDB = "statisticdb";

	// 记录推广来源的cookie名
	public static final String SOURCE_COOKIE_NAME = "showqd";

	public static final String CHANNEL_COOKIE_NAME = "channel";

	// 第一次进直播间
	public static final String ENTER_COOKIE_NAME = "is_first";

	// 用户唯一标示cookie名
	public static final String UNIQUE_COOKIE_NAME = "show_cxg_unique";

	public static final String CXG_COOKIE_SPECIAL = "cxg_special";

	// 客户端信息cookie名
	public static final String CLIENT_INFO = "clientInfo";

	// 高级礼物判断(103-高级/104-幸运/105-奢华)
	public static final String TOP_GIFT_TYPE = "103,104,105";

	// 任务
	public static enum TASK {
		NULL, GUIDE;

	}

	;

	// 子任务
	public static enum SUBTASK {
		// 完成注册, 首次修改昵称, 首次和主播聊天, 首次关注主播, 首次送礼, 首次充值
		NULL, ACTIVATE, UPDATE_NICKNAME, FIRST_TALK, FIRST_Follow, FIRST_SEND_GIFT, FIRST_RECHARGE

	}

	;

	// 任务状态
	public static enum TASK_STATUS {
		CLOSE, OPEN
	}

	// 任务完成状态
	public static enum TASK_DONE_STATUS {
		UNDONE, DONE, GIVE_UP
	}

	// 任务奖励领取状态
	public static enum TASK_GETAWARD_STATUS {
		NO, YES
	}

	// 主播指定推流服务器
	public static enum PUSH_TYPE {
		YES, NO
	}

	// 主播是否是假人
	public static enum MASTER_IS_ROBOT {
		NO, YES
	}

	public static enum VISIT_TYPE {
		LOGIN, ACTIVATE, REGISTER, ENABLE, DUPLICATE_IP
	}

	// 游戏开关
	public static enum GAME_SWITCH {
		CLOSE, OPEN
	}

	public static enum OPEN_SWITCH {
		ON, OFF
	}

	public static enum KEY_VALUE {
		OPEN_GAME_SWITCH
	}

	public static enum THREE_GAME_TYPE {

		GAME1(10, "四大美女"), GAME2(11, "水果转转"), GAME3(12, "跑马"), GAME4(1, "砸蛋"), GAME5(2, "四大名著");
		// GAME5(14, "捕鱼");

		int id;

		String name;

		THREE_GAME_TYPE(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

	}

	// // 游戏类别
	// public static enum GAME_TYPE {
	// CHEST
	// }

	;

	// 奖项状态
	public static enum GAMEAWARD_STATUS {
		ENABLE, DISABLE
	}

	;

	// 奖项类型
	public static enum GAMEAWARD_TYPE {
		// 小奖、大奖
		SMALL, BIG
	}

	;

	// 奖项状态
	public static enum LUCKY_GIFT_AWARD_STATUS {
		ENABLE, DISABLE
	}

	;

	// 奖项类型
	public static enum LUCKY_GIFT_AWARD_TYPE {
		// 小奖、大奖
		SMALL, BIG
	}

	;

	public static enum PLAN_STATUS {
		DISABLE, ENABLE
	}

	;

	public static enum LUCKY_GIFT_PLAN_STATUS {
		DISABLE, ENABLE
	}

	;

	public static enum JINBI_ACTIVITY_PLAN_STATUS {
		DISABLE, ENABLE
	}

	;

	// 计划任务状态
	public static enum TASKPLAN_STATUS {
		WAITING, RUNING, EXPIRED
	}

	;

	public static final int RATE_RANG = 10000;

	public static final int RATE_RANG_FOR_LUCK_GIFT = 10000;

	public static final int RATE_RANG_FOR_JINBI_ACTIVITY = 100;

	// 初始奖池
	public static final long POOL_INIT = 1000000;

	// 房间初始人数
	public static final int ROOM_INIT_LIMIT = 50;
	// 房间无限人数
	public static final int ROOM_MAX_LIMIT = 1000000;

	public static enum MOBILE_SORT {
		NO, MASTER, ONLINEUSERS, GIFTCOUNT
	}

	;

	// 主播隐身状态
	public static enum MASTER_HIDDEN_STATUS {
		FALSE, TRUE
	}

	;

	// 主播公聊状态
	public static enum MASTER_PUBLIC_CHAT_STATUS {
		DENY, ALLOW
	}

	;

	// 是否展示礼物
	public static enum GIFT_SHOW_STATUS {
		FALSE, TRUE
	}

	;

	// token中的用户类型
	public static enum TOKEN_USERTYPE {
		// 主播：50 巡管：10 房管：40 普通成员：30 游客：20
		UNDEAD(5), MANAGER(10), ANONYMOUS(20), MEMBER(30), SA(40), MASTER(50);

		private final int value;

		TOKEN_USERTYPE(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}

	}

	// app define
	public static enum APP {
		NULL, CHAT, UPLOAD, VIDEO, LIVE
	}

	;

	// 账单结算状态
	public static enum CLEARING_STATUS {
		INIT, SUBMIT, CANCEL, CLOSE, FAILED
	}

	;

	// 账单类型
	public static enum CLEARING_TYPE {
		NULL, MASTER, AGENT, ROOM, BASESALARY
	}

	;

	// 是否允许直播
	public static enum LIVE_LIMIT {
		ALLOW, FORBIDDEN
	}

	;

	/**
	 * 1付费,2密码
	 * <p>
	 * 直播房间类型 <br>
	 * 2014年2月28日 下午1:45:41
	 * </p>
	 * 
	 * @author <a href="mailto:wujianing@cyou-inc.com">wujianing</a>
	 */
	public static enum LIVE_ROOM_TYPE {
		PUBLIC, ENTRYFEEREQUIRED, PASSWORD
	}

	;

	// 直播房间大分类
	public static enum LIVE_MAJOR_TYPE {
		PERSONAL, THREE
	}

	;

	// IBackEndUsrMgr
	public static enum SEARCH_TYPE {
		UID, MASTERNO
	}

	;

	// 封号状态
	public static enum LOCK_TYPE {
		NONE, LOCK, NONEIP, LOCKIP, MASTER_UN_SIGN
	}

	;

	// 个人直播间角色
	public static enum ROLE {
		// 普通用户，巡管，代理，经纪人
		NOMAL, PATROL, AGENT, SPY
	}

	;

	// 主播组类型
	public static enum MASTER_GRADE_LEVEL {
		// ,试唱广场, 草根云集, 初露锋芒, 星光灿烂, 绝世歌神
		GROUP0, GROUP1, GROUP2, GROUP3, GROUP4, NONE, ALL
	}

	;

	// 服务器分类
	public static enum SERVER_TYPE {
		FRONT, ADMIN, CHATROUTE, CHATSCHEDULER
	}

	;

	// 公告类型
	public static enum NOTICE_TYPE {
		// , 首页系统公告，直播间公告，直播间私聊区公告
		NULL, SYSTEM, ROOM, PRIVATE_MSG, EVENT, AD,
	}

	;

	// 导量类型
	public static enum TRAFFIC_TYPE {
		DEFAULT, NORMAL, EMERGENCY
	}

	;

	// 导量状态
	public static enum TRAFFIC_STATUS {
		DEFAULT, LIVE
	}

	;

	// 用户消息类型
	public static enum MESSAGE_TYPE {
		NORMAL, SYSTEM
	}

	;

	// 活动状态类型
	public static enum HD_STATUS {
		WAIT, PREPARE, START, END
	}

	;

	// 活动礼物记录类型
	public static enum HD_GIFTRECORD_TYPE {
		NULL, RECEIVE, SEND
	}

	;

	/**
	 * 年度盛典 获取/消耗 人气卡 action
	 * @author xzz
	 * @time 2019/11/13
	 *
	 */
	public static enum YEAE_CEREMONY_ACTION_TYPE {
		//0客户端上报、1app客户端上报、2老用户召回、3赠送、4领取进度节点5.购买
		REPORT, APPREPORT,RECALL,SEND,DRAW,BUY
	};
	
	/**
	 * 年度盛典 获取/消耗 人气卡 action
	 * @author xzz
	 * @time 2019/11/13
	 *
	 */
	public static enum YEAE_CEREMONY_BOX_TYPE {
		// 铜 、 银 、金
		COPPER, SILVER, GOLD
	};
	
	/**
	 * 年度盛典 节点状态
	 * @author xzz
	 * @time 2019/11/13
	 *
	 */
	public static enum YEAE_CEREMONY_NODE_STATUS {
		// 0.无资格 1.可领取 2.领取 3.已发放
		NO, DRAW, DRAWED, GRANT
	};
	
	// 投诉建议状态
	public static final int COMPLAINT_STATUS_NOTREAD = 0;// 未读
	public static final int COMPLAINT_STATUS_READED = 1;// 已读

	public static final int[] TREE = { 1000, 10000, 40000 };
	public static final int[] SOCK = { 10000, 100000, 500000 };
	public static final int[] CAP = { 2000, 20000, 100000 };
	public static final int[] REWARD_TREE = { 50000, 200000, 500000 };
	public static final int[] REWARD_SOCK = { 10000, 50000, 100000 };
	public static final int[] REWARD_CAP = { 20000, 100000, 200000 };

	// 投诉建议类型
	public static final int COMPLAINT_TYPE_ADVISE = 2;// 建议
	public static final int COMPLAINT_TYPE_COMPLAINT = 1;// 投诉

	// 用户消息状态
	public static enum USERMESSAGE_STATUS {
		NEW, READED
	}

	;

	// 道具ID定义
	public static enum PROP {
		NULL(0), MOSTVIP(1), VIP(2), HIDDEN(6), SPEAKER(102), COMMON_FLY(111), XUANCAI_FLY(112), HAOHUA_FLY(113), BUY_MASTERNO(200), PRIVATE_ROOM(300), CHARGE_ROOM(400), HAMMER_GOLD(500), HAMMER_SILVER(600), HAMMER_COPPER(700), ROOM_NAME_CHANGE(800);

		private final int value;

		PROP(int value) {
			this.value = value;
		}

		public int intValue() {
			return this.value;
		}

		public long longValue() {
			return this.value;
		}
	}

	;

	public static enum PROP_VIP {

		VIP_PURPLE(1), VIP_YELLOW(2);

		private long id;

		PROP_VIP(int id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}

	}

	public static enum PROP_ITEM {

		ZIVIP_YEAR(4), HUANGVIP_YEAR(8);

		private final int value;

		PROP_ITEM(int value) {
			this.value = value;
		}

		public int intValue() {
			return this.value;
		}

		public long longValue() {
			return this.value;
		}
	}

	// 道具费用
	public static enum PROP_PRICE {
		// 密码房间, 收费房间, 小喇叭
		PRIVATE_ROOM(50), CHARGE_ROOM(100), SPEAKER(500);
		private final int value;

		PROP_PRICE(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}

	};

	// 发飞屏费用
	public static enum PROP_FLYSCREEN_PRICE {
		COMMON_FLY(111L, 300), XUANCAI_FLY(112L, 500), HAOHUA_FLY(113L, 1000);
		long id;
		int value;

		PROP_FLYSCREEN_PRICE(long id, int value) {
			this.id = id;
			this.value = value;
		}

		public long id() {
			return this.id;
		}

		public int val() {
			return this.value;
		}

		public static int getPrice(long id) {
			PROP_FLYSCREEN_PRICE[] flys = PROP_FLYSCREEN_PRICE.values();
			for (PROP_FLYSCREEN_PRICE fly : flys) {
				if (fly.id() == id) {
					return fly.val();
				}
			}
			return 0;
		}

	};

	public static enum PROP_COUNT {
		// 小喇叭
		SPEAKER(100);
		private final int value;

		PROP_COUNT(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}
	}

	// 道具100年为限
	public static int PROP_LIMIT_COUNT = 240;
	public static int PROP_LIMIT_UNIT = 2;

	public static int PROPSPEAKER_CALL_LIMIT = 100;
	// 无限 15年
	public static int NOLIMIT_YEAR_MILLIS = 150000;

	// 徽章上下架状态
	public static enum MEDAL_STATUS {
		DISABLE, ENABLE
	}

	;

	// 道具上下架状态
	public static enum PROP_STATUS {
		DISABLE, ENABLE
	}

	;

	// 道具上有效期 单位
	public static enum PROP_ITEM_UNIT {
		MINUTE, YEAR, MONTH, WEEK, QUARTER, DAY
	}

	;

	// 前后台都显示 都不显示 仅后台
	public static enum PROP_ITEM_STATUS {
		YES, NO, BACK
	};

	// 轮播公告上下架状态
	public static enum NOTICE_STATUS {
		ENABLE, DISABLE
	}

	;

	// 轮播公告显示状态
	public static enum CHATNOTICE_DISPLAY_STATUS {
		NONE, DISPLAY
	}

	;

	// 礼物分类定义
	public static enum GIFT_TYPE_STATUS {
		DISABLE, ENABLE
	}

	;

	// 礼物分类展现位置
	public static enum GIFT_TYPE_USETYPE {
		// 不显示， 个人/家族直播页，达人秀直播页
		NONE, DEFAULT, DRX
	}

	public static enum GIFT_TYPE {
		STAR(0), BASIC(1), JUNIOR(2), SENIOR(3), LUXURY(4), HOLIDAY(5), PET(6), FLASH_MESSAGE(10), RECEIVE_GIFT(1102);

		private final int value;

		GIFT_TYPE(int value) {
			this.value = value;
		}

		public int intValue() {
			return this.value;
		}

		public long longValue() {
			return this.value;
		}

	}

	;

	// 送礼全城广播的金额设置
	public static enum GIFT_MONEY {
		// BROADCAST(10000) 2014年8月15日,钱少提出修改成50元rmb对应的乐币数,可以上跑道
		// BROADCAST(50000) 2015年1月16日，产品应运营提出修改成100元rmb对应的乐币数，可以上跑道
		BROADCAST(10000), EFFECTS(5000), SENDTOLIVE(20000);
		private final int value;

		GIFT_MONEY(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}

	}

	;

	// 账号类型
	public static enum ACCOUNT_TYPE {
		BANK, ALIPAY
	}

	;

	// 主播签约状态
	public static enum MASTER_SIGN_STATUS {
		NO, APPLY, YES, REJECT, ALL
	}

	;

	public static enum MASTER_SIGN_TYPE {
		PERSONAL, STUDIO
	}

	;

	// 主播分类
	public static enum MASTER_MAJOR_TYPE {
		// 默认，达人秀，华秀欢唱，所有,星座闪约
		// modify by xiaozhi 2015-05-28 默认(专业主播),玩家主播
		DEFAULT, GAMEANCHOR
	}

	;

	// 家族分类
	public static enum ROOM_MAJOR_TYPE {
		// 玩乐天地,综艺广场,美女点歌
		DEFAULT, ZYGC, MNDG
	}

	;

	// 主播状态
	public static enum MASTER_STATUS {
		ENABLED, DISABLED
	}

	;

	// 我的家族
	public static final int ROOM_APPLY_HISTORY = 3;// 家族申请历史记录表中actiontype值

	// 房间状态
	public static enum ROOM_STATUS {
		SUCCESS, FAIL
	}

	// 家族申请状态，和上面重复
	public static enum ROOM_MEMBER_STATUS {
		SUCCESS, FAIL
	}

	// 播放器模式
	public static final int ROOM_PLAYER_3MAI = 0;// 3麦模式
	public static final int ROOM_PLAYER_ZHUMAI = 1;// 主麦模式

	public static enum ROOM_MEMBER_ROLE {
		// 房间拥有者、副房主、主播(艺人)、管理员 6骑士 7王子
		ALL(0), OWNER(1), SUB(2), MASTER(3), ADMIN(4), SPY(5), KNIGHT(6), PRINCE(7);

		private final long value;

		ROOM_MEMBER_ROLE(long value) {
			this.value = value;
		}

		public long val() {
			return value;
		}
	}

	;

	// 角色map
	public static Map<Long, ROOM_MEMBER_ROLE> roleMap = new HashMap<Long, ROOM_MEMBER_ROLE>();

	static {
		for (ROOM_MEMBER_ROLE role : ROOM_MEMBER_ROLE.values()) {
			roleMap.put(role.val(), role);
		}
	}

	public static final int ROOM_ORDER_MASTERLEVEL_DESC = 1;
	public static final int ROOM_ORDER_MASTERLEVEL_ASC = 2;
	public static final int ROOM_ORDER_FANLEVEL_DESC = 3;
	public static final int ROOM_ORDER_FANLEVEL_ASC = 4;
	public static final int ROOM_ORDER_ISLIVESHOW = 5;

	public static final int ROOM_OPEN = 1; // 家族房开启
	public static final int ROOM_CLOSE = 0; // 家族房关闭

	public static final int ROOM_RANK_COUNT = 20; // 所有家族排行榜每榜列表数目

	public static enum ROOM_RANK_TYPE {
		NULL, POPULARITY, CASHCOW
	}

	;

	// 家族直播间排行类型
	public static enum ROOM_LIVE_RANK_TYPE {
		// 粉丝、红人
		FANS, MASTER
	}

	;

	public static enum ROOM_LOG_TYPE {
		// 添加 删除 修改
		ADD, DEL, MOD
	}

	;

	// 汇总表类型
	public static enum RANK_TYPE {
		NULL, STAR, CASHCOW, POPULARITY, WEEKGIFT
	}

	;

	// 主播徽章类型
	public static enum MASTER_MEDAL_TYPE {
		NULL, SUPER, MONTH, WEEK, DAY
	}

	;

	// 主播徽章状态
	public static enum MASTER_MEDAL_STATUS {
		FALSE, TRUE
	}

	;

	// 主播推送屏蔽
	public static enum MASTER_PUSH {
		YES, NO
	}

	;

	// 排行更新的字段项
	public static enum RANK_REF_DATE {
		DEFAULT(1), D(1), D_1(3), D_7(5), D_30(7);

		private final int value;

		RANK_REF_DATE(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}
	}

	;

	// 交易记录中礼物标示
	public static enum TRADE_TYPE {
		GIFT("礼物"), PROP("道具"), HAMMER("砸蛋"), MASTERNO("靓号");
		private final String value;

		TRADE_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	// 靓号活动属性
	public static enum MASTERNO_PROP {
		FIXED, ACTIVITY
	}

	public static enum PRESENTLOG_TYPE {
		// 0 赠送靓号, 1 完成心愿返值,2 系统赠送乐币体验,3 礼物周星系统赠送乐币,4 家族长赠送主播金豆,5 系统赠送金豆,6
		// 系统回收乐币,7 系统回收金豆,8 充值,9 兑换,10 结算，11 消费,12 收礼,13 砸蛋中奖,14
		// 主播金豆转移给家族长,15兑换游戏币,16代理乐币赠送
		MASTERNO("靓号赠送"), SYSTEM_ADD_BI("系统赠送乐币"), GIFTSTAR_BI("礼物周星赠币"), ROOM2HOST_DOU("家族長贈送金豆"), SYSTEM_ADD_DOU("系统赠送金豆"), SYSTEM_MINUS_BI("系统回收乐币"), SYSTEM_MINUS_DOU("系统回收金豆"), CHARGE("充值"), EXCHANGE("兑换乐币"), SETTLE("结算"), CONSUME("消费"), INCOME("收礼"), EGG_GAME("砸蛋游戏中奖"), HOST2ROOM_DOU("主播转移金豆给家族長"), PRESENT_JINBI("代理乐币赠送"), TASK_AWARD("任务奖励"), SYSTEM_MINUS_ROOMDOU("系统回收家族长金豆");
		private final String value;

		PRESENTLOG_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	// 直播状态
	public static enum LIVE_STATUS {
		// 0 结束
		// 1 直播中
		// 2 设置房间类型 ???
		END, IN, READY
	}

	// CDN流状态
	public static enum CDN_STREAM_STATUS {
		// 停止、开始、分发拉流失败、暂停
		STOP, START, BREAK, PAUSE
	}

	// 直播CDN类型
	// public static enum CDN_TYPE {
	// //[0自建],[1帝联],[2帝联测试],[3有孚],[4快网],[5网宿],[6蓝汛]
	// MYSELF, DILIAN, DILIAN_TEST, YOUFU, KUAIWANG, WANGSU, LANXUN;
	//
	// public static CDN_TYPE valueOf(int ordinal) {
	// if (ordinal < 0 || ordinal >= values().length) {
	// throw new IndexOutOfBoundsException("Invalid ordinal");
	// }
	// return values()[ordinal];
	// }
	// }

	// 是否为默认CDN类型
	public static enum CDN_TYPE_DEFAULT {
		NO, YES;

		public static CDN_TYPE_DEFAULT valueOf(int ordinal) {
			if (ordinal < 0 || ordinal >= values().length) {
				throw new IndexOutOfBoundsException("Invalid ordinal");
			}
			return values()[ordinal];
		}
	}

	// 直播开始方式
	public static enum LIVE_START_WAY {
		NORMAL, CONTINUE
	}

	// 直播结束原因
	public static enum LIVE_END_REASON {

		DEFAULT("初始默认"), EXCEPTION("异常关闭"), MASTER("主播关闭"), ADMIN("管理关闭"), FORBIDDEN("强制断流"), BALANCE_NOT_ENOUGH("余额不足"), BREAK_TIMEOUT("断流超时"), SYNC_STATUS("状态监测"), SWITCH_MIKE("切麦关闭"), ROOM_CLOSE("房间关闭"), BLOCK_MASTER("封号/封IP"), CDN_CLOSE("cdn回调");

		// EXCEPTION,
		// ADMIN,
		// MASTER,
		// FORBIDDEN,
		// BALANCE_NOT_ENOUGH,
		// BREAK_TIMEOUT,
		// SYNC_STATUS,
		// SWITCH_MIKE,
		// ROOM_CLOSE;

		private String reason;

		LIVE_END_REASON(String reason) {
			this.reason = reason;
		}

		public String val() {
			return this.reason;
		}

		public String getVal() {
			return val();
		}

	}

	// 收费房间进入房间收费礼物ID
	public static enum GIFT_ID {
		PAY_ROOM(52), WEEK_STAR(100), ROSE(1002);

		private final int value;

		GIFT_ID(int value) {
			this.value = value;
		}

		public int intValue() {
			return this.value;
		}

		public long longValue() {
			return this.value;
		}
	}

	// 活动徽章使用状态
	public static enum HDMEDAL_ISUSE {
		// 未使用,使用
		UNUSED, USE
	}

	// 活动徽章有效状态
	public static enum HDMEDAL_STATUS {
		// 有效,失效
		ENABLE, EXPIRED
	}

	// 定时任务的类型
	public static enum QUARTZ {
		QUARTZ_HOT_LIVE_ROOM_LIST(1377), CRON_RANK_DAY_STAR(1001), CRON_RANK_DAY_CASHCOW(1002), CRON_RANK_DAY_POPULARITY(1003), CRON_RANK_WEEK_STAR(1004), CRON_RANK_WEEK_CASHCOW(1005), CRON_RANK_WEEK_POPULARITY(1006), CRON_RANK_MONTH_STAR(1007), CRON_RANK_MONTH_CASHCOW(1008), CRON_RANK_MONTH_POPULARITY(1009), CRON_RANK_ALL_STAR(1010), CRON_RANK_ALL_CASHCOW(1011), CRON_RANK_ALL_POPULARITY(1012), CRON_RANK_MASTER_FAN_DAY(1101), CRON_RANK_MASTER_FAN_7DAY(1102), CRON_RANK_MASTER_FAN_30DAY(1103), CRON_RANK_MASTER_FAN_ALL(1104), CRON_RANK_GIFT_STAR_WEEK(1105), CRON_RANK_WEEK_GIFT(1106), QUARTZ_LIVESHOWSTATUS_SCHEDULE(1204), QUARTZ_ACCESSSTAT_SCHEDULE(1206), QUARTZ_MONTHDURATION_SCHEDULE(1301), QUARTZ_SYNCONLINEMASTER_SCHEDULE(1302), QUARTZ_SENDBULLETIN_SCHEDULE(1303), QUARTZ_REFRESHGIFTWEEKSTAT_SCHEDULE(
				1354), QUARTZ_HD_SCHEDULE(1355), QUARTZ_MOBILE_HD_SCHEDULE(1378), QUARTZ_CLIENT_HD_SCHEDULE(1380), QUARTZ_MEM_SCHEDULE(1356),
		// 刷新推荐新增主播的定时器
		QUARTZ_NEWROOM_RANK_SCHEDULE(1357),

		// 家族房相关统计
		CRON_ROOM_RANK_CASHCOW(1304), CRON_ROOM_RANK_CASHCOW_DAY(1305), CRON_ROOM_RANK_CASHCOW_WEEK(1306), CRON_ROOM_RANK_CASHCOW_MONTH(1307), CRON_ROOM_RANK_POPULARITY(1308), CRON_ROOM_RANK_POPULARITY_DAY(1309), CRON_ROOM_RANK_POPULARITY_WEEK(1310), CRON_ROOM_RANK_POPULARITY_MONTH(1311), CRON_ROOM_RANK_CROWN_DAY(1312), CRON_ROOM_RANK_DIAMOND_DAY(1313),

		// 砸蛋统计
		QUARTZ_CHEST_STATISTIC(1318),
		// 砸蛋游戏展示
		QUARTZ_CHEST_SHOW(1319),

		// 徽章关系过期处理
		QUARTZ_HDMEDAL_LOG_EXPIRE(1357),

		// 徽章发放记录
		QUARTZ_MISSION_LOG_SCAN(1358),

		// 结算
		QUARTZ_CLEARING_ROOM(1321), QUARTZ_CLEARING_MASTER(1322), QUARTZ_CLEARING_AGENT(1323), QUARTZ_CLEARING_BASE(1324),
		// 对账单
		QUARTZ_STATEMENT_SUMMARIZE_SCHEDULE(1325),

		// 假人定时任务
		QUARTZ_ROBOT(1361), QUARTZ_RQ(1362), CRON_FLASH_STAR(1363),
		// 财务报表
		QUARTZ_FINANCE_MASTER_SCHEDULER(1326), QUARTZ_FINANCE_ALL_SCHEDULER(1327),

		// 主播转移金豆给家族长
		QUARTZ_HANDIN_ROOM(1328),

		// 家族房关闭
		QUARTZ_ROOM_LIVE_CLOSE(1330),

		// 流量导入
		QUARTZ_FLOWIMPORT(1373),
		// 自动统计报表发送
		QUARTZ_REPORTTASK(1374), QUARTZ_MASTER_NOTIFY(1375),
		// 数据同步
		SYNC_GIFT(1382), SYNC_ROOM_GIFT_RANK(1383), SYNC_INTERFACE_DATA(1384), SYNC_LIVE_MASTER_LIST(1385), SYNC_USER_CHAT_STATUS(1392), SYNC_TEN_SECOND_INDEX(1386), SYNC_TEN_SECOND_RANK(1387), SYNC_MASTER_TAG(1388), SYNC_BAN_USER_IP(1389), ONLINE_COUNT(1390), SBC_COUNT(1391),
		// 魅力等级排行榜
		CRON_RANK_ANCHOR_CHARM_7DAYS(1393),
		// 魅力等级每日更新任务
		CRON_ANCHOR_CHARM_INIT_PERDAY(1394),
		// 畅游内消息推送
		INSIDE_MESSAGE_SEND(1395),
		// 主播数据统计
		ANCHOR_STAT(1396),
		// 视频播放量统计
		VIDEO_VIEW_STAT(1397),
		// 礼盒数据每日更新
		SYNC_TEAMBOX_STATUS(1398),
		// 主播魅力值更新
		CRON_ANCHOR_CHARM_VALUE(1399),
		// 观众数据统计
		AUDIENCE_STAT(1340),
		// 排行榜
		RANK_DATA(1342),
		// 排行榜
		RANK(1343),
		// 抢车位
		PARK(1341),
		// 连麦超时
		CONC_MIC(1344),
		// 连麦峰值
		CONC_MIC_APEX(1345),
		// 主播列表
		ANCHOR_LIST(1346),
		// 签到活动榜单
		CRON_SIGN_RANK_SCHEDULE(1400),
		// PK统计
		CRON_PK_STATISTICS_SCHEDULE(1401),

		//植树节活动定时器
		TREE_ACTIVITY_SCHEDULER(1402),
		//植树节活动每天执行一次的定时器
		TREE_ACTIVITY_DAY_SCHEDULER(1403),
		//植树节活动小园丁定时器
		TREE_ACTIVITY_GARDENER_SCHEDULER(1404);

		private final int value;

		QUARTZ(int value) {
			this.value = value;
		}

		public long val() {
			return this.value;
		}

		public static QUARTZ get(int value) {

			QUARTZ[] q = QUARTZ.values();
			for (int i = 0; i < q.length; i++) {
				if (q[i].val() == value) {
					return q[i];
				}
			}
			return null;
		}
	}

	// 冻结金豆
	public static enum FREEZE_TYPE {
		NONE, NEXT, ALLWAYS
	}

	public final static int BATCH_COUNT = 30;
	// // 底薪计算 从规则表t_dd_basesalary中获取记录个数
	public final static int BASESALARY_ROW_NUMBER = 26;

	public final static String ROOM_BAN = "1";
	public final static String ROOM_NOT_BAN = "0";

	public final static String ROOM_IP_BAN = "11";
	public final static String ROOM_ACCOUNT_BAN = "22";

	// 代理商标示
	public static final int PAYMENT_AGENT = 1;

	// 星探标识
	public static final int MASTER_SPY = 1;

	public static final String NOTLOGINMSG = "账号/角色异常或天龙维护，已退出畅秀阁";

	public static final String MAINTAIN_TRADE_MSG = "直播功能维护中，暂时无法进行购买！";

	public static final String PARAMERRORMSG = "参数不正确";

	// 操作返回状态
	/**
	 * <v k="402018">您已被拉黑，不可发送飞屏</v> <v k="402019">您已被拉黑，不可发送广播</v>
	 * 
	 * @author gaojianping
	 *
	 */
	public static enum RESULT {
		// 成功、失败、用户不存在、未登陆、正在直播、余额不足、等级不够 000049:魅力值榜单获取失败
		SUCCESS("000000"), FAILED("000001"), NOEXIST("000003"), NOTLOGIN("000004"), LIVE_STATUS_IN("000005"), EXIST("000006"), BALANCE("200000"), LEVEL("000009"), USER_FORBIDDEN("300000"), USER_DISABLED("300001"), FORCE_REFRESH("900000"), NOENOUGH("000011"), SOLD_OUT("000022"), NO_NUM("000033"), ERR_NUM("000044"), SYSTEM_EXCEPTION("999999"), VIP("000012"), PARAM("000013"), NOT_EXCEED("000014"), IS_NOT_MASTER("000040"), ORDERCODE_ERROR("000041"), ORDERCODE_AUTH_ERROR("000042"), ANCHOR_ACCOUNT_ERROR("000043"), MASTER_YB_NOENOUGH("000044"), NOTDRAW("000045"), COSTYB_ERROR("000046"), DRAW_ORDER_ADD_ERROR("000047"), PARAM_ERROR("000048"), ERROR_CHARMRANK_GET_ERROR("000049"), ERROR_NUMBER_ENOUGH("000050"), BLACK_FLY_ERROR("402018"), BLACK_GB_ERROR("402019"), BLACK_ADMIN_ERROR("402020"), LIVE_NOT_IN(
				"000060"), LIVE_NOT_EXIST("000061"), ROLE_IN_LIVE("000062"), PASSWORD_ROOM("000063"), PASSWORD_ERROR("000064"), GAMEGIRLGIFTERROR("000065"), LIVE_TER_ERROR("000099"), LIVE_VERSION_ERROR("000100")
		,LIVE_CANNOT_ENTER("000101");

		private final String value;

		RESULT(String value) {
			this.value = value;
		}

		public String val() {
			return this.value;
		}
	}

	public static enum PAY_RESULT {
		SUCCESS("000000"), FAILED("100000"), BALANCE_NOT_ENOUGH("200000"), BALANCE_NOT_ENOUGH2("000004"), SOLD("sold");

		private final String value;

		PAY_RESULT(String value) {
			this.value = value;
		}

		public String val() {
			return this.value;
		}
	}

	// 靓号流通状态
	public static enum MASTERNO_LIFE_STATUS {
		// 官方赠送,系统回收,用户赠送,靓号销售,系统生成
		GFZS, XTHS, YHZS, LHXS, XTSC
	}

	public static enum ACCOUNT_STATEMENT_USERTYPE {
		ALL, FAN, MASTER, ROOM;
	}

	public static enum SEX_TYPE {
		UNKNOWN, MAN, WOMAN;

		public static SEX_TYPE getSEX(int i) {
			SEX_TYPE[] sexs = SEX_TYPE.values();
			return sexs[i];
		}
	}

	/**
	 * 签约审核是否分配房间
	 */
	public static enum SIGN_CREATE_ROOM {
		NO, SINGLE, THREE;
	}

	public static enum ADMIN_FUNC_TYPE {
		OPERATE, FUNCTION
	}

	public static enum ADMIN_FUNC_STATUS {
		ENABLE, DISABLE
	}

	public static enum ADMIN_ROLE_STATUS {
		ENABLE, DISABLE
	}

	public static enum ADMIN_USER_STATUS {
		ENABLE, DISABLE
	}

	public static enum CHARGE_STATUS {
		PENDING, SUCCESS, FAILED;
	}

	// 充值类型（系统内部使用）
	public static enum CHARGE_TYPE {
		// 普通(0) 代理赠币自己充值(1)
		COMMON, AGENTPRESENTJINBI;
	}

	// 靓号销售状态
	public static enum MASTERNO_SALE_STATUS {
		// 待售、已售、闲置
		SALE, SOLD, IDLE
	}

	// 主播拥有的靓号状态
	public static enum MASTERNO_LIST_STATUS {
		// 已购买、正在使用、过期
		BOUGHT, USEING, EXPIRE
	}

	public static enum MASTERNO_LIST_TYPE {
		DEFAULT, ACTIVE, FIX
	}

	public static int EXCHANGE_RATE_RENMINBI_2_JINBI = 100;

	// 代理分成比例
	public static double AGENT_RATIO = 0.10D;
	// 家族长分成比例
	public static double ROOM_RATIO = 0.05D;
	// 人民币兑金豆比例
	public static int SETTLE_RATIO = 125;
	// 人民币最小结算单位
	public static int SETTLE_UNIT = 100;

	public static enum TRADE_IDENTITY {
		FAN, MASTER;
	}

	public static enum DOMAIN_TYPE {
		PORTAL_PAGE, ADMIN_PAGE, LIVE_PAGE, JS, CSS, IMAGE, SWF, GSLB
	}

	public static enum DOMAIN_STATUS {
		ENABLE, DISABLE
	}

	public static enum COMMON_STATUS {
		ENABLE, DISABLE
	}

	public static enum CARD_STATUS {
		ENABLE, FREEZE, DISABLE, USED
	}

	// 礼物，收费房间，统计时需要屏蔽判断
	public static final int GIFT_ROOM_FEE = 52;

	// 本周礼物排行，每种礼物的排行统计数量，保存前50名
	public static final int RANK_WEEK_GIFT_COUNT = 50;

	public static enum GIFT_STAR_HISTORY_STATUS {
		UNFINISHED, FINISH
	}

	public static enum GIFT_STAR_AWARD_STATUS {
		UNFINISHED, FINISH
	}

	// Feed类型
	public static enum FEED_TYPE {
		GIFT, LIVE, MASTER_LEVEL, FAN_LEVEL, SIGN, CHEST, SONG, OUT_GAME
	}

	// 禁言时间，5分钟
	public static final Integer BANCHAT_TIMEOUT = 1800;

	// 截图质量
	public static final Integer CAPTURE_QULITY = 90;
	// 截图缩小的尺寸
	public static final Dimension INDEX_IMAGE_THU_SIZE = new Dimension(120, 90);
	// 截图剪裁的尺寸
	public static final Dimension INDEX_IMAGE_CAT_SIZE = new Dimension(120, 80);

	// 高清插件直播状态
	public static enum SHOW_HD_TYPE {
		// 高清，无插件高清，普通
		HD, NORMAL_HD, NORMAL, NOHD
	}

	// peak key
	// public static String PEAK_KEY = "!Rcn2w3V%z";
	// psid
	// public static String PS_ID = "1773";

	// 流状态
	public static enum LIVE_STREAM_STATUS {
		/**
		 * 0 正在直播, 但没有推流
		 */
		WAIT,
		/**
		 * 1 正在直播
		 */
		IN,
		/**
		 * 2 正在直播, 但流中断了
		 */
		BREAKING,
		/**
		 * 3 续播
		 */
		RESUME,
		/**
		 * 4 流结束
		 */
		END
	}

	// 首页直播默认图片集
	public static String DEFAULT_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.default");
	// 首页直播网宿图片集
	public static String WANGSU_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.wangsu");

	// 首页直播网宿图片集-金山云
	public static String JINSHAN_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.jinshan");

	// 首页直播网宿图片集-星域云
	public static String XINGYU_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.xingyu");

	// 首页直播网宿图片集-阿里云
	public static String ALI_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.ali");

	// 首页直播网宿图片集-视界云
	public static String VC_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.vc");

	// 密码房封面照
	public static String PASSWORD_SHOW_SNAPSHOT = Loader.getInstance().getProps("home.live.cover.password");

	public static enum SNAPSHOT_TYPE {
		WANGSU(5, WANGSU_SHOW_SNAPSHOT), JINSHAN(18, JINSHAN_SHOW_SNAPSHOT), XINGYU(14, XINGYU_SHOW_SNAPSHOT), ALI(16, ALI_SHOW_SNAPSHOT), VC(19, VC_SHOW_SNAPSHOT);
		int id;
		String value;

		SNAPSHOT_TYPE(int id, String value) {
			this.id = id;
			this.value = value;
		}

		public int id() {
			return this.id;
		}

		public String val() {
			return this.value;
		}

		public static String getVal(int id) {
			SNAPSHOT_TYPE[] sts = SNAPSHOT_TYPE.values();
			for (SNAPSHOT_TYPE st : sts) {
				if (st.id() == id) {
					return st.val();
				}
			}
			return WANGSU_SHOW_SNAPSHOT;
		}

	};

	// public static List<String> DEFAULT_SHOW_SNAPSHOTS = new
	// ArrayList<String>();
	//
	// static {
	// DEFAULT_SHOW_SNAPSHOTS
	// .add("http://tv.sohu.com/upload/hdlive/gift/suoluetu/morentu/suoluetu%20(4).jpg");
	// }

	// 签约封面图标志，1只去封面 0按照顺序取
	public static enum PIC_COVER_FLAG {
		NO, YES
	}

	// 首页屏蔽 不屏蔽，仅屏蔽大图，屏蔽整个首页
	public static enum SHOW_INDEX_DISPUSH {
		NO, ONLYBIGPIC, ALL
	}

	// 图片审核APPID枚举
	public static enum IMG_AUTH_APPID {
		NOTHING(0), BACKGROUND(1), ROOMLOGO(2), SNAPSHOT(4);

		private final int value;

		IMG_AUTH_APPID(int value) {
			this.value = value;
		}

		public int val() {
			return this.value;
		}
	}

	// public static String DEFAULT_ROOM_SNAPSHOT =
	// "http://img.live.tv.itc.cn/images/default_family.jpg";

	// 首页线别line style width
	public static final Float LINE_STYLE_WIDTH = .7f;

	// public static final String IMG_AUDIT_DOMAIN =
	// "imgaudit.v.internal.17173.com";

	// 奖金类型
	public static enum BONUS_TYPE {
		NONE, WOKRHARDBONUS, LONELYBONUS, SEASONBONUS, YEARBONUS, MASTERCOMPENSATION, AGENTCOMPENSATION, SALARY, RQBONUS
	}

	public static enum PHOTO_APP {
		DBSEARCH300_180, WAILIAN128_72, DBBFY, IMG_4_3, IMG_16_9, IMG_9_16, IMG_1_1
	}

	public static enum MASTER_ISPUSH {
		NO, YES
	}

	// 任务类型
	public static enum MISSION_TYPE {
		NULL, GIFT, CONSUME
	}

	// 任务状态
	public static enum MISSION_STATUS {
		DISABLE, ENABLE
	}

	// 发放徽章的记录状态
	public static enum MISSION_LOG_STATUS {
		NULL, READY, SUCCESS, FAILED
	}

	public static enum SPAM_STATUS {
		DISABLE, ENABLE
	}

	public static enum MASTER_CUSTOM_CONFIG_PLUGIN_LOG {
		DISABLE, ENABLE
	}

	public static enum NET_TYPE {
		CNC, TELECOM, MOBILE
	}

	public static enum CHAT_SERVER_TYPE {
		CNC, TELECOM
	}

	// TODO: 注解聊天服务器的硬编码 xiaozhi
	// public static enum CHAT_SERVER_IP {
	//
	// C1("61.135.176.133", "220.181.89.1"), C2("61.135.176.136",
	// "220.181.89.2"), C3(
	// "61.135.176.137", "220.181.89.3"), C4("61.135.176.138", "220.181.89.10"),
	// C5(
	// "61.135.176.139", "220.181.89.11"), C6("61.135.176.140",
	// "220.181.89.12"), C7(
	// "61.135.176.222", "220.181.89.6"), C8("61.135.176.227", "220.181.89.7");
	//
	// private final String[] ips;
	//
	// CHAT_SERVER_IP(String ip1, String ip2) {
	// this.ips = new String[]{ip1, ip2};
	// }
	//
	// public String val(int type) {
	// return this.ips[type];
	// }
	//
	// }

	// 拒绝私聊的限制级别
	public static int PRIVATE_CHAT_DENY_LEVEL = 3;

	// 私聊限制的状态，ALLOW可以私聊，DENY不能私聊
	public static enum MASTER_PRIVATE_CHAT_STATUS {
		DENY, ALLOW
	}

	public static enum IS_SPECIAL_ROOM {
		NO, YES
	}

	// 渠道用户状态
	public static enum QD_USER_STATUS {
		ENABLED, DISABLED
	}

	public static enum QD_GROUP_STATUS {
		ENABLED, DISABLED
	}

	// 充值渠道显示状态
	public static enum CHARGE_QD_HIDE_STATUS {
		DISPLAY, HIDE
	}

	// 用户的激活注册状态
	public static enum QD_IP_STATUS {
		ACTIVATE, ENABLE
	}

	// 渠道类型，CPA按照注册和激活记费，CPM按照访问记费
	public static enum QD_TYPE {
		NONE, CPC, CPA, CPS, CPM
	}

	public static enum MASTER_REG_TYPE {
		ACTIVATE, REGIST
	}

	public static enum MASTER_ENABLE_TYPE {
		NONE, ENABLE, DUPLICATE_IP
	}

	// 代理销售提成 30%
	public static int PROP_SALE_RATIO = 20;

	// 签约主播是否结算
	public static enum MASTER_SETTLE_FLAG {
		NO, YES
	}

	;

	// 假人任务类型
	public static enum ROBOT_TASK_TYPE {
		PERSONAL, ROOM
	}

	// 假人任务状态
	public static enum ROBOT_TASK_STATUS {
		READY, RUNNING, FINISH, STOP, KILL, RECREATE
	}

	// 假人分类
	public static enum ROBOT_TYPE {
		TASK, TRIGGER, ALL
	}

	public static enum GRADE_LEVEL_HIDDEN {
		SHOW, HIDE
	}

	public static enum QQ_TYPE {
		NULL(0), MASTER(1), ROOM(2), HD(3), BD(4), CS(5);

		private final long value;

		QQ_TYPE(long value) {
			this.value = value;
		}

		public long val() {
			return this.value;
		}
	}

	// 是否开通赠币代理
	public static enum PRESENT_JINBI_AGENT_FLAG {
		// 未开通 开通 关闭
		UNOPEN, OPEN, CLOSE
	}

	;

	// 代理赠币结果
	public static enum PRESENT_JINBI_RESULT {
		SUCCESS("000000"), FAILED("100000"), BALANCE_GET_FAILED("000001"), MASTER_GET_FAILED("000002"), MASTER_RIGHT_INVALID("000003"), BALANCE_AMOUNT_INVALID("000004"), PARAM_INVALID("000005");
		private final String value;

		PRESENT_JINBI_RESULT(String value) {
			this.value = value;
		}

		public String val() {
			return this.value;
		}
	}

	;

	// 流量导入任务状态
	public static enum FLOW_IMPORT_STATUS {
		// 运行 导量结束 强制关闭
		UNCOMPLETE, COMPLETE, CLOSE
	}

	// 流量导入类型
	public static enum FLOW_IMPORT_TYPE {
		TIME, IMPORTPEOPLE, REALPEOPLE
	}

	// 流量导入房间类型
	public static enum FLOW_IMPORT_ROOM_TYPE {
		PERSON, ROOM
	}

	public static final Long MONITORID_LOCAL = 0l;

	// 家族信息修改的类型
	public static enum ROOM_INFO_CHANGE_TYPE {
		NONE(0), NAME(500), PRICE(0);

		private final int value;

		ROOM_INFO_CHANGE_TYPE(int value) {
			this.value = value;
		}

		public long longValue() {
			return this.value;
		}

		public int intValue() {
			return this.value;
		}
	}

	// 家族信息修改的状态
	public static enum ROOM_INFO_CHANGE_STATUS {
		SUBMIT, APPLY, REFUES
	}

	public static enum ADVERTISEMENT_TYPE {
		NORMAL, CORNER, LIVE_LUNBO
	}

	public static enum FINANCE_COMPANY {
		SOHU, GUANJIA, LAOWU, COLLECT, ALL
	}

	public static enum FINANCE_BUSINESS_TYPE {
		PAYMENT, TAX, COSTPRICE
	}

	// 邮件配置
	public static enum MAIL_CONFIG_TYPE {
		NONE, DAILYREPORT, DURATIONREPORT, LIVE_MONITOR_WARNING, MIC_ERROR;
	}

	;

	// 报表发送状态
	public static enum REPORT_SEND_STATUS {
		TOSEND, HASSEND, SENDFAIL;
	}

	// 报表统计周期
	public static enum REPORT_PERIOD {
		DAY, WEEK, MONTH, QUARTER, YEAR
	}

	// 报表类型
	public static enum REPORT_TYPE {
		/**
		 * DAY_CONSUME_CHARGE(每日消费充值报表)、 DAY_MASTER_DURATION
		 * (每日总直播时长报表)、DAY_LEVEL_MASTE_DURATION
		 * (每日按开播等级排序的主播时长报表)、DAY_NEWCHARGE_MASTER(每日新增充值人数)
		 */
		DAY_CONSUME_CHARGE("消费充值"), DAY_MASTER_DURATION("总直播时长"), DAY_LEVEL_MASTE_DURATION("开播主播时长"), DAY_NEWCHARGE_MASTER("新增充值人数");
		private final String value;

		REPORT_TYPE(String value) {
			this.value = value;
		}

		public String stringVal() {
			return this.value;
		}
	}

	// 报表任务执行类型
	public static enum REPORT_TASK_TYPE {
		ONCE, PERIOD
	}

	// 物品分类 幸运礼物、靓号、入场特效、会员 add GIFTANDPROP xiaozhi by 2015-07-09
	public static enum ITEM_TYPE {
		GIFT, PROP, MONEY, GAME, MASTERNO, CARD, LUCKYGIFT, NUMBERS, EFFECT, VIP, OTHERS, GIFTANDPROP, SPEAKER, FLYSCREEN, PARK, GUARD, FREEGIFT, STARCOUPON;
	};

	// 兑换类型
	public static enum EXCHANGE_TYPE {
		// 库存，实体
		PKG, ENTITY;
	};

	// 礼物参与排行状态
	public static enum RANK_STATUS {
		YES, NO;
	}

	// MONEY分类
	public static enum MONEY {
		JINBI(1, "乐币"), JINDOU(2, "金豆"), JINDOU_TICHENG(3, "家族长提成金豆"), ROOM_TICHENG(4, "房间提成");

		private final int id;
		private final String name;

		MONEY(int value, String name) {
			this.id = value;
			this.name = name;
		}

		public long getId() {
			return this.id;
		}

		public String getName() {
			return this.name;
		}
	}

	;

	// 播放器配置干预模式：
	public static enum MODE {
		NORMAL, INTERVENE
	}

	// 播放器定时获取数据状态：
	public static enum QUARTZ_DEF_TYPE {
		NO, YES
	}

	public static enum DISPLAY_STATUS {
		BLACK, RECOMMOND
	}

	/**
	 * 级别类型
	 */
	public static enum LEVEL_TYPE {
		FANLEVEL, MASTERLEVEL
	}

	/**
	 * 首页人工干预类型
	 */
	public static enum HOT_LIVE_TYPE {
		HEAD, POPULAR, STAR
	}

	/**
	 * 房间操作状态
	 */
	public static enum ROOM_LOG_STATUS {
		APPLY, ENTER, NOENTER
	}

	public static enum SPECIAL_GIFT_ID {

		GIFT01(Long.parseLong(Loader.getInstance().getProps("special.gift.xiangbin"))), GIFT02(Long.parseLong(Loader.getInstance().getProps("special.gift.jiweijiu")));

		private Long giftId;

		SPECIAL_GIFT_ID(Long giftId) {
			this.giftId = giftId;
		}

		public Long getGiftId() {
			return giftId;
		}

	}

	public static enum APP_ID_LOG {
		WEB("0", "weblive"), CLIENT("1", "live"), PC_CLIENT("2", "pclive");

		private final String code;

		private final String value;

		APP_ID_LOG(String code, String value) {
			this.code = code;
			this.value = value;
		}

		public String getCode() {
			return code;
		}

		public String getValue() {
			return value;
		}
	}

	public static Map<String, String> appIdAndValue = new HashMap<String, String>();

	static {
		for (APP_ID_LOG appid : APP_ID_LOG.values()) {
			appIdAndValue.put(appid.getCode(), appid.getValue());
		}
	}

	// 代理买卡冻结超时回收时间
	public static final Integer CARD_FREEZE_EXPIRED_TIME = 60 * 60 * 24 * 2;

	// 代理赠币业务锁key
	public static final String CARD_BUSINESS_LOCK_KEY = "cardBusinessKey";
	public static final String CHRISMAS_BUSINESS_LOCK_KEY = "chrismasBusinessKey";

	// AA注册类别
	public static final int REG_COUNT_TYPE_REG = 0;
	// AA在线用户数类别
	public static final int REG_COUNT_TYPE_USERS = 1;

	// 未登录用户推荐主播随机选取量
	public static final Integer NO_LOGIN_RANDOM_SUBRECOMMEND_NUM = 3;
	// 登录用户推荐订阅个数
	public static final Integer LOGIN_SUBRECOMMEND_NUM = 24;

	/**
	 * 用户中秋节教师节活动礼物次数的类型 送礼还是收礼
	 */
	public static enum MASTER_HD_COUNT_TYPE {
		SEND, RECEIVE
	}

	public static enum HD_TYPE {
		ZHONGQIU, JIAOSHI, GUOQING, GUANGGUN
	}

	public static enum HD_RESULT_STATUS {
		ENABLE, DISABLED
	}

	public static enum HD_RESULT_TYPE_ID {
		ZHONGQIU_SEND_CHAMPION, ZHONGQIU_RECEIVE_CHAMPION, TEACHER_SEND_CHAMPION, TEACHER_SEND_SECOND_CHAMPION, GUOQING_SEND_ONE, GUOQING_SEND_TWO, GUOQING_SEND_THREE, GUOQING_RECEIVE_ONE, GUOQING_RECEIVE_TWO, GUOQING_RECEIVE_THREE, GUANGGUN_SEND_ONE, GUANGGUN_SEND_TWO, GUANGGUN_SEND_THREE, GUANGGUN_RECEIVE_ONE, GUANGGUN_RECEIVE_TWO, GUANGGUN_RECEIVE_THREE
	}

	public static enum HD_EFFECT_TYPE {
		ENTER, ROOM, LUCKYGIFT_ROOM_FLY, LUCKYGIFT_ALL_ROOM_FLY
	}

	/**
	 * 房间推荐策略
	 */
	public static enum REC_ROOM_TYPE {
		TOP, ALL, RECOMMEND
	}

	/**
	 * 礼物分类 普通、幸运
	 */
	public static enum GIFT_TYPES {
		COMMON, LUCKYGIFT;
	}

	/**
	 * 商城-商品类型
	 */
	// public static enum GOODS_TYPES{
	// NUMBERS,EFFECT,VIP,OTHERS
	// }

	/**
	 * 商城-商品是否可以续费 不可续费，可以续费
	 */
	public static enum GOODS_CAN_RENEW {
		NO, YES
	}

	/**
	 * 商城-靓号上架状态 0=未上架，1=已上架
	 */
	public static enum GOODS_SELECT_STATES {
		UNSELECTED, SELECTED
	}

	/**
	 * 商城-靓号销售状态 0=未售，1=已售
	 */
	public static enum GOODS_SALE_STATES {
		UNSALE, SALE
	}

	/**
	 * 商城-靓号类型
	 */
	public static enum GOODS_NO_TYPE {
		FOUR, FIVE, SIX, SEVEN, EIGHT
	}

	/**
	 * 商城购买时长
	 */
	// public static enum GOODS_USETIME_TYPE{
	// WEEK1,MONTH1,MONTH2,MONTH3,MONTH6,YEAR1
	// }

	public static enum GOODS_USETIME_TYPE {
		WEEK1(7, "7天"), HALFMONTH(15, "半个月"), MONTH1(30, "一个月"), MONTH2(60, "两个月"), MONTH3(90, "三个月"), MONTH6(180, "六个月"), YEAR1(365, "一年"), ZERO(0, "瞬时");

		private Integer days;
		private String comment;

		GOODS_USETIME_TYPE(Integer days, String comment) {
			this.days = days;
			this.comment = comment;
		}

		public Integer getDays() {
			return days;
		}

		public String getComment() {
			return comment;
		}
	}

	public static enum MALL_RESULT {
		SUCCESS("000000", "成功"), FAILED_PARAM("401001", "参数错误"), FAILED_BALACE("000002", "对不起，您的余额不足，请先充值"), FAILED_LEVEL("000003", "对不起，您的财富等级不足"), UN_LOGIN("000004", "您处于未登录状态"), FAILED_SYSTEM("000005", "对不起，系统繁忙，稍后再试"), FAILED_TRADE_SYSTEM("000006", "对不起，消费系统繁忙，稍后再试"), FAILD_NO_NOT_EXIST("000007", "对不起，该靓号已售出"), FAILED_VIP_TOMASTER("000008", "对不起，被赠送用户的VIP等级不足"), FAILED_LEVEL_TOMASTER("000009", "对不起，被赠送用户的财富等级不足"), FAILD_NULL_TOMASTER("000010", "被赠送人不存在"), FAILD_GOODS_OFF("000011", "商品已下架"), FAILED_VIP("000012", "对不起，您的VIP等级不足");
		private String code;
		private String msg;

		MALL_RESULT(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	/**
	 * 商城购买类型 首购、续费
	 */
	public static enum GOODS_BUY_TYPE {
		FIRST, RENEW
	}

	/**
	 * 商城-会员类型
	 */
	public static enum GOODS_VIP_TYPES {
		ALL, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
	}

	/**
	 * 商城 下架，上架
	 */
	public static enum GOODS_STATUS {
		OFF, ON
	}

	/**
	 * 商城 未使用，使用中，不显示使用状态
	 */
	public static enum GOODS_USE_STATUS {
		NO, YES, NONE
	}

	/**
	 * 商城-展示图标 (无,热销,新品,打折)
	 */
	public static enum SHOW_ICON {
		NULL, HOT, NEW, SALE
	}

	/**
	 * 商城-是否即将过期 (非、是、已过期)
	 */
	public static enum EXPIRE_SOON {
		NO, YES, EXPIRED
	}

	/**
	 * 客户端bug类型 视频问题、软件bug、卡顿问题
	 */
	public static enum CLIENT_FEED_TYPE {
		VIDEO, SOFTBUG, SLOWLY
	}

	/**
	 * 客户端操作系统 XP、win7、win8、win10、mac
	 */
	public static enum CLIENT_OPERATE_SYSTEM {
		WIN_XP, WIN7, WIN8, WIN10, MAC
	}

	/**
	 * 版本更新状态 不可用、激活的
	 */
	public static enum VERSION_STATUS {
		DISABLED, ENABLED
	}

	/**
	 * 导量状态 不可用、激活的
	 */
	public static enum SPREAD_STATUS {
		DISABLED, ENABLED
	}

	/**
	 * 导量类型 安装完成、卸载完成、退出、设首
	 */
	public static enum SPREAD_TYPE {
		INSTALL_FINISH, UNLOAD_FINISH, QUIT, HOME_PAGE
	}

	/**
	 * 推荐位状态 不可用、激活的
	 */
	public static enum COMMEND_STATUS {
		DISABLED, ENABLED
	}

	/**
	 * 推荐类型 小游戏、广告
	 */
	public static enum COMMEND_TYPE {
		MINI_GAME, ADVERT
	}

	// 大烟花礼物id
	public static final long LARGE_FIREWORK = 7402L;
	// 小烟花礼物id
	public static final long SMALL_FIREWORK = 7403L;

	/**
	 * 
	 * 主播状态：0-解约，1-签约，2-停权
	 * 
	 * @author chentao
	 * 
	 */
	public static enum ANCHOR_STATUS {
		BREAK_OFF, SIGNED, PRISON
	}

	/**
	 * 主播类型：0-专业主播，1-玩家主播 2-游戏主播 3-娱乐主播 4-扩展 5-拓展2(2/3/4/5针对APP来说的)
	 * 
	 * @author chentao
	 * 
	 */
	// modify by xzz 2018/09/13
	public static enum ANCHOR_TYPE {
		PROFESSINAL, GAMER, APPGAME, APPAMUSEMENT, APPEXTEND, APPEXTEND2
	}

	/**
	 * 天龙角色状态：0-正常，1-角色停权，2-账号停权 3-畅游+停权
	 * 
	 * @author chentao
	 * 
	 */
	public static enum TLBB_ROLE_STATUS {
		NORMAL, ROLE_FORBIDDEN, MASTER_FORBIDDEN, CHANGYOU_FORBIDDEN
	}

	/**
	 * 礼物分类是否默认显示：0-不显示，1-显示
	 * 
	 * @author chentao
	 * 
	 */
	public static enum GIFT_TYPE_DEFAULT_SHOW {
		DISABLED, ENABLED
	}

	/**
	 * 礼物分类是否默认显示：0-下架，1-上架
	 * 
	 * @author chentao
	 * 
	 */
	public static enum MALL_CATEGORY_STATUS {
		DISABLED, ENABLED
	}

	/**
	 * 公告链接类型：0-无链接，1-URL地址，2-主播id
	 * 
	 * @author chentao
	 * 
	 */
	public static enum CHATNOTICE_LINK_TYPE {
		NONE, URL, ANCHOR_ID
	}

	/**
	 * 投放时间类型：0-发布后立即投放，1-指定时间
	 * 
	 * @author chentao
	 * 
	 */
	public static enum CHATNOTICE_STARTTIME_TYPE {
		IMMEDIATELY, APPOINT
	}

	/**
	 * 商品标签：0-无标签，1-新品，2-热门，3-促销
	 * 
	 * @author chentao
	 * 
	 */
	public static enum GOODS_LABEL {
		NONE, NEW, HOT, PROMOTION
	}

	/**
	 * 商城一级分类是否默认显示：0-不显示，1-显示
	 * 
	 * @author chentao
	 * 
	 */
	public static enum MALL_FIRST_CATEGORY_DEFAULT_SHOW {
		DISABLED, ENABLED
	}

	/**
	 * 通栏url类型:0-url,1-主播id
	 * 
	 * @author chentao
	 * 
	 */
	public static enum BANNER_URL_TYPE {
		URL, ANCHOR_ID
	}

	/**
	 * 通栏类型:0-原顶部通栏,1-焦点图,2-广告位
	 * 
	 * @author chentao
	 * 
	 */
	public static enum BANNER_TYPE {
		TOP, FOCUS, ADVERTISE
	}

	/**
	 * 直播类型：歌手、萌妹、搞笑、性感、帅气
	 * 
	 * @author chentao
	 * 
	 */
	public static enum LIVE_TYPE {
		GESHOU, MENGMEI, GAOXIAO, XINGGAN, SHUAIQI
	}

	/**
	 * 主播招募申请状态：0-待审核，1-审核通过，2-审核未通过
	 * 
	 * @author ChenTao
	 * 
	 */
	public static enum ANCHOR_APPLY_STATUS {
		UNAUDIT, SUCCESS, FAILURE
	}

	/**
	 * 主播招募申请类型：0-主播申请，1-后台添加
	 * 
	 * @author ChenTao
	 * 
	 */
	public static enum ANCHOR_APPLY_TYPE {
		FROM_ANCHOR, FROM_MANAGER
	}

	/**
	 * 支出类型：2-提元宝，3-罚款，4-提现金
	 * 
	 * @author chentao
	 * 
	 */
	public static enum PAY_TYPE {
		DRAW_YUANBAO(2), PENALTY(3), DRAW_CASH(4);

		private final int value;

		PAY_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
	 * 关注类型：0-订阅，1-退订
	 * 
	 * @author xiaozhi
	 * 
	 */
	public static enum SUB_TYPE {
		SUB, UNSUB
	}

	public static enum MATERIAL_TYPE {
		SWF, PNG, GIF, JPG, JPEG
	}

	public static enum LUCK_DRAW_TYPE {
		SUPER_HIGH, HIGH, NORMAL, LOWER
	}

	// 天龙VIP等级
	public static int[] tlbbVipLevel = new int[] { 0, 4000, 8000, 20000, 40000, 120000, 200000, 400000, 800000, 2400000 };

	/** 守护开通类型，按照月，年 */
	public static enum GUARD_BUY_TYPE {
		MONTH, YEAR;
	}

	public static enum GUARD_TYPE {
		KNIGHT, PRINCE;
	}

	// modify by xzz 2018/10/10
	public static enum GUARD_PROPERTY {
		PRICE_MONTH("priceMonth"), PRICE_YEAR("priceYear"), POINT_MONTH("pointMonth"), POINT_YEAR("pointYear"), CUS_MONEY_MONTH("cusMoneyMonth"), CUS_MONEY_YEAR("cusMoneyYear"), CUS_FAVOR_MONTH("cusFavorMonth"), CUS_FAVOR_YEAR("cusFavorYear"), EXPERIENCE_MONTH("experienceMonth"), EXPERIENCE_YEAR("experienceYear"), STARJEWEL_MONTH("starjewelMonth"), STARJEWEL_YEAR("starjewelYear");
		GUARD_PROPERTY(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}
	}

	public static enum PROP_ITEM_LIMIT {
		ALL, KNIGHT, PRINCE;
	}

	// 游戏类型 3-天龙、4-蛮荒
	public static enum GAME_TYPE {
		TL(3), MH(4);

		private final int value;

		GAME_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public static enum ACTIVITY_NAME {
		DEAMON_ACTIVTIY(""), SIGN_ACTIVITY("签到活动"), SIGN_RANK_SHOW("签到活动榜单展示"), GODNESS_RANK("我是女神排行榜"), POPULARITY_STAR_RANK("人气之星排行榜"), SPRING_BUMP_LOVE_ACTIVITY("新春撞上爱榜单"), SPRING_BUMP_LOVE_SCORE("新春撞上爱积分"), WINNER_ACTIVITY("天下会武"), WINNER_RANK_SHOW("天下会武活动榜单展示"), QUIZ_ACTIVITY("竞猜活动"), CAKE_ACT("蛋糕活动"), CAKE_RANK_ACT("蛋糕排行榜"), QIXI_ACT("七夕活动"), QIXI_RANK_ACT("七夕排行榜"), WINNER_DRAW_ACTIVITY("天下会武-抽奖乐翻天"), WINNER_DRAW_RANK_SHOW("抽奖乐翻天榜单")
		, LOVE_ACT("520活动"), LOVE_RANK_ACT("520排行榜"), WINNER_ACTIVITY_NEW("新天下会武"), WINNER_RANK_SHOW_NEW("新天下会武榜单");

		private final String name;

		private ACTIVITY_NAME(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public static enum APP_ACTIVITY_INDEX_URL {
		SELECTED_HOST("精选主播", "appRoomList.action?order=7"), ALL_HOST("全部主播", "appRoomList.action?order=1"), MY_ATTENTION("我的关注", "appSub_queryAll.action"), WINNER_ACTIVITY(ACTIVITY_NAME.WINNER_ACTIVITY.getName(), "appGetWinnerList.action");

		private final String name;
		private final String url;

		private APP_ACTIVITY_INDEX_URL(String name, String url) {
			this.name = name;
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public String getUrl() {
			return url;
		}
	}

	public enum QuizTypeEnum {
		NORMAL_QUIZ(1), OFFICIAL_CHOICE(2);

		private final Integer name;

		private QuizTypeEnum(Integer name) {
			this.name = name;
		}

		public Integer getName() {
			return name;
		}
	}

	public enum QuizIsTimeLimitEnum {
		YES(0), NO(1);

		private final Integer name;

		private QuizIsTimeLimitEnum(Integer name) {
			this.name = name;
		}

		public Integer getName() {
			return name;
		}
	}

	public enum QuizStatesEnum {
		RUNNING(0), SEAL(1), END(2), CANCEL(3);

		private final Integer name;

		private QuizStatesEnum(Integer name) {
			this.name = name;
		}

		public Integer getName() {
			return name;
		}
	}

	public enum QuizStatesOption {
		A(1), B(2), C(3);

		private final Integer name;

		private QuizStatesOption(Integer name) {
			this.name = name;
		}

		public Integer getName() {
			return name;
		}
	}

	public enum QuizDetailStatesEnum {
		/**
		 * right
		 */
		RIGHT(0),
		/**
		 * wrong
		 */
		WRONG(1),
		/**
		 * cancel
		 */
		CANCEL(2),

		/* choice */
		CHOICE(3);

		private final Integer name;

		private QuizDetailStatesEnum(Integer name) {
			this.name = name;
		}

		public Integer getName() {
			return name;
		}
	}

	// 签到活动分页大小
	public static final Integer SIGN_RANK_LIMIT = 30;

	/**
	 * pk倒计时
	 */
	public static int COUNT_DOWN = 5;

	/**
	 * PK进度类型（对标TCP协议）
	 * 
	 * @author wangbingwu
	 *
	 */
	public static enum PK_TIME_TYPE {
		PK_WILL_START(1), PK_START(2), PK_MID(3), PK_WILL_END(4), PK_END(5);
		int id;

		PK_TIME_TYPE(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}

	};

	/**
	 * PK 进度类型（对标数据库state字段）
	 * 
	 * @author wangbingwu
	 *
	 */
	public static enum PK_STATE_TYPE {
		PK_WILL_START(0), PK_START(1), PK_END(2);
		int id;

		PK_STATE_TYPE(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}

	};

	/**
	 * Pk 结果对标TCP协议） 1-胜/2-负/3-平
	 * 
	 * @author wangbingwu
	 *
	 */
	public static enum PK_RESULT_TYPE {
		WINNER(1), FAILED(2), DRAW(3);
		int id;

		PK_RESULT_TYPE(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}

	};

	public static enum ANCHOR_TAG {
		WINNING(6);
		int id;

		private ANCHOR_TAG(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}

	}

	public static enum TEMP_TASK {
		// 争霸赛
		WINNING(1);
		int id;

		private TEMP_TASK(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}

		public static TEMP_TASK getTask(int id) {
			for (TEMP_TASK t : TEMP_TASK.values()) {
				if (t.id == id) {
					return t;
				}
			}
			return null;
		}
	}

	/**
	 * 战队目前状态 0 比赛中 1 32强 2 16强 3 8强 4 4强 5 季军 6 亚军 7 冠军
	 */
	public static enum CYOU_TL_TEAM_STATES {
		DOING, STRONG32, STRONG16, STRONG8, STRONG4, STRONG3, STRONG2, STRONG1
	}

	public static enum APP_CORNER_INDEX_URL {
		NONE("", ""), QUIZ("兑换奖品", "appGuessExchange.action");

		private final String name;
		private final String url;

		private APP_CORNER_INDEX_URL(String name, String url) {
			this.name = name;
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public String getUrl() {
			return url;
		}
	}

	/*
	 * 玩家类型
	 */
	public static enum CYOU_PERSON_TYPE {
		TL, NT
	}

	/**
	 * 日常任务状态
	 * 
	 * @author gaojianping
	 */
	public static enum CXG_CODE_TASK_STATUS {
		DOING(1), FINISH(2), RECEIVED(3);
		int id;

		CXG_CODE_TASK_STATUS(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}
	};

	public static final Integer NatureVIP = 0;

	public static enum NORMAL_RESULT {
		SUCCESS_0("操作成功"), FAILED_1("系统错误"), PARAMLOST_2("参数丢失"), NAMEUPPERLIMIT_3("昵称修改次数已达上限"), ADDFAILED_4("增加失败"), DELFAILED_5("删除失败"), MODIFYFAILED_6("修改失败"), NOTONLY_7("您申请的名称已被使用，请更换"), NOTEXIST_8("用户信息不存在"), BANROLE_9("您的角色被封禁"), BANCN_10("您的账号被禁"), HASSENSTIVE_11("您的昵称中含有敏感词信息"), NOLOGIN_12("用户未登录"), STREAMNOTIN_13("房主并未直播"), MASTERCLOSEMIC_14("主播已关闭连麦功能"), AUDIENCEBANCHAT_15("您已被禁言"), AUDIENCEBANBLACK_16("您已被拉黑"), MASTERISONMIC_17("当前主播正在与其他主播连麦"), MASTERISONMIC_18("麦上人数已达上限"), AUDIENCEBANCHATED_19("对方已被禁言"), AUDIENCEBANBLACKED_20("对方已被拉黑"), NOTLOGIN_21("当前登录已失效，请重新登录!"), NOPOWER_22("抱歉你并没有权限执行此操作"), ROOMNOTEXIST_23("房间不存在"), AUDIENCENOTIN_24("对方已经离开本房间"), AUDIENCEWAITHANDLE_25("等待玩家处理中， 请稍后再试"), AUDIENCEOVERCOUNT_26("今日连麦次数已用尽"), AUDIENCEXIST_27("您已在主播的连麦申请列表中"), AUDIENCENOWNERCANNOT_28(
				"主播暂时无法接受连麦"), AUDIENCECHANNELERROR_29("创建频道失败"), AUDIENCEMICACTIVITYCLOSE_30("观众连麦活动暂时关闭，请您谅解"), AUDIENCEMICMASTERCLOSE_31("主播已关播，暂时无法向主播申请"), AUDIENCEAPPCANNOT_32("您好主播使用移动开播， 暂不支持连麦功能"), AUDIENCEAPPCANNOT_33("对方客户端版本，暂不支持连麦"), AUDIENCEMASTEREXPIRE_34("抱歉, 主播邀请已过期"), OPREATORVERSIONLOW_35("您无法对该用户操作， 请升级APP版本后重试。"), AUDIENCEMICCLOSE_36("无可关闭的连麦，连麦已关闭"), NATRUE_NAME_SIZE_OVER_37("您的名字过长，请重试"), NATRUE_NAME_CONTAINS_SPECIAL_38("您的名字中包含特殊字符。"), AUDIENCEONMIC_39("您正在连麦， 暂不能进行申请"), AUDIENCEMICISRUNNING_40("对方正在连麦中，请稍后再邀请!"), AUDIENCEMICISRUNNING_41("您正在连麦， 暂不能同意"), REBANCHAT_ERORR_42("该用户已被禁言"), NOT_SHARE_ROOM_43("非官方直播间"),
		PARAMERROR_44("真爱团昵称不可有特殊字符"), RUNNINGLOW_45("余额不足"), NAMEREPEAT_46("真爱团昵称已存在"), NAMEILLEGLE_47("真爱团昵称存在敏感词汇");

		private final String value;

		NORMAL_RESULT(String value) {
			this.value = value;
		}

		public String val() {
			return this.value;
		}

		public Integer code() {
			return Integer.parseInt(this.toString().split("_")[1]);
		}
	}

	// 活动预约:活动状态
	public static enum RESERVATION_STATUS {
		READY, RUNNING, END;
	}

	// 活动预约:预约，取消预约
	public static enum RESERVATION_OPT {
		OPEN, END;
	}

	// 直播质量监控key前缀
	public static final String PREFIX_KEY = "MONITOR_PREFIX_KEY_";

	// 直播质量监控异常状态
	public static enum LIVE_MONITOR_STATUS {
		ERROR, OK;
	}

	public static enum AUDIENCE_MIC_STATUS {
		RUNING, STOP, PARTJOINERROR
	}

	public static final int ANCHOR_REQ_TIMEOUT_REDIS = 40;
	public static final int ANCHOR_MIC_LOCK = 20;

	// 观众端连麦角色身份。
	public static enum AUDIENCE_MIC_ROLE {
		MASTER, ADMIN, USER
	}

	public static final String SUFFIX_LIVE_UPDATE_STATUS_AUDIENCEMIC = "_4";

	// 观众端连麦角色身份。
	public static enum CLEAR_APPLIES_TYPE {
		PERSON, ALL
	}

	// 彩色弹幕
	public static enum BARRAGE_COLOR {
		BARRAGE_COLOR_RED(1, "#FF2929"), BARRAGE_COLOR_YELLOW(2, "#FFD327"), BARRAGE_COLOR_GREEN(3, "#4FB41B");
		int type;
		String color;

		BARRAGE_COLOR(int type, String color) {
			this.type = type;
			this.color = color;
		}

		public Integer getType() {
			return this.type;
		}

		public String getColor() {
			return this.color;
		}
	}

	public static enum WEALTH_PROP {
		WEALTH_PROP_EMOJI_MBZ(2,"萌宝猪"),//萌萌猪
		WEALTH_PROP_EMOJI_DDJ(3,"蛋蛋鸡"),//蛋蛋鸡

		WEALTH_PROP_FLY_SCREEN_COMMON(0,"普通飞屏"),//普通飞屏
		WEALTH_PROP_FLY_SCREEN_COLORFUL(1,"炫彩飞屏"),//炫彩飞屏
		WEALTH_PROP_FLY_SCREEN_LUXURY(2,"豪华飞屏"),//豪华飞屏
		WEALTH_PROP_FLY_SCREEN_KNIGHT(3,"骑士飞屏"),//豪华飞屏

		WEALTH_PROP_FLY_SCREEN_COLOR_COMMON(0,"普通"),//普通弹幕
		WEALTH_PROP_FLY_SCREEN_COLOR_RED(1,"红"),//彩色弹幕
		WEALTH_PROP_FLY_SCREEN_COLOR_YELLOW(2,"黄"),
		WEALTH_PROP_FLY_SCREEN_COLOR_GREEN(3,"绿");
		int type;
		String name;

		WEALTH_PROP(int type, String name){
			this.type = type;
			this.name = name;
		}


		public Integer getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}

	}


	// 彩色弹幕渐变颜色
	public static enum BARRAGE_NEAR_COLOR {
		BARRAGE_NEAR_COLOR_RED(1, "#FF3E73,#FF1D1D"), BARRAGE_NEAR_COLOR_YELLOW(2, "#FFBD3E,#FF8F1D"), BARRAGE_NEAR_COLOR_GREEN(3, "#9DFF1D,#72CE03");
		int type;
		String color;

		BARRAGE_NEAR_COLOR(int type, String color) {
			this.type = type;
			this.color = color;
		}

		public Integer getType() {
			return this.type;
		}

		public String getColor() {
			return this.color;
		}
	}

	// 彩色弹幕渐变颜色
	public static enum BARRAGE_COLOR_NAME {
		BARRAGE_RED(1, "红"), BARRAGE_YELLOW(2, "黄"), BARRAGE_GREEN(3, "绿");
		int type;
		String name;

		BARRAGE_COLOR_NAME(int type, String name) {
			this.type = type;
			this.name = name;
		}

		public Integer getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}
	}

	public static enum APP_VERSION_UP {
		AUDIENCMIC(6), NT(7);
		int id;

		APP_VERSION_UP(int id) {
			this.id = id;
		}

		public int id() {
			return this.id;
		}
	};

	public static final String NAME_NO_USE = "@#%&+\\=?/{}[] ";

	public static final String CYOUZONEWORLD = "畅游＋";

	// 大转盘抽奖次数 获取来源
	public static enum LOTTERY_SOURCE_TYPE {
		// 观看，答题
		VIDEO, ANSWER6, ANSWER9, ANSWER12;
	}

	//魔盒礼物
	public static final String RANDOM_GIFT_KEY = "RANDOM_GIFT_KEY_";
	
	// 大转盘抽奖 redis key前缀
	public static final String LOTTERY_PREFIX_KEY = "LOTTERY_PREFIX_KEY";
	// 进行抽奖前完成的任务
	public static final String LOTTERY_TASK_KEY = "LOTTERY_TASK_KEY";
	// 中奖名单
	public static final String LOTTERY_DETAIL_KEY = "LOTTERY_DETAIL_KEY";
	// 我的中奖名单
	public static final String LOTTERY_MY_DETAIL_KEY = "LOTTERY_MY_DETAIL_KEY";
	// 奖品抽中数量
	public static final String LOTTERY_AWARD_KEY = "LOTTERY_AWARD_KEY";
	// 大转盘假人中奖
	public static final String LOTTERY_ROBOT_KEY = "LOTTERY_ROBOT_KEY";
	// 答题抽奖紧急关停
	public static final String ANSWER_LOTTERY_KEY_SOS = "ANSWER_LOTTERY_KEY_SOS";
	//抽奖答题配置-list
	public static final String LOTTERY_AWARDS_CONFIG_lIST_KEY = "LOTTERY_AWARDS_CONFIG_lIST_KEY";
	//奖品数量
	public static final String LOTTERY_AWARDS_CONFIG_AWARDS_ID_KEY = "LOTTERY_AWARDS_CONFIG_AWARDS_ID_KEY_";
	//领取星钻
	public static final String ANSWER_RECEIVE_XZCODE_KEY = "ANSWER_RECEIVE_XZCODE_KEY_";
	public static final String ANSWER_RECEIVE_XZCODE_NX_KEY = "ANSWER_RECEIVE_XZCODE_NX_KEY_";
	// 直播间假人清理任务
	public static final String TASK_DELETE_ROBOT_ROOMID_KEY = "TASK_DELETE_ROBOT_ROOMID_KEY";

	// 秀场卡顿人数
	public static final String CYOU_UPLOAD_ERROR_TYPE_9_ONLINEUSER_KEY = "CYOU_UPLOAD_ERROR_TYPE_9_ONLINEUSER_KEY";

	public static final Integer LIGNT_OF_PERSON = 3;
	
	//年度盛典 徽章 added by xzz 2019/11/13
	public static final Integer YEAR_CEREMONY_BADGE = 4;
	//2020情人节 徽章 added by xzz 2019/12/18
	public static final Integer LOVE_CEREMONY_BADGE = 5;

	// 监控观众连麦数据
	// app端建立连麦的
	public static final String CYOU_AUDIENCE_MIC_REPLY_INVITE_APP_KEY = "CYOU_AUDIENCE_MIC_REPLY_INVITE_APP_KEY";
	// pc端建立连麦的
	public static final String CYOU_AUDIENCE_MIC_REPLY_INVITE_PC_KEY = "CYOU_AUDIENCE_MIC_REPLY_INVITE_PC_KEY";
	// pc端申请连麦的
	public static final String CYOU_AUDIENCE_MIC_APPLY_PC_KEY = "CYOU_AUDIENCE_MIC_APPLY_PC_KEY";
	// app端申请连麦的
	public static final String CYOU_AUDIENCE_MIC_APPLY_APP_KEY = "CYOU_AUDIENCE_MIC_APPLY_APP_KEY";
	//视频管理-token
	public static final String VIDEO_LOGIN_TOKEN_KEY = "VIDEO_LOGIN_TOKEN_KEY_";

	// 假人发言
	// 假人拟真，消息发布频道
	public static final String CHANNEL_ROBOTCHAT_MONITOR_MESSAGE = "CHANNEL_ROBOTCHAT_MONITOR_MESSAGE";
	public static final String CHANNEL_PALMS18_MONITOR_MESSAGE_SENDCHAT="CHANNEL_PALMS18_MONITOR_MESSAGE_SENDCHAT";

	// Web着陆页 推荐位个数
	public static final int WEB_LANDING_PAGE_RECOMMEND_CT = 5;


	// 流错误信息分类
	public static enum STREAM_ERROR_TYPE {
		// （5-网宿/10-内网自测/14-迅雷测试/15-乐视/16-阿里云/17-腾讯云/18-金山云）
		SERVICE(0, "服务端"), HELPER(1, "助手"), MOBILE(2, "手机"), SHENWANG(3, "声网"), WANGSHU(5, "网宿"), NEIWANG(10, "内网自测"), XUNLEI(14, "迅雷测试"), LESHI(15, "乐视"), ALI(16, "阿里云"), QQ(17, "腾讯云"), JIGNSHAN(18, "金山云"), WEIZHI(-1, "根据流核对");
		int id;
		String name;

		private STREAM_ERROR_TYPE(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public static STREAM_ERROR_TYPE getTask(int id) {
			for (STREAM_ERROR_TYPE t : STREAM_ERROR_TYPE.values()) {
				if (t.id == id) {
					return t;
				}
			}
			return WEIZHI;
		}

		public static STREAM_ERROR_TYPE getTask(String idStr) {
			if (StringUtils.isBlank(idStr)) {
				return WEIZHI;
			}
			int id = Integer.parseInt(idStr);
			for (STREAM_ERROR_TYPE t : STREAM_ERROR_TYPE.values()) {
				if (t.id == id) {
					return t;
				}
			}
			return WEIZHI;
		}
	}

	// 偷塔消息类型(周/月榜争夺战) added by xzz 2019/07/30
	public static enum STEALINT_TOWER_MESSAGE {
		DIFFERENCE(1, "差额类"), STAGE(2, "阶段类"), CUTDOWN(3, "倒计时类"), RESULT(4, "结果类");
		int id;
		String name;

		private STEALINT_TOWER_MESSAGE(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}

	// 偷塔阶段(周/月榜争夺战) added by xzz 2019/07/30
	public static enum STEALINT_TOWER_STAGE {
		CUTDOWN(0, "倒计时阶段"), BULL(1, "斗牛阶段"), PUBLICITY(2, "公示阶段"), RESULT(3, "结束");
		int id;
		String name;

		private STEALINT_TOWER_STAGE(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
	
	/**
	 * 积分商城 商品购买时长
	 *
	 */
	public static enum INTEGRAL_GOODS_USETIME_TYPE {
		DAY1(1,"1天"),WEEK1(7, "7天"), HALFMONTH(15, "半个月"), MONTH1(30, "一个月"), MONTH2(60, "两个月"), MONTH3(90, "三个月"), MONTH6(180, "六个月"), YEAR1(365, "一年");

		private Integer days;
		private String comment;

		INTEGRAL_GOODS_USETIME_TYPE(Integer days, String comment) {
			this.days = days;
			this.comment = comment;
		}

		public Integer getDays() {
			return days;
		}

		public String getComment() {
			return comment;
		}
	}
	
	/**
	 * 畅秀阁语料库类型
	 */
	public static enum CYOU_CORPUS_TYPE{
		CORPUS_PALMS18(1,"降龙十八掌"),CORPUS_CHAT_NOCHAT(0*100+0*10,"假人拟真-无人发言"),
		CORPUS_CHAT_HAVECHAT(0*100+1*10,"假人拟真-有人发言"),CORPUS_CHAT_HAVECHAT_ONE(0*100+2*10,"假人拟真-有人发言-相同"),CORPUS_CONSUME(1*100+0*10,"假人拟真-消费类发言"),CORPUS_SUB(2*100+0*10,"关注-代发私聊"),CORPUS_GIVEUP(2*100+1*10,"关注-回复私聊：算了");
		private Integer type;
		private String comment;
		CYOU_CORPUS_TYPE(Integer type,String comment){
			this.type=type;
			this.comment=comment;
		}
		public Integer getType() {
			return type;
		}
		public String getComment() {
			return comment;
		}
		
	}
	
	/**
	 * 积分商城：降龙十八掌奖池任务玩法类型
	 *
	 */
	public static enum INTEGRAL_MALL_PALMS18_AWARDSTASK_TYPE{
		FREEPOINT,REMAIN_FREEPOINT;
	}
	
	/***
	 * 假人拟真-任务计划状态
	 */
	public static enum ROBOT_AI_PLAN_STATUS{
		ON,OFF;
	}
	
	/**
	 * 假人拟真-任务类型-主要类型
	 *
	 */
	public static enum ROBOT_AI_PALN_MAJOR_TYPE{
		//0-发言类，1-消费类
		CHAT,CONSUME;
	}
	
	/**
	 * 假人拟真-任务类型-次要类型
	 *
	 */
	public static enum ROBOT_AI_PALN_MINOR_TYPE{
		//0-无人发言，1-有人发言 ，2-有人重复发言
		NOCHAT,HAVECHAT,HAVECHAT_ONE
	}
	
	/**
	 * 
	 *消息同步时的，频道类型（1-增加任务计划 ，2-更新任务计划 ，3-删除任务计划 ，4-下线任务计划 ，5-送礼消费）
	 */
	public static enum CHANNEL_ROBOTCHAT_MONITOR_MESSAGE_TYPE{
		ROBOT_TYPE_PLAN_SAVE(1),ROBOT_TYPE_PLAN_UPDATE(2),ROBOT_TYPE_PLAN_DEL(3),ROBOT_TYPE_PLAN_OFFLINE(4),ROBOT_TYPE_CONSUME(5);
		int type=0;
		 CHANNEL_ROBOTCHAT_MONITOR_MESSAGE_TYPE(int type){
			this.type=type;
		}
		public int getType() {
			return type;
		}
	}
	
	public static enum ACTIVITY_NAME_1{
		情人节("t_rank_key_qrj");
		String type="";
		ACTIVITY_NAME_1(String type){
			this.type=type;
		}
		public String getType() {
			return type;
		}
	}
	public static void main(String[] args) {
		System.out.println(ACTIVITY_NAME_1.情人节.getType());
	}
	
	public static final String NO_ACOUNT_COOKIE = "uid-cxg-random";

	public static final int NON_ACCOUNT_OUT_LINK = 2;
	public static final int NON_ACCOUNT_H5 = 1;
	public static final int NON_ACCOUNT_WEB = 0;
	public static final int NON_ACCOUNT_WEB_OLD = 3;
	
	public static final int MAX_I_CAN_HAVE = 5;//20;

	public static final Integer MAX_I_CAN_HAVE_MIC = 11;//50000;
	public static final Long[] TAG_OLD_TL = new Long[] {308L,309L,3010L};
	public static final String TAG_OLD_TL_STR = "308,309,3010";
	
	public static enum GAME_ID_ENUM{
		JINDIAN(1),OLD(3);
		int id=0;
		GAME_ID_ENUM(int id){
			this.id=id;
		}
		public int id() {
			return id;
		}
	}

	/**
	 * 个性礼物配置图形的状态 0:下架 1:上架
	 */
	public static enum SELFDOMGIFTCONFIG_STATUS {
		DISABLE, ENABLE
	}

	/**
	 * 植树节活动所需常量配置
	 */
	public static final Long TREE_ACTIVITY_ID = 1L;//植树节活动配置id
	public static final int TREE_TASK_INTERVAL = 180;//植树任务间隔为3分钟
	public static final String TREE_TASK_SECONDS = "tree_task_seconds_";//植树任务初始化时间(不同的任务类型有不同的初始化时间)
	public static final String CYOUTREE_ID = "cyoutree_id";//存在redis中的用户树id对应的key
	public static final int TREE_REDIS_EXPIRE_TIME = 3600*24;//植树节活动redis中数据的过期时间
	public static final int TREE_GARDENER_EXECUTE_TIME = 15;//小园丁正在执行的任务锁的过期时间
	public static final String LOOKED_GROWTH_VALUE = "looked_growth_value";//用户当天已查看过的成长值数
	public static final String LOOKED_INTERACT_VALUE = "looked_interact_value";//用户当天已查看过的银锭数
	public static final String LOOKED_GOLD_VALUE = "looked_gold_value";//用户当天已查看过的金锭数
	public static final int TREE_ACTIVITY_DELAY_TIME = 3600*24*3;//植树节活动结束后一周内,仍可进入植树页面
	public static final int TREE_GROWTH_CHARM_EXCHANGE = 10;//成长值和魅力值的兑换关系 1成长值=10魅力值
	public static final int TREE_GROWTH_ZERO = 10;//可获得银锭的成长值临界值
	public static final int TREE_GROWTH_FIRST = 30;//可获得金锭的成长值第一阶段临界值
	public static final int TREE_GROWTH_SECOND = 50;//可获得金锭的成长值第二阶段临界值
	public static final int TREE_GROWTH_THIRD = 75;//可获得金锭的成长值第三阶段临界值
	public static final int TREE_GROWTH_FOUR = 100;//可获得金锭的成长值第四阶段临界值
	public static final String TREE_REDIS_AWARD_NUM_KEY = "tree_award_num_key_";//植树节抽奖奖品已被抽中的数量(存在redis中的key前缀)
	public static final String TREE_REDIS_AWARD_LIST = "tree_redis_award_list";//植树节活动抽奖奖品列表(存在redis中的key)
	public static final int TREE_EFFECT_FRAGMENT_EXCHANGE_NUM = 20;//代表20个入场特效碎片兑换一个入场特效
	public static final String TREE_EFFECT_FRAGMENT_GOLDTREE_NAME = "摇钱树";//摇钱树入场特效名称
	public static final int TREE_CHAT_BACKGROUND_ID = 1;//植树节活动聊天背景id
	public static final int TREE_BADGE_ID = 1;//植树节活动元宝徽章id
	public static final int TREE_TAILLIGHT_ID = 9;//植树节活动招财进宝尾灯id
	/**
	 * 植树任务类型可完成的限制次数
	 */
	public static enum TREE_TASKTYPE_LIMIT{
		WATERING_TIMES("watering",5),
		FERTILIZE_TIMES("fertilize",30),
		TRIM_TIMES("trim",30),
		DISINSECT_TIMES("disinsect",30);

		private final String sign;
		private final Integer times;

		TREE_TASKTYPE_LIMIT(String sign, Integer times) {
			this.sign = sign;
			this.times = times;
		}

		public String getSign() {
			return sign;
		}

		public Integer getTimes() {
			return times;
		}

		public static Integer getTimes(String sign){
			TREE_TASKTYPE_LIMIT[] values = TREE_TASKTYPE_LIMIT.values();
			for (TREE_TASKTYPE_LIMIT value : values) {
				if(value.getSign().equals(sign)){
					return value.getTimes();
				}
			}
			return WATERING_TIMES.getTimes();
		}
	}

	public static enum TREE_ACTIVITY_STATUS{//植树节活动通用状态 代表'否'/'是'两种状态
		DISABLE, ENABLE
	}
	public static enum TREE_GARDENER_EMPLOY_STATUS{//小园丁雇佣状态
		NO, YES
	}
	public static enum TREE_ACTIVITY_TCP_TYPE{//植树节活动TCP类型 有新任务tcp,任务完成tcp
		NEW_TASK,FINISH_TASK
	}

	/**
	 * 植树节活动所需付费物品 浇水,施肥,修剪,除虫,小园丁  注意:value值最好与t_mall_goods中的任一Fid不同
	 */
	public static enum TREE_ACTIVITY_ITEM {
		WATERING_ITEM(90000001L,"浇水","watering"),//浇水
		FERTILIZE_ITEM(90000002L,"施肥","fertilize"),//施肥
		TRIM_ITEM(90000003L,"修剪","trim"),//修剪
		DISINSECT_ITEM(90000004L,"除虫","disinsect"),//除虫
		GARDENER_ITEM(90000006L,"小园丁","gardener")//小园丁

		;

		private final Long key;
		private final String name;
		private final String sign;

		TREE_ACTIVITY_ITEM(Long key, String name, String sign) {
			this.key = key;
			this.name = name;
			this.sign = sign;
		}

		public Long getKey() {
			return key;
		}

		public String getName() {
			return name;
		}

		public String getSign() {
			return sign;
		}

		public static String getSign(Long key){
			TREE_ACTIVITY_ITEM[] values = TREE_ACTIVITY_ITEM.values();
			for (TREE_ACTIVITY_ITEM value : values) {
				if(value.getKey().equals(key)){
					return value.getSign();
				}
			}
			return null;
		}

		public static Long getKey(String sign){
			TREE_ACTIVITY_ITEM[] values = TREE_ACTIVITY_ITEM.values();
			for (TREE_ACTIVITY_ITEM value : values) {
				if(value.getSign().equals(sign)){
					return value.getKey();
				}
			}
			return null;
		}
	}

	/**
	 * 植树节活动消费所需状态码
	 */
	public static enum TREE_MALL_RESULT {
		SUCCESS("000000", "成功"), FAILED_PARAM("401001", "参数错误"), FAILED_BALACE("000002", "对不起，您的余额不足，请先充值"), FAILED_LEVEL("000003", "对不起，您的财富等级不足"), UN_LOGIN("000004", "您处于未登录状态"), FAILED_SYSTEM("000005", "对不起，系统繁忙，稍后再试"), FAILED_TRADE_SYSTEM("000006", "对不起，消费系统繁忙，稍后再试"),  FAILD_GOODS_OFF("000011", "任务已下架"),FAILD_TREE_INVALID("000012", "当天用户树已失效,请退出直播间后重试"),FAILD_TASK_FINISHED("000013", "任务已完成,无需再次执行"),FAILD_TASK_NOFIND("000014", "任务不存在"),FAILED_CODE_BALACE("000015", "积分不足"),;
		private String code;
		private String msg;

		TREE_MALL_RESULT(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	/**
	 * 植树节活动-抽奖奖品配置
	 */
	public static enum TREE_AWARD_ITEM {
		CHARMVALUE(1,"魅力值*100",""),
		TAILLIGHT(2,"招财进宝-尾灯*1天",""),
		CHARMVALUE_Z(3,"【招】碎片*1天",""),
		CHARMVALUE_C(4,"【财】碎片*1天",""),
		CHARMVALUE_J(5,"【进】碎片*1天",""),
		CHARMVALUE_B(6,"【宝】碎片*1天",""),
		CHATBACK(7,"聊天底板*1天",""),
		ENTEREFFECT(8,"摇钱树-入场特效*1天",""),
		ENTEREFFECT_FRAGMENT(9,"摇钱树-入场特效碎片*1天",""),
		LUCKY_PACKS(10,"幸运大礼包*1",""),
		INGOTBADGE(11,"小园丁徽章*1天",""),
		ENTEREFFECT_2(12,"摇钱树-入场特效*15天",""),
		INGOTBADGE_2(13,"小园丁徽章*15天",""),
		CHATBACK_2(14,"聊天底板*15天",""),
		TAILLIGHT_2(15,"招财进宝-尾灯*15天",""),
		ENTEREFFECT_3(16,"摇钱树-入场特效*30天",""),
		INGOTBADGE_3(17,"小园丁徽章*30天",""),
		CHATBACK_3(18,"聊天底板*30天",""),
		TAILLIGHT_3(19,"招财进宝-尾灯*30天",""),
		;
		private Integer awardId;//奖品id(对应t_cyou_tree_award_config表的Fid字段)
		private String awardName;//奖品名称
		private String goodsId;//奖品对应的商品id


		TREE_AWARD_ITEM(Integer awardId, String awardName, String goodsId) {
			this.awardId = awardId;
			this.awardName = awardName;
			this.goodsId = goodsId;
		}

		public static TREE_AWARD_ITEM getAwardItem(Integer awardId){
			TREE_AWARD_ITEM[] values = TREE_AWARD_ITEM.values();
			for (TREE_AWARD_ITEM value : values) {
				if(value.getAwardId().intValue()==awardId){
					return value;
				}
			}
			return null;
		}

		public Integer getAwardId() {
			return awardId;
		}

		public void setAwardId(Integer awardId) {
			this.awardId = awardId;
		}

		public String getAwardName() {
			return awardName;
		}

		public void setAwardName(String awardName) {
			this.awardName = awardName;
		}

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}
	}
}
