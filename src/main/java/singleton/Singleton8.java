package singleton;

/**
 * 描述：     枚举单例，生产实践中最佳写法
 * 优点
 * 1、写法简单
 * 2、线程安全有保障，枚举类经过反编译后是一个静态的对象，继承了枚举父类
 * 3、避免反序列、反射重新创建对象，破坏单例
 *
 */
public enum Singleton8 {
    INSTANCE;

    public void whatever() {

    }

}
