package com.shizy.job.gen.docx;

import com.shizy.job.gen.docx.po.TableBase;
import com.shizy.job.gen.docx.po.TcBase;
import com.shizy.job.gen.docx.po.TrBase;
import com.shizy.job.gen.docx.po.WordBase;
import com.shizy.job.gen.docx.util.FileContentUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocxGenerator {

    //工程中运行才读的到，打包后不能这么读
    public static String templatePath = "src/main/java/com/shizy/job/gen/docx/template/";

    //工程中运行才写的到
    public static String outputPath = "src/main/java/com/shizy/job/gen/docx/output/zzz.xml";

    public void genDocx() {

        //查数据
        List<String> tableHead = QueryData.getTableHead();
        List<String> tableColumn = QueryData.getTableColumn();
        List<List<Map<String, String>>> dataList = QueryData.getDataList();

        //根据数据生成xml
        String docxXml = genXml(tableHead, tableColumn, dataList);

        //xml写带文件
        FileContentUtils.writeFile(new File(outputPath), docxXml);
    }

    public String genXml(List<String> tableHead, List<String> tableColumn, List<List<Map<String, String>>> dataList) {
        //List<Map<String, String>> -> List<Map<String, Object>>

        StringBuilder wordBaseStr = new StringBuilder();

        List<Map<String, Object>> dataList2 = dataFormat1(tableColumn, dataList);
        for (Map<String, Object> oneTableData : dataList2) {
            List<List<String>> list = (List<List<String>>) oneTableData.get("list");
            String tableName = (String) oneTableData.get("tableName");
            String comments = (String) oneTableData.get("comments");

            TableBase tableBase = new TableBase(genTable(list, tableHead), tableName, comments);

            wordBaseStr.append(tableBase.toString());
        }

        return new WordBase(wordBaseStr.toString()).toString();
    }

    private String genTable(List<List<String>> oneTableData, List<String> tableHead) {
        StringBuilder tableBaseStr = new StringBuilder();

        tableBaseStr.append(genTr(tableHead));

        for (List<String> rowValue : oneTableData) {
            tableBaseStr.append(genTr(rowValue));
        }
        return tableBaseStr.toString();
    }

    private String genTr(List<String> rowValue) {
        StringBuilder trBaseStr = new StringBuilder();
        for (String cellValue : rowValue) {
            trBaseStr.append(new TcBase(cellValue).toString());
        }
        TrBase trBase = new TrBase(trBaseStr.toString());
        return trBase.toString();
    }

    private List<Map<String, Object>> dataFormat1(List<String> tableColumn, List<List<Map<String, String>>> dataList) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (List<Map<String, String>> list : dataList) {
            List<List<String>> listRecode = new ArrayList<>();
            for (Map<String, String> record : list) {
                List<String> record2 = new ArrayList<>();
                for (String column : tableColumn) {
                    String value = getColumnValue(column, record);
                    record2.add(value);
                }
                listRecode.add(record2);
            }

            Map listRecodeMap = new HashMap();
            listRecodeMap.put("list", listRecode);
            listRecodeMap.put("comments", list.get(0).get("TABLE_COMMENTS"));
            listRecodeMap.put("tableName", list.get(0).get("TABLE_NAME"));

            result.add(listRecodeMap);
        }
        return result;
    }

    private String getColumnValue(String column, Map<String, String> record) {
        for (Map.Entry entry : record.entrySet()) {
            if (entry.getKey().equals(column)) {
                if (entry.getValue() == null) {
                    return entry.getKey().toString();
                }
                return escapeSymbol(entry.getValue().toString());
            }
        }
        return null;
    }

    private String escapeSymbol(String value) {
        return value.replace("&", "&amp;");
    }

    public static void main(String[] args) {
        new DocxGenerator().genDocx();
    }

}

















