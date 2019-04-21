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
   