/**
 * ***************************************************************************
 * 工程：IntelliJ IDEA v1.0
 * All Rights Reserved.
 * <p>       类
 *
 * @author chenweizhao
 * 创建日期：2019/11/26 21:33
 * 版 本 号： 1.0
 * <p>
 * ****************************************************************************
 */
package synchronize;

/**
 * 概念：Java类可以有多多个对象，但只有一个class对象
 * 所谓类硕本质是用class对象作为锁，类锁只能在同一时刻被一个对象拥有
 * 类锁的第一种形式，static方法形式
 * 把synchronized关键字加到静态方法上
 */
public class SyncharonizedClassStatic4 implements Runnable {
    static SyncharonizedClassStatic4 instance1 = new SyncharonizedClassStatic4();
    static SyncharonizedClassStatic4 instance2 = new SyncharonizedClassStatic4();

    @Override
    public void run() {
        method();
    }

    /**
     * 即使不同的实例对象运行，同一时刻只会有一个线程运行方法
     */
    private static synchronized void method() {
        System.out.println("我是类锁的第一种形式,我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance1);
        Thread thread2 = new Thread(instance2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("fininshed");
    }
}
