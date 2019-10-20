- ### 入门
    Elasticsearch是一个实时分布式搜索和分析引擎，它用于全文搜索、结构化搜搜、分析以及
    将这三者混合使用。ES鼓励你浏览并利用你的数据而不是让它烂在数据库里。ES是一个基于Apache
    Lucene的开源搜索引擎。但Lucene只是一个库，想要使用它必须使用Java作为开发语言并将其直接
    集成到你的应用中。ES也是使用Java开发并使用并使用Lucene作为核心来实现索引和搜索的功能，
    但它通过简单的RESTful API来隐藏复杂性，使全文检索变得简单。
    
    节点（node）是运行着的一个es实例。集群（cluster）是一组具有相同cluster.name的节点集合。
    
    es是面向文档的，这意味着它可以存储整个对象或文档，并会索引每个文档的内容使之可被搜索。在
    es中，你可以对文档进行索引、搜索、排序、过滤。
    ```
    关系型数据库   -> Databases -> Table -> Rows -> Columns
    ElasticSearch -> Indices   -> Types -> Documents -> Fields
    ```
    es集群可以包含多个索引（Indices），每一个索引包含多个类型（Type），每一个类型包含多个
    文档（documents），然后每个文档包含多个字段（Fields）。索引在es中有着不同的含义，其代表传统
    数据库中的database，它是文档存储的地方。传统数据中使用B-tree索引来加速检索，es和Lucene使用
    一种叫做倒排索引（inverted index）的数据结构来达到相同目的。
    
    ```
    PUT /megacorp/employee/1
    {
    "first_name" : "John",
    "last_name" : "Smith",
    "age" : 25,
    "about" : "I love to go rock climbing",
    "interests": [ "sports", "music" ]
    }
    ```
    该请求向es中增加了索引和文档，索引名megacorop,类型名employee
    ```
    GET /megacorp/employee/1
  
    ->
    {
      	"_index": "megavorp",
      	"_type": "employee",
      	"_id": "1",
      	"_version": 2,
      	"_seq_no": 1,
      	"_primary_term": 1,
      	"found": true,
      	"_source": {
      		"first_name": "John",
      		"last_name": "Smith",
      		"age": 25,
      		"about": "I love to go rock climbing",
      		"interests": [
      			"sports",
      			"music"
      		]
      	}
      }
    ```
    上述请求将检索哦我们添加的员工信息，原始的JSON文档包含在'_source'字段中,如果想检索所有可以尝试，默认会返回前10个结果
    ```
    GET /megacorp/employee/_serach   
  
    GET /megacorp/employee/_search?q=last_name:Smith
  
    GET /megacorp/employee/_search
    {
        "query" : {
            "match" : {
                "last_name" : "Smith"
            }
        }
    }
    ```
  
  利用es进行全文搜索，会返回相关性（relevance）最大的结果集。而在传统数据库中则只有匹配和不匹配。在es中高亮匹配片段
  也是非常容易的
  ```
    GET /megacorp/employee/_search
    {
        "query" : {
            "match_phrase" : {
                "about" : "rock climbing"
            }
        },
            "highlight": {
            "fields" : {
                "about" : {}
            }
        }
    }
  ```
  
  es有一个聚合（aggregations）功能，允许你在数据上生成复杂的分析统计，它很类似SQL中的`group by`，但是功能更强大
  
- ### 分布式集群

    一个节点就是一个es实例，集群是由一个或多个节点组成，它们有相同的cluster.name，他们协同工作，分享和负载数据。集群中的
    一个节点会被选为主节点（master），它将临时管理集群级别的一些变更，例如新建或删除索引、增加或移除节点等。主节点
    不参与文档级别的变更或搜索。
    每个节点都知道文档存在于哪个节点上。
    
    索引是一个用来指向一个或多个分片的逻辑命名空间。
    
- ### 数据
    
    文档除了数据，还包含三个元数据，_index文档存储的地方,_type文档类型,_id唯一标识
    ```
    PUT /{index}/{type}/{id}
    {
        "field": "value",
        ...
    }
    ```
  
    
