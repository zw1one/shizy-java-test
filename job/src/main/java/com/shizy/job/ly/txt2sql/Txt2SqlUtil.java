package com.shizy.job.ly.txt2sql;

import com.shizy.utils.file.FilePathUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.*;
import java.util.*;

/**
 * <p>
 * 工具类：从Txt文件生成建表sql
 * </p>
 *
 * @author cuiwj
 * @since 2020/7/7
 */
public class Txt2SqlUtil {

    public static void main(String[] args) {
        String inputFilePath = FilePathUtils.getProjectPath() + "job/src/main/java/com/shizy/job/ly/txt2sql/input.txt";
        String outputFilePath = FilePathUtils.getProjectPath() + "job/src/main/java/com/shizy/job/ly/txt2sql/output.sql";
        genSql(inputFilePath, outputFilePath);
    }

    /**
     * 生成建表sql文件
     *
     * @param inputFilePath txt文件路径
     */
    public static void genSql(String inputFilePath, String outputFilePath) {
        File file = new File(outputFilePath);
        String content;
        if (file.exists()) {
            file.delete();
        }
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             BufferedWriter bfw = new BufferedWriter(fw)) {
            List<Map<String, Object>> params = readTxt(inputFilePath);
            for (Map<String, Object> param : params) {
                content = genSql0(param);
                bfw.write(content);
                bfw.newLine();
            }
            System.out.println("已生成sql文件,路径：" + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> readTxt(String filePath) throws IOException {
        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        List<Map<String, String>> cols = new ArrayList<>();//用来存放各列定义数据
        boolean isNewTb = true;
        String[] tmp;
        BufferedReader fin = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = fin.readLine()) != null) {
            line = line.trim();
            if ("".equals(line)) {
                if (!param.isEmpty()) {
                    param.put("cols", cols);
                    params.add(param);
                    param = new HashMap<>();
                    cols = new ArrayList<>();
                    isNewTb = true;
                }
                continue;
            }
            tmp = line.split("\\s+");
            if (isNewTb) { // 获取表注释和表名
                if (tmp.length < 2) {
                    throw new RuntimeException("表注释或表名不能省略！");
                }
                param.put("tbComment", tmp[0]);
                param.put("tbName", tmp[1].toUpperCase());
                isNewTb = false;
            } else { // 获取列注释和列名
                if (tmp.length == 1) { // 若列名省略根据列注释生成列名
                    String colName = Pinyin4jUtil.getFirstPinYin(tmp[0]);
                    tmp = Arrays.copyOf(tmp, tmp.length + 1);
                    tmp[1] = colName;
                }
                if (line.contains("key")) { // 主键列
                    if (tmp.length == 2) {
                        String pkName = Pinyin4jUtil.getFirstPinYin(tmp[0]);
                        tmp[1] = pkName;
                    }
                    param.put("pkComment", tmp[0]);
                    param.put("pkName", tmp[1].toUpperCase());

                } else { // 普通列
                    Map<String, String> col = new HashMap<>(4);
                    col.put("colComment", tmp[0]);
                    col.put("colName", tmp[1].toUpperCase());
                    cols.add(col);
                }
            }
        }
        if (!param.isEmpty()) { // 防止漏掉最后一张表
            param.put("cols", cols);
            params.add(param);
        }
        return params;
    }

    private static String genSql0(Map<String, Object> param) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("drop table \"%s\" cascade constraints;\n", param.get("tbName")));
        result.append(String.format("create table \"%s\" \n" + "(", param.get("tbName")));
        if (param.containsKey("pkName")) { //主键列
            result.append(String.format("\"%s\"                 VARCHAR(32)         not null,\n", param.get("pkName")));
        }
        // 处理各列
        List<Map<String, String>> cols = (List<Map<String, String>>) param.get("cols");
        if (cols != null) {
            for (Map<String, String> col : cols) {
                result.append(String.format(" \"%s\"        VARCHAR(100),\n", col.get("colName")));
            }
        }
        //主键约束
        if (param.containsKey("pkName")) { //主键列
            result.append(String.format(" constraint PK_%s primary key (\"%s\")\n",
                    ((String) param.get("tbName")).toUpperCase(), param.get("pkName")));
        } else {
            result.deleteCharAt(result.lastIndexOf(","));
        }
        result.append(");\n");
        //表注释
        result.append(String.format("comment on table \"%s\" is '%s';\n", param.get("tbName"), param.get("tbComment")));
        //主键列注释
        if (param.containsKey("pkName")) {
            result.append(String.format("comment on column \"%s\".\"%s\" is '%s';\n",
                    param.get("tbName"), param.get("pkName"), param.get("pkComment")));
        }
        //各列注释
        if (cols != null) {
            for (Map<String, String> col : cols) {
                result.append(String.format("comment on column \"%s\".\"%s\" is '%s';\n",
                        param.get("tbName"), col.get("colName"), col.get("colComment")));
            }
        }
        return result.toString();
    }

    private static class Pinyin4jUtil {
        public static String getFirstPinYin(String text) {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            StringBuilder firstPinyin = new StringBuilder();
            char[] hanyuArr = text.trim().toCharArray();
            try {
                for (char c : hanyuArr) {
                    if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                        String[] pys = PinyinHelper.toHanyuPinyinStringArray(c, format);
                        firstPinyin.append(pys[0].charAt(0));
                    } else {
                        firstPinyin.append(c);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
            return firstPinyin.toString();
        }
    }

}
