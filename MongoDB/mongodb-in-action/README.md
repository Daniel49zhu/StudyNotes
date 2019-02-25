- 全新的Web数据库

    MongoDb是为快速开发互联网Web应用而设计的数据库系统。其数据模型和持久化策略就是为了构建高读/写吞吐量和
    高自动灾备伸缩性的系统。MongoDb的数据存储在文档里，什么是文档？
    ```
    {
        _id:10,
        username:'peter',
        email:'peter@163.com'
    }
    ```
    这是个非常简单的文档，保存的是用户的信息字段，如果这是在关系型数据库中，当用户有多个邮箱，可能会需要
    单独的一张邮箱表并通过外键来关联用户，而MongoDb可以
    ```
    {
            _id:10,
            username:'peter',
            email:[
            'peter@163.com',
            'peter2@163.com'
            ]
        }
    ```
    在数据模型发生变化时只需调整文档的结构即可，不必担心数据模型发生变化。MongoDb的文档格式基于JSON。加入要为
    电商网站设计数据库，按照传统关系型数据库的范式设计可能要分割几十张表存储，要查找完整的商品信息则需要关联
    SQL查询。而在MongoDb中则可以将每个商品的信息存储在单个文档里，而且也能更好的映射到面向对象的语言中的Domian对象。
    
    MongoDb以二进制JSON格式存储文档数据，或者叫做BSON。关系型数据库包含表，MongoDb拥有集合，集合与文档的关系类似表与
    其中的行。
    
    ad hoc查询，是指不需要事先定义系统接收何种查询。关系型数据库忠实的执行格式争取的包含各种条件的sql查询。而键值
    存储系统则牺牲了丰富的查询功能来换取简单的伸缩模型。而MongoDb则保留大部分关系型数据库的功能。
    
    例如要查找标签包含politics并且有10个以上投票的文章
    ```
    SELECT * FROM posts 
    JOIN posts_tags ON posts.id = posts_tags.post_id 
    JOIN tags ON posts_tags.tag_id == tags.id
    WHERE tags.text = 'politics' AND posts.vote_count > 10;
    ```
    等价于MongoDb查询
    ```
    db.posts.find({'tags':'politics','vote_count':{'$gt':10}});
    ```
    
    随着数据库中文档数量越来越多，查询成本水涨船高，因为需要一种高效的方式来搜索数据，MongoDb中的索引
    就是B-树（平衡树）。大部分数据库会给每个文档对象一个主键（primary key），一个唯一的数据标识。每个主键
    会自动建立索引。MongoDb也不例外，每个集合可以创建64个索引。之后会深入索引相关的内容。
    
    伸缩数据库最简单的方法就是升级硬件，如扩展硬盘等，这种提升单节点的做法叫做垂直扩展，而水平扩展实在多台机器
    上分布式存储数据库。水平扩展可以运行在多台小型、廉价的设备上，可以减少硬件成本，同时可以降低宕机造成的数据
    丢失。
    
    MongoDb包含了几个命令行工具
    - mongodump和mongostore  --备份和恢复数据库的工具。mongodump把数据库数据保存为原生的BSON格式
    - mongoexport和mongoimport --导入和到处JSON、CSV、TSV格式的数据
    - mongosniff    --一个用于查看发送给数据库命令的嗅探工具
    - monogonstat --与iostat类似，用来轮询MongoDB，提供有帮助的状态信息，包括每秒的操作数，分配虚拟内存的数量，
        以及服务器的连接数量。
    - mongotop --轮询工具，显示他在每个集合读取和写入的时间总数。
    - mongoperf --帮助了解磁盘操作请况
    - mongooplog --展示MongoDb操作日志的信息
    - Bsondump --把BSON文件转为JSON
    
- 第2章 通过JavaScript shell操作MongoDB

    MongoDB把集合分别存储在不同的数据库中。与传统的SQL数据库不同，MongoDB只区分集合的命名空间。要查询MongoDB
    数据库，需要知道存储文档数据的数据库和集合的名字。如果没有指定数据库，shell会默认选择test数据库。为了统一，我们
    切换到tutorial数据库：`use tutorila`,插入文档`db.users.insert({username:"simth"})`，在此刻，磁盘上还没有
    tutorial数据库，也没有users这个集合。因为要为二者创建初始化文件。
    
    此刻可以使用查询来查看数据`db.users.find() -->{ "_id" : ObjectId("5c6ff328524054df3f9ad84c"), "username" : "smith" }`
    ,注意到_id字段已经默认添加到文档中，我们可以把它当成主键。继续插入第二个用户当users集合中`db.users.insert({username:"jones"})`
    ,查询`db.users.find({username:"jones"})`，这个查询条件会返回索引名字jones的文档数据，多个条件的AND和OR操作
    ```
    db.users.find({username:"smith",_id:ObjectId("5c6ff328524054df3f9ad84c")});
    db.users.find({$and:[{_id:ObjectId("5c6ff328524054df3f9ad84c")},{username:"smith"} ]});
    db.users.find({$or:[{_id:ObjectId("5c6ff328524054df3f9ad84c")},{username:"smith"} ]});
    ```
    
    更新文档： 所有的更新至少需要两个参数，第一个指定要更新的文档，第二个指定要修改的属性
    ```
    db.users.update({username:"smith"},{$set:{country:"America"}})
    db.users.update({username:"smith"},{country:""America})
    ```
    第一种是修改文档，在原文档上添加或者修改属性，第二种是替换更新，会直接替换原文档的值，
    除了$set，还可以使用`$push`和`$addToSet`。这两个命令都是往数组中添加数据，但是第二个会阻止
    向数组中添加重复的数据。
    
    删除数据：`db.users.remove()`在不传入参数时，默认会清空users集合里的所有文档，`db.users.drop()`会删除集合及其索引数据。
    
    - 使用索引创建和查询
    
        创建大集合：索引只有在一个集合中文档特别多时才有意义，先创建一个大集合，
        ```
        > for(i = 0 ; i < 20000; i++) {
            db.numbers.save({num:i});
        }
        WriteResult({ "nInserted" : 1 })
        > db.numbers.count();
        20000
        -- 范围查询
        > db.numbers.find({num:{"$gt":19995,"$lt":1998}});
        { "_id" : ObjectId("5c73e38571d823c7dd15b49a"), "num" : 19996 }
        { "_id" : ObjectId("5c73e38571d823c7dd15b49b"), "num" : 19997 }
        ```
        $gt 大于，$lt小于，$gte大于等于，$lte小于等于，$ne 不等于
        
        索引和explain()：如果熟悉关系型数据库对于SQL的EXPLAIN非常熟悉，它是一个调试和优化查询的好工具。
        当所有数据库接收到查询后，必须决定如何执行查询，这称为查询计划。EXPLAIN描述了查询路径并允许
        开发者通过查询使用的索引来诊断慢的查询语句。
        ``` 
        > db.numbers.find({name:{"$gt":19995}}).explain("executionStats");
        {
                ...
                "executionStats" : {
                        "executionSuccess" : true,
                        "nReturned" : 0,
                        "executionTimeMillis" : 90,
                        "totalKeysExamined" : 0,
                        "totalDocsExamined" : 20000,
                        "executionStages" : {
                                "stage" : "COLLSCAN",
                                "filter" : {
                                        "name" : {
                                                "$gt" : 19995
                                        }
                                },
                                ...
        }
        ```
        你会发现`totalDocsExamined`是20000，说明扫描了整个集合，而`totalKeysExamined`是0，说明没有索引。
        因此我们需要为num建来创建一个索引
        `db.numbers.createIndex({num:1})`,
        通过`db.numbers.getIndexes()`来检测该集合的索引
        ```
        [
                {
                        "v" : 2,
                        "key" : {
                                "_id" : 1
                        },
                        "name" : "_id_",
                        "ns" : "numbers.numbers"
                },
                {
                        "v" : 2,
                        "key" : {
                                "num" : 1
                        },
                        "name" : "num_1",
                        "ns" : "numbers.numbers"
                }
        ]
        ```
        说明有两个索引，一个是`_id`的默认索引，叫做`_id_`，一个是`num`索引，叫做`num_1`，再来看一次执行计划
        ```
        > db.numbers.find({num:{"$gt":19995}}).explain("executionStats");
        ...
        "executionStats" : {
                        "executionSuccess" : true,
                        "nReturned" : 4,
                        "executionTimeMillis" : 81,
                        "totalKeysExamined" : 4,
                        "totalDocsExamined" : 4,
                        "executionStages" : {
                                "stage" : "FETCH",
                                "nReturned" : 4,
                                "executionTimeMillisEstimate" : 11,
                                "works" : 5,
                                "advanced" : 4,
                                ...
        ```
        此时就只扫描了4个文档，查询效率大大优化。
        
        - 基本管理
        ```
        > show dbs          --打印系统中所有的数据库列表信息
        > show collections  --展示当前数据库中的所有集合
        > db.stats()        --对数据库和集合的状态分析，也可以在单个集合上执行
        > db.runCommand()   --可以传入参数来调用其他任意命令
        > db.help()         -- 获取帮助
        ```
*停更*     
        
        
    