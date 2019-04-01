- 第1章 了解web及网络基础

    - 使用HTTP协议访问Web
    
    Web使用一种名为HTTP（HyperText Transfer Protocol）超文本传输协议作为规范，完成从客户端到服务端等一系列
    运作流程。可以说Web就是建立在HTTP协议上通信的。
    
    HTTP/1.0
    https://www.ietf.org/rfc/rfc1945.txt
    
    HTTP/1.1
    http://www.ietf.org/rfc/rfc2616.txt
    
    - 网络基础TCP/IP
    
        为了理解HTTP，我们必须先了解一下TCP/IP协议族。通常使用的互联网是在TCP/IP基础上运作的。
    而HTTP属于它内部的一个子集。
        计算机如果要相互通信，必须基于相同的方法，比如如何探测到通信目标，那一边先发起通信，怎么结束
    通信等。不同的硬件、操作系统之间的通信，所有的这一切都需要一种规则，我们把这种规则称为协议（protocol）。
        
        协议存在各种各样的内容，从电缆的规格到IP地址的选定方法、寻找异地用户的方法、双方建立通信的顺序，以及Web页面
    显示需要处理的步骤等等。我们把与互联网相关的协议结合总称为TCP/IP。
    
        - 应用层： FTP，DNS，HTTP
        - 传输层： TCP，UDP
        - 网络层
        - 链路层
    - 与HTTP关系密切的协议：IP、TCP、DNS

   