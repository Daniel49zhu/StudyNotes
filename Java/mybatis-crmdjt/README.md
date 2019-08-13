- 第1章 MyBatis入门

[第一个例子](mybatis-demo/demo-1/src/test/java/com/zjc/mybatis/CountryMapperTest.java)
1. 通过Resources工具类将配置文件读入Reader
2. 通过SqlSessionFactoryBuilder构建SqlSessionFactory对象
3. 通过SqlSessionFactory获取SqlSession，通过其selectList方法罩到映射文件中id=“selectAll”的方法，
执行SQL查询。
4. MyBatis底层使用JDBC执行SQL，获取ResultSet之后，根据resultType的配置将结果映射为Country对象