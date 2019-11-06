package threadcoreknowledge.createthreads;

/**
 * 描述：     用Runnable方式创建线程
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    /**
     * 最终调用target.run()；
     */
    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
