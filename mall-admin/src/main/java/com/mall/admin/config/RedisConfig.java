package com.mall.admin.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.Charset;
import java.time.Duration;

/*
* @Author:wangyongchun
*@Date:2019-01-20 15:05
* @Description:缓存对象配置
*/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    /*
    * 设置redis默认过期时间
    * 设置@cacheable方式
    *
    * @return
    */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(fastJsonRedisSerializer)).entryTtl(Duration.ofHours(1));

        return redisCacheConfiguration;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer<Object> redisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(redisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    static class FastJsonRedisSerializer<T> implements RedisSerializer<T>{

        private static final Charset DEFAULT_CHARSET = Charset.forName("utf-8");

        private Class<T> clazz;

        public FastJsonRedisSerializer(Class<T> clazz){
            super();
            this.clazz = clazz;
        }

        @Override
        public byte[] serialize(T t) throws SerializationException {
            if (t == null){
                return new byte[0];
            }
            return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
        }

        @Override
        public T deserialize(byte[] bytes) throws SerializationException {
            if (bytes == null || bytes.length <= 0){
                return null;
            }
            String str = new String(bytes, DEFAULT_CHARSET);
            return JSON.parseObject(str, clazz);
        }
    }
}
