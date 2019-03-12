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
        
    4. 打包和运行
    
       完成编译和测试之后，项目就需要打包（package）。在pom.xml中没有指定
       打包类型，所以会默认打包为jar。运行命令`mvn clean package`
       ```
       Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
       
       [INFO]
       [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ hello-world ---
       [INFO] Building jar: D:\IdeaProjects\StudyNotes\Maven\maven-in-action\chapter-03\hello-world\target\hello-world-1.0-SNAPSHOT.jar
       [INFO] ------------------------------------------------------------------------
       [INFO] BUILD SUCCESS
       ```
       经过clean、compile、test之后，在target目录中看到了打包后的jar文件，默认的命名规则时
       artifactId-version，可以在pom.xml中build元素下通过fileName来指定。如果希望别的项目可以使用这个jar，
       还需要执行`mvn clean install`，可以看到该jar包在被编译完成之后被添加到的本地的Maven仓库中。
       
       到目前为止还没有运行Hello World项目，因为带有main方法的类信息不会添加到manifest中，为了生成可执行jar文件，需要在pom.xml中添加maven-shade-plugin
       ```
       <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>1.2.1</version>
            <executions>
              <execution>
                <phase>package</phase>
                <goals>
                  <goal>shade</goal>
                </goals>
                <configuration>
                  <transformers>
                    <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                      <mainClass>com.zjc.mvnbook.helloworld.HelloWorld</mainClass>
                    </transformer>
                  </transformers>
                </configuration>
              </execution>
            </executions>
          </plugin>
       ```
          我们指定了mainClass为HelloWorld类，项目打包时会将该信息放入MAINFEST中。现在执行`mvn clean install`，现在在target目录下
          将会看到除了`hello-world-1.0-SANPSHOT.jar`之外，还有一个`original-hello-world-1.0-SNAPSHOT.jar`，前者是带有main方法的可运行的
          jar，后者为原始jar,通过`java -jar hello-world-1.0-SNAPSHOT.jar`运行，可以在控制台看到输出了。
          
    5. 使用Archetype生成骨架
    
        通过`mvn archetype:create -DgroupId=xxx -DartifactId=xxx -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false`命令，我们可以让Maven为我们快速勾勒出项目骨架。
        
- 第4章 项目背景

    - 略 
    
- 第5张 坐标和依赖

    坐标（Coordinate），在平面几何中，（x，y）定义一个平面内的坐标，在实际生活中，省市区街道门牌号
    顶一个地址坐标。Maven坐标的元素包括groupId、 artifactId、version、packaging、classifier。Maven内置了一个中央仓
    库的地址（http://repo.maven.org/maven2），Maven会根据坐标去那里下载。在我们开发自己项目的时候，也需要为其定义适当的坐标，这是Maven强制要求的。
    ```
    <groupId>org.sonatype.nexus</groupId>
    <artifactId>nexus-indexer</artifactId>
    <version>2.0.0</version>
    <packaging>jar</packaging>
    ```
    这是nexus-indexer的坐标定义。groupId：定义当前Maven项目隶属的实际项目。artifactId：该元素定义实际项目中的一个Maven项目（模块）。
    version：该元素定义Maven项目当前所处的版本。packaging：该元素定义Maven项目的打包方式。classifier：该元素用来帮助定义构建输出的一些附属构件。
    javadoc和sources就是这两个附属构件的classifier。
      
    上述5个元素中，groupId、artifactId、version是必须定义的，packaging是可选的（默认为jar），而classifier是不能直接定义的。
    
    新建一个account-email项目，[pom.xml](chapter-05/account-email/pom.xml)在此。
    
    主代码[AccountEmailException.java](chapter-05/account-email/src/main/java/com/zjc/mvnbook/account/email/AccountEmailException.java),
    [AccountEmailService.java](chapter-05/account-email/src/main/java/com/zjc/mvnbook/account/email/AccountEmailService.java),
    [AccountEmailServiceImpl.java](chapter-05/account-email/src/main/java/com/zjc/mvnbook/account/email/AccountEmailServiceImpl.java),
    
    接着是定义邮箱服务器的配置[service.properties](chapter-05/account-email/src/main/resources/service.properties)
    
    在完成测试之后运行`mvn clean install`我们九江account-email-1.0.SNAPSHOT安装到了本地仓库中，这时该模块就可以共其他Maven项目使用了。
    
    - 依赖配置
    
    观察dependency下的元素，除了groupId、artifactId、version这三个定义坐标的元素之外，还有一些别的元素
    type:声明依赖的类型，对应的是packaging元素，默认为jar。scope：依赖的作用范围，默认是main和test文件夹。
    optional：标记依赖是否可选。exclusions：用来排除传递性依赖。
    
    - 依赖范围
    
        我们已经知道通过scope元素来指定依赖范围。首先要知道，Maven在编译项目主代码时需要使用一套classpath。在本例中，需要依赖spring-core，，该文件
        以依赖的形式引入到classpath中。在编译和和执行测试代码时需要另外一套classpath，而最终在运行时又会需要一套classpath。
        
        依赖范围就是控制这三种classpath（编译classpath、测试classpath、运行classpath），scope元素有这几种选项：
        - compile:编译依赖范围，默认为此，在编译、测试、运行三种classpath中都有效
        - test：测试依赖范围，只在测试classpath中有效
        - provided：已提供依赖范围，编译和测试时都有效，但运行时无效。典型的是servlet-api，在编译和测试时都需要，但在生产环境中tomcat会提供。
        - runtime：运行时依赖范围，测试和运行时有效，但在编译主代码时无效，典型的是JDBC，编译时只需要JDK提供的接口即可，但在测试和运行时需要具体的JDBC驱动
        - system：系统依赖范围，与provided运行范围一致，需要在dependency中通过systemPath制定具体路径，但是往往与本机系统绑定，不建议使用。
        - import:导入依赖范围，第8章会具体介绍
        
    - 传递性依赖
    
        当我们的项目依赖spring-core项目，而spring-core象奴本身也会依赖一些别的项目，如commons-logging，这种就是传递性依赖。Maven会解析这些间接依赖
        ，将其引入到项目中来。我们对spring-core的依赖范围是compile，spring-core对commons-logging的依赖范围也是compile，那么项目对commons-logging的
        依赖范围就是compile。这种间接依赖的交叉如下,左边的列代表第一直接依赖范围，上边的行代表第二直接依赖范围。
        !["传递性依赖"](images/dependency.jpg '传递性依赖')
        
    - 依赖调解
    
        加入项目中存在这样两条依赖关系：A->B->C->X,A->D->X，X是A的传递性依赖，但是哪个X会被Maven解析并依赖呢？都解析是不对的，那会造成依赖重复，因此必须选择一个，
        Maven的依赖调解(Dependency Mediation)的第一原则是：路径最近优先。第二原则是路径相同则第一声明者优先，即在POM中顺序最靠前的dependency优先。
        
    - 可选依赖
    
        假设项目A->B,B又同时依赖X，Y两个项目，如果将X、Y被配置为可选依赖，则不会影响到项目A。
        
    - 排除依赖
    
        出于一些原因，我们不希望在项目中引入间接依赖，就需要在dependency中添加exclusion元素来排除这些间接依赖。
        ```xml
        <dependencies>
          <dependency>
              <groupId>com.zjc.mvnbook</groupId>
              <groupId>account-email</groupId>
              <version>1.0.0</version>
              <exclusions>
                  <exclusion>
                      <groupId>org.springframework</groupId>
                      <artifactId>spring-core</artifactId>
                  </exclusion>        
              </exclusions>
          </dependency> 
        </dependencies>
        ```
        只需指定groupId和artifactId即可。
        
    - 归类依赖
    
        对于spring-core,spring-tx，spring-context等项目，我们可以通过properties元素来统一定义版本号
        ```
          <properties>
            <springframework.version>2.5.6</springframework.version>
          </properties>
          <dependencies>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-core</artifactId>
              <version>${springframework.version}</version>
            </dependency>
            <dependency>
              <groupId>org.springframework</groupId>
              <artifactId>spring-beans</artifactId>
              <version>${springframework.version}</version>
            </dependency>
          </dependencies>  
        ```
        
    - 优化依赖
    
       通过`mvn dependency:list`我们可以查看所有已解析依赖，`mvn dependency:tree`可以按照
       树形结构展示已解析依赖，通过`mvn dependency:analyze`会展示出项目中声明了但未使用的依赖以及
       使用到的但未显示声明的依赖，经过分析我们可以删除一些不需要的依赖。
       
   
- 第6章 仓库

    坐标和依赖都是构件的逻辑表达形式，而且对应的具体物理表达方式就是一个个的文件，仓库就是统一
    管理所有文件。仓库分为两类，本地仓库和远程仓库。Maven会先查看本地仓库，如果不存在此构件，则会直接去
    远程仓库下载到本地仓库再使用。如果远程仓库也不存在此构件，则会报错。
    
    中央仓库是Maven自带的远程仓库，包含了绝大部分开源构件。为了节约时间也可以在本地局域网搭建私有的仓库，另外包括JBoss
    等公司也有开放的公共库可以使用。
    
    本地仓库默认会在c盘下的用户文件夹下，如果需要改变本地仓库地址，可以修改settings.xml文件，但并不推荐
    直接修改全局的配置文件。
    
    当远程的中央仓库无法满足需求时，我们可以通过在pom.xml添加配置来指定其他公共库或是私服仓库
    ```
    ......
      <repositories>
        <repository>
          <id>jboss</id>
          <name>JBoss Repository</name>
          <url>http://repository.jboss.com/maven2/</url>
          <releases>
              <enabled>true</enabled>
          </releases>
          <snapshots>
              <enabled>true</enabled>
          </snapshots>
          <layout>default</layout>
        </repository>
      </repositories>
    ......
    ```        
    
- 第7章 生命周期和插件

    除了坐标、仓库和依赖，Maven另外的两个核心概念就是生命周期和插件。生命周期和插件协同工作，密不可分。
    
    Maven从大量项目和构建工具中学习和反思，总结了一套高度完善、易于扩展的生命周期。包含了项目的清理、
    初始化、编译、测试、打包、集成测试、验证、部署和站点生成等几乎所有构建步骤。生命周期抽象除了构建过程
    的各个步骤，每个步骤都可以绑定一个或多个插件行为，Maven为这些步骤提供了默认插件，例如编译插件maven-compiler-plugin，针对
    测试有maven-surefire-plugin。当用户存在特殊需求时则可以自定义插件。
    
    Maven有三套生命周期，分别是clean：负责清理项目，default：负责构建项目，site：负责建立项目站点。每个生命周期
    又有一些具体的阶段（phase），以clean为例，包含pre-clean，clean和post-clean，当用户调用`mvn clean`时，pre-clean和clean会顺序执行。
    
    default生命周期定义了真正构建时所需要执行的所有步骤，它是所有生命周期中最核心的部分，包含validate、initialize、
    generate-sources、process-sources、generate-resources、process-resources、compile、process-classes、
    generate-test-sources、process-test-sources、test-compile、process-test-classes、test、prepare-package、package、
    pre-integration-test、integration-test、post-integration-test、verify、install、deploy。
    
    site生命周期的目的是建立和发布项目站点，Maven能够基于POM所包含的信息，自动生成一个友好的站点，方便团队交流
    和发布项目信息，该生命周期包含 pre-site，site，post-site，site-deploy。
    
    命令行所调用的就是Maven生命周期的某个阶段，例如`mvn clean install`就是执行clean周期的到clean阶段，然后执行default到install阶段
    
    - 插件目标（Plugin Goal）

    一个插件本身为了复用能够后完成多个目标，比如maven-dependency-plugin有十多个目标，每个目标对应一个功能，上述提到
    几个功能分别对应的插件目标为dependency:analyze、dependency:tree和dependency:list。这是一种通用写法，冒号
    前面是插件的前缀，后面是目标，类似还有compiler:compile,surefire:test。
    
    - 插件绑定
    
    Maven的生命周期和插件相互绑定，用以完成实际的构建任务。例如项目编译任务对应了default生命周期的compile这一阶段，
    而maven-compiler-plugin这一插件的compile目标能够完成该任务，因此将他们绑定就可以实现项目的编译。 
    
    - 插件解析机制
    
    与依赖一样，插件构件也是存储于Maven仓库中，在需要时，Maven会从本地仓库寻找插件，如果不存在，则从远程仓库查找下载
    到本地仓库后使用。但是不同于依赖，插件的远程仓库使用pluginRepositories和pluginRepository进行配置。当该插件
    是Maven官方提供的插件，则可以省略其groupId`org.apache.maven.plugins`。为了简化POM的配置，Maven在超级POM中为所有
    核心插件设定了版本，因此用户不用任何配置就可以使用`maven-clean-plugin`。
    
- 第8章 聚合与继承

    在开始之前，先为项目添加一个account-persist模块。该模块负责账户数据持久化，以XML文件报错账户数据，并支持
    CRUD操作。具体pom文件和主代码见chapter-08下的account-persist项目。
    - 聚合
    
    到目前为止，我们的用户注册模块已经有了两个模块，一个account-email，一个account-persist。自然而然我们希望把两个项目聚合在一起。
    系那件一个account-aggregator项目，它必须有自己的pom，不过作为一个聚合项目，其POM有特殊之处。配置如下
    ```
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.zjc.mvnbook.account</groupId>
        <artifactId>account-aggregator</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <packaging>pom</packaging>
        <name>Account Aggregator</name>
        <modules>
            <module>./account-email</module>
            <module>./account-persist</module>
        </modules>
    </project>
    ```
    groupId依然为通用的`com.zjc.mvnbook.account`，但是artifactId为`account-aggregator`，版本也与两个模块保持一致为`1.0.0-SNAPSHOT`。这里的第一个特殊的
    元素是packaging，值为pom，之前项目打包的方式都是默认为了jar，对于这种聚合模式，packaging的值必须是pom。
    
    之后是一个新的元素modules，这是实现聚合最核心的配置，用户可以声明任意数量的module元素，每一个module的值对应的就是相对当前pom的相对目录。
    
    可以发现account-aggregator只是一个包含了pom.xml和子模块的目录，并没有src/main/java之类的目录。
    
    - 继承
    
    我们目前已经将多个模块聚合在一个项目下，但是目前还存在一个问题，在两个子模块中
    pom存在很多相似的配置，类似groupId、version以及dependencies下诸多的依赖。想要消除
    这些重复的定义，就需要继承。
    
    在account-aggregator项目下新建一个account-parent项目，[pom.xml](chapter-08/account-aggregator/account-parent/pom.xml)，其
    packaging的类型也为pom，接下来就是让其他模块继承这个pom，修改account-email项目的pom如下
    ```
      <parent>
        <groupId>com.zjc.mvnbook.account</groupId>
        <artifactId>account-parent</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <relativePath>../account-parent/pom.xml</relativePath>
      </parent>
    
      <artifactId>account-email</artifactId>
      <name>Account Email</name>
    ```
    通过parent和其中定义的groupId、artifactId、version来指定要继承的父级pom文件，而account-email将隐式的继承groupId和version，当然
    也可以声明来覆盖父级pom中的。
    
    dependencies属性也是可以从父级POM中继承的，我们把org.springframework：spring-core：2.5.6、 org.springframework：spring-beans：2.5.6、
    org.springframework：spring-context：2.5.6和junit：junit：4.7这几个两个模块共同需要的jar提取到父级模块account-parent中
    ```
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-core</artifactId>
                    <version>${springframework.version}</version>
                </dependency>
                <dependency>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-beans</artifactId>
                    <version>${springframework.version}</version>
                </dependency>
            </dependencies>
        </dependencyManagement>
    ```
    通过dependencyManagement来管理父级POM中的这些元素，子POM只需在dependency中指定groupId和artifactId就可以引用父级POM中的这些依赖，这样是为了避免
    其余不需要springframework依赖的子模块也被迫引入了这些依赖。
    
    父POM中除了依赖管理之外，也有pluginManagement来管理插件
    
    - 聚合和继承的关系
    
    聚合的目的是为了快速的整合多个子项目，父模块知道自己聚合了那些模块，而被聚合的模块则不知道。继承是为了消除重复的配置，子模块知道自己从父模块继承了
    哪些重复配置而父模块对此则不知道。
    
    - 反应堆
    
    在一个多模块的Maven项目中，反应堆（Reactor）是指所有模块组成的一个构建结构。对于单模块项目，反应堆就是该模块本身。多模块项目，反应堆就是各个模块
    之间的继承和依赖关系的集合，从而能够计算出模块构建的顺序。最终会形成一个有向非循环图。当出现循环引用时就会报错。
    
    
- 第9章 使用Nexus创建私服 
    
    - 略
    
- 第10章 使用Maven进行测试    

    随着敏捷开发模式的日益流行，软件开发人员也越来月认识到日常编程工作中单元测试的重要性。Maven的重要责任之一就是自动运行单元测试，它通过
    maven-surefire-plugin与主流的单元测试框架JUnit3、JUnit4以及TestNG集成，并且能够自动生成丰富的结果测试。
    
    首先要引入一个实现账户注册服务的account-captcha模块，该模块主要负责处理账户注册时验证码key的生成、图片生成以及验证等。除了SpringFramework
    和JUnit之外，还有一个com.google.code.kaptcha：kaptcha。这是一个专门用来生成验证码（Captcha）的开源类库。
    
   