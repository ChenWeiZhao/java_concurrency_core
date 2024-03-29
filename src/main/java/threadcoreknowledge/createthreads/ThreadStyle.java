package threadcoreknowledge.createthreads;

/**
 * 描述：     用Thread方式实现线程
 * <p>
 * 继承Thread类是不推荐的，因为它有以下的一些缺点：
 * 1. 从代码架构角度：具体的任务（run方法）应该和“创建和运行线程的机制（Thread类）”解耦，用runnable对象可以实现解耦。
 * 2. 使用继承Thread的方式的话，那么每次想新建一个任务，只能新建一个独立的线程，而这样做的损耗会比
 * 较大（比如重头开始创建一个线程、执行完毕以后再销毁等。如果线程的实际工作内容，也就是run()函数
 * 里只是简单的打印一行文字的话，那么可能线程的实际工作内容还不如损耗来的大）。如果使用Runnable
 * 和线程池，就可以大大减小这样的损耗。
 * 3. 继承Thread类以后，由于Java语言不支持双继承，这样就无法再继承其他的类，限制了可扩展性。
 */
public class ThreadStyle extends Thread {

    /**
     * 整个run方法被重写
     */
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}





