package com.zoo.ninestar.utils.queue;

/**
 * 处理延时队列元素服务
 * @author lilinyu
 *
 */
public interface DelayedQueueService {

    /**
     *处理队列元素
     * @param delayedVo
     */
    public <T> void execute(DelayedVo<T> delayedVo);

}
