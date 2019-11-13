package threadcoreknowledge.stopthreads;

/**
 * 描述：     注意Thread.interrupted()方法的目标对象是“当前线程”，而不管本方法来自于哪个对象
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                }
            }
        });

        // 启动线程
        threadOne.start();
        //设置中断标志
        threadOne.interrupt();
        //获取中断标志，true
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        //获取中断标志并重置，false
        // 不管哪个实例调用，interrupted是执行该方法的线程，即main函数，而不是实例
        System.out.println("isInterrupted: " + threadOne.interrupted());
        //获取中断标志并重直，false
        System.out.println("isInterrupted: " + Thread.interrupted());
        //获取中断标志，true，即依然是中断状态
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("Main thread is over.");
    }
}
