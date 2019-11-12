/**
 * 一、start方法的执行流程是什么？
 * 1. 检查线程状态，只有NEW状态下的线程才能继续，否则会抛出IllegalThreadStateException
 * （在运行中或者已结束的线程，都不能再次启动详见{@link threadcoreknowledge.startthread.CanNotStartTwice}）
 * 2. 被加入线程组
 * 3. 调用start0()方法启动线程
 * 注意点：
 * start方法是被synchronized修饰的方法，可以保证线程安全；
 * 由JVM创建的main方法线程和system组线程，并不会通过start来启动。
 * <p>
 * 二、run方法源码描述（经典三行强行装一波）；
 * 执行的两种结果是什么；
 * 结论是：和一般定义方法没啥不同，并不会真正的启动一个新的线程；
 * 但是调用start 则会启动一个新的线程，会执行线程的各个生命周期
 */
package threadcoreknowledge.startthread;