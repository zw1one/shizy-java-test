package com.shizy.fun.lol.score;

import com.shizy.fun.lol.fetter.EffectiveFetter;
import com.shizy.fun.lol.hero.Hero;

import java.util.List;

public class ScoreManager {

    public static float scoreCalculate(List<Hero> randomHeros, List<EffectiveFetter> effectiveFetters) {

        float sum = 0;

        //英雄得分
        for (Hero hero : randomHeros) {
            //越贵越牛逼
            sum = sum + (hero.getCost() + 4) / 10;
        }

        //羁绊得分
        for (EffectiveFetter effective : effectiveFetters) {

            int[] fetterRank = effective.getFetterRank();

            //羁绊越高级，参与羁绊的英雄越多，就牛逼
            int rankFactor = effective.getFetterCrtRank();//羁绊等级，这里不参与高级羁绊的评分。若无此步骤，则6恶魔(共3阶)强于6贵族(共2阶)
            if(rankFactor > 2){
                rankFactor = 2;
            }
            if(effective.getHeroNum() == 1){
                System.out.println();
            }
            sum = sum + (fetterRank[effective.getFetterCrtRank() - 1] / 2 * rankFactor * 3 * fetterRank[fetterRank.length - 1]) * 2;

            //羁绊为最当前羁绊最高等级，并且人数大于等于4，就牛逼
            if (effective.getHeroNum() >= 6) {
                int[] rank = effective.getFetterRank();
                int rankLastNum = rank[rank.length - 1];
                if (effective.getFetterCrtRank() == rank.length) {
                    //羁绊需要的人多，并且羁绊等级上限低，就牛逼
                    sum = sum + (rankLastNum * 3 / effective.getFetterCrtRank() + 1) * 2.5f;
                }
            } else if (effective.getHeroNum() >= 4) {
                int[] rank = effective.getFetterRank();
                int rankLastNum = rank[rank.length - 1];
                if (effective.getFetterCrtRank() == rank.length) {
                    //羁绊需要的人多，并且羁绊等级上限低，就牛逼
                    sum = sum + (rankLastNum * 3 / effective.getFetterCrtRank() + 1) * 2;
                }

            }
        }

        return sum;
    }

}





















