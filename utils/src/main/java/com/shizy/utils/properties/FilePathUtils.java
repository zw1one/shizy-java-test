package com.shizy.utils.properties;

import java.io.File;
import java.util.Objects;

/**
 * FilePathUtils
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/5/8 14:23
 */
public class FilePathUtils {

    /**
     * IDAE中运行：编译后 src\main\resources 的中的绝对路径
     * 服务器jar包运行：
     * 服务器tomcat运行：
     */
    public static String getTargetResourcesPath() {
        return Objects.requireNonNull(FilePathUtils.class.getClassLoader().getResource("")).getFile();
    }

    /**
     * IDAE中运行：获取 当前工程 的绝对路径
     * 服务器jar包运行：
     * 服务器tomcat运行：
     */
    public static String getProjectPath() {
        return new File("").getAbsolutePath() + "/";
    }
}

























