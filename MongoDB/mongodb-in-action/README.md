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
    