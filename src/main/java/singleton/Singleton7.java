package singleton;

/**
 * 描述：     静态内部类方式，属于懒汉（可用）
 * 类的加载JVM保证线程安全
 */
public class Singleton7 {

    private Singleton7() {
    }

    private static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
