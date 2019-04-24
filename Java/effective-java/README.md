1. 第1条 考虑用静态工厂方法代替构造器
   - 优势1 静态工厂方法有名称 eg:BigInteger.probablePrime 
   - 优势2 不必每次调用时都创建一个新对象 eg:Boolean.valueOf(boolean)，将构建好的实例重复利用
   - 优势3 可以返回原返回类型的任何子类型对象 eg:EnumSet会根据元素大小返回不同的实例
   ```
        public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
            Enum<?>[] universe = getUniverse(elementType);
            if (universe == null)
                throw new ClassCastException(elementType + " not an enum");
            if (universe.length <= 64)
                return new RegularEnumSet<>(elementType, universe);
            else
                return new JumboEnumSet<>(elementType, universe);
        }
   ```
   - 优势4 创建参数化类型实例时，使代码更简洁
   ```
    //如果HashMap提供了这样一个方法
    public static <K,V>  HashMap<K,V> newInstance() {
       return new HashMap<K,V>();
    } 
    //就可以用
    Map<String,List<String>> map = HashMap.newInstance();
    //代替
    Map<String,List<String>> map = new HashMap<String,List<String>>();
   ```
   - 静态工厂惯用命名：valueOf、of、getInstance、newInstance
   
- 第2条 遇到多个构造器参数时要考虑用构建器

    假设有一个营养成分表包含多个参数域，其中有一些是必要的，一些是可选的。如果按照传统方法我们会创建
    多个构造器，让其级联调用。这样可行但是当参数域多过是则显得不直观。我们可以使用Builder模式来代替
    
    [NutritionFacts.java](demo/src/NutritionFacts.java)
    
    想要创建一个NutrionFacts类我们需要这样
    ```
    new NutritionFacts.Bulder(230,8).calories(100).sodium(35).build();
    ```
    Builder提供了可选的具名的可选参数，让代码可读性更强。
    
- 第3条 用私有构造器或枚举类强化Singleton属性

    Singleton指仅实例化一次的类，比如窗口管理或者文件系统。在1.5之前实现Singleton有两种方式，都是
    保持构造器私有，并导出公有的静态成员，以便客户端能访问该类的唯一实例。
    ```
    public class Elvis {
        public static final Elvis INSTANCE = new Elvis();
        private Elvis() {}
        public static Elvis getInstance(return INSTANCE;)
    }
    ```
    注意这种方式可能通过反射修改构造器Accessible来创建第二个实例，我们可以在构造其中抛出异常来抵御这种攻击。
    JDK1.5以后我们可以通过枚举类实现单例，它更加简洁
    ```java
    public enum Elvis {
      INSTANCE;
      public void doSth() {}
    }
    ```
    它是线程安全的且能低于反序列化攻击。
    
- 第4条 通过私有构造器强化不可实例化的能力

    对于工具类 我们不希望其被实例化，然而在缺少显式构造器的情况下，会自动提供一个共有且
    无参数的缺省构造器。因此只要让其包含一个私有构造器就不能被实例化了。
   
- 第5条 避免创建不必要对象

    使用"hello"代替new String("hello")，优先使用valueOf来代替new Integer()等
    
- 第6条 消除过期的对象引用

    常见引发原因有三 1，只要是类自己管理内存的，一旦元素被释放掉，该元素中包含的任何对象引用都应该
    被清空。 2，缓存中存在的对象被遗忘 3，来源于监听器和其他回调
    
- 第7条 避免使用finalizer方法

    finalizer方法通常是不可预测的，也是很危险的，一般情况下是不必要的。
    
- 第8条 覆盖equals时请遵守通用约定

    覆盖equals方法看似简单，但如果不注意会引发严重的后果，当我们不覆盖equals方法时，每个类的实例
    只会与自身相等。如果一个类，有自己的逻辑相等的概念，则需要覆盖equals方法。实现是需要遵循等价关系
    - 自反性：对于任何非null的引用值x，x.equals(x)必须返回true
    - 对称性：对于任何非null的引用值x、y，x.equals(y)返回true，则y.equals(x)也要返回true
    - 传递性：对于任何非null的引用值x、y、z，x.equals(y)返回true，y.equals(z)返回true，则x.equals(z)必须返回true
    - 一致性：对于任何非null的引用值x和y，只要equals的比较操作在对象中所用的信息没有被修改，多次调用x.equals(y)必须返回同样的结果
    
    为了编写高质量的equals方法，有以下建议：
    - 使用`==`操作符检查参数是否为这个对象的引用
    - 使用`instance of`操作符检查参数是否为正确的类型
    - 把参数转换成正确的类型
    - 对于该类中的每个关键域，检查参数中的域是否与该对象中对应的域相匹配
    - 当你编写完成了equals方法之后，要问一问自己它是否是对称的、传递的、一致的。
    - 覆盖equals方法时总要覆盖hashCode
    
- 第9条 覆盖equals时总要覆盖hashCode

    当覆盖了equals而未覆盖了hashCode方法，则导致该类无法结合所有基于散列的集合一起正常工作，这样的
    集合包括HashMap、HashSet和Hashtable。
    
    如果两个对象根据equals方式比较是相等的，则两个对象的hashCode方法必须产生同样的整数结果。
    
- 第10条 始终要覆盖toString

    虽然java.string.Object提供了toString方法，但它返回的字符串通常不是用户所期望看到的，覆盖toString将会有利于
    阅读。
    
- 第11条 谨慎地覆盖clone

- 第12条 考虑实现Comparable接口

    CompareTo方法并没有在Object中声明，而是Comparable接口的唯一函数
    
    