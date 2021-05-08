package com.shizy.fun.sudoku.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MyRandomUtil {

    public static int[] getRandoms(int size) {
        Random random = new Random();

        int[] randoms = new int[size];

        int i = 0;
        Set noDuplication = new HashSet();
        while (i < randoms.length) {
            int randomNum = random.nextInt(size);

            if (noDuplication.contains(randomNum)) {
                continue;
            }

            randoms[i] = randomNum;
            noDuplication.add(randomNum);
            i++;
        }

        return randoms;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getRandoms(10)));


    }

}
