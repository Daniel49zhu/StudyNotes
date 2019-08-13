- 第1章 MyBatis入门

    [CountryMapperTest](mybatis-demo/demo-1/src/test/java/com/zjc/mybatis/CountryMapperTest.java)
1. 通过Resources工具类将配置文件读入Reader
2. 通过SqlSessionFactoryBuilder构建SqlSessionFactory对象
3. 通过SqlSessionFactory获取SqlSession，通过其selectList方法罩到映射文件中id=“selectAll”的方法，
执行SQL查询。
4. MyBatis底层使用JDBC执行SQL，获取ResultSet之后，根据resultType的配置将结果映射为Country对象

- 第2章 MyBatis XML方式的基本用法

    [UserMapper.xml](mybatis-demo/demo-2/src/main/resource/tk/mybatis/simple/mapper/UserMapper.xml),
    - select标签代表查询语句
    - id属性是命名空间中的唯一标识，用来代表这条语句
    - resultMap：用于设置返回值的类型映射关系
    - select标签中的		select * from sys_user where id = #{id} 是查询语句
    - `#{id}`:预编译参数的一种方式，大括号中是传入的参数名

    