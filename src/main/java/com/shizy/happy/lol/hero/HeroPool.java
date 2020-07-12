package com.shizy.happy.lol.hero;

import com.shizy.happy.lol.fetter.FetterPool;
import com.shizy.happy.lol.utils.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeroPool {

    public static Map<String, Hero> heros = new HashMap();

    /**
     * todo
     * 英雄做成以Hero为接口/基类，每个Hero为一个class
     */

    static {
        heros.put("男枪", new Hero("男枪", 1, FetterPool.getFetters("海盗", "枪手")));
        heros.put("小炮", new Hero("小炮", 1, FetterPool.getFetters("约德尔人", "枪手")));
        heros.put("铁男", new Hero("铁男", 1, FetterPool.getFetters("骑士", "暗影")));
        heros.put("诺手", new Hero("诺手", 1, FetterPool.getFetters("骑士", "帝国")));
        heros.put("盖伦", new Hero("盖伦", 1, FetterPool.getFetters("骑士", "贵族")));
        heros.put("VN", new Hero("VN", 1, FetterPool.getFetters("贵族", "游侠")));
        heros.put("剑姬", new Hero("剑姬", 1, FetterPool.getFetters("贵族", "剑士")));
        heros.put("腿女", new Hero("腿女", 1, FetterPool.getFetters("海克斯", "剑士")));
        heros.put("豹女", new Hero("豹女", 1, FetterPool.getFetters("狂野", "换形师")));
        heros.put("狼人", new Hero("狼人", 1, FetterPool.getFetters("狂野", "斗士")));
        heros.put("卡萨", new Hero("卡萨丁", 1, FetterPool.getFetters("虚空", "法师")));
        heros.put("螳螂", new Hero("螳螂", 1, FetterPool.getFetters("虚空", "刺客")));
        heros.put("蜘蛛", new Hero("蜘蛛", 1, FetterPool.getFetters("恶魔", "换形师")));

        heros.put("狐狸", new Hero("狐狸", 2, FetterPool.getFetters("狂野", "法师")));
        heros.put("奥巴马", new Hero("奥巴马", 2, FetterPool.getFetters("贵族", "枪手")));
        heros.put("维鲁斯", new Hero("维鲁斯", 2, FetterPool.getFetters("恶魔", "游侠")));
        heros.put("挖掘机", new Hero("挖掘机", 2, FetterPool.getFetters("虚空", "斗士")));
        heros.put("布隆", new Hero("布隆", 2, FetterPool.getFetters("极地", "护卫")));
        heros.put("劫", new Hero("劫", 2, FetterPool.getFetters("刺客", "忍者")));
        heros.put("慎", new Hero("慎", 2, FetterPool.getFetters("剑士", "忍者")));
        heros.put("杰斯", new Hero("杰斯", 2, FetterPool.getFetters("海克斯", "换形师")));
        heros.put("卡牌", new Hero("卡牌", 2, FetterPool.getFetters("海盗", "法师")));
        heros.put("派克", new Hero("派克", 2, FetterPool.getFetters("海盗", "刺客")));
        heros.put("机器", new Hero("机器", 2, FetterPool.getFetters("机器人", "斗士")));
        heros.put("露露", new Hero("露露", 2, FetterPool.getFetters("约德尔人", "法师")));
        heros.put("冰女", new Hero("冰女", 2, FetterPool.getFetters("极地", "元素使")));

        heros.put("剑魔", new Hero("剑魔", 3, FetterPool.getFetters("恶魔", "剑士")));
        heros.put("莫甘娜", new Hero("莫甘娜", 3, FetterPool.getFetters("恶魔", "法师")));
        heros.put("寡妇", new Hero("寡妇", 3, FetterPool.getFetters("恶魔", "刺客")));
        heros.put("狗熊", new Hero("狗熊", 3, FetterPool.getFetters("极地", "斗士")));
        heros.put("船长", new Hero("船长", 3, FetterPool.getFetters("海盗", "枪手", "剑士")));
        heros.put("狮子狗", new Hero("狮子狗", 3, FetterPool.getFetters("狂野", "刺客")));
        heros.put("蔚", new Hero("蔚", 3, FetterPool.getFetters("海克斯", "斗士")));
        heros.put("小法", new Hero("小法", 3, FetterPool.getFetters("约德尔人", "法师")));
        heros.put("凯南", new Hero("凯南", 3, FetterPool.getFetters("约德尔人", "忍者", "元素使")));
        heros.put("卡特", new Hero("卡特", 3, FetterPool.getFetters("帝国", "刺客")));
        heros.put("寒冰", new Hero("寒冰", 3, FetterPool.getFetters("极地", "游侠")));
        heros.put("波比", new Hero("波比", 3, FetterPool.getFetters("约德尔人", "骑士")));
        heros.put("龙女", new Hero("龙女", 3, FetterPool.getFetters("龙", "换形师")));

        heros.put("德莱文", new Hero("德莱文", 4, FetterPool.getFetters("帝国", "剑士")));
        heros.put("火男", new Hero("火男", 4, FetterPool.getFetters("恶魔", "元素使")));
        heros.put("阿卡丽", new Hero("阿卡丽", 4, FetterPool.getFetters("忍者", "刺客")));
        heros.put("金克斯", new Hero("金克斯", 4, FetterPool.getFetters("海克斯", "枪手")));
        heros.put("千珏", new Hero("千珏", 4, FetterPool.getFetters("暗影", "游侠")));
        heros.put("猪妹", new Hero("猪妹", 4, FetterPool.getFetters("极地", "骑士")));
        heros.put("女坦", new Hero("女坦", 4, FetterPool.getFetters("贵族", "护卫")));
        heros.put("龙王", new Hero("龙王", 4, FetterPool.getFetters("龙", "法师")));

        heros.put("天使", new Hero("天使", 5, FetterPool.getFetters("贵族", "骑士")));
        heros.put("乌鸦", new Hero("乌鸦", 5, FetterPool.getFetters("帝国", "恶魔", "换形师")));
        heros.put("亚索", new Hero("亚索", 5, FetterPool.getFetters("浪人", "剑士")));
        heros.put("女枪", new Hero("女枪", 5, FetterPool.getFetters("海盗", "枪手")));
        heros.put("死歌", new Hero("死歌", 5, FetterPool.getFetters("暗影", "法师")));
        heros.put("冰鸟", new Hero("冰鸟", 5, FetterPool.getFetters("极地", "元素使")));

    }

    public static Hero getHero(String name) {
        return heros.get(name);
    }

    public static String[] getHeroNames(Integer cost) {
        Object[] heroNameObjs = HeroPool.heros.keySet().toArray();
        String[] heroNames = new String[heroNameObjs.length];

        int j = 0;
        for (int i = 0; i < heroNameObjs.length; i++) {
            String heroName = heroNameObjs[i].toString();
            if (cost != null) {
                if (getHero(heroName).getCost() > cost) {
                    continue;
                }
            }
            heroNames[j++] = heroName;
        }

        String[] rtnNames = new String[j];
        for (int i = 0; i < rtnNames.length; i++) {
            rtnNames[i] = heroNames[i];
        }
        return rtnNames;
    }

    public static List<Hero> getRandomHeros(int count) {
        return getRandomHeros(count, null);
    }

    public static List<Hero> getRandomHeros(int count, Integer cost) {
        String[] heroNames = HeroPool.getHeroNames(cost);
        int[] randomArray = RandomUtil.genRandomArray(heroNames.length, count);

        List randomHeros = new ArrayList();
        for (int randomNum : randomArray) {
            String randomHeroName = heroNames[randomNum];
            randomHeros.add(HeroPool.getHero(randomHeroName));
        }

        return randomHeros;
    }

}
