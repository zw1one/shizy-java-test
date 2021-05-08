maven及工程部署相关

#### 1、打包成可直接运行的jar包

* Maven打包(包含jar包内容)
```
mvn clean compile assembly:single
```

使用带依赖的jar包`com.shizy-java-test-1.0-SNAPSHOT-jar-with-dependencies.jar`
  
配置正确即可使用，例子见：`test/java/example`