变量
var 声明1个或多个变量。您可以一次声明多个变量。Go将推断出初始化变量的类型。
在没有相应初始化的情况下声明的变量为零值。例如，对于一个int初始值是0。
`:=`语法速记用于声明和初始化的变量，例如用于 var f string = "apple"在这种情况下      

常量
const 声明一个常量值。   Go支持字符，字符串，布尔值和数字值的常量。   常量表达式以任意精度执行算术运算。

for是Go唯一的循环结构 [for.go](05For.go)

`range`关键字可以迭代多种数据结构中的元素，包含数组、slice、Map、string等

可变参数函数，可以传入任意数量的参数，如果一个slice中已经有多个参数，可以通过
`func(slice...)`的形式来应用于可变参数

闭包，Go支持匿名函数，形成闭包

go支持针对struct定义方法[methof.go](19Methods.go)，go会隐式的转换
指针和值

channel是连接goroutine的管道，可以将值从一个goroutine发送到
channel。通过make(chan val-type)声明一个管道，通过`<-channel`接受消息。
默认管道双方都是阻塞的.
select使您可以等待多个通道操作
           

              
