package threadcoreknowledge.safeproblem;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：     第一种：运行结果出错。 演示计数不准确（减少），找出具体出错的位置。
 * {@link CyclicBarrier}
 * 可以让我们的线程，可以根据我们的需要，在某一个地方等待,知道等待的人员都就绪了，再一起出发
 */
public class MultiThreadsError implements Runnable {

    private static MultiThreadsError instance = new MultiThreadsError();
    private int index = 0;
    private static AtomicInteger realIndex = new AtomicInteger();
    private static AtomicInteger wrongCount = new AtomicInteger();
    //构造参数：等待2个线程
    private static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    private static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    final boolean[] marked = new boolean[10000000];

    @Override
    public void run() {
        //提前标记0位ture，以便检查出第一次就发生碰撞
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                //重置栅栏
                cyclicBarrier2.reset();
                //栅栏：若2个线程都执行了await()就会放行，统一的去下方执行index++
                cyclicBarrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                //等待2个线程都执行完index++出错
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();

            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是" + instance.index);
        System.out.println("真正运行的次数" + realIndex.get());
        System.out.println("错误次数" + wrongCount.get());

    }
}
