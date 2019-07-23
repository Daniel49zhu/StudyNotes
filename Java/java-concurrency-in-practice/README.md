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
    