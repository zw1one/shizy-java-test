package com.shizy.fun.lol.utils;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtil {

    public static int[] genRandomArray(int range, int size) {
        Set<Integer> numSet = new LinkedHashSet<Integer>(); //集合是没有重复的值,LinkedHashSet是有顺序不重复集合,HashSet则为无顺序不重复集合
        while (numSet.size() < size) {
            Integer tmp = new Random().nextInt(range); //0-range之间随机选一个数
            numSet.add(tmp);//直接加入，当有重复值时，不会往里加入
        }

        int[] rnt = new int[size];
        Object[] nums = numSet.toArray();
        for (int i = 0; i < nums.length; i++) {
            rnt[i] = (int) nums[i];
        }

        return rnt;
    }

}
















