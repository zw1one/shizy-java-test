package com.shizy.job.gen.docx;

import com.shizy.job.gen.docx.util.FileContentUtils;
import com.shizy.job.gen.docx.util.JdbcService;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryData {

    public static List<String> getTableHead() {
        List<String> tableHead = new ArrayList<>();
        tableHead.add("名称");
        tableHead.add("字段名称");
        tableHead.add("数据类型");
        tableHead.add("长度");
        tableHead.add("非空");
        tableHead.add("主键");
        return tableHead;
    }

    public static List<String> getTableColumn() {
        List<String> tableColumn = new ArrayList<>();
        tableColumn.add("COLUMN_COMMENTS");
        tableColumn.add("COLUMN_NAME");
        tableColumn.add("DATA_TYPE");
        tableColumn.add("DATA_LENGTH");
        tableColumn.add("NULLABLE");
        tableColumn.add("ISPRIMARY");
        return tableColumn;
    }

    public static List<List<Map<String, String>>> getDataList() {
        List<Map<String, String>> dataList = queryDataBase();

        Map<String, List<Map<String, String>>> groupMap1 = dataList.stream().collect(Collectors.groupingBy(e ->
                e.get("TABLE_NAME")
        ));

        List<List<Map<String, String>>> result = new ArrayList<>();
        for (Map.Entry entry : groupMap1.entrySet()) {
            List<Map<String, String>> list = (List<Map<String, String>>) entry.getValue();
            list.sort(Comparator.comparing(o -> o.get("COLUMN_NAME")));
            result.add(list);
        }
        result.sort(Comparator.comparing(o -> o.get(0).get("TABLE_NAME")));

        //debug用 有的表有特殊字符 word打开会报错
//        List<List<Map<String, String>>> result2 = new ArrayList<>();
//        for (int i = 78; i < 79; i++) {
//            result2.add(result.get(i));
//        }
//        result = result2;

        return result;
    }

    private static List queryDataBase() {
        JdbcService jdbcService = new JdbcService("oracle.jdbc.OracleDriver",
                "jdbc:oracle:thin:@192.168.37.12:1521:orcl",
                "ly_cb",
                "ly_cb");

        String sql = FileContentUtils.readFileContent(new File("src/main/java/com/shizy/job/gen/docx/sql/sql.txt"));
        return jdbcService.query(sql);
    }

//    public static List<List<Map<String, String>>> getDataList2() {
//        List<List<Map<String, String>>> result = new ArrayList<>();
//
//        List<Map<String, String>> dataList = new ArrayList<>();
//        Map data1 = new HashMap();
//        data1.put("TABLE_COMMENTS", "主流程节点");
//        data1.put("TABLE_NAME", "LY_YY_CB_ZLCJD");
//        data1.put("COLUMN_COMMENTS", "报表名称");
//        data1.put("COLUMN_NAME", "REPORTNAME");
//        data1.put("DATA_TYPE", "VARCHAR2");
//        data1.put("DATA_LENGTH", "128");
//        data1.put("NULLABLE", "TRUE");
//        data1.put("ISPRIMARY", "TRUE");
//
//        dataList.add(data1);
//        dataList.add(data1);
//
//        result.add(dataList);
//        result.add(dataList);
//
//        return result;
//    }
}
























