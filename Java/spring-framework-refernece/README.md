1. IoC 容器

`org.springframework.beans`和`org.springframework.context`是Spring IoC容器的基础。
BeanFactory接口提供了基于配置的管理任意Object的能力，ApplicationContext是BeanFactory的
一个子接口，它能更好的与Spring AOP整合