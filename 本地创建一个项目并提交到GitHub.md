### 本地创建一个项目并提交到GitHub

往Git上提交代码，一般按照这个顺序  
1、本地安装Git客户端（非GitHub），成功后在

    Android Studio中Version Control --- Git ---Test  
2、Github账号配置到Android Studio  
3、配置忽略文件 --- 项目structure中配置或者在项目的.ignore文件中编写忽略规则
    
    一般Android项目忽略这些目录或文件：
        /build
        /app/build
        /.gradle
        /.idea
        local.properties
        *.iml  
4、VCS --- Import into --- Share on GitHub
    
    此时会先在当前GitHub账号下创建仓库
    成功后会提示需要提交的文件（代码）  
5、到此关联GitHub并且提交代码到仓库已经成功
6、以后有代码修改就会有提示，按照正常顺序提交即可