package threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 描述：在join期间，主线程是wait状态
 * 第一种：先join再mainThread.getState()
 * 第二种：通过debugger看线程join前后状态的对比
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("Thread-0运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        //主线程调用thread.join后，主线程是waiting状态，子线程是运行状态
        thread.join();
        System.out.println("子线程运行完毕");

    }
}
