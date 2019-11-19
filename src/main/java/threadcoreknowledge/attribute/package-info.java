/**
 * 一、线程名字：
 * 默认：Thread-x
 * 修改：线程一旦启动就会在native层生成一个name，没有办法改变。但
 * 是线程内部的name，线程启动后可以通过setName修改
 * <p>
 * 二、守护线程：
 * 作用：守护自己编写的线程
 * 特性：
 * 线程的默认类型继承自父线程
 * 被谁启动
 * 不影响jvm退出
 * 守护线程和普通线程的区别：
 * 整体并无不同，他们关心jvm什么时候退出，作用不同普通线程是逻辑处
 * 理，守护线程是服务我们的线程的
 * 我们时候需要给线程设置为守护线程？
 * 不应该，比如我们将一个访问文件的线程设置为守护线程，当只有这个
 * 线程运行的时候，jvm发现只有守护线程了。就会关闭jvm那么就会导致
 * 数据不一致等问题。
 * <p>
 * 三、线程优先级：
 * 10个优先级，默认5.
 * 程序的设计不应该优先级
 * 1、不同的操作系统不一样
 * 2、优先级会被操作系统改变
 */
package threadcoreknowledge.attribute;