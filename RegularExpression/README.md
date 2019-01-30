#### 《正则表达式必知必会》

- 引言

    正则表达式（Regular Expression）已经出现了很多年，它可以用来完成各种复杂的文本处理
    工作，更重要的是，几乎所有程序设计语言和平台都支持。我们将会从简单的文本匹配开始，循序渐进的设计
    一些复杂的专题，包括回溯引用(backreference)、条件性求职(conditional evaluaion)和前后查找(lookingaround)等
    
- 第一章 入门

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
    
- 第二章 匹配单个字符
    
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
    
    
   