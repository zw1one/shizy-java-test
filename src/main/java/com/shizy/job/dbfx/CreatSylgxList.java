package com.shizy.job.dbfx;

import java.util.*;

public class CreatSylgxList {

    public static void main(String[] args) {
        new CreatSylgxList().doThis();
    }


    private void doThis() {

        String[] ylgxs = new String[]{
                "北京大学",
                "中国人民大学",
                "清华大学",
                "北京航空航天大学",
                "北京理工大学",
                "中国农业大学",
                "北京师范大学",
                "中央民族大学",
                "南开大学",
                "天津大学",
                "大连理工大学",
                "吉林大学",
                "哈尔滨工业大学",
                "复旦大学",
                "同济大学",
                "上海交通大学",
                "华东师范大学",
                "南京大学",
                "东南大学",
                "浙江大学",
                "中国科学技术大学",
                "厦门大学",
                "山东大学",
                "中国海洋大学",
                "武汉大学",
                "华中科技大学",
                "中南大学",
                "中山大学",
                "华南理工大学",
                "四川大学",
                "重庆大学",
                "电子科技大学",
                "西安交通大学",
                "西北工业大学",
                "兰州大学",
                "国防科技大学",
                "东北大学",
                "郑州大学",
                "湖南大学",
                "云南大学",
                "西北农林科技大学",
                "新疆大学",
        };

        String[] ylxks = new String[]{
                "北京交通大学",
                "北京工业大学",
                "北京科技大学",
                "北京化工大学",
                "北京邮电大学",
                "北京林业大学",
                "北京协和医学院",
                "北京中医药大学",
                "首都师范大学",
                "北京外国语大学",
                "中国传媒大学",
                "中央财经大学",
                "对外经济贸易大学",
                "外交学院",
                "中国人民公安大学",
                "北京体育大学",
                "中央音乐学院",
                "中国音乐学院",
                "中央美术学院",
                "中央戏剧学院",
                "中国政法大学",
                "天津工业大学",
                "天津医科大学",
                "天津中医药大学",
                "华北电力大学",
                "河北工业大学",
                "太原理工大学",
                "内蒙古大学",
                "辽宁大学",
                "大连海事大学",
                "延边大学",
                "东北师范大学",
                "哈尔滨工程大学",
                "东北农业大学",
                "东北林业大学",
                "华东理工大学",
                "东华大学",
                "上海海洋大学",
                "上海中医药大学",
                "上海外国语大学",
                "上海财经大学",
                "上海体育学院",
                "上海音乐学院",
                "上海大学",
                "苏州大学",
                "南京航空航天大学",
                "南京理工大学",
                "中国矿业大学",
                "南京邮电大学",
                "河海大学",
                "江南大学",
                "南京林业大学",
                "南京信息工程大学",
                "南京农业大学",
                "南京中医药大学",
                "中国药科大学",
                "南京师范大学",
                "中国美术学院",
                "安徽大学",
                "合肥工业大学",
                "福州大学",
                "南昌大学",
                "河南大学",
                "中国地质大学",
                "武汉理工大学",
                "华中农业大学",
                "华中师范大学",
                "中南财经政法大学",
                "湖南师范大学",
                "暨南大学",
                "广州中医药大学",
                "华南师范大学",
                "海南大学",
                "广西大学",
                "西南交通大学",
                "西南石油大学",
                "成都理工大学",
                "四川农业大学",
                "成都中医药大学",
                "西南大学",
                "西南财经大学",
                "贵州大学",
                "西藏大学",
                "西北大学",
                "西安电子科技大学",
                "长安大学",
                "陕西师范大学",
                "青海大学",
                "宁夏大学",
                "石河子大学",
                "中国石油大学",
                "宁波大学",
                "中国科学院大学",
                "第二军医大学",
                "第四军医大学",
        };

        Map dataMap = new HashMap();

        for (String schoolName : ylgxs) {
            if (!dataMap.containsKey(schoolName)) {
                Map record = new HashMap();
                record.put("schoolName", schoolName);
                record.put("一流高校", "是");
                dataMap.put(schoolName, record);
            } else {
                Map record = (Map) dataMap.get(schoolName);
                record.put("一流高校", "是");
            }
        }

        for (String schoolName : ylxks) {
            if (!dataMap.containsKey(schoolName)) {
                Map record = new HashMap();
                record.put("schoolName", schoolName);
                record.put("一流学科", "是");
                dataMap.put(schoolName, record);
            } else {
                Map record = (Map) dataMap.get(schoolName);
                record.put("一流学科", "是");
//                dataMap.put(schoolName, record);
            }
        }

        System.out.println();


        List dataList = new ArrayList();

        for (Map.Entry entry : (Set<Map.Entry>) dataMap.entrySet()) {
            Map record = (Map) entry.getValue();
            dataList.add(record);
        }

        for (Map record : (List<Map>) dataList) {
            String name = (String) record.get("schoolName");
            String 一流高校 = (String) record.get("一流高校");
            String 一流学科 = (String) record.get("一流学科");

            if (一流高校 == null) {
                一流高校 = "0";
            }else {
                一流高校 = "1";
            }
            if (一流学科 == null) {
                一流学科 = "0";
            }else {
                一流学科 = "1";
            }

            System.out.println(name + "\t" + 一流高校 + "\t" + 一流学科);
        }

    }


}






















