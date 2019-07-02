1. IoC 容器

    `org.springframework.beans`和`org.springframework.context`是Spring IoC容器的基础。
    BeanFactory接口提供了基于配置的管理任意Object的能力，ApplicationContext是BeanFactory的
    一个子接口，它能更好的与Spring AOP整合。总的来说BeanFactory提供了基于配置的框架而ApplicationContext
    更面向企业。
    
    Spring中这些组成你业务骨架的实体类被称为bean，`org.springframework.context.ApplicationContext`接口
    代表了IoC容器来初始化、装配和管理bean。这些配置信息的元数据可以是XML、Java注解或是Java代码。它有诸如
    `ClassPathXmlApplicationContext`和` FileSystemXmlApplicationContext`这样的实现类，XML是传统的定义方式，
    你也可以写少量的XML配置来让Spring通过Java注解和Java代码来装配bean。 Spring2.5开始支持注解，Spring3.0
    开始支持Java代码。
    ```
    ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
    
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd">
            
        <bean id="..." class="...">   
            <!-- collaborators and configuration for this bean go here -->
        </bean>
        
        <bean id="..." class="...">
            <!-- collaborators and configuration for this bean go here -->
        </bean>
        <!-- more bean definitions go here -->
    </beans>
    ```
    
    容器根据配置信息来组织bean,而bean的定义在容器内由BeanDefinition这个类来代表，这个类中包含了如下的
    属性：
    ```
    Class                       Instantiating Beans
    Name                        Naming Beans
    Scope                       Bean Scopes
    Constructor arguments       Dependency Injection
    Properties Dependency       Injection
    Autowiring mode             Autowiring Collaborators
    Lazy initialization mode    Lazy-initialized Beans
    Initialization method       Initialization Callbacks
    Destruction method          Destruction Callbacks
    ```
    对于不是由容器实例化（开发者手动实例化）的Object，如果需要交由容器管理，可以通过ApplicationContext的getBeanFactory()方法
    获得`DefaultListableBeanFactory`，然后通过`registerSingleton(..)`方法实现。但是一个典型的应用还是应该交由容器来实现类的实例。
    
    一个bean在其所在的容器内必须有一个唯一标识符，在XML配置中，你可以用id或者name属性，或者两者借用来指定唯一的
    一个bean，如果你不提供容器会为你生成一个唯一的标识。大部分情况不提供name的原因是inner bean
    ```
    <bean id="outer" class="...">
        <!-- instead of using a reference to a target bean, simply define the target bean inline -->
        <property name="target">
            <bean class="com.example.Person"> <!-- this is the inner bean -->
                <property name="name" value="Fiona Apple"/>
                <property name="age" value="25"/>
            </bean>
        </property>
    </bean>
    ```
    Spring支持别名
    ```
        <bean id="helloService" class="com.zjc.services.HelloService" >
        </bean>
        <alias name="helloService" alias="firstService"/>
        <alias name="helloService" alias="demoService"/>
    ```
    
    Bean的实例化默认是通过构造器，你也可通过静态的工厂方法
    ```
    <bean id="clientService"  class="examples.ClientService" factory-method="createInstance"/>
    
    public class ClientService {
        private static ClientService clientService = new ClientService();
        private ClientService() {}
        public static ClientService createInstance() {
            return clientService;
        }
    }
    ```
   你也可以通过静态工厂方法来实现
   ```
    <!-- the factory bean, which contains a method called createInstance() -->
    <bean id="serviceLocator" class="examples.DefaultServiceLocator">
        <!-- inject any dependencies required by this locator bean -->
    </bean>
    
    <!-- the bean to be created via the factory bean -->
    <bean id="clientService"
        factory-bean="serviceLocator"
        factory-method="createClientServiceInstance"/>
        
    <bean id="accountService"
        factory-bean="serviceLocator"
        factory-method="createAccountServiceInstance"/>
        
    public class DefaultServiceLocator {
        private static ClientService clientService = new ClientServiceImpl();
        
        public ClientService createClientServiceInstance() {
            return clientService;
        }
        public AccountService createAccountServiceInstance() {
            return accountService;
        }
    }
   ``` 
    
    
    
