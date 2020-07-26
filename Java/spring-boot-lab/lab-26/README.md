解决Session一致性问题的三种方案
1. Session黏连
    使用 Nginx 实现会话黏连，将相同 sessionid 的浏览器所发起的请求，转发到同一台服务器。这样，就不会存在多个 Web 服务器创建多个 Session 的情况，也就不会发生 Session 不一致的问题。
    不过，这种方式目前基本不被采用。因为，如果一台服务器重启，那么会导致转发到这个服务器上的 Session 全部丢失。
2. Session复制
    Web 服务器之间，进行 Session 复制同步。仅仅适用于实现 Session 复制的 Web 容器，例如说 Tomcat 、Weblogic 等等。
    不过，这种方式目前基本也不被采用。试想一下，如果我们有 5 台 Web 服务器，所有的 Session 都要同步到每一个节点上，一个是效率低，一个是浪费内存。
3. Session外部化存储
    Session 外部化存储，考虑不再采用 Web 容器的内存中存储 Session ，而是将 Session 存储外部化，持久化到 MySQL、Redis、MongoDB 等等数据库中。这样，Tomcat 就可以无状态化，专注提供 Web 服务或者 API 接口，未来拓展扩容也变得更加容易。
    
入门Spring Session + Redis: lab-26-distributed-session-01
 