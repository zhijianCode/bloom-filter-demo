package cn.meibee.RedisBloomFilterDemo;

public interface RedisBloomFilterService {

    /**
     * 插入
     * @param key
     * @param value
     * @return
     */
    boolean add(String key, Object value);

    /**
     * 检查是否存在
     * @param key
     * @param value
     * @return
     */
    boolean exists(String key, Object value);

}
