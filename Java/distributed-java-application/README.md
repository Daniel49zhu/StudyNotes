- 第1章 分布式Java应用

    大型应用通常会被拆分成多个子系统，这些子系统可能被部署在一台机器上，也可能被不是在
    不同机器上，之间通过通信来共同实现业务功能。
    
    当要通信时，就需要向外发送消息，包括字节流、字节数组甚至是Java对象， 常用的协议有TCP和UDP。
    完成传输还需要对数据进行读取和写入，这就需要同步IO，BIO或NIO，异步ID，AIO的支持。
    
    在Java中主要通过Socket、ServerSocket来实现TCP+BIO的系统间通信，ServerSocket用于实现服务器端口
    的监听及Socket对象的获取。Socket用来建立连接和网络IO操作。BIO常常是一个连接一个线程。
    
    TCP+NIO可基于java.nio.channels中的Channel和Selector的相关类来实现。Channel有SocketChannel和ServerSocketChannel
    两种，SocketChannel用来建立连接、监听事件及读写操作。ServerSocketChannel用于监听端口及
    监听连接事件；程序通过Selector来获取是都有要处理的事件。NIO是典型的Reactor模式的实现。
    
    UDP+BIO同样采用Socket机制，Java中可基于DatagramSocket和DatagramPacket来实现。DatagramSocket负责监听
    端口及读写数据。DatagramPacket作为数据流对象进行传输。由于UDP通信两端不建立连接，就不会有通信连接
    竞争的问题，只是最终读写流的操作是同步的。
    
    UDP+NIO可通过DatagramChannel和ByteBuffer来实现。DatagramChannel负责监听端口及读写，ByteBuffer则用于数据流传输。
    
    如果要实现多播，则在Java中基于MulticastSocket和DatagramPacket来实现多播网络通信。在Java应用中，多播常用于多台
    机器间的状态同步。
    
    远程调用方式就是尽可能使系统间的通信和系统内一样，实现远程调用的技术主要有RMI和WebService两种。WebService是一种
    跨语言的系统间交互标准。在这个标准中，对外提供功能的一方以HTTP的方式提供服务。调用端和服务端通过SOAP（Simple Object
    Access Protocol）方式进行交互。JavaSE6中继承了WebService，通过@WebService来标记对外暴露的WebService实现类。
    
    Spring RMI是Spring Remoting中的一个子框架，基于Spring RMI可以简单地就实现RMI方式地Java远程调用。
    
    Apache CXF是Apache的一个顶级项目，也是目前Java社区中用来实现WebService流行的一个开源框架。类似这样的开源框架还有不少例如Axis。
    
- 第2章 大型分布式Java应用与SOA

    当对系统进行拆分时，很可能出现不同系统之间的交互方式不同：Http、TCP+NIO、Hessian、RMI、WebService等；同步、异步交杂。
    解决方式就是统一交互方式，SOA无疑时实现这种方式的首选。SOA全称是面向服务架构。
    
- 第3章 深入理解JVM
    
    Java程序运行在JVM上，JVM的运行状况对于Java程序而言会产生很大影响。因此掌握JVM中的关键机制对于编写稳定、高性能的Java
    程序至关重要。
    
    Java源码编译机制：1.分析和输入到符号表（Parse and Enter），2.注解处理（Annotation Processing），
    3.语义分析和生成class文件（Analyse and Generate）
    
    类加载机制：是指class文件加载到JVM，并形成Class对象的机制，之后应用就可对Class对象进行实例化并调用。JVM将装载
    分为三个步骤：1.装载，2.链接，3.初始化。
    
    类执行机制：加载完成后，就可以执行Class对象的静态方法或实例化对象来调用了。JVM通过invokestatic、invokespecial、
    invokevirtual及invokeinterface 4种指令调用对应类型的方法。
    
- 第4章 分布式Java应用与JDK类库

    集合：ArrayList、LinkedList、Vector、Stack、HashSet、TreeSet、TreeMap
    
    并发包：ConcurrentHashMap、CopyOnWriteArrayList、CopyOnWriteArraySet、ArrayBlockingQueue、
        AtomicInteger、ThreadPoolExecutor
    