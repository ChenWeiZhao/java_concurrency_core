package threadcoreknowledge.sixstates;

/**
 * 描述：     阻塞状态
 * 展示Blocked（被阻塞）, Waiting（等待）, TimedWaiting（计时等待）
 */
public class BlockedWaitingTimedWaiting implements Runnable {

    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //打印出Timed_Waiting状态，因为线程1正在执行Thread.sleep(1000);
        System.out.println(thread1.getState());

        //打印出BLOCKED状态，因为thread2想拿得到sync()的锁却拿不到
        System.out.println(thread2.getState());

        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程1，打印出WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            //睡眠1秒钟，不要尽快释放锁，TimedWaiting
            Thread.sleep(1000);
            //Waiting
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
