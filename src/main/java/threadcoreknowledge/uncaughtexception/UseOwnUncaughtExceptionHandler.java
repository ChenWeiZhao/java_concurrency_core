package threadcoreknowledge.uncaughtexception;

/**
 * 描述：     使用刚才自己写的UncaughtExceptionHandler
 * 对全局异常进行处理
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        //设置自己实现的异常捕获器
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));

        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
    }


    @Override
    public void run() {
        throw new RuntimeException();
    }
}
