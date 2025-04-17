package com.example.demo.cache.service;

import com.example.demo.cache.event.XoaCacheEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class XoaCachePublisher {
    private final RedisTemplate<UUID, Object> redisTemplate;
    private final ChannelTopic topic;

    public XoaCachePublisher(RedisTemplate<UUID, Object> redisTemplate,
                               ChannelTopic cacheInvalidationTopic) {
        this.redisTemplate = redisTemplate;
        this.topic = cacheInvalidationTopic;
    }

    public void publishEvent(XoaCacheEvent event) {
        redisTemplate.convertAndSend(topic.getTopic(), event);
    }
}
