# maven deploy命令生成工具

MvnDeployGeneretor - 根据本地repository生成maven deploy命令

## 使用说明

1、修改 MvnDeployGeneretor#main() 的
- 资源库路径
- 开始迭代路径
- repositoryId
- 推送仓库url
- outputPath

2、运行 MvnDeployGeneretor#main()

3、查看并运行生成的`maven-deploy.bat`文件
- 需要管理员权限运行
- 需要配置mvn环境变量