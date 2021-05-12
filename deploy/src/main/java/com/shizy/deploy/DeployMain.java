package com.shizy.deploy;

import com.shizy.utils.file.FilePathUtils;

/**
 * DeployMain
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/5/8 11:37
 */
public class DeployMain {

    public static void main(String[] args) {
        System.out.println(FilePathUtils.getProjectPath());
    }
}
