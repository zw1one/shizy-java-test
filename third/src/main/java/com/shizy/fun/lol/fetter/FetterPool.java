package com.shizy.fun.lol.fetter;

import com.shizy.fun.lol.hero.Hero;

import java.util.*;

public class FetterPool {

    public static Map<String, Fetter> fetters = new HashMap();

    /**
     * todo
     * 羁绊做成以Fetter为接口，每种羁绊为一个class
     */

    static {
        fetters.put("虚空", new Fetter("虚空", new int[]{3}));
        fetters.put("帝国", new Fetter("帝国", new int[]{2, 4}));
        fetters.put("贵族", new Fetter("贵族", new int[]{3, 6}));
        fetters.put("狂野", new Fetter("狂野", new int[]{2, 4}));
        fetters.put("约德尔人", new Fetter("约德尔人", new int[]{3, 6}));
        fetters.put("暗影", new Fetter("暗影", new int[]{2}));
        fetters.put("恶魔", new Fetter("恶魔", new int[]{2, 4, 6}));
        fetters.put("海盗", new Fetter("海盗", new int[]{3}));
        fetters.put("龙", new Fetter("龙", new int[]{2}));
        fetters.put("忍者", new Ninja("忍者", new int[]{1, 4}));
        fetters.put("海克斯", new Fetter("海克斯", new int[]{2, 4}));
        fetters.put("极地", new Fetter("极地", new int[]{2, 4, 6}));
        fetters.put("机器人", new Fetter("机器人", new int[]{1}));
        fetters.put("浪人", new Fetter("浪人", new int[]{1}));

        fetters.put("换形师", new Fetter("换形师", new int[]{3, 6}));
        fetters.put("游侠", new Fetter("游侠", new int[]{2, 4}));
        fetters.put("斗士", new Fetter("斗士", new int[]{2, 4, 6}));
        fetters.put("剑士", new Fetter("剑士", new int[]{3, 6, 9}));
        fetters.put("枪手", new Fetter("枪手", new int[]{2, 4, 6}));
        fetters.put("法师", new Fetter("法师", new int[]{3, 6}));
        fetters.put("骑士", new Fetter("骑士", new int[]{2, 4, 6}));
        fetters.put("护卫", new Fetter("护卫", new int[]{2}));
        fetters.put("刺客", new Fetter("刺客", new int[]{3, 6}));
        fetters.put("元素使", new Fetter("元素使", new int[]{3}));
    }

    public static Fetter getFetter(String name) {
        return fetters.get(name);
    }

    public static List<Fetter> getFetters(String... name) {
        List list = new ArrayList();
        for (String s : name) {
            list.add(getFetter(s));
        }
        return list;
    }

    public static Map<String, Integer> getFetterTotal(List<Hero> heros) {
        Map<String, Integer> fetterMap = new HashMap();
        //迭代所有羁绊
        for (Hero hero : heros) {
            List<Fetter> fetters = hero.getFetters();
            for (Fetter fetter : fetters) {
                if (fetter == null) {
                    System.out.println("羁绊不存在");
                }
                String fetterName = fetter.getName();
                if (fetterMap.containsKey(fetterName)) {
                    //若羁绊存在，则+1
                    int fetterCnt = fetterMap.get(fetterName);
                    fetterMap.put(fetterName, ++fetterCnt);
                } else {
                    //若羁绊不存在，则创建
                    fetterMap.put(fetterName, 1);
                }
            }
        }
        return fetterMap;
    }


    public static List<EffectiveFetter> effectiveFetters(Map fetters) {
        List<EffectiveFetter> effective = new ArrayList();
        for (Map.Entry entry : (Set<Map.Entry>) fetters.entrySet()) {
            String fetterName = (String) entry.getKey();
            int fetterCnt = (int) entry.getValue();

            Fetter fetter = FetterPool.getFetter(fetterName);
            Object[] rtn = fetter.getFetterCnt(fetterCnt);

            if (rtn != null) {
                effective.add(new EffectiveFetter(fetterName, (int) rtn[0], (int) rtn[1], (int[]) rtn[2]));
            }
        }
        return effective;
    }

}




















