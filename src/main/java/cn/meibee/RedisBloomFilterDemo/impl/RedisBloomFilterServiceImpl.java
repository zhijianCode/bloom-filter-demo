package cn.meibee.RedisBloomFilterDemo.impl;

import cn.meibee.RedisBloomFilterDemo.RedisBloomFilterService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class RedisBloomFilterServiceImpl implements RedisBloomFilterService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean add(String key, Object value) {
        String script = "return redis.call('BF.ADD',KEYS[1],ARGV[1])";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        return stringRedisTemplate.execute(redisScript, Collections.singletonList(key), value);
    }

    @Override
    public boolean exists(String key, Object value) {
        String script = "return redis.call('BF.EXISTS',KEYS[1],ARGV[1])";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        return stringRedisTemplate.execute(redisScript, Collections.singletonList(key), value);
    }
}
