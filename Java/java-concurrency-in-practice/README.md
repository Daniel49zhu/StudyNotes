- 第1章 简介

    编写正确的程序很难，而编写正确的并发程序则难上加难。与串行相比，并发程序中存在更多容易
    出错的地方。线程带来的风险有：
    - 安全性问题
    ```
    @NotThreadSafe
    public class UnsafeSequence {
        private int value;
        /** 返回一个唯一的数值 */
        public int getNext() { return value++; }
    }
    ```
    如果执行时机不对，两个线程在调用getNext可能会返回相同的值。虽然value++看似是一个操作，単实际
    包含了三个独立的操作。这种被称为竞态条件（Race Condition）。
    
    - 活跃性问题
    
    安全性的含义是“永远不发生糟糕的事”，而活跃性则是关注“某件正确的事情最终会发生”。诸如死锁、饥饿、活锁等问题，
    因为它往往和不同线程的发生时序相关，所以不容易分析和重现。
    
    - 性能问题
    
    如果涉及不好则多线程会频繁出现上下文切换（Context Switch）操作，带来极大的开销。
    
- 第2章 线程安全性

    要编写线程安全的代码，其核心在于对于状态访问操作进行管理。特别是共享的（Shared）和可变的（Mutable）状态的访问。
    共享意味着变量可以由多个线程同时访问。而可变i围着在其生命周期内可能会发生变化。
    
    Java中的主要同步机制关键字是synchronized，它提供了一种独特的加锁方式。同步还包括了volatile类型的变量，显式锁
    以及原子变量。
    
    如果多个线程访问一个可变变量发生了安全性问题可以从三个角度解决问题：
    - 不在线程之间共享该状态变量
    - 将这个状态变量修改为不可变变量
    - 在访问状态变量时使用同步
    
    ```
    @ThreadSafe
    public class StatelessFactorizer implements Servlet {
        public void service(ServletRequest req,ServletResponse resp) {
            BigInteger i = extractFromRequest(req);
            Biginter[] factors = factor(i);
            encodeIntToResponse(resp,factors);
        }
    }
    ```
    上面是一段简单的因数分解的Servlet的伪代码，它是无状态的，因为其中不包含任何域，也不包含任何
    对其他类中域的引用。计算的结果仅仅依赖线程栈上的局部变量，且只能由当前线程访问。因为两个线程
    之间没有共享状态，因此这个对象是线程安全的。
    
    如果我们在Servlet中增加一个long来技术，每处理一个请求就将值加1。
    ```
    @NotThreadSafe
    public class UnsafeCountingFactorizer implements Servlet {
        private long count = 0;
        public long getCount() {return count;}
        public void service(ServletRequest req,ServletResponse resp) {
            BigInteger i = extractFromRequest(req);
            Biginter[] factors = factor(i);
            ++count;
            encodeIntToResponse(resp,factors);
        }
    }
    ```
    这个Servlet就不是一个线程安全的类，虽然在单线程环境下能正常运行。但是在多线程环境下，可能会丢失一些
    自增的操作，因为++count并不是一个原子操作。这种由于不恰当的执行时序而出现不正确结果的情况叫做“竞态条件”（Race Condition）。
    
    最常见的竞态条件类型就是“先检查后执行（Check-Then-Act）”。即通过一个可能会失效的结果来决定下一步的动作。当你得到一个count数
    时，多线程下这个数值就存在失效的可能。
    ```
    @ThreadSafe
    public class CountingFactorizer implements Servlet {
        private final AtomicLong count = new AtomicLong(0);
        public long getCount() {return count.get();}
         public void service(ServletRequest req,ServletResponse resp) {
            BigInteger i = extractFromRequest(req);
            Biginter[] factors = factor(i);
            count.incrementAndGet();
            encodeIntToResponse(resp,factors);
         }
    }
    ```
    为了避免可以将自增操作原子化，以上是一种线程安全的实现方式。java.util.concurrent.atomic包中包含了
    一些原子变量类，用于实现在数值和对象因用上的原子状态转换。用AtomicLong来代替long，以确保对count的
    所有操作都是原子的。
    
    当只有一个状态变量时，我们可以通过线程安全的对象来代替，以维护Servlet的线程安全性。但如果想在Servlet中
    添加更多的状态，则不仅仅是使用多个原子类来代替。java提供了一种内置的锁机制来支持原子性：同步代码块
    （Synchronized Block）。
    
    同步代码块包含两个部分：一个作为锁的对象引用，一个作为由这个锁保护的代码块。以关键词synchronized来修饰的
    方法就是一种横跨整个方法体的同步代码块。静态的synchronized以整个Class对象作为锁。
    ```
    @ThreadSafe
    public class SyncronizedFactorizer implements Servlet {
        private BigInteger lastNumber;
        private BigInteger[] lastFactors;
        
        public synchronized void service(ServletRequest req,ServletResponse resp) {
            BigInteger i = extractFromRequest(req);
            if(i.equals(lastNumber)) {
                encodeIntoResponse(resp,lastFactors);
            } else {
                BigInteger[] factors = factor(i);
                lastNumber = i;
                lastFactos = factoes;
                encodeIntoRersponse(resp,factors);
            }
        }
    }
    ```
    现在这个类是安全的了，但方法却过于极端，因为多个客户端无法同时使用该Servlet，服务响应性非常低。
    
    当一个线程请求由别的线程持有的锁时，发出请求的线程会被阻塞。但内置锁时可重入的，因此当线程试图获得
    一个已经由它持有的锁时，请求就会成功。重入意味着锁的操作粒度是针对“线程”而不是“方法调用”。
    
    上面的SyncronizedFactorizer类，通过同步整个service方法来确保安全性，但代码的执行性能将会非常糟糕。当需要因数分解
    一个大数值则需要执行很长时间，其他的客户端就会一直等待。我们将这种Web应用程序称为不良并发（Poor Concurrency）应用。
    
- 第3章 对象的共享

    第2章主要是讲述如何通过同步避免多个线程在同一时刻访问先攻的数据。本章将介绍如何通过共享和发布对象，使它们能够安全地由多个
    线程同时访问。
    [NoVisibility](concurrency-demo/src/main/java/com/zjc/concurrencydemo/cp3/NoVisibility.java)
    NotVisibility可能会持续循环下去，因为读循环一直看不到ready的值，也可能输出0，因为看到了ready却没看到number的值。
    这种现象被称为重排序，当主线程首先写入number，然后在没有同步的情况下写入ready，那么读线程看到的顺序可能完全相反。
    这被称为重排序（Reordering）。NotVisibility是一个简单的并发程序，只包含两个共享变量和两个线程，只要有数据在多个
    线程间共享，就能使用正确的同步。
    ```
    @NotThreadSafe
    public class MutableInteger {
        private int value;
        public int get() {return value;}
        public void set(int value) {this.value=value;}
    }
    ```
    上例不是一个线程安全的类，因为get和set方法都是在没有同步的情况下访问value的，当一个线程使用set，另一个正在调用get的
    线程可能会看到更新了的value也可能看不到。通过对get、set方法进行同步，可以有效解决这个问题。
    
    对于非volatile类型的long和double变量，JVM允许将对64位操作拆解成两个32位操作。
    
    Java提供了一种稍弱的同步机制，即volatile变量，用来确保将变量的更新操作通知到其他线程。当一个变量被声明为
    volatile时，编译器会注意到这个变量是共享的，因此不会将该变量上的操作与其他内存操作一起重排序。你可以把
    对volatile变量想象成其读写操作都进行了加锁。然而我们不建议过度依赖volatile提供的变量可见性，如果通过
    volatile来控制状态可见，同行比用锁更加脆弱，也会更难以理解。加锁技能确保可见性，又能确保原子性，而volatile
    只能确保可见性。
    
    当且仅当满足以下所有条件时才建议使用volatile：
    - 当变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值
    - 该变量不会与其他状态变量一起纳入不变性条件中
    - 在访问变量时不需要加锁
    
    如果仅在单线程内部访问数据，就不需要使用同步，这种技术被称为线程封闭（Thread Confinement）。一种常见的
    应用就是JDBC的Connection对象。线程封闭的一种规范用法是使用ThreadLocal，这个类能使线程中的某个值与保存
    值的对象联系起来。ThreadLocal提供了get与set等访问接口或方法。
    
    ThreadLocal对象通常用于防止对可变的单实例变量或全局变量进行共享。通过将JDBC的连接保存到ThreadLocal中，每个
    线程都会拥有书自己的连接对象。
    ```
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<> {
        public Connection initalValue() {
            return DriverManaget.getConnection(DB_URL);
        }
    };
    public static Connection getConnection() {
        return connectionHolder.get();
    }
    ```
    
- 第4章 对象的组合

    通过组合模式可以将一些现有的线程安全组件组合为更大规模的应用，而不用对每一次内存访问都进行安全性分析。
    在设计线程安全类的过程中，需要包含以下三个基本要素：
    - 找出构成对象状态的所有变量
    - 找出约束状态变量的不变性条件
    - 建立对象状态的并发访问管理策略
    ```
    @ThreadSafe
    public class PersonSet {
        private final Set<Person> mySet = new HashSet<Person>();
        
        public synchronized void addPerson(Person p) {
            mySet.add(p);
        }
        
        public synchronized boolean containsPerson(Person p) {
            return mySet.contains(p);
        }
    }
    ```

    