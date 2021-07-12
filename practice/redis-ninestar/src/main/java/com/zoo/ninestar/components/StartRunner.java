package com.zoo.ninestar.components;

import com.zoo.ninestar.domains.entity.Master;
import com.zoo.ninestar.domains.entity.NSPK;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.services.NSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class StartRunner implements ApplicationRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NSService nsService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("---------------程序执行完毕，开始执行初始化操作-------------------");
        TimeUnit.SECONDS.sleep(3);
        initData();
        TimeUnit.SECONDS.sleep(2);
        nsService.getLoadSkills(true);
        log.info("---------------执行初始化操作完毕...   -------------------");
    }


    /**
     * 初始化数据源
     */
    private void initData(){
        final Master master1 = new Master(null, "张三");
        final Master master2 = new Master(null, "李四");
        final Master master3 = new Master(null, "王五");
        final Master master4 = new Master(null, "赵刘");
        final Master master5 = new Master(null, "陈七");
        final Master master6 = new Master(null, "宋八");
        entityManager.persist(master1);
        entityManager.persist(master2);
        entityManager.persist(master3);
        entityManager.persist(master4);
        entityManager.persist(master5);
        entityManager.persist(master6);


        final NSPKSkill skill1 = new NSPKSkill(null, "烈火燎原", "http://xxxx.jpg", 0, 50L, 50L, 50L, 50L, 50L, null, null, "KO挑战期间，会减少对手5000生命值",
                5000, null, null, null, null, null, null, null,null, 1,null, null, null);
        final NSPKSkill skill2 = new NSPKSkill(null, "蓄力烈火", "http://xxxx.jpg", 0, 5000L, 5000L, 5000L, 5000L, 5000L, null, null, "烈火燎原发动10次后可使用，给对手施加1层Debuff状态，持续时间15s，每秒减200点生命值，最多叠加5层！",
                null, null, 15, 5, 100, 10, null,1L, 10, 0,null, null, null);
        final NSPKSkill skill3 = new NSPKSkill(null, "净化", "http://xxxx.jpg", 0, 5000L, 5000L, 5000L, 5000L, 5000L, null, 10, "KO挑战期间，清除己方Debuff",
                null, null, null, null, null, null, 1, null,null,0,null, null, null);
        final NSPKSkill skill4 = new NSPKSkill(null, "晴天一击", "http://xxxx.jpg", 0, 50L, 50L, 50L, 50L, 50L, null, null, "KO挑战期间，会减少对手5000生命值",
                5000, null, null, null, null, null, null, null,null,1,null, null, null);
        final NSPKSkill skill5 = new NSPKSkill(null, "蓄力一击", "http://xxxx.jpg", 0, 5000L, 5000L, 5000L, 5000L, 5000L, null, null, "晴天一击发动10次后可使用，会减少对手50000生命值",
                50000, null, null, null, null, null, null, 4L,10, 0,null, null, null);
        final NSPKSkill skill6 = new NSPKSkill(null, "救赎", "http://xxxx.jpg", 0, 5000L, 5000L, 5000L, 5000L, 5000L, null, 10, "KO挑战期间，恢复己方1000生命值",
                null, 1000, null, null, null, null, null, null,null,0,null, null, null);

        final NSPKSkill skill7 = new NSPKSkill(null, "起死回生", "http://xxxx.jpg", 1, null, null, null, null, null, 3, 1, "立刻恢复一定百分百最大生命值",
                null, 1000, null, null, null, null, null, null,null,0,null, null, null);

        entityManager.persist(skill1);
        entityManager.persist(skill2);
        entityManager.persist(skill3);
        entityManager.persist(skill4);
        entityManager.persist(skill5);
        entityManager.persist(skill6);
        entityManager.persist(skill7);
        skill1.setId(1L);
        skill2.setId(2L);
        skill3.setId(3L);
        skill4.setId(4L);
        skill5.setId(5L);
        skill6.setId(6L);
        skill7.setId(7L);

        entityManager.persist(skill1);
        entityManager.persist(skill2);
        entityManager.persist(skill3);
        entityManager.persist(skill4);
        entityManager.persist(skill5);
        entityManager.persist(skill6);
        entityManager.persist(skill7);

        // 初始化一场PK赛记录
        final NSPK nspk = new NSPK(null, 1000L, 1001L, new Date(), new Date(), 30000, null, 1, null, null);
        entityManager.persist(nspk);
    }
}
