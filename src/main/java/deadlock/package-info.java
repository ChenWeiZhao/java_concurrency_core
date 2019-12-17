/**
 * 一、死锁的4个必要条件
 * 1、互斥条件；2、请求与保持条件；3、不剥夺条件；4、循环等待条件
 * <p>
 * 二、线上发生死锁怎么办？
 * 用命令保存堆栈信息，重启服务器。  常见的修复策略：避免策略、检测与恢复策略、鸵鸟策略。
 * 1、避免策略：哲学家就餐还手方案、转账换序方案。
 * 2、检测与恢复策略：一段时间检测是否有死锁，如果有就剥夺某一个资源，打开死锁。
 * *  锁的调用链路图，定期检查是否存在环路，一旦发生死锁就用死锁恢复机制
 * *  恢复方法1：进程终止，逐个终止线程，直到死锁消除，终止顺序：优先级、已占用资源，已经运行的时间
 * *  恢复方法2：资源抢占，把已经发出去的锁给收回来，让线程回退几部，这样不用结束整个线程，成本较低
 * 3、鸵鸟策略：如果发生死锁概率极低，那么我们直接忽略它，直到死锁发生的时候，再人工修复。
 *
 * 三、实际工程中如何避免死锁
 * 1、设置超时时间
 * 2、多使用并发类而不是自己设计锁
 * 3、尽量降低锁的使用粒度：用不同的锁而不是一个锁
 * 4、如果能使用同步代码块，就不使用同步方法：自己指定锁的对象
 * 5、给线程都起一个有意义的名字：debug排查时事半功倍，框架和jdk都遵循这个最佳实践
 * 6、避免锁的嵌套:MustDeadLock类
 * 7、分配资源前先看能不能收回来：银行家算法
 * 8、尽量不要几个功能使用同一把锁：即专锁专用
 */
package deadlock;