package singleton;

/**
 * 描述：     饿汉式（静态常量）（可用）
 * 类的加载由JVM自身保证线程安全
 */
public class Singleton1 {

    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

}
