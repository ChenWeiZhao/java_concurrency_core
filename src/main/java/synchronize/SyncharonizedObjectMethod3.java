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
 * 对象锁示例
 * 1、方法形式
 */
public class SyncharonizedObjectMethod3 implements Runnable {
    static SyncharonizedObjectMethod3 instance = new SyncharonizedObjectMethod3();

    @Override
    public void run() {
        method();
    }

    private synchronized void method() {
        System.out.println("我是对象锁的方法修饰符形式,我叫" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("finished");
    }
}
