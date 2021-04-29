package com.redis.lottery.component;

import com.redis.lottery.domains.Master;
import com.redis.lottery.domains.ZnqPrize;
import com.redis.lottery.domains.ZnqPrizeDetail;
import com.redis.lottery.service.IZnqService;
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
    private IZnqService IZnqService;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        log.info("---------------程序执行完毕，开始执行初始化操作-------------------");
        // 初始化模拟数据
        initEntityTable();
        // 开始进行znq 初始化相关的操作...
        IZnqService.clearAndResetEveryDay();
        log.info("---------------执行初始化操作完毕...   -------------------");
    }

    private void initEntityTable(){
        Master master1 = new Master(null,1L,"张三");
        Master master2 = new Master(null,1L,"李四");
        Master master3 = new Master(null,1L,"王五");
        Master master4 = new Master(null,2L,"赵刘");
        Master master5 = new Master(null,2L,"宋七");
        Master master6 = new Master(null,2L,"钱八");

        // 创建奖品实例
        ZnqPrize znqPrize1 = new ZnqPrize(null, "iPad", "特等奖", "http://imagexxx", 1, 1, 0.003d, -1, "3", null);
        ZnqPrize znqPrize2 = new ZnqPrize(null, "10000星钻", "一等奖", "http://imagexxx", 1, 6, 0.01d, 10000, "1,2,3", null);
        ZnqPrize znqPrize3 = new ZnqPrize(null, "100星钻", "二等奖", "http://imagexxx", 0, 1000, 2d, 100, "1,2,3", null);
        ZnqPrize znqPrize4 = new ZnqPrize(null, "限定入场特效\"焦骨牡丹\" (7天)", "三等奖", "http://imagexxx", 0, 2000, 4d, 0, "1,2,3", null);
        ZnqPrize znqPrize5 = new ZnqPrize(null, "5星钻", "四等奖", "http://imagexxx", 0, 5000, 10d, 5, "1,2,3", null);
        ZnqPrize znqPrize6 = new ZnqPrize(null, "2星钻", "五等奖", "http://imagexxx", 0, 20000, 40d, 2, "1,2,3", null);
        ZnqPrize znqPrize7 = new ZnqPrize(null, "谢谢参与", "其他", "http://imagexxx", 0, 10000000, 43.99d, -2, "1,2,3",null);
        ZnqPrize znqPrize8 = new ZnqPrize(null, "谢谢参与", "其他", "http://imagexxx", 0, 10000000, 43.99d, -2, "1,2,3",null);
        ZnqPrize znqPrize9 = new ZnqPrize(null, "谢谢参与", "其他", "http://imagexxx", 0, 10000000, 43.99d, -2, "1,2,3",null);


        entityManager.persist(master1);
        entityManager.persist(master2);
        entityManager.persist(master3);
        entityManager.persist(master4);
        entityManager.persist(master5);
        entityManager.persist(master6);
        entityManager.persist(znqPrize1);
        entityManager.persist(znqPrize2);
        entityManager.persist(znqPrize3);
        entityManager.persist(znqPrize4);
        entityManager.persist(znqPrize5);
        entityManager.persist(znqPrize6);
        entityManager.persist(znqPrize7);
        entityManager.persist(znqPrize8);
        entityManager.persist(znqPrize9);

        final ZnqPrizeDetail detail = new ZnqPrizeDetail(null, 105L, 1L, 206L, 1L, new Date(2021, 5, 20), 20210520, 0, 1, 0, "xxx", 3, 1, null, 0);
        entityManager.persist(detail);
        log.info("表数据实例化完毕......");
    }
}
