package cn.meibee.MyBloomFilterDemo;

import java.util.BitSet;
import java.util.UUID;

public class MyBloomFilter {

    public static void main(String[] args) {
        int bitsize = 2 << 24;
        int[] seeds = new int[]{3, 13, 46, 71, 91, 134};
        MyBloomFilter myBloomFilter = new MyBloomFilter(bitsize, seeds);

        for(int i=0; i<1000000; i++){
            String str = UUID.randomUUID().toString().replaceAll("-","");
            myBloomFilter.add(str);
        }

        int count = 0;
        for(int i=0; i<2000000; i++){
            String str = UUID.randomUUID().toString().replaceAll("-","");
            if(myBloomFilter.contains(str)){
                count++;
            }
        }
        System.out.println("count: "+count);
    }

    /**
     * 位数组的大小
     */
    private static int bitsize;
    /**
     * 通过这个数组可以创建 6 个不同的哈希函数
     */
    private static int[] seeds;

    /**
     * 位数组。数组中的元素只能是 0 或者 1
     */
    private BitSet bits;

    /**
     * 存放包含 hash 函数的类的数组
     */
    private SimpleHash[] func;

    /**
     * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
     */
    public MyBloomFilter(int bitsize, int[] seeds) {
        this.bitsize = bitsize;
        this.seeds = seeds;
        this.bits = new BitSet(bitsize);
        this.func = new SimpleHash[seeds.length];
        // 初始化多个不同的 Hash 函数
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(bitsize, seeds[i]);
        }
    }

    /**
     * 添加元素到位数组
     */
    public void add(Object value) {
        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    /**
     * 判断指定元素是否存在于位数组
     */
    public boolean contains(Object value) {
        boolean ret = true;
        for (SimpleHash f : func) {
            ret = ret && bits.get(f.hash(value));
        }
        return ret;
    }

    /**
     * 静态内部类。用于 hash 操作！
     */
    public static class SimpleHash {
        private int cap;
        private int seed;
        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }
        /**
         * 计算 hash 值
         */
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }
    }
}
