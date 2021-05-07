package com.shizy.third.nlp;

import com.shizy.third.nlp.keyword.algorithm.TextRank;

import java.util.List;

public class KeywordTest {
    public static void main(String[] args) {


        List z = TextRank.getKeyword("", "学费是一次付款吗？为什么我只扣了2000块");

        System.out.println(z);
    }
}
