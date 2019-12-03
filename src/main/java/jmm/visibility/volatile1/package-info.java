/**
 * 一、volatile是什么？
 * 1、是一种同步机制，提供了可见性，比synchronized或者Lock相关类更轻量，因为使用bolatile是无锁的，且不会发生上下文切换等开销很大的行为
 * 2、如果一个变量被修饰成volatile，那么JVM就知道这个变量可能会被并发修改
 * 3、volatile做不到synchronized的原子性保护。
 * <p>
 * 二、使用场景
 * 某个属性被多个线程共享，其中一个线程修改了此属性，其它线程可以立即得到修改后的值
 * <p>
 * 1、不适用：a++
 * 2、适用场景1：boolean flag，自始至终只有赋值没有其他操作，因为赋值自身是有原子性的
 * 3、作为刷新之前变量的触发器，即满足happen-before原则，触发之前操作的可见性
 * <p>
 * 三、两点作用
 * 1、读一个volatile变量之前，需要先使相应的本地缓存失效，这样就必须读取到主内存读取最新值，写一个volatile属性会立即输入到主内存
 * 2、禁止指令重排序优化：解决单例双重锁乱序问题
 */
package jmm.visibility.volatile1;