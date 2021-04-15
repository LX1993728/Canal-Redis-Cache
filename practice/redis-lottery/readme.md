
### 周年庆活动抽奖核心表
- **t_master** : 用户表 - 包含字段如下
  - id: 用户ID
  - username: 用户名称  
- **t_znq_prize** : 周年庆活动奖品配置表
  - id: 奖品ID
  - name: 奖品名称
  - image: 奖品图片地址  
  - total: **每天**发放奖品的总数量
  - probability: 中奖概率
  - broad: 是否开启广播 默认false
  - star_diamonds: 星钻数量(谢谢参与:-2 IPAD: -1,牡丹特效:0, 星钻:大于0 )
  - description: 奖品描述-(特/一/二/三/.../等奖)  
  - types：奖品类型数组 1,2 最多两项 
    (1-基础作战,2-进阶作战,3-彩蛋)
- **t_znq_prize_detail**: 周年庆活动中奖记录详情表
  - id: 详情表ID
  - masterid：用户id
  - roleid: 用户的角色id
  - prizeid: 中奖的奖品id
  - time: 中奖时间
  - date: 中奖日期(yyyy-MM-dd)
  - terminal: 设备类型 (0-PC端,1-App端)
  - nature：是否是自然人 (0-非自然人,1-是自然人)
  - received: 是否已经领取 (0-已领取,1- 未领取) 
  - code: 中奖码
  - stage: 活动的阶段 (1-基础阶段,2-进阶阶段,3-彩蛋阶段)
  - allow_edit: 是否允许编辑 中奖结果

### 配置在文件的静态内容
  - 活动时间段 (2021.5.14 00:00:00 ~ 2021.5.19 23:59:59)
  - 活动期间每天初始化时间点 (00:00:00)

### 待处理的需求
  - 任务是针对每个直播间的