package com.example.demo.cache.service;

import com.example.demo.cache.event.XoaCacheEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class XoaCacheSubscriber implements MessageListener {

    private final CacheManager cacheManager;
    private final ObjectMapper objectMapper;

    public XoaCacheSubscriber(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.cacheManager = cacheManager;
        this.objectMapper = objectMapper;
    }

    /*public void onMessage(XoaCacheEvent event) {
        Cache cache = cacheManager.getCache(event.getValue());
        if (cache != null) {
            if (event.isEvictAll()) {
                cache.clear();
            } else if (event.getKey() != null) {
                cache.evict(event.getKey());
            }
        }
    }*/

    @Override
    public void onMessage(Message message, byte[] pattern) {

        XoaCacheEvent event;
        try {
            event = objectMapper.readValue(message.toString(), XoaCacheEvent.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (event != null) {
            Cache cache = cacheManager.getCache(event.getValue());
            if (cache != null) {
                if (event.isEvictAll()) {
                    cache.clear();
                } else if (event.getKey() != null) {
                    cache.evict(event.getKey());
                }
            }
        }
    }
}
