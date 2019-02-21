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
    