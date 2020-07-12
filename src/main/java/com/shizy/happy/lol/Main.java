package com.shizy.happy.lol;

import com.shizy.happy.lol.fetter.EffectiveFetter;
import com.shizy.happy.lol.fetter.FetterPool;
import com.shizy.happy.lol.hero.Hero;
import com.shizy.happy.lol.hero.HeroPool;
import com.shizy.happy.lol.score.ScoreManager;
import com.shizy.happy.lol.score.ScoreResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {

        int population = 8;//人口

        List<ScoreResult> squads = getSquads(population);//分值较大的阵容

        for (ScoreResult scoreResult : squads) {
            System.out.println("===================");
            scoreResult.show();
            System.out.println("===================");
        }
    }

    private List getSquads(int population) {

        float maxScore = getMaxScore(population);
//        float maxScore = 440;

        float f = 0.1f;//最大分值浮动
        int containsCnt = 0;//迭代出的阵容已经存在squads中的计数

        List<ScoreResult> squads = new ArrayList();//分值较大的阵容

        while (squads.size() < 200) {
            Object[] param = genRandomScore(population);
            List heros = (List) param[0];
            List effectiveFetters = (List) param[1];
            float score = (float) param[2];

            if (maxScore * (1 - f) <= score) {
                ScoreResult.sortSR(heros, effectiveFetters);
                ScoreResult sr = new ScoreResult(heros, effectiveFetters, score);
                if (!squads.contains(sr)) {
                    squads.add(sr);
                    System.out.println("squads.size: " + squads.size());
                    System.out.println(squads);
                    containsCnt = 0;
                } else {
                    containsCnt++;
                }
            }
            if (containsCnt >= 10) {
                f += 0.01;
                System.out.println("f: " + f);
            }
        }

        squads.sort((Comparator) (o1, o2) -> Float.compare(((ScoreResult) o2).getScore(), ((ScoreResult) o1).getScore()));

        List rtnList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            rtnList.add(squads.get(i));
        }

        return rtnList;
    }

    private float getMaxScore(int population) {
        float maxScore = 0;

        int noMaxCnt = 0;//很久没算出没有比现在这个大/相等的了
        int isMaxCnt = 0;
        while (true) {
            Object[] param = genRandomScore(population);
            List heros = (List) param[0];
            List effectiveFetters = (List) param[1];
            float score = (float) param[2];

            if (score > maxScore) {
                maxScore = score;
                isMaxCnt = 0;
                noMaxCnt = 0;
            } else if (score == maxScore) {
                if (isMaxCnt == 5) {
                    break;
                } else {
                    System.out.println("max score: " + maxScore + "  max score cnt: " + isMaxCnt);
                    isMaxCnt++;
                }
                noMaxCnt = 0;
            }else {
                if(noMaxCnt == 10000000){
                    System.out.println("break in max score: " + maxScore + "  max score cnt: " + isMaxCnt);
                    break;
                }else{
                    noMaxCnt++;
                }
            }
        }

        return maxScore;
    }

    /**
     * 生成随机阵容与分数
     */
    private Object[] genRandomScore(int population) {
        //get随机英雄作为阵容
        List<Hero> randomHeros = HeroPool.getRandomHeros(population);

        //获得阵容的羁绊数
        Map fetterMap = FetterPool.getFetterTotal(randomHeros);//阵容羁绊的英雄数
        List<EffectiveFetter> effective = FetterPool.effectiveFetters(fetterMap);//阵容有效羁绊的英雄数

        //计算阵容分数
        float score = ScoreManager.scoreCalculate(randomHeros, effective);

        return new Object[]{randomHeros, effective, score};
    }


}














