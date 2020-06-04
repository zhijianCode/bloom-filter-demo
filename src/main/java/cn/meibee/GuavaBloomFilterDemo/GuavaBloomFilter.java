package cn.meibee.GuavaBloomFilterDemo;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.UUID;

public class GuavaBloomFilter {

    public static void main(String[] args) {
        int size = 1000000; // 预计插入数据量
        double fpp = 0.0000001; // 期盼的误判率，不能为0
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), size, fpp);
        System.out.println(bloomFilter.mightContain(UUID.randomUUID().toString()));

        for(int i=0; i<size; i++){
            bloomFilter.put(UUID.randomUUID().toString());
        }
        int count = 0;
        for(int i=0; i<size; i++){
            if(bloomFilter.mightContain(UUID.randomUUID().toString())){
                count++;
            }
        }
        System.out.println("count: "+count);
    }
}
