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
 * 同时访问静态synchronized方法和非静态synchronized方法
 * 2个方法依然可以同时运行
 * 因为2个指定的锁对象不是同一个锁
 */
public class SyncharonizedStaticAndNormal8 implements Runnable {
    static SyncharonizedStaticAndNormal8 instance = new SyncharonizedStaticAndNormal8();

    @Override
    public void run() {
        if ("Thread-0".equals(Thread.currentThread().getName())){
            method1();
        }else {
            method2();
        }
    }

    private static synchronized void method1() {
        System.out.println("我是静态加锁的方法1,我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    private synchronized void method2() {
        System.out.println("我是非静态加锁的方法2,我叫" + Thread.currentThread().getName());
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
