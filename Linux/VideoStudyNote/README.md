- 第一天

   命令解析器：shell，--unix操作系统终端中输入的命令就是shell，bash，--linux操作系统中
   输入的命令，两种称呼都可以。根据命令去调用可执行程序
   
   快捷键：
   
    - history：打印所有历史命令 ，对应 ctrl+P,可以逐条向上滚动，ctrl+N，可以逐条向下滚动，对应
                的方向键上下也可以。
   
    - 光标移动：ctrl+B使光标一个一个字符倒叙移动、ctrl+F使光标逐个正序移动，ctrl+A使光标
            移动到开头，ctrl+E使光标移动到结尾。方向键左右也可实现光标前后逐个移动
   
    - 删除字符：ctrl+H、Backspace 删除光标前的字符 ctrl+D删除光标后的字符，ctrl+U，删除光标
            前的所有字符
   
    - 自动补齐命令：按一次tab可以自动填充、按两次可以列出所有命令
    
    目录结构：Linux的目录结构是树状的。任意位置输入命令`ls /`可以查看根目录
    - bin目录:Binary缩写，存放的是常用命令文件
    
    - boot目录：存放的是开机启动的一些文件，包括一些连接文件以及镜像文件
    
    - dev目录：Device缩写，该目录存放的是Linux的外部设备，在Linux访问设备和访问文件的
    方式是相同的
    
    - etc目录：存放所有的系统管理所需的配置文件和子目录
    
    - home目录：存放的是所有用户的主目录
    
    - lib目录：存放的是最基本的动态连接共享库
    
    - list+found：当非法关机时，这里就会存放一些文件
    
    - media目录：系统会把U盘中的内容自动挂载到该目录下
    
    - mnt目录：手动把U盘中的内容挂载到该目录下
    
    - opt目录：主机额外安装软件需摆放的目录
    
    - proc目录：系统内存映射目录
    
    - root目录：超级用户的主目录
    
    - sbin目录：超级用户使用的的一些系统管理程序
    
    - usr目录： 非常重要的目录，类似Windows下的program files
    
    - usr/bin目录：系统用户使用的目录
    
    切换到超级用户 `sudo su`,成为超级用户后，目录后会有一个`#`，普通用户目录后会有一个`$`
    
    文件操作
    
    - 查看目录
    
    首先在联网站台下执行`sudo apt-get install tree`按照tree这个命令,tree可以以树状结构打印当前目录下的文件结构
    
    白色：普通文件、蓝色：目录、红色：压缩文件、绿色：可执行文件、青色：链接文件、黄色：设备文件、灰色：其他文件
    ,文件前面加点说明是隐藏文件
    
    ls 列出当前目录文件，ls -a，列出包含隐藏文件，ls -l，列出文件详细信息，ls -al 略
    
    - 创建目录
    
    mkdir [dirName]：创建一个目录；
    
    mkdir dir/dir1/dir2 -p：创建多层目录
    
    - 删除目录
    
    rmdir [dirName]：删除一个空目录（不常用）
    rm [dirName] -r：递归删除目录
    
    - 创建文件
    
    touch [fileName]:创建一个普通文件，或是修改已存在文件的时间
    
    - 拷贝文件
    
    cp [fromFile] [toFile]：将fromFile文件复制到toFile文件中
    
    - 查看文件内容
    
    cat [fileName]:查看文件内容并全部打印出来
    
    more [fileName]:部分查看文件内容
    
    less [fileName]:部分查看文件内容
    
    head [fileName] -10:查看正数10行
    
    tail [fileName] -10:查看倒数10行
    
    - 修改文件名
    
    mv originName newName:修改文件名
    
    mv originName existedDirName:移动到这个目录下
    
    - 创建软链接
        
    ln -s absDirPath  newName:根据绝对路径创建一个快捷方式，注意要使用绝对路径才能在整个系统中打开
    
    - 创建硬链接
    
    ln absDirPath  newName：相当于对原文件进行了一次拷贝
    
   - 查看和修改文件权限
   
   whoami：查看当前登陆用户
   
   修改文件权限：（1）文字设定法 chmod [who] [+|-|=] [mode] target,who 文件所有者 u ，同组 g，其他人o，所有人a， +增加权限
   ，-减少权限，=覆盖权限，mode x执行 r读 w写 eg.chmod a=xrw target 为所有人赋予三种权限；（2）数字设定法：r：4，
   w：2，x：1，eg.chmod 755 target
   
   修改文件所属用户和组：
   
   sudo chmod [who] [opt] [mode] | sudo chgrp [-cfhRv][--help][--version][所属群组][文件或目录...] 
   
   - 文件查找和检索
   
     - 按文件名 find   path   -name   filename
     - 按文件内容查找 grep 
     
   - 软件的安装和卸载
     -  在线安装 sudo apt-get install xxx|sudo apt-get remove  xxx
     - deb包安装 sudo dpkg -i xxx.deb|sudo dpkg -r xxx.deb
     
- 第二天

    - 压缩包管理
    
    https://www.cnblogs.com/newcaoguo/p/5896975.html
    
    - ps命令 查看进程
    
    ps a 查看所有进程
    
    
    
   
   