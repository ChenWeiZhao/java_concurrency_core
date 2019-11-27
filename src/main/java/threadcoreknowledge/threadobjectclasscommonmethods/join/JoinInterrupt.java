package threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 描述：     演示join期间被中断的效果
 * 1、子线程加入主线程
 * 2、主线程的引用去中断主线程
 * 3、而主线程开始等待子线程了，遇到中断就会抛出异常，并把中断传递给子线程
 * 4、子线程中断
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //主线程的引用去中断主线程
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread finished.");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "子线程中断");
                }
            }
        });

        thread1.start();

        System.out.println("主线程等待子线程运行完毕");
        try {
            //子线程加入了主线程，即主线程在等待子线程执行
            thread1.join();
        } catch (InterruptedException e) {
            //因此是主线程抛出的异常
            System.out.println(Thread.currentThread().getName() + "主线程中断了");
            //再把中断传递给子线程
            thread1.interrupt();
        }
        System.out.println("子线程已运行完毕");
    }

}
