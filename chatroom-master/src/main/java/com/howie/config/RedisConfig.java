package com.howie.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

@Configuration
@EnableCaching//开启注解
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory){

        RedisTemplate<Object, Object> template=new RedisTemplate<Object, Object>();

        template.setConnectionFactory(connectionFactory);
        //实现序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        //实现序列化和反序列化redis的value值,默认使用JdkSerializationRedisSerializer
        //template.setValueSerializer(new RedisObjectSerializer());
        //template.setValueSerializer();
        return template;

    }


    @Bean
    public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // cacheManager.setCacheNames(Arrays.asList("users", "emptyUsers"));
        cacheManager.setUsePrefix(true);
        // Number of seconds before expiration. Defaults to unlimited (0)
        cacheManager.setDefaultExpiration(1800L);
        return cacheManager;
    }

    //自定义keyGenerator必须实现org.springframework.cache.interceptor.KeyGenerator接口
    @Bean
    public KeyGenerator accountKeyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //first parameter is caching object
                //second paramter is the name of the method, we like the caching key has nothing to do with method name
                //third parameter is the list of parameters in the method being called
                return target.getClass().toString() + "accountId:" + params[0].toString();
            }
        };
    }
}
