package com.shizy.utils.properties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    /**
     * 读取文件内容
     * <p>
     * main运行
     * FileContentUtils.readFileContent(new File("src/main/java/com/shizy/job/gen/docx/template/tr_base.xml"));//填充${tc_base}
     * <p>
     * tomcat运行
     * FileContentUtils.readFileContent(new File(this.getClass().getResource("").getPath()+"zzz.txt"));//打包后可能没有txt文件，注意放到resource
     */
    public static String readFileContent(File file) {

        if (!file.exists()) {
            throw new RuntimeException("文件[" + file.toString() + "]不存在");
        }

        StringBuilder sbf = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {//try with resource
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr).append("\r\n");
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sbf.toString();
    }

    /**
     * 将内容写入到文件
     */
    public static void writeFile(File file, String content, boolean append) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileWriter writer = new FileWriter(file, append)) {//append为true时为追加模式，false或缺省则为覆盖模式
            writer.append(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file, String content) {
        writeFile(file, content, false);
    }

    /**
     * 遍历指定路径下的全部文件/文件夹
     * 用例见：FileUtils.searchFileByKeyWord
     *
     * @param filter 返回true则在当前pathname继续往下迭代  例：return pathname.isDirectory();
     */
    public static void recursionFile(String filePath, FileFilter filter) {
        recursionFile0(fileCheckAndCreate(filePath), filter);
    }

    private static void recursionFile0(File file, FileFilter filter) {
        for (File subFile : Objects.requireNonNull(file.listFiles(filter))) {
            if (subFile.isDirectory()) {
                recursionFile0(subFile, filter);
            }
        }
    }

    /**
     * 查找文件名包含关键字keyWord的文件list
     */
    public static List<File> searchFileByKeyWord(String filePath, String keyWord) {
        Objects.requireNonNull(keyWord);
        List<File> resultFiles = new ArrayList<>();
        recursionFile(filePath, pathname -> {
            //当前文件是文件，且文件名与keyWord匹配
            if ((pathname.isFile() && pathname.getName().toLowerCase().equals(keyWord.toLowerCase()))) {
                resultFiles.add(pathname);
            }
            return pathname.isDirectory();//当前文件是目录，则返回给迭代器继续迭代
        });
        return resultFiles;
    }

    public static File fileCheckAndCreate(String filePath) {
        Objects.requireNonNull(filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("文件或目录不存在：" + file.getAbsolutePath());
        }
        return file;
    }
}

















