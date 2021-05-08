package com.shizy.algorithm.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * TwoNumSum
 * <p>
 * 输入：
 * 1、值不重复的数组a
 * 2、目标值target
 * <p>
 * 输出：
 * 找出数组a中两数之后为target（可以认为只有一组解）
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2020/11/30 19:07
 */
public class TwoNumSum {

    public static void main(String[] args) {
        int[] a = new int[]{13, 21, 65, 49, 8, 7, 54, 6};
        int target = 2811;

        int[] result = twoNumSum(a, target);
        System.out.println(Arrays.toString(result));

    }

    private static int[] twoNumSum(int[] input, int target) {
        Arrays.sort(input);
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < input.length; i++) {
            if (map.containsKey(target - input[i])) {
                return new int[]{target - input[i], input[i]};
            }
            map.put(input[i], i);
        }
        return null;
    }
}





















