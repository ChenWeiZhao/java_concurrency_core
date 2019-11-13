package threadcoreknowledge.stopthreads.wrongways.volatiledemo;

/**
 * 描述：     演示用volatile的局限
 * part1 看似可行，但有局限性
 */
public class WrongVolatile implements Runnable {

    /**
     * volatile让变量具有可见性，让多个线程都可以看到他真实的值
     */
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongVolatile r = new WrongVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        r.canceled = true;
    }
}

