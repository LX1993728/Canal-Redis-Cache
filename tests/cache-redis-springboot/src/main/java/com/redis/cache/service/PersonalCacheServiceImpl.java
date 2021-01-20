package com.redis.cache.service;

import org.springframework.stereotype.Service;

@Service
public class PersonalCacheServiceImpl implements PersonalCacheService{
    @Override
    public String getPersonalUserId() {
        return "userId1";
    }
}
