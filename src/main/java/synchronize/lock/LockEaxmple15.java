/**
 * ***************************************************************************
 * 工程：IntelliJ IDEA v1.0
 * All Rights Reserved.
 * <p>       类
 *
 * @author chenweizhao
 * 创建日期：2019/11/27 19:38
 * 版 本 号： 1.0
 * <p>
 * ****************************************************************************
 */
package synchronize.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 展示lock的方法
 */
public class LockEaxmple15 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
        //尝试获得锁
        lock.tryLock();

        //lock.tryLock(1000, TimeUnit.SECONDS)
    }
}
