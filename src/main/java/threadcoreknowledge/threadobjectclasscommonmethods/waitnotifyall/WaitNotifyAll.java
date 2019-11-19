package threadcoreknowledge.threadobjectclasscommonmethods.waitnotifyall;

/**
 * 描述：
 * 3个线程，线程A和线程B首先被阻塞，线程C唤醒它们。notify, notifyAll。
 * start先执行不代表线程先启动。
 */
public class WaitNotifyAll implements Runnable {
    /**
     * 锁
     */
    private static final Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    //线程C去唤醒线程A和B
                    resourceA.notifyAll();
//                    resourceA.notify();
                    System.out.println("ThreadC notified.");
                }
            }
        });
        threadA.start();
        threadB.start();
//        Thread.sleep(200);
        threadC.start();
    }
    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName()+" got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName()+" waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+"'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
