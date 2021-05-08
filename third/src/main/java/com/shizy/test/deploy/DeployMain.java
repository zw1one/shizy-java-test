package com.shizy.test.deploy;

import com.shizy.utils.properties.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DeployMain {

    private static final Logger logger = LoggerFactory.getLogger(DeployMain.class);

    public static void main(String[] args) {

        logger.info("Hello World!");

        System.out.println("Hello World2!");

        //get config demo
        Properties properties = PropertiesUtils.getPropertiesInDir(System.getProperty("user.dir") + "/config.properties");//读 jar包同目录(或工程根路径)的配置文件
        String mongoDBhost = properties.getProperty("mongoDB.host", "127.0.0.1");
        System.out.println(mongoDBhost);
    }

}
