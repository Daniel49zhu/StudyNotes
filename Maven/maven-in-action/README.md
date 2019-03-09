《Maven实战》

- 第1章 Maven简介

     我们每天都会有相当一部分时间花在编译、运行测试代码、生成文档、打包和部署等
     繁琐且不起眼的工作上，这就是构建。而Maven就是这样一个优秀的构建工具，只需要
     配置好Maven，执行简单的命令，Maven就会帮我们完成这些繁琐的工作。
     
     除了构建应用之外，几乎所有Java应用都会引入第三方的开源jar包，Maven通过坐标能够
     准确定位每一个构件（artifact）。Maven还能包我们管理原本分散在项目中各个角落的项目信息，
     包括描述、开发者列表、版本控制系统、许可证、缺陷管理系统地址等。
     
     Maven还为全世界提供了一个免费的中央仓库，几乎可以找到任何主流的开源类库。
     
     使用Maven的另一个好处就是Maven对于项目目录结构、测试用例命名方式等都有既定的规则。只要
     遵循规则就可以在项目间切换的时候免去额外的学习成本，可以说是约定优于配置（Convention Over Configuration）。
     
- 第2章 Maven的安装和配置 

    - 略
    
- 第3章 Maven使用入门

    1. 编写POM
    
        Maven项目的核心是pom.xml(Project Object Model,项目对象模型)，定义了项目的基本信息，用于描述
        项目如何构建、声明项目依赖等等，现在为Hello World编写一个简单的pom.xml
        ```
        <?xml version="1.0" encoding="UTF-8"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
        http://maven.apache.org/maven-v4_0_0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <groupId>com.zjc.mvnbook</groupId>
            <artifactId>hello-world</artifactId>
            <version>1.0-SNAPSHOT</version>
            <name>Maven Hello World Project</name>
        
        </project>
        ```
        第一行xml指定了改文档的版本和编码方式。紧接着是project元素，是所有元素的根元素，它还声明
        了一些POM相关的命名空间及xsd元素。
        
        modelVersion指定了当前POM的模型版本，对于Maven2和Maven3只能是4.0.0.
        
        最重要的是后面的groupId artifactId和version三行。这三个元素定义了一个项目的基本坐标，在Maven的世界
        里，任何jar、pom或者war都是以基于这些基本坐标进行区分的。groupId一般是公司网址的倒序，
        artifactId定义了该项目在整个group中的唯一Id，而Version就是当前项目的版本号，1.0-SNAPSHOT意味着
        这是1.0版本的一个快照，是不稳定版本，仍处于开发中。
        
        最后的name声明了一个对用户更为友好的项目名称，建议每个POM都声明name。
    
    2. 编写主代码
    
        接下来按照Maven的约定建立目录，创建一个目录src/main/java，在下面添加
        [com.zjc.mvnbook.helloworld.HelloWorld.java](chapter-03/hello-world/src/main/java/com/zjc/mvnbook/helloworld/HelloWorld.java)
        ```
        package com.zjc.mvnbook.helloworld;
        public class HelloWorld {
            public String sayHello() {
                return "Hello Maven";
            }
            public static void main(String[] args) {
                System.out.println(new HelloWorld().sayHello());
            }
        }
        ```
        遵循约定，Maven会自动搜寻目录找到项目的主代码。
        
        代码编写完使用Maven进行编译。在根目录下运行`mvn clean compile`,clean是告诉Maven清空
        target目录，接着执行compile，将项目主代码编译至target/classes目录。
        
        接下来编写一些单元测试代码并让Maven执行自动化测试
        
    3. 编写测试代码
    
        项目中测试代码的主目录位于src/test/java下。首先我们要在pom.xml中添加Junit依赖。
        ```
            <dependencies>
                <!-- https://mvnrepository.com/artifact/junit/junit -->
                <dependency>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                    <version>4.12</version>
                    <scope>test</scope>
                </dependency>
            </dependencies>
        ```
        上面除了groupId、artifactId、version之外，还有一个scope元素，值为test，scope代表依赖范围，
        为test说明只对测试有效，在test文件夹下的测试代码可以import junit，而在主目录下不可以。如果不
        特殊声明则默认是compile作用域，即对主目录和测试目录都可以。
        
        接着编写测试类
        ```
        import org.junit.Assert;
        import org.junit.Test;
        public class HelloWorldTest {
            @Test
            public void testSayHello() {
                HelloWorld helloWorld = new HelloWorld();
                String result = helloWorld.sayHello();
                Assert.assertSame("Hello Maven",result);
            }
        }
        ```
        一个典型的单元测试包括三个步骤：1.准备测试数据；2.执行要测试的行为；3.检查结果。编写完后运行
        `mvn clean test`，如果在编译时报错说不支持注解，则需要在pom.xml中需要配置时compile插件支持jdk1.5
        ```
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <configuration>
                            <source>1.5</source>
                            <target>1.5</target>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        ```
        最后可以在输出中看到测试执行了多少，失败了多少等具体测试信息。
 