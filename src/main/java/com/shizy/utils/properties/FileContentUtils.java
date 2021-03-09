package com.shizy.utils.properties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileContentUtils {

    /**
     * main运行
     * FileContentUtils.readFileContent(new File("src/main/java/com/shizy/job/gen/docx/template/tr_base.xml"));//填充${tc_base}
     *
     * tomcat运行
     * FileContentUtils.readFileContent(new File(this.getClass().getResource("").getPath()+"zzz.txt"));//打包后可能没有txt文件，注意放到resource
     *
     */
    public static String readFileContent(File file) {

        if (!file.exists()) {
            throw new RuntimeException("文件[" + file.toString() + "]不存在");
        }

        BufferedReader reader = null;
        StringBuilder sbf = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr).append("\r\n");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static void writeFile(File file, String content) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter writer = new FileWriter(file, false)) {//append为true时为追加模式，false或缺省则为覆盖模式
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> searchFile(String filePath, String keyWord) {
        Objects.requireNonNull(filePath);
        Objects.requireNonNull(keyWord);

        File folder = new File(filePath);// 默认目录
        if (!folder.exists()) {// 如果文件夹不存在
            throw new RuntimeException("目录不存在：" + folder.getAbsolutePath());
        }
        return searchFile0(folder, keyWord);
    }

    private static List<File> searchFile0(File folder, final String keyWord) {// 递归查找包含关键字的文件
        File[] subFolders = folder.listFiles(pathname -> {// 实现FileFilter类的accept方法
            // 目录或文件包含关键字
            return pathname.isDirectory()
                    || (pathname.isFile() && pathname.getName().toLowerCase().equals(keyWord.toLowerCase()));
        });

        List<File> result = new ArrayList<>();
        for (File subFolder : Objects.requireNonNull(subFolders)) {// 循环文件夹或文件
            if (subFolder.isFile()) {// 如果是文件则将文件添加到结果列表中
                result.add(subFolder);
            } else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                result.addAll(searchFile0(subFolder, keyWord));
            }
        }

        return result;
    }

}
