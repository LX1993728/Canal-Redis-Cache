package com.zoo.ninestar.utils.queue;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列实体
 *
 * @param <T>
 */
@Data
public class DelayedVo<T> implements Delayed {

	//延时时间
	private  long delayTime;
	//到期时间
	private  long expire;
	//数据
	private T data;
	
	private DelayedQueueService service;
	
	/**
	 * 构造函数
	 * @param delayTime 延时时间
	 * @param data	队列数据
	 * @param delayService	处理队列元素的服务
	 */
	public DelayedVo(long delayTime, T data, DelayedQueueService delayService) {
		// TODO Auto-generated constructor stub
		this.delayTime=delayTime;
		this.data=data;
		this.expire=delayTime+System.currentTimeMillis();
		this.service=delayService;
	}
	
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		long compareRSLong=this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS);
		return compareRSLong>0?1:(compareRSLong<0?-1:0);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long delay=unit.convert(expire-System.currentTimeMillis(), unit);
		return delay;
	}

}
