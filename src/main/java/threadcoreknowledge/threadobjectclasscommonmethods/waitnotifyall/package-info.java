/**
 * 一、作用、用法：阻塞阶段、唤醒阶段、遇到中断
 * a.阻塞阶段
 * 控制线程休息和唤醒，进入休息即是进入阻塞阶段
 * 直到4种情况之一，才会被唤醒
 * 1、另一个线程调用这个对象的notify()方法且刚好被唤醒的是本线程
 * 2、另一个线程调用这个对象的notifyAll()方法
 * 3、过了wait(long timeoyt)规定的超时时间，如果传入0就是永久等待
 * 4、线程自身调用了interrupt()
 * <p>
 * b.唤醒阶段
 * notify()会唤醒单个正在等待某对象monitor的线程
 * 唤醒的时候如果有多个线程都在等待，notify()他只会选取其中一个，选择是任意的
 * notify()和wait()都需要在synchronize保护的代码块中去执行
 * notifyAll()会把所有等待的线程都一次性唤醒
 * <p>
 * c.遇到中断
 * 会抛出InterruptExecption，并且会释放掉已经获取到的monitor
 *
 *
 *
 */
package threadcoreknowledge.threadobjectclasscommonmethods.waitnotifyall;