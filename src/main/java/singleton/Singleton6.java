package singleton;

/**
 * 描述：     双重检查（推荐面试使用）
 * 优点：线程安全，延迟加载，效率较高
 */
public class Singleton6 {

    /**
     * 为什么加入volatile？ 1、防止重排序；2、重排序会带来NPE
     * 新建对象不是原子的，新建对象实际有3个步骤
     * 1、创建一个空的对象，分配内存
     * 2、调用构造方法
     * 3、将引用对象指向分配的内存空间
     * 若发生了重排序：
     * 1、创建一个空的对象，分配内存
     * 2、将引用对象指向分配的内存空间（此时对象已经不为空了，但里面的属性已经不为空）
     * 3、调用构造函数
     * 此时会得到一个未经过构造函数初始化完毕的的对象
     */
    private volatile static Singleton6 instance;

    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                //进来以后，再做一次检查
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
