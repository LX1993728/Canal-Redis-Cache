package com.jedis.demo.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
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
