- 第一章 Spring框架的由来

- 第二章 IoC的基本概念

    IoC（Inversion of Control），控制反转，或者叫依赖注入。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体将其所依赖的对象的引用传递给它。
    也可以说，依赖被注入到对象中。注入有三种形式，构造器注入、setter注入和接口注入。
    
- 第三章 掌管大局的IoC Service Provider

    Ioc Service Provider的职责相对来说比较简单，主要有两个：业务对象的构建管理和业务对象间的依赖绑定。

    当前流行的IoC Service Provider产品使用的注册对象管理信息的方式主要有：1. 直接编码方式 2. 配置文件方式 3. 元数据方式
    
- 第四章 Spring的IoC容器之BeanFactory 

    Spring提供了两种容器类型：BeanFactory和ApplicationContext。
    - BeanFactory。基础类型IoC容器，提供完整的IoC服务支持。如果没有特殊指定，默认采用延迟初始化策略（lazy-load）。
    - ApplicationContext，在BeanFactory的基础上构建，是相对高级的容器实现，除了拥有BeanFactory的所有支持，ApplicationContext还提供了
    其他高级特性，比如事件发布、国际化信息支持等。默认在类型容器启动之后，就完成全部初始化和绑定
    
        
    
        