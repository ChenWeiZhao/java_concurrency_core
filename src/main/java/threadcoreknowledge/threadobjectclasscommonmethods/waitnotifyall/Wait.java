package threadcoreknowledge.threadobjectclasscommonmethods.waitnotifyall;

/**
 * 描述：     展示wait和notify的基本用法 1. 研究代码执行顺序 2. 证明wait释放锁
 */
public class Wait {

    public static Object object = new Object();

    static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                //进入synchronized代码块中说明已经得到锁
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    //调用wait()的线程1会释放锁object，线程1会陷入阻塞，即wait状态
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //又重新拿到了锁
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁。");
            }
        }
    }

    /**
     * 线程2调用notify()唤醒线程1
     */
    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                //线程2拿到锁，并去唤醒线程1
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        //保证thread的wait先执行
        Thread.sleep(200);
        thread2.start();
    }
}
