package com.shizy.fun.sudoku;

import com.shizy.fun.sudoku.utils.MyRandomUtil;

import java.util.Arrays;

public class SudokuMain {

    public static void main(String[] args) {
        new SudokuMain().start();
    }

    private void start() {

        //1、生成可行的数独
        genSudoku();

        //2、正解转为图片base64

        //3、清除部分格子作为问题

        //4、展示问题与答案base64


        System.out.println();

    }

    private int[][] genSudoku() {

        int[][] randomSite = genRandomSite();
        while (!checkSite(randomSite)) {
            randomSite = genRandomSite();
        }

        return randomSite;
    }

    /**
     * site[y][x]
     * <p>
     * 0  x  x  x  x  x  x  x  9
     * y
     * y
     * y
     * y
     * y
     * y
     * y
     * y
     * 9
     */
    private boolean checkSite(int[][] site) {

        //判断每一行都有1-9
        for (int i = 0; i < site.length; i++) {
            if (!exist1_9(site[i])) {
                return false;
            }
        }

        //判断每一列都有1-9

        //判断3*3都有1-9

        return true;
    }

    private boolean exist1_9(int[] list) {

        int[] list1_9 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list1_9.length; j++) {
                if (list[i] == list1_9[j]) {
                    list1_9[j] = 0;
                }
            }
        }

        for (int i = 0; i < list1_9.length; i++) {
            if (list1_9[i] != 0) {
                System.out.println(Arrays.toString(list1_9));
                return false;
            }
        }

        return true;
    }

    private int[][] genRandomSite() {
        int[][] site = new int[9][9];

        //init numPool
        int[] numPool = new int[81];
        int z = 1;
        for (int i = 0; i < numPool.length; i++) {
            numPool[i] = z;
            if (z == 9) {
                z = 1;
            } else {
                z++;
            }
        }

        //get randomNum
        int[] randomNum = MyRandomUtil.getRandoms(81);

        //set site1
        int[] site1 = new int[81];
        for (int i = 0; i < randomNum.length; i++) {
            site1[i] = numPool[randomNum[i]];
        }

        //site1 to site
        for (int i = 0; i < site1.length; i++) {
            site[i / 9][i % 9] = site1[i];
        }

        return site;
    }


}

















