package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 描述：     自己的MyUncaughtExceptionHanlder
 * 为什么需要该类：
 * 1、主线程可以轻松发现异常，子线程却不行；
 * 2、子线程异常无法用传统方式捕获
 * 3、不能直接捕获，提高健壮性
 * <p>
 * Thread.UncaughtExceptionHandler
 * 能够检测出线程由于未捕获异常而终止的情况，并且对此进行处理
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //日志处理
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止啦" + t.getName(), e);
        System.out.println(name + "捕获了异常" + t.getName() + "异常");
    }
}
