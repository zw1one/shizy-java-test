package com.shizy.job.ly.docx;

import com.shizy.job.ly.docx.po.TableBase;
import com.shizy.job.ly.docx.po.TcBase;
import com.shizy.job.ly.docx.po.TrBase;
import com.shizy.job.ly.docx.po.WordBase;
import com.shizy.utils.properties.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取数据库表结构，按模板格式将数据库表信息生成到docx文档。
 */
public class DocxGenerator {

    //工程中运行才读的到，打包后不能这么读
    public static String templatePath = "src/main/java/com/com/shizy/job/gen/docx/template/";

    //工程中运行才写的到
    public static String outputPath = "src/main/java/com/com/shizy/job/gen/docx/output/zzz.xml";

    public void genDocx() {

        //查数据
        List<String> tableHead = QueryData.getTableHead();
        List<String> tableColumn = QueryData.getTableColumn();
        List<List<Map<String, String>>> dataList = QueryData.getDataList();

        //根据数据生成xml
        String docxXml = genXml(tableHead, tableColumn, dataList);

        //xml写带文件
        FileUtils.writeFile(new File(outputPath), docxXml);
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
            listRecodeMap.put("comments", escapeSymbol(list.get(0).get("TABLE_COMMENTS")));
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
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&apos;")
                .replace("\"", "&quot;");
    }

    /**
     * 用法：
     * 1、修改数据库连接 见 queryDataBase()
     * 2、运行main 输出结果见outputPath
     * 3、将输出的zzz.xml 在word文档中，选择"文件"-"打开"，然后选zzz.xml 就可以打开了
     * 3-1、xml打不开，则使用getDataList() - debug用 来测试 （注释中有奇怪的符号，会被识别为xml标签 导致打不开 见escapeSymbol()）
     * 4、将内容复制到需要的word，或者新建一个word来放。因为毕竟是xml格式用word直接打开不方便
     */

    public static void main(String[] args) {
        new DocxGenerator().genDocx();
    }

}

















