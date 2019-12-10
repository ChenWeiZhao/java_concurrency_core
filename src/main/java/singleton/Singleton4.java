package singleton;

/**
 * 描述：     懒汉式（线程安全）（不推荐）
 * 效率太低，拿实例的方法无法并行
 *
 */
public class Singleton4 {

    private static Singleton4 instance;

    private Singleton4() {

    }

    public synchronized static Singleton4 getInstance() {
        if (instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
