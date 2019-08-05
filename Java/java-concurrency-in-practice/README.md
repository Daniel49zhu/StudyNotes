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

    在某些情况下，通过多个线程安全的类组合而成的类可能是线程安全的，也可能不是。
    ```
    @ThreadSafe
    public class DelegatingVehicleTracker {
        private final CouncurrentMap<String,Point> locations;
        private final Map<String,Point> unmodifiableMap;
        
        public DelefatingVehicleTracker(Map<String,Point> poinits) {
            locations = new ConcurrentHashMap<String,Point>(points);
            unmodifiableMap = Collections.unmodifiableMap(locations);
        }
        
        public Map<String,Point> getLocation() {
            return unmodifiableMap;
        }
        
        public Point getLocation(String id) {
            return location.get(id);
        }
        
        public void setLocation(String id,int x,int y) {
            if(location.reaplce(id,new Point(x,y)==null)) 
                throw new IllegalArgumentException("invalid vehicle name:"+id);
        }
    }
    
    @Immutable
    public class Point {
        public final int x,y;
        public Point(x,y) {
            this.x = x;
            this.y = y;
        }
    }
    ```
    
    ```
    @NotThreadSafe
    public class ListHelper<E> {
        public List<E> list = Collections.synchronizedList(new ArrayList<E>());
        
        public synchronized boolean putIfAbsent(E x) {
            boolean absent = !list.contains(x);
            if (absebt) list.add(x);
            return absent;
        }
    }
    ```
    这个方法虽然呗声明为synchronized，但其使用的锁并不是ListHelper上的锁，只是带来了同步的假象，
    为了使方法能够正确执行，必须在实现客户端加锁或外部加锁时使用同一个锁。
    ```
        @ThreadSafe
        public class ListHelper<E> {
            public List<E> list = Collections.synchronizedList(new ArrayList<E>());
            
            public  boolean putIfAbsent(E x) {
                synchronized(list) {
                    boolean absent = !list.contains(x);
                    if (absebt) 
                        list.add(x);
                    return absent;
                }
            }
        }
    ```
    当要为现有的类添加一个原子操作时，更好的一种做法是组合。
    ```
    @ThreadSafe
    public class ImprovedList<T> implements List<T> {
        private final List<T> list;
        
        public ImprovedList(List<T> list) {
            this.list = list;
        }
        
        public synchronized boolean putIfAbsent(T x) {
            boolean contains = list.contains(x);
            if(!contains) list.add(x);
            return !contains;     
        }
    }
    ```
    ImprovedList并不关心底层List是否是线程安全的，而是通过提供一致的加锁机制来实现其安全性。
    
- 第5章 基础模块构建

    阻塞队列适用于生产者-消费者模式，双端队列（Deque和BlockingDeque），适用于另一种相关模式，工作密取（Work Stealing），
    当一个消费者处理完自己的双端队列中的任务，就可以秘密的从其他消费者的队列末尾获取工作。相较于
    传统的生产消费者模型拥有更高的可伸缩性。
    
    ```
    public class TestHarness {
        public long timeTasks(int nThreads,final Runnable task) throws InterruptException{
            final CountDownLatch startGate = new CountDownLatch(1);
            final CountDownLatch endGate = new CountDownLatch(nThreads);
            
            for(int i=0;i<nThreads;i++) {
                Thread t = new Thread() {
                    public void run() {
                    try {
                            startGate.await();
                            try {
                                task.run();
                            } finally {
                                endGate.countDown();
                            }
                        } catch (InterruptedException ignored{})
                    }
                };
                t.start();
            }
            long start = System.nanoTime();
            startGate.countDown();
            endGate.await();
            long end = System.nanoTime();
            return end-start;
        }
    }
    ```
    
    ```
    public interface Computeble<A,V> {
        V compute(A arg) throws InterruptException;
    }
    
    public class ExpensiveFunction implements Computable<String,BigInteger> {
        public BigInteger compute(String arg) {
            //......
            return new BigInteger(arg);
        }
    }
    
    public class Memoizer1<A,V> implements Computable<A,V> {
        private final Map<A,V> cache = new HashMap<A,V>();
        private final Computable<A,V> c;
        
        public Memoizer1(Computable<A,V> c) {
            this.c = c;
        }
        
        public synchronized V compute(A arg) throws InterruptException {
            V result = cache.get(arg);
            if (result == null) {
                result = c.compute(arg);
                cache.put(arg,result);
            }
            return result
        }
    }
    ```
    我们通过HashMap实现了一个带缓存功能的class，为了确保线程安全，对整个compute进行了同步，这就会带来
    一个明显的伸缩性问题，每次只有一个线程能够执行compute。在下面的程序中我们通过线程安全的类ConcurrentHashMap
    来避免这种对compute方法进行串行.
    ``` 
     public class Memoizer2<A,V> implements Computable<A,V> {
            private final Map<A,V> cache = new ConcurrentHashMap<A,V>();
            private final Computable<A,V> c;
            
            public Memoizer2(Computable<A,V> c) {
                this.c = c;
            }
            
            public V compute(A arg) throws InterruptException {
                V result = cache.get(arg);
                if (result == null) {
                    result = c.compute(arg);
                    cache.put(arg,result);
                }
                return result
            }
    }
    ```
    但是这个类也存在问题，如果compute进行的计算需要很大的开销。而其他线程并不知道这个计算正在进行，就会
    出现重复计算。理想的结果就是等待线程X计算结果，而FutureTask能完成这个功能。
    ```
      public class Memoizer3<A,V> implements Computable<A,V> {
                private final Map<A,V> cache = new ConcurrentHashMap<A,Future<V>>();
                private final Computable<A,V> c;
                
                public Memoizer3(Computable<A,V> c) {
                    this.c = c;
                }
                
                public V compute(final A arg) throws InterruptException {
                    Future<V> f= cache.get(arg);
                    if(f==null) {
                        Callable<V> eval = new Callable<V>() {
                            public V call() throws InterruptException {
                                return c.compute(arg);
                            }
                        };
                        FutureTask<V> ft = new FutureTask<V>(eval);
                        try {
                            return f.get();
                        } catch (ExecutionException e) {
                            throws launderThrowable(e.getCause());
                        }
                    }
                }
        }
    ```
    Memoizer3几乎是完美的，有非常好的并发性，但仍然存在零个线程进行相同计算的漏洞，这个漏洞的发生概率要远小于Memoizer2。
    ```
        ```
          public class Memoizer<A,V> implements Computable<A,V> {
            public V compute(final A arg) throws InterruptException {
                while(true) {
                    Future<V> f = cache.get(arg);
                    if(f==null) {
                        Callable<V> eval = new Callable<V>() {
                            public V call() throws InterruptException {
                                return c.compute(arg);
                            }
                        };
                        FutureTask<V> ft = new FutureTask<V>(eval);
                        f = cache.putIfAbsent(arg,ft);
                        if(f==null) {f= ft;ft.run();}
                    }
                    try {
                        return f.get();
                    } catch (ExecutionException e) {
                        throws launderThrowable(e.getCause());
                    }
                }
            }
        } 
    ```
    
    第一部分小结：
    - 可变状态是至关重要的（It's the mutable state，stupid）：所有的
        并发问题都可以归结为如何协调对并发状态的访问，可变状态越少，越容易确保线程安全性。
    - 尽量将域声明为final，除非需要它们是可变的。
    - 不可变对象一定是线程安全的
    - 封装有助于管理复杂性：将数据封装在对象中，更易于维持不变性条件
    - 用锁来保护每个可变变量
    - 当保护同一个不变性条件中所有的变量时，需要使用用一个锁
    - 在执行复合操作期间，要持有锁
    - 如果从多个线程中访问同一个可变变量没有同步机制，程序就可能出问题
    - 在设计过程中考虑线程安全，或者在文档中明确指出它不是线程安全的
    - 将同步策略文档化
        
- 第6章 任务执行

    大多数并发应用都是围绕“任务执行（Task Execution）”来构造的：任务通常是一些抽象的
    且离散的工作单元。通过把应用程序的工作分解到多个任务中，可以简化程序的组织结构。
    ```
    class SingleThreadWebServer {
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while(true) {
                Socket connection = socket.accept();
                handleRequest(connection);
            }
        }
    }
    ```
    这个程序理论上时正确的，但是在实际生产环境执行性能会很糟糕，因为一次只能处理一个请求，当
    服务器正在处理请求时，新来的连接必须等待请求处理完成。
    
    通过为每一个新来的请求建立一个新的线程来提供服务，可以实现更高的响应性。
    ```
    class ThreadPerTaskWebServer {
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while(true) {
                final Socket connection = soclet.accept();
              Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
              };
                new Thread(task).start();
            }
        }
    }
    ```
    为每一个任务分配一个线程存在一些缺陷，尤其是当需要创建大量的线程时，
    - 线程生命周期的开销非常高
    - 活跃的线程会消耗系统资源，尤其是内存
    - 可创建的线程数随着平台的限制而各不相同，也受JVM启动参数等限制
    
    线程池在简化了线程的管理工作，并且java.util.concurrent提供了一种灵活的
    线程池实现作为Executor框架的一部分，任务执行的主要抽象不是Thread，而是Executor
    ```
    public interface Executor {
        void execute(Runnable command);
    }
    ```
    虽然Executor是个简单的接口，但缺位灵活且强大的异步任务执行框架提供了基础，Executor基于
    生产者-消费者模式，提交任务的操作相当于生产者，执行任务的线程则相当于消费者。
    ```
    class TaskExecutionWenServer {
        private static final int NTHREADS = 100;
        private static final Executor exec
                = Executors.newFixedThreadPool(NTHREADS);
    
        public static void main(String[] args) throws IOException {
            ServerSocket socket = new ServerSocket(80);
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = new Runnable() {
                    public void run() {
                        handleRequest(connection);
                    }
                };
                exec.execute(task);
            }
        }
    }
    ```
    在TaskExecutionWebServer中，通过使用Executor，将请求处理任务的提交与任务的执行解耦开来。
    Executors中的静态工厂方法可以创建的有：
    - newFixedThreadPool，定容线程池
    - newCachedThreadPool，线程池规模无上限
    - newSingleThreadExecutor，单线程的Executor
    - newScheduledThreadPool，定容线程池，而且通过延迟或定时的方式来执行任务
    
    Executor框架使用Runnable作为基本的任务表达形式，Runnable有一种很大的局限，因为任务
    实际上都存在延迟的计算，执行数据库查询，从网络上获取资源，或者计算某个复杂的功能。对于
    这些任务，Callable是一种更好的抽象：它认为主入口点（call方法）将返回一个值，并可能
    抛出异常。
    
    示例：使用Future来实现页面渲染器，为了使页面渲染实现更高的并发性，将渲染过程分解为
    两个任务，一个负责渲染所有文本，另一个下载图片。（因为其中一个是CPU密集，一个是IO密集）
    ``` 
    public class FutureRenderer {
        private final ExecutorService executor = Executors.newCachedThreadPool();
    
        void renderPage(CharSequence source) {
            final List<ImageInfo> imageInfos = scanForImageInfo(source);
            Callable<List<ImageData>> task =
                    new Callable<List<ImageData>>() {
                        public List<ImageData> call() {
                            List<ImageData> result = new ArrayList<>();
                            for (ImageInfo imageInfo : imageInfos) {
                                result.add(imageInfo.downloadImage());
                            }
                            return result;
                        }
                    };
            Future<List<ImageData>> future = executor.submit(task);
            renderText(source);
            
            try {
                List<ImageData> imageData = future.get();
                for(ImageDatt data : imageData) {
                    renderImage(data);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                future.cancel(true);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }
    ```
    
- 第7章 取消与关闭

    任务和线程的启动很容易，大多数情况下哦我们会让其运行到结束。然后有时我们希望提前结束任务或线程，或是因为
    用户取消了操作，或是应用程序需要快速关闭。
    
    如果外部代码能在某个操作正常完成之前将其置入“完成”状态，那么这个操作就可以称为可取消的（Cancellable）。
    ```
    @ThreadSafe
    public class PrimeGenerator implemens Runnable {
        @GuardBy("this") private final List<BiInteger> primes 
                                    = new ArrayList<BigInteger>();
        private volatile boolean cancelled = false;
        
        public void run() {
            BigInteger p = BigInteger.ONE;
            while(!cancelled) {
                p = p.nextProbablePrime();
                synchronized(this) {
                    primes.add(p);
                }
            }
        }
        
        public void cancel() {cancelled=true;}
        
        public synchronized List<BigInteger> get() {
            return new ArrayList<BigInteger>(primes); 
        }
    }
    ```
    PrimeGenerator使用了一种简单的取消策略：客户通过调用cancel来取消请求。ExecutorService也提供共
    两种关闭方法：shutdown正常关闭和shutdownNow强行关闭