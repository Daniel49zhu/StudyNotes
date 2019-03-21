#### 枚举类型

关键字`enum`可以将一组具名的值的有限集合创建为一种新的类型，这些具名的值可以作为一种常规
组件使用。

当你在创建enum时，编译器会为你生成一个相关的类，这个类继承自java.lang.Enum。

- 基本特性

[EnumClass.java](EnumClass.java)中，values()返回enum类实例的数组，ordinal()方法返回一个int值，这是每个enum实例在声明时的次序，从0开始。可以用`==`来比较enum实例，
编译器会自动为你提供equals()和hashCode()方法，同时它也具有compareTo()方法。

调用getDeclaringClass()我们就知道
它属于的enum类。name()返回的是实例声明时的名字，其等价于toString()方法。enum类的valueOf()会
根据给定的名字返回对应的enum实例，不存在则抛出异常。

- 静态导入

enum类支持静态导入，但是必须要定义在不同包中（存在争议），见[Spiciness.java](enumerated/Spiciness.java)和
[Burrito.java](Burrito.java);

- 添加方法

除了无法继承enum之外，我们基本可以把enum看作一个普通类，可以向其中添加方法甚至可以是main方法。[OzWitch.java](OzWitch.java),
记住要先定义实例，并在实例最后加上分号,我们也可以复写toString方法,见[SpaceShip.java](SpaceShip.java);

- switch中使用enum
   
   一般来说，switch中只能使用整数值，而枚举天生就具备整数值的次序，可以通过ordinal()获得其次序，因此我们可以在switch语句中
   使用enum。[TrafficLight.java](TrafficLight.java),（在case语句中，我们不需要enum类型来修饰一个enum实例。）
   
- values()方法探秘

    enum类都继承自java.lang.Enum类，但valueOf却并不是Enum类中的方法,通过一个反射的例子来看一下[Reflection.java](Reflection.java)，反编译Explore.class文件
    ```
    final class Explore extends java.lang.Enum<Explore> {
      public static final Explore HERE;
      public static final Explore THERE;
      public static Explore[] values();
      public static Explore valueOf(java.lang.String);
      static {};
    }
    ```
    ，因此，values方法是编译器添加的，在创建Explore的过程中，便以为还为其添加了valueOf方法。当你把
    enum实例向上转型为Enum时，就无法通过values()方法获得所有实例，我们可以通过Class中的getEnumConstants()方法来实现。
    [UpcastEnum.java](UpcastEnum.java),虽然该方法属于Class类，但当你对普通Class调用该方法时则会报空指针异常。
    
- 实现，而非继承

    enum是继承自Enum类，因此无法再去继承其他类，但我们可以让enum继承多个接口。[EnumImplementation.java](enumerated/EnumImplementation.java),
    比较奇怪的是你必须要有一个enum实例才能调用其上的方法。
    
- 随机选择

    在CartoonCharacter.next()中看到的这样我们可能需要从enum实例中随机选择，我们可以利用泛型将这个工作一般化并加入工具库中。
    ```
    public class Enums {
      private static Random rand = new Random(47);
      public static <T extends Enum<T>> T random(Class<T> ec) {
          return random(ec.getEnumConstants());  
      }
      public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
      }
    }
    ```
    [RandomTest.java](RandomTest.java)
    
- 使用接口组织枚举

    在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组，可以达到
    将枚举元素分类组织的目的。举例来说，假设你想用enum来表示不同类别的
    食物，同时还希望每个enum元素仍然保持Food类型。[Food.java](menu/Food.java)。
    
    对于enum而言，实现接口是使其子类化的唯一办法，所有嵌入在Food中的每个enum
    
    另外一种更简洁的管理枚举的方法就是将一个enum嵌套在另一个enum中。像[SecurityCategory.java](SecurityCategory.java)这样。
    用Security接口将Security组织成一个公共类型是必要的。我们也对Meal进行了改写[Meal2.java](menu/Meal2.java)。

- 使用EnumSet替代标志

    Set是一种不能包含重复元素的集合，而enum也要求其成员唯一。
    
    
    