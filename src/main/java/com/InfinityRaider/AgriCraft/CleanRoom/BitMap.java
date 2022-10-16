package com.InfinityRaider.AgriCraft.CleanRoom;

import java.util.Arrays;
import java.util.function.Consumer;

public class BitMap {
    public static final int bit = Byte.SIZE - 1, offset;

    static {
        int source = bit + 1;
        int t = 0;
        if (source % 2 != 0) throw new RuntimeException("BitMap数据结构异常");
        while (source > 1) {
            source /= 2;
            t += 1;
        }
        offset = t;
    }

    private byte[] drums;

    public BitMap(int size) {
        if (size <= 0) throw new RuntimeException("BitMap不能小于等于0");
        // size & 0x07 == size % 8          size >> 3 == size /= 8
        // size & 0x0F == size % 16          size >> 4 == size /= 16
        int drumSize = (size & bit) == 0 ? size >> offset : (size >> offset) + 1;
        drums = new byte[drumSize];
    }

    public void add(int num) {
        int position = findDrumPosition(num);
        drums[position] |= 1 << (num & bit);
    }

    public void remove(int num) {
        int position = findDrumPosition(num);
        drums[position] &= ~(1 << (num & bit));
    }

    public boolean has(int num) {
        int position = findDrumPosition(num);
        return ((drums[position] >> (num & bit)) & 0x01) == 1;
    }

    public void forEach(Consumer<Integer> exist, Consumer<Integer> noExist) {
        if (noExist == null) noExist = e -> {
        };
        if (exist == null) exist = e -> {
        };
        for (int i = 0; i < drums.length << offset; i++) {
            if (!has(i)) noExist.accept(i);
            else exist.accept(i);
        }
    }

    // 定位桶
    private int findDrumPosition(int num) {
        if (num < 0 || num >= drums.length << offset)
            throw new RuntimeException("存入的数据应该大于等于0且小于BitMap的长度: " + num);
        return num >> offset;
    }

    @Override
    public String toString() {
        return "BitMap{" + "values=" + Arrays.toString(drums) + '}';
    }
}
