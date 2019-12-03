/**
 * 一、为什么会有可见性问题？
 * 1、CPU有多层缓存，导致读的数据可能会过期
 * * 高速缓存的容量比主存小，但速度仅次于寄存器，所以CPU和主存间多了Cache层
 * * 线程间的对于共享变量的可见性问题不是直接由多和引起的，而是有多缓存引起的
 * 2、CPU有多级缓存，导致读的数据过期
 * * 如果所有个核心都只用一个缓存，那么也就不存在内存可见性问题了
 * * 但是通常情况，每个核心都会将自己需要的数据读到独占缓存中，
 * * 数据修改后也是写入到缓存中，然后等待刷入到主存中。所以会导致有些核心读取的值是一个过期的值。
 *
 * 二、什么是JMM里面的主内存和本地内存？
 * 1、Java 作为高级语言，屏蔽了CPU多层缓存这些底层细节，用 JMM 定义了一套读写内存数据的规范，
 * 虽然不再需要关心一级缓存和二级缓存的问题，但是，JMM 抽象了主内存和本地内存的概念。
 * 2、这里说的本地内存并不是真的是一块给每个线程分配的内存，而是 JMM 的一个抽象，是对于寄存器、一级缓存、二级缓存等的抽象。
 *
 * 三、主内存和本地内存的关系是什么？
 * JMM有以下规定:
 * 1. 所有的变量都存储在主内存中，同时每个线程也有自己独立的工作内存，工作内存中的变量内容是主内存中的拷贝
 * 2. 线程不能直接读写主内存中的变量,而是只能操作自己工作内存中的变量，然后再同步到主内存中
 * 3. 主内存是多个线程共享的，但线程间不共享工作内存,如果线程间需要通信，必须借助主内存中转来完成
 * 所有的共享变量存在于主内存中，每个线程有自己的本地内存，而且线程读写共享数据也是通过本地内存交换的，所以才导致了可见性问题。
 */
package jmm.visibility;