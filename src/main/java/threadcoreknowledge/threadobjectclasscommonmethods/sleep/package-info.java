/**
 * 一、作用：
 * sleep方法可以让线程进入Waiting状态，并且不占用CPU资源，但是不释放锁，
 * 直到规定时间后再执行，休眠期间如果被中断，会抛出异常并清除中断状态。
 *
 * 二、wait和sleep方法的异同？
 * 解答：
 * 相同
 * 1. Wait和sleep方法都可以使线程阻塞，对应线程状态是Waiting或Time_Waiting。
 * 2. wait和sleep方法都可以响应中断Thread.interrupt()。
 * 不同
 * 1. wait方法的执行必须在同步方法中进行，而sleep则不需要。放在同步方法中是为了保证线程安全。
 * 2. 在同步方法里执行sleep方法时，不会释放monitor锁，但是wait方法会释放monitor锁。
 * 3. sleep方法短暂休眠之后会主动退出阻塞，而没有指定时间的 wait方法则需要其他线程中断后才能退出阻塞。
 * 4. wait()和notify(),notifyAll()是Object类的方法，sleep()和yield()是Thread类的法
 */
package threadcoreknowledge.threadobjectclasscommonmethods.sleep;