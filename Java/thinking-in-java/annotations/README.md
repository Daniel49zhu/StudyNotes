#### 注解

   注解也被称为元数据，为我们在代码中添加信息提供了一个形式化的方式。一定程度上把元数据和
   源代码结合在一起。Jdk5引入了注解，并内置了三种注解
   - @Override 表示当前方法覆盖超类中的方法
   - @Deprecated 表示当前方法已被废弃，如果使用了会在编译期间发出警告信息
   - @SuppressWarnings 关闭不当的编译器警告信息
   
   另外还提供了四种注解负责新注解的创建，会在后面学习。
   
   - 基本语法
   
       在[Testable.java](Testable.java)中，使用了一个@Test对testExecute方法进行了注解，这个注解本
       身并不做任何事情。
       进入这个注解内部
       ```
        package net.mindview.atunit;
        
        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;
        
        @Target({ElementType.METHOD})
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Test {
        }
       ```
       看起来很像是一个空的接口，在定义注解时，需要一些元注解，如@Target和@Retention，@Target
       用来定义注解应用于什么地方，方法或是域上。@Retention用来定义该注解在哪一个级别可用，源代码（SOURCE），
       类文件（CLASS）或是运行时（RUNTIME）。没有元素的注解称为标记注解，类似@Test。
       
       下面定义一个注解。[UseCase.java](UseCase.java),在[PasswordUtils.java](PasswordUtils.java)中有三个方法被
       添加了注解。如果没有编写注解处理器，那么注解就和注释一样无法发挥作用。下面是一个简单的处理器来处理PasswordUtils,
       [UseCaseTracker.java](UseCaseTracker.java),这个处理器中用到了getAnnotation和getDeclaredMethods方法