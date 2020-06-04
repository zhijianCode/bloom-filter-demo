package cn.meibee.service;

import cn.meibee.ApplicationTests;
import cn.meibee.RedisBloomFilterDemo.RedisBloomFilterService;
import org.junit.Test;

import javax.annotation.Resource;

public class RedisBloomFilterServiceTest extends ApplicationTests {

    @Resource
    private RedisBloomFilterService redisBloomFilterService;

    @Test
    public void testAdd() {
        String key = "url_bloom_filter";
        String url1 = "https://www.aliyun.com/";
        String url2 = "https://cloud.tencent.com/";
        redisBloomFilterService.add(key, url1);

        System.out.println(redisBloomFilterService.exists(key, url1));
        System.out.println(redisBloomFilterService.exists(key, url2));
    }
    @Test
    public void testExists() {
        String key = "newFilter";
        String foo = "foo";
        String foo2 = "foo2";
        String foo7 = "foo7";

        System.out.println(redisBloomFilterService.exists(key, foo));
        System.out.println(redisBloomFilterService.exists(key, foo2));
        System.out.println(redisBloomFilterService.exists(key, foo7));
    }

}
