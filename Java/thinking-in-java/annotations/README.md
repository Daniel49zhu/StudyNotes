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
       
       注解中支持的类型包括所有基本类型、String、Class、enum、Annotation，如果在注解中使用了其他类型，那么编译器就会报错。
       注解中的元素不能有不确定的值，也就是必须要提供值或者由注解提供默认值。其次，非基本类型的值不能用null作为其值。这个
       约束使得处理器很难表现一个元素的存在或缺失的状态，因此我们会定义空字符串或负数来表示某个元素不存在。
       
       在用Hibernate关联数据库时，我们可能会需要使用XML配置很多类和数据库关系的映射，这就会造成信息的冗余，我们通过
       实现一些新的注解，将定义的Bean关联数据库，来自动生成一些表。[DBTable/java](database/DBTable.java)，告诉处理器，你需要
       为我生成一个数据库表。接下来是为修饰JavaBean域准备的注解。[Constraints.java](database/Constraints.java),[SQLString.java](database/SQLString.java)
       ,[SQLInteger.java](database/SQLInteger.java)。接着利用上面的这些注解定义一个JavaBean，[Member.java](database/Member.java),@DBTable的值将来
       会作为表的名字，@SQLString设置了字符串的长度。最后我们要实现一个处理器，它会检查类上的注解，并根据注解最后生成一个
       创建数据库的SQL命令。[TableCreator.java](database/TableCreator.java)，最主要的方法就是利用getDeclaredAnnotations检查域上的所有注解。
       
       （第4版中后面提到了apt接口来实现注解处理器，该系列在jdk1.8中已经被移除了，取而代之的是Pluggable Annotation Processing API）
       https://www.cnblogs.com/throwable/p/9139908.html
       
   
       
       
       