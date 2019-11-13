package threadcoreknowledge.stopthreads.wrongways.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 描述：
 * 演示用volatile的局限part2 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢，
 * 所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongVolatileCanNotStop {

    public static void main(String[] args) throws InterruptedException {
        /*
         * 阻塞队列，满的时候放不进去，空的时候也会阻塞
         */
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        //一旦消费不需要更多数据了，我们应该让生产者也停下来，但是实际情况并没有停下来
        producer.canceled = true;
        //打印canceled依然为true
        System.out.println(producer.canceled);
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {

    public volatile boolean canceled = false;

    /**
     * 阻塞队列（挂起线程）
     */
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    //当阻塞队列满了以后，阻塞在这里，无法跳到上面的canceled判断逻辑
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //并没有进入finally
            System.out.println("生产者结束运行");
        }
    }
}

/**
 * 消费者
 */
class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}