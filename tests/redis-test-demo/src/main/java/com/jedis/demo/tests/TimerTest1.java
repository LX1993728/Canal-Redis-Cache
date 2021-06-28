package com.jedis.demo.tests;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TimerTest1 {
    @Test
    public void test1() throws InterruptedException {
        Timer timer = new Timer("TimerTest");
        Timer timer2 = new Timer("TimerTest2");


        //timer.scheduleAtFixedRate(timerTask, 1000, 1000);
        timer.scheduleAtFixedRate(getTask("task-1"), 1000, 1000);
        timer2.schedule(getTask("task-2"), 1000, 1000);
        TimeUnit.HOURS.sleep(1);
    }

    @Test
    public void test2(){
        Set<String> keys = new HashSet<>();
        for (int i=0; i < 10; i ++){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                keys.add("BBBBB_" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(keys.toString());
        List<String> keysList = new ArrayList<>();
        keysList.addAll(keys);
        Collections.sort(keysList);
        log.info(keysList.toString());
    }

    private TimerTask getTask(String name){
        final long startTime = System.currentTimeMillis();
        TimerTask timerTask = new TimerTask() {
            int i = 1;
            @Override
            public void run() {
                if (i >= 5){
                    // timer.cancel();
                    cancel();
                }
                final long tStartTime = System.currentTimeMillis();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final long endTime = System.currentTimeMillis();
                log.info("name={}\t i={}\t time={}\t totalTime={}", name, i, (endTime - tStartTime)/1000, (endTime - startTime)/1000);
                i++;

            }
        };
        return timerTask;
    }
}
