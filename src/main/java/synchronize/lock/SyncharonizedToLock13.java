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
package synchronize.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**

 */
public class SyncharonizedToLock13 {
    Lock lock = new ReentrantLock();

    private synchronized void method1() {
        System.out.println("我是synchronized形式的锁");
    }

    private synchronized void method2() {
        //获取锁
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncharonizedToLock13 syncharonizedToLock13 = new SyncharonizedToLock13();
        syncharonizedToLock13.method1();
        syncharonizedToLock13.method2();
        System.out.println("fininshed");
    }
}
