# Akka-demo
Akka的学习
```
Akka提供可扩展的实时事务处理。
Akka是一个运行时与编程模型一致的系统，为以下目标设计：
垂直扩展（并发）
水平扩展（远程调用）
高容错

```
## 特点
```aidl
Akka的核心，Akka-actor是非常小的，可以非常方便地放进你的应用中，
提供你需要的异步无锁并行功能，不会有任何困扰。
```
## 优点
```aidl
AKKA提供一种Actor并发模型，其粒度比线程小很多，这意味着你可以在项目
中使用大量的Actor。
Akka提供了一套容错机制，允许在Actor出错时进行一些恢复或者重置操作
AKKA不仅可以在单机上构建高并发程序，也可以在网络中构建分布式程序，
并提供位置透明的Actor定位服务 
```
# Actor
```aidl
actor是akka执行的基本单元，比线程更轻量级，使用akka可以忘掉线程了。
事实上，线程调度已经被akka封装。
这个akka应用是有消息驱动的，消息是除了actor之外最重要的核心组件。
在actor之前投递消息应该满足不可变性，也就是不便模式
消息投递有3种策略：之多一次投递，至少一次投递，精确的消息投递。
BUT ，没必要在akka层面保证消息的可靠性，一般在业务层在保证
```
## 模块使用
```aidl
!	发送异步消息，没有返回值。
!?	发送同步消息，等待返回值。
!!	发送异步消息，返回值是 Future[Any]。
```
