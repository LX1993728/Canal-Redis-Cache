package com.redis.lottery.controllers;

import com.redis.lottery.domains.ZnqPrize;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@RestController
public class TestController {
    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/prizes")
    public List<ZnqPrize>  getPrizes(){
        final List<ZnqPrize> znqPrizes = entityManager.createQuery(
                "SELECT zp FROM ZnqPrize zp WHERE zp.starDiamonds <> -2 ORDER BY zp.probability ASC",
                ZnqPrize.class).getResultList();
        return znqPrizes;
    }
}
