package com.redis.lottery.utils;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


/**
 * Quartz调度管理器
 * @author lilinyu
 *
 */
public class QuartzManager {
	
	private static Logger log = LoggerFactory.getLogger(QuartzManager.class);
	public static String JOB_GROUP_NAME="JOB_GROUP_NAME";
	public static String TRIGGER_GROUP_NAME="TRIGGER_GROUP_NAME";
	
	public static  Scheduler scheduler=null;
	
	static{
		if(scheduler==null){
			SchedulerFactory factory=new StdSchedulerFactory();
			try {
				scheduler=factory.getScheduler();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static Scheduler getScheduler(){
		return scheduler;
	}
	
	
	/**
	 * 添加作业
	 * @param sched
	 * @param jobName
	 * @param cls
	 * @param time 秒值
	 * @param dataMap
	 */
	public static void addJob(Scheduler sched,String jobName,Class cls,long time,Map<String,String> dataMap){
		try {
			JobDetail jobDetail=new JobDetail(jobName, JOB_GROUP_NAME, cls);
			if(dataMap!=null){
				jobDetail.getJobDataMap().putAll(dataMap);
			}
			CronTrigger cronTrigger=new CronTrigger(jobName, TRIGGER_GROUP_NAME);
			cronTrigger.setCronExpression(dateConvertCronExpression(new Date(time*1000)));
			sched.scheduleJob(jobDetail, cronTrigger);
			
			start(sched);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeJob(Scheduler sched, String jobName) {
		try {
			sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
			sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
			sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void modifyJobTime(Scheduler sched, String triggerName,long time){
		try {
			CronTrigger trigger=(CronTrigger) sched.getTrigger(triggerName, TRIGGER_GROUP_NAME);
			JobDetail jobDetail=sched.getJobDetail(triggerName, JOB_GROUP_NAME);
			if(trigger==null||jobDetail==null){
				return ;
			}
			String oldTime=trigger.getCronExpression();
			String crontime=dateConvertCronExpression(new Date(time*1000));
			if(!oldTime.equals(crontime)){
				removeJob(sched, triggerName);
				addJob(sched, triggerName, jobDetail.getJobClass(), time, jobDetail.getJobDataMap());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void start(Scheduler scheduler) throws SchedulerException{
		if(scheduler==null){
			return ;
		}
		if(!scheduler.isStarted()){
			scheduler.start();
		}
	}
	
	/**
	 * 指定任务名称的 任务是否开启
	 * @param scheduler
	 * @param jobName
	 * @return
	 */
	public static  boolean isTasked(Scheduler scheduler,int jobName){
		try {
			JobDetail jobDetail=scheduler.getJobDetail(jobName+"", JOB_GROUP_NAME);
			Trigger trigger= scheduler.getTrigger(jobName+"", TRIGGER_GROUP_NAME);
			if(jobDetail==null||trigger==null){
				return false;
			}
			return true;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 将日期转化成cron表达式
	 * @param date
	 * @return
	 */
	public static String dateConvertCronExpression(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateStr=sdf.format(date);
		String[] dateArrays=dateStr.split("-");
		String cronStr=dateArrays[5]+" "+dateArrays[4]+" "+dateArrays[3]+" "+dateArrays[2]+" "+dateArrays[1]+" "+"?"+" "+dateArrays[0];
		return cronStr;
	}
	
}
