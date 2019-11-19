package threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 描述：     通过讲解join原理，分析出join的代替写法
 */
public class JoinPrinciple {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //run方法全部执行完毕之后，会自动执行notify()，唤醒下面的wait
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            }
        });
        //启动线程
        thread.start();
        System.out.println("开始等待子线程运行完毕");
        //thread.join();
        //等价代码
        synchronized (thread) {
            //执行thread.wait()后，主线程会陷入阻塞wait状态，去执行thread类的run方法
            thread.wait();
        }
        System.out.println("所有子线程执行完毕");
    }
}
