#### 《正则表达式必知必会》

- 引言

    正则表达式（Regular Expression）已经出现了很多年，它可以用来完成各种复杂的文本处理
    工作，更重要的是，几乎所有程序设计语言和平台都支持。我们将会从简单的文本匹配开始，循序渐进的设计
    一些复杂的专题，包括回溯引用(backreference)、条件性求值(conditional evaluaion)和前后查找(lookingaround)等
    
- 第1章 入门

    正则表达式（Regular Expression，regex）是一种工具，为了解决一类专门的问题而发明的，包含
    查找特定的信息（搜索）和查找并编辑特定的信息（替换）。简单的说，正则就是用来匹配和处理文本的字符串。
    
    以下都是合法的正则表达式
   ```
   Ben
   .
   www\.forta\.com
   [a-zA-Z0-9_.]*
   <[Hh]1>.*</[Hh]>
   \r\n\r\n
   \d{3,3}-\d{3,3}-\d{4,4}
    ```
    语法是正则表达式中最容易掌握的部分，真正的挑战是学会运用语法解决实际问题。
    本书提供了一个小工具来帮助可视化的匹配正则
    [regextester.html](regextester.html)
    
- 第2章 匹配单个字符
    
    - 匹配纯文本
    
    `Ben`是一个正则表达式，也是一个纯文本，用来匹配自身。
    
    - 有多个匹配结果
    
    正则表达式引擎默认值返回第一个匹配到的结果，如果要一次把所有匹配的结果
    全部找出来（返回一个数组或是其他专有格式）。在Javascript中有可选的g标志来开启全局匹配。
    
    - 不区分大小写
    
    默认正则是区分大小写的，可以通过可选的i来忽略大小写
    
   ```
   > var str = "123#abc#ABC";
   var re = /abc/ig;
   re.exec(str)
   < ["abc", index: 4, input: "123#abc#ABC", groups: undefined]
   
   > re.exec(str)
   <["ABC", index: 8, input: "123#abc#ABC", groups: undefined]
   ```
   
   - 匹配任意字符
   
   在正则表达式里，特殊字符（或字符集合）用来给出要搜索的对象。`.`字符有可以匹配任何一个单个
   的字符，这类似与DOS中的`?`和SQL中的`_`字符。
   因此`c.t`可以同时匹配`cat`和`cot`。
   
   ```
   > var str = "sales1.xls sales2.xls sales3.xls sa1.xls"
   var reg = /sales./g
   reg.exec(str)
   < ["sales1", index: 0, input: "sales1.xls sales2.xls sales3.xls sa1.xls", groups: undefined]
   > reg.exec(str)
   < ["sales2", index: 11, input: "sales1.xls sales2.xls sales3.xls sa1.xls", groups: undefined]
   > reg.exec(str)
   < ["sales3", index: 22, input: "sales1.xls sales2.xls sales3.xls sa1.xls", groups: undefined]
   > reg.exec(str)
   ```
   - 匹配特殊字符
   
   如果就是希望匹配`.`，则必须加一个`\`(反斜杠)进行转义
   ```
   > var str = "sa.xls na.xls pap.xls"
      var reg = /.a\./g
      reg.exec(str)
   < ["sa.", index: 0, input: "sa.xls na.xls pap.xls", groups: undefined]
   > reg.exec(str)
   < ["na.", index: 7, input: "sa.xls na.xls pap.xls", groups: undefined]
   > reg.exec(str)
   < null
   ```
   `\`是一个元字符（metacharacter），表示这个字符有特殊的含义，而不是字符本身的含义。
   
- 第3章 匹配一组字符

    本章将会学习如何与字符集合打交道。
    
    - 匹配多个字符中的某一个
    
    在正则表达式里，我们可以使用`[`和`]`来定义一个字符集合。在使用`[`和`]`定义的字符集合里，
    在这两个元字符之间的所有字符都是该集合的组成部分。
    ```
    > var str = "na1.xls sa1.xls sa2.xls ca1.xls";
    var reg = /[ns]a.\.xls/g;
    reg.exec(str);
    < ["na1.xls", index: 0, input: "na1.xls sa1.xls sa2.xls ca1.xls", groups: undefined]
    > reg.exec(str);
    < ["sa1.xls", index: 8, input: "na1.xls sa1.xls sa2.xls ca1.xls", groups: undefined]
    > reg.exec(str);
    < ["sa2.xls", index: 16, input: "na1.xls sa1.xls sa2.xls ca1.xls", groups: undefined]
    > reg.exec(str);
    < null
    ```
    `[ns]`这个集合匹配了字符n或s（但不会匹配c字符），接下来将会匹配a字符
    
    
   