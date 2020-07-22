package com.shizy.job.sql;

import com.shizy.feature.pinyin.PinyinUtil;

public class CreatTableSQL {

    public static void main(String[] args) {
        new CreatTableSQL().genSQL();
    }

    private void genSQL() {
        String tableName = "专业同步配置";
        String columnName = "年份，指标名称，单位，指标类型，值预期";

        String columnSql = genColumnSql(columnName);
        String sql = "create table " + PinyinUtil.getPinYinHeadChar(tableName).toUpperCase() + "(" + columnSql + ") comment '" + tableName + "表';";

        System.out.println(sql);
    }

    private String genColumnSql(String columnNames) {
        StringBuilder sql = new StringBuilder();
        for (String name : columnNames.split("，")) {
            sql.append(PinyinUtil.getPinYinHeadChar(name).toUpperCase()).append(" varchar(255) null comment '").append(name).append("', ");
        }

        return sql.substring(0, sql.length() - 2);
    }

}
