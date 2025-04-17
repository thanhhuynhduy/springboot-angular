package com.example.demo.config;

import com.example.demo.cache.service.XoaCacheSubscriber;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableCaching
public class RedisConfig implements CachingConfigurer {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        // redisStandaloneConfiguration.setDatabase(1);
        redisStandaloneConfiguration.setUsername("default");
        redisStandaloneConfiguration.setPassword("redisdemo");
        return new JedisConnectionFactory (redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<UUID, Object> redisTemplate() {
        RedisTemplate<UUID, Object> template = new RedisTemplate<>();
        //key dạng String
        template.setKeySerializer(new StringRedisSerializer());
        //value dạng JSON
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600)) //Time to live cua cache
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) //Kieu cua key
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())) //Kieu cua value
                .disableCachingNullValues();
        return RedisCacheManager.builder(jedisConnectionFactory())
                .cacheDefaults(config)
                /*.withInitialCacheConfigurations(Map.of(
                        "users", RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30)),
                        "products", RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofHours(1))
                ))*/
                .build();
    }

    @Bean
    public ChannelTopic xoaCacheTopic() {
        return new ChannelTopic("xoa_cache");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            MessageListenerAdapter messageListenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListenerAdapter, xoaCacheTopic());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(XoaCacheSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessage");
    }
}
