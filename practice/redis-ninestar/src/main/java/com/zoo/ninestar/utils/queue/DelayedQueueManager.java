package com.zoo.ninestar.utils.queue;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.DelayQueue;

@Slf4j
public class DelayedQueueManager {
	private static DelayQueue<DelayedVo> delayQueue=new DelayQueue<DelayedVo>();
	
	private DelayedQueueManager(){
		init();
	}
	private static DelayedQueueManager instance=new DelayedQueueManager();
	private Thread task;
	
	/**
	 * 加入队列
	 * @param data	元素数据
	 * @param delayTime	延时时间
	 * @param service 处理元素数据的服务
	 */
	public <T> void addQueue(T data,long delayTime,DelayedQueueService service){
		DelayedVo<T> vo=new DelayedVo<T>(delayTime, data, service);
		delayQueue.put(vo);
		log.info("delay_queue_add:"+data.toString());
	}
	
	public static DelayedQueueManager getInstance(){
		return instance;
	}
	
	private class DelayTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			log.info("处理队列中的任务作业已开启");
			while(!Thread.currentThread().isInterrupted()){
				try {
					DelayedVo vo=delayQueue.take();
					log.info("queue_item_is_null?-->{}",(vo==null));
					if(vo!=null){
						log.info("ready_queue_handler:...");
						//处理队列元素
						DelayedQueueService service=vo.getService();
						if(service==null){
							log.info("queue_handler_service_is_null...");
							continue;
						}
						log.info("queue_handler:"+service.getClass()+"-->"+service.getClass().getSimpleName());
						vo.getService().execute(vo);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info("queue_item_handler_exception", e,e);
				}
			}
			
		}
	}
	/**
	 * 初始化，开启作业类线程
	 */
	private void init(){
		task=new Thread(new DelayTask());
		task.start();
	}
	
	/**
	 * 中断作业类任务
	 */
	public void close(){
		task.interrupt();
	}
	
	
}
