package com.shizy.job.midea.genmvn;

import com.shizy.job.midea.genmvn.pojo.Dependency;
import com.shizy.utils.bean.BeanUtil;
import com.shizy.utils.file.FileUtils;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.*;

/**
 * mvnDeployGeneretor
 *
 * @author by shizy19 shizy19@meicloud.com
 * @Date 2021/4/29 17:34
 */
@AllArgsConstructor
public class MvnDeployGeneretor {

    private String repositoryFilePath; // repository根目录
    private String[] findFilePath; // 开始迭代的目录
    private String outputPath; // mvn命令输出文件路径
    private String repositoryId; // maven命令的repositoryId 用于匹配推送的账号密码id
    private String url; // maven命令的url 推送仓库的url

    public static void main(String[] args) {

        String repositoryFilePath = "E:\\Program Files\\apache-maven-3.6.3\\repository\\";

        String[] findFilePath = {
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\midea\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\ibm\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\artofsolving\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\oracle\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\baidu\\ueditor\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\deepoove\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\com\\baidu\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\org\\json\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\org\\jasig\\cas\\client\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\io\\springfox\\",
//                "E:\\Program Files\\apache-maven-3.6.3\\repository\\io\\swagger\\",

                "E:\\Program Files\\apache-maven-3.6.3\\repository\\",
        };

        String outputPath = "D:\\MyData\\shizy19\\Desktop\\maven-deploy.bat";

        String repositoryId = "devops-deploy-admin";
        String url = "http://nexus.meicloud.com/repository/b7f31d17eff5490aac04c96f7e9039f6_1/";

        new MvnDeployGeneretor(repositoryFilePath, findFilePath, outputPath, repositoryId, url).generete();
    }

    public void generete() {

        // 1、读取指定目录，遍历出所有dependency文件夹，组装成dependencyList
        List<List<File>> dependencyFiles = new ArrayList<>();
        for (String filePath : findFilePath) {
            dependencyFiles.add(traverseFilePath(filePath));
        }

        // 2、遍历读取dependencyFileList文件夹下的内容，组装到dependency中，生成mvn命令
        List<List<Dependency>> dependencys = new ArrayList<>();
        dependencyFiles.forEach(files -> {
            dependencys.add(assembleDependency(files));
        });

        // 3、遍历dependencyList，打印出mvn命令
        printMvnCommand(dependencys);
    }

    /**
     * ******************************************************************************************************************
     */
    private List<File> traverseFilePath(String filePath) {
        Map<String, File> dependencyFileMap = new HashMap<>();
        FileUtils.recursionFile(filePath, pathname -> {
            //如果当前文件不是文件夹，则讲上级文件夹加入map。重复加入的会覆盖，故不会重复
            if (!pathname.isDirectory()) {
                dependencyFileMap.put(pathname.getParentFile().getAbsolutePath(), pathname.getParentFile());
            }
            return true;//返回true则代表继续遍历
        });
        return new ArrayList(dependencyFileMap.values());
    }

    /**
     * ******************************************************************************************************************
     */
    private List<Dependency> assembleDependency(List<File> dependencyFiles) {
        List<Dependency> dependencys = Collections.synchronizedList(new ArrayList<>());
        dependencyFiles.parallelStream().forEach(dependencyFile -> {
            // 按文件夹名称读取maven信息。因为有些包中没有maven-metadata.xml文件，但文件夹路径不会错
            Dependency dependency = new Dependency();
            dependency.setPackaging("jar");

            setDependencyInfo(dependencyFile, dependency);//set version, artifactId, groupId, file
            if (dependency.getFile() == null) {
                return;
            }

            dependency.setDependencyPath(dependencyFile.getAbsolutePath());
            dependency.setUrl(this.url);
            dependency.setRepositoryId(this.repositoryId);
            dependency.setMvnDeployCommand(getMvnDeployCommand(dependency));

            //提交jar
            checkFileAndAppend(dependency, dependencys);

            //提交pom。因为一个被依赖的工程不仅有jar包，还有工程本身的依赖
            Dependency dependencyPom = BeanUtil.copyProperties(dependency, Dependency.class);
            dependencyPom.setPackaging("pom");
            setDependencyInfo(dependencyFile, dependencyPom);
            dependencyPom.setMvnDeployCommand(getMvnDeployCommand(dependencyPom));

            //提交pom
            checkFileAndAppend(dependencyPom, dependencys);
        });
        return dependencys;
    }

    private String getMvnDeployCommand(Dependency dependency) {
        return new StringBuilder()
                .append("mvn deploy:deploy-file")
                .append(" -DgroupId=").append(dependency.getGroupId())
                .append(" -DartifactId=").append(dependency.getArtifactId())
                .append(" -Dversion=").append(dependency.getVersion())
                .append(" -Dpackaging=").append(dependency.getPackaging())
                .append(" -Dfile=").append("\"").append(dependency.getFile()).append("\"")
                .append(" -Durl=").append(dependency.getUrl())
                .append(" -DrepositoryId=").append(dependency.getRepositoryId())
                .toString();
    }

    /**
     * @param dependencyFile //E:\Program Files\apache-maven-3.6.3\repository\com\midea\jr\gfp\gfp.gfsm.service\0.0.2-SNAPSHOT
     */
    private void setDependencyInfo(File dependencyFile, Dependency dependency) {
        String version = dependencyFile.getName(); //0.0.2-SNAPSHOT
        String artifactId = dependencyFile.getParentFile().getName(); //gfp.gfsm.service

        String groupId = null;
        try {
            groupId = dependencyFile.getParentFile().getParentFile().getAbsolutePath()
                    .substring(FileUtils.checkAndGetFile(this.repositoryFilePath).getAbsolutePath().length())
                    .replaceAll("\\\\", ".").substring(1); //com.midea.jr.gfp
        } catch (Exception e) {
            // debug
//            e.printStackTrace();
//            dependency.setVersion("");
//            dependency.setArtifactId("");
//            dependency.setGroupId("");
//            dependency.setFile("");
            return;
        }

        String file = new StringBuilder()
                .append(dependencyFile.getAbsolutePath()).append("/").append(artifactId).append("-").append(version).append(".").append(dependency.getPackaging())
                .toString().replaceAll("\\\\", "/"); //dependencyFile + gfp.gfsm.service-0.0.2-SNAPSHOT.jar

        dependency.setVersion(version);
        dependency.setArtifactId(artifactId);
        dependency.setGroupId(groupId);
        dependency.setFile(file);
    }

    /**
     * 校验要deploy的文件是否存在，因为有的工程没有pom要提交。有的pom没有jar要提交
     */
    private void checkFileAndAppend(Dependency dependency, List<Dependency> dependencys) {
        if (new File(dependency.getFile()).exists()) {
            dependencys.add(dependency);
        }
    }

    /**
     * ******************************************************************************************************************
     */
    private void printMvnCommand(List<List<Dependency>> dependencys) {
        StringBuilder content = new StringBuilder();
        dependencys.forEach(dependencies -> {
            dependencies.forEach(dependency ->
                    content.append("call ")// call命令可以在dos调用mvn命令时不终止当前脚本运行
                            .append(dependency.getMvnDeployCommand()).append("\r\n"));
        });
        content.append("pause");//dos窗口运行完成后不关闭

        FileUtils.writeFile(new File(outputPath), content.toString());
    }
}















