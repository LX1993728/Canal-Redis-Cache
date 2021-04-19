package com.redis.lottery.constants;

import com.redis.lottery.vo.ZnqRoomInfoVO;

/**
 * znq resolve room callback
 */
public interface ZnqRoomAction<T>{
    T action(ZnqRoomInfoVO roomInfoVO, String key);
}
