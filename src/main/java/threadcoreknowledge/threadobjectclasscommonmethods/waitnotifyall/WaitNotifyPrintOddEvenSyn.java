package threadcoreknowledge.threadobjectclasscommonmethods.waitnotifyall;

/**
 * 描述：     两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 */
public class WaitNotifyPrintOddEvenSyn {

    private static int count;

    /**
     * 锁，2个线程内部会竞争
     */
    private static final Object lock = new Object();

    /**
     * 新建2个线程
     * 1个只处理偶数，第二个只处理奇数（用位运算）
     * 用synchronized来通信
     */
    public static void main(String[] args) {
        /**
         * 偶数线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    //两个线程，内部竞争lock锁
                    synchronized (lock) {
                        //'&'位与操作，把count最后一位取出来，与1位与运算
                        //如果是1代表是奇数，是0代表是偶数
                        if ((count & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "偶数").start();

        /**
         * 奇数线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (lock) {
                        if ((count & 1) == 1) {
                            System.out.println(Thread.currentThread().getName() + ":" + count++);
                        }
                    }
                }
            }
        }, "奇数").start();
    }
}
