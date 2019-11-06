package threadcoreknowledge.createthreads;

/**
 * 描述：     同时使用Runnable和Thread两种实现线程的方式
 */
public class BothRunnableThread {

    /**
     * 因为thread的run方法被重写，所以打印“我来自Thread”
     */
    public static void main(String[] args) {
        //匿名内部类
        new Thread(new Runnable() {
            //传入了runnable对象
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            //重写了thread类的方法
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
