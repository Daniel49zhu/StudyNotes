- 第1章 简介

  Redis是一个开源的高性能键值对数据库。它通过提供多种键值数据类型来适应不用场景下的存储需求，并借助许多高层级的
  接口使其可以胜任如缓存、队列系统等不同角色。
  
  - 存储结构
  
  有过开发经验的人对字典（或称映射、关联数组）数据结构一定很数据，类似`dict["key"]="value"`中的dict是一个
  字典结构变量，字符串key是键名，value是键值。在字典中我们可以获取或设置键名对应的键值，也可以删除一个键。
  
  Redis是Remote Dictionary Server（远程字典服务器）的缩写，它以字典数据结构存储数据，并允许其他应用通过
  TCP协议读写字典中的内容。Redis字典中的键值除了可以是字符串，还可以是其他数据类型，如下：
    - 字符串类型 
    - 散列类型
    - 列表类型
    - 集合类型
    - 有序集合类型
    这与MySQL中二维表形式的存储结构有很大的差异，假如我们要在post变量存储了一篇文章的数据（包括标题、正文、阅读量和标签）
    ```
    post["title"]="Hello World!"
    post["content"]="Blablabla..."
    post["views"]=0
    post["tags"]=["PHP","Ruby","Node.js"]
    ```
    如果这篇文章存储在数据库中，并要求可以通过标签搜索出文章。一般标题、正文、阅读量会放在一张表中，标签会保存在另一张表中，
    同时可能还会需要第三张表来保存标签和文章之间的关联关系，通过标签搜索文章时需要关联三张表，不是很直观。而Redis字典结构的
    存储方式和对多种键值数据的支持使得开发者可以将程序中的数据直接映射到Redis中。
    
    Redis数据库中的数据都存储在内存中，由于内存的读写速度远快于硬盘，因此在性能上对比其他硬盘存储的数据优势明显。不过当断电
    时内存中的数据会丢失，因此Redis可以将内存中的数据异步的写道硬盘中，同时不影响继续提供服务。
    
    Redis虽然是作为数据库开发的，但其也能被用于缓存、队列系统等。Redis可以为每个键设置生存时间（Time To Live，TTL），其也是另一缓存
    系统Memcached的有力竞争者。
    
    Redis提供了一百多个命令来操作数据库，常用的大约十几种且易于记忆。
    
- 第2章 安装

    略 具体操作参照https://blog.csdn.net/qq_32444825/article/details/80718650
    
- 第3章 入门

    本章将会详细介绍redis的五种数据类型及相应的命令。
    
    - 热身
    
    1. 获得符合规则的键名列表： KEYS pattern
    
    pattern支持如下的字符
    !["glob风格通配符"](images/pattern.jpg "glob风格通配符")
    
    2. 判断键值是否存在：EXISTS key，返回1为存在，0为不存在
    
    3. 删除键：DEL key1 key2，返回值时删除的键的个数
    
    4. 获得键值的数据类型： TYPE key
  
    - 字符串类型
    
    最基本的数据类型，能存储任何形式的字符串，包括二进制数据。你可以用来存储JSON对象甚至是图片，一个字符串
    类型的键允许存储的数据的最大容量是512Mb（3.0版本以前）
    
    - 命令
    1. 赋值：SET key value
    2. 取值：GET key，若键不存在会返回空值
    3. 递增数字：INCR key，键值为整型会自增，键值为空会变为1，键值不是整型则会报错，在关系型数据库中我们可以通过设置字段属性为AUTO_INCREMENT来实现每增加一条记录自动生成一个唯一
    的递增ID的目的，而在Redis中，对于每一类对象使用名为对象类型：count的键（如users:count）来存储当前对象的数量，
    每增加一个新对象都是用INCR命令递增该键的值，返回的数字即是当前对象的总数，也是新增加对象的id。
    
    4. 增加指定的整数：INCRBY key increment
    5. 减少指定的整数：DECR key decrement
    6. 增加指定浮点数：INCRBYFLOAT key increment
    7. 向尾部追加值：APPEND key value，向键值的末尾添加value，返回值是追加后的字符串的总长度
    8. 获取字符串长度：STRLEN key
    9. 同时获得和设置多个键值对：
          ```
          MGET key1 key2
          MSET key1 v1 key2 v2
          ```
          
    10. 位操作：
    ```
    GETBIT key offset
    SETBIT key offset value
    BITCOUNT key [start] [end]
    BITOP operation destkey key [key …]
    ```
    Redis提供了四个命令直接操作键值的二进制的值