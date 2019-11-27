/**
 * ***************************************************************************
 * 工程：IntelliJ IDEA v1.0
 * All Rights Reserved.
 * <p>       类
 *
 * @author chenweizhao
 * 创建日期：2019/11/26 21:06
 * 版 本 号： 1.0
 * <p>
 * ****************************************************************************
 */
package synchronize;

/**
 * 同时访问一个类的不同的普通同步方法
 */
public class SyncharonizedDifferentMethod7 implements Runnable {
    static SyncharonizedDifferentMethod7 instance = new SyncharonizedDifferentMethod7();

    @Override
    public void run() {
        if ("Thread-0".equals(Thread.currentThread().getName())){
            method1();
        }else {
            method2();
        }
    }

    private synchronized void method1() {
        System.out.println("我是加锁的方法1,我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    private synchronized void method2() {
        System.out.println("我是加锁的方法2,我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("finished");
    }
}
