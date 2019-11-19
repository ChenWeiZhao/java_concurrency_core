package threadcoreknowledge.uncaughtexception.reason;

/**
 * 描述： 原因二
 * 1. 不加try catch抛出4个异常，都带线程名字
 * 2. 加了try catch,期望捕获到第一个线程的异常，线程234不应该运行，希望看到打印出Caught Exception
 * 3. 执行时发现，根本没有Caught Exception，线程234依然运行并且抛出异常
 *
 * 说明线程的异常不能用传统方法捕获
 */
public class CanNotCatchDirectly implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        try {
            new Thread(new CanNotCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CanNotCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CanNotCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CanNotCatchDirectly(), "MyThread-4").start();
        } catch (RuntimeException e) {
            //这里是针对主线程的
            System.out.println("Caught Exception.");
        }

    }

    @Override
    public void run() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            //这里是针对子线程捕获
            System.out.println("Caught Exception.");
        }
    }
}
