/**
 * ***************************************************************************
 * 工程：IntelliJ IDEA v1.0
 * All Rights Reserved.
 * <p>       类
 *
 * @author chenweizhao
 * 创建日期：2019/11/26 20:57
 * 版 本 号： 1.0
 * <p>
 * ****************************************************************************
 */
package synchronize;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消失的请求
 * 原因：count++看似一个操作，实际包含三个动作：
 * 1、读取count；2、将count加一；3、将count值写入到内存中
 */
public class DisappearRequest1 implements Runnable {
    static DisappearRequest1 instance = new DisappearRequest1();

    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            i++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //预期结果：不满20万
        System.out.println(i);
    }
}
