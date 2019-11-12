package threadcoreknowledge.startthread;

import threadcoreknowledge.createthreads.ThreadStyle;

/**
 * 描述：     对比start和run两种启动线程的方式
 */
public class StartAndRunMethod {

    public static void main(String[] args) {
        //若直接调用run方法，就是一个普通的方法，不会被子线程调用
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());

        };
        runnable.run();
        //该语句被主线程或副线程执行
        new Thread(runnable).start();
    }
}
