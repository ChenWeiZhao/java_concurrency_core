package jmm.outoforder;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：     演示重排序的现象 “直到达到某个条件才停止”，测试小概率事件
 * 若按代码执行顺序分析，会出现3种结果：(1,0)、(0,1)、(1,1)
 * 若发生重排序：则线程2执行顺序重排：y=a; a=1; x=b; b=1; 则结果为(0,0)
 * 重排序：
 * 线程内部的两行代码执行顺序和代码在java文件中的顺序不一致，代码顺序并不是严格按照代码语句顺序执行
 *
 */
public class OutOfOrderExecution {

    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            // 构造函数：需要几次倒计时，这里设置为3次
            CountDownLatch latch = new CountDownLatch(3);

            Thread one = new Thread(() -> {
                try {
                    latch.countDown();
                    //栅栏：需要等待的地方，若收到信号同时执行下面代码
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.countDown();
                        //栅栏：需要等待的地方，若收到信号同时执行下面代码
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });
            two.start();
            one.start();
            //倒数一下，放开闸门，发射信号
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次（" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }


}
