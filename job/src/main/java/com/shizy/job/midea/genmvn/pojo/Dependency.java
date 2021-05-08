package com.shizy.job.midea.genmvn.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dependency
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/4/29 17:57
 */
@Data
@NoArgsConstructor
public class Dependency {

    public Dependency(String dependencyPath) {
        this.dependencyPath = dependencyPath;
    }

    /**
     * 依赖包所在的文件夹路径
     * E:\Program Files\apache-maven-3.6.3\repository\com\midea\jr\gfp\gfp.gfsm.service\0.0.2-SNAPSHOT
     */
    private String dependencyPath;

    /**
     * mvn deploy命令必要条件 groupId
     * com.midea.jr.gfp
     */
    private String groupId;

    /**
     * mvn deploy命令必要条件 artifactId
     * gfp.gfsm.service
     */
    private String artifactId;

    /**
     * mvn deploy命令必要条件 version
     * 0.0.2-SNAPSHOT
     */
    private String version;

    /**
     * mvn deploy命令必要条件 packaging
     * jar/pom
     */
    private String packaging;

    /**
     * mvn deploy命令必要条件 file
     * E:/Program Files/apache-maven-3.6.3/repository/com/midea/finance/framework/finance.framework.mq/0.0.1-SNAPSHOT/finance.framework.mq-0.0.1-SNAPSHOT.jar
     */
    private String file;

    /**
     * mvn deploy命令必要条件 url
     * http://nexus.meicloud.com/repository/b7f31d17eff5490aac04c96f7e9039f6_1/
     */
    private String url;

    /**
     * mvn deploy命令必要条件 repositoryId
     * devops-deploy-admin
     */
    private String repositoryId;

    /**
     * 组装好的 mvn deploy 命令
     */
    private String mvnDeployCommand;

}

