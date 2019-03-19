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