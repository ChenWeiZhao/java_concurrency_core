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
 * * 概念：Java类可以有多多个对象，但只有一个class对象
 * 类锁的第二种形式，synchronized(*.class)形式
 * 把synchronized关键字加到静态方法上
 */
public class SyncharonizedClassclass6 implements Runnable {
    static SyncharonizedClassclass6 instance1 = new SyncharonizedClassclass6();
    static SyncharonizedClassclass6 instance2 = new SyncharonizedClassclass6();

    @Override
    public void run() {
        method();
    }

    private static void method() {
        /**
         * 类锁只能在同一时刻被一个对象拥有
         * 若下面用普通的object锁,且不加staic，那么不同的实例对象可同时运行互不影响
         * 加了*.class后，即使是不同的实例对象，也只能被一个对象拥有
         */
        synchronized (SyncharonizedClassclass6.class) {
            System.out.println("我是类锁的第二种形式synchronized(*.class),我叫" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");

        }
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
