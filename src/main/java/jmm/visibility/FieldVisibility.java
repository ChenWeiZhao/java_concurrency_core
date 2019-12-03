package jmm.visibility;

/**
 * 描述：     1、演示可见性带来的问题
 * 正常情况下分析出现的结果：(a=3,b=2)(a=3,b=3)(a=1,b=2)
 * 由于可见性问题会出现 (b=3,a=1)
 * 即线程2在执行print打印的时候，看见了线程1chang对b=3的修改，却没看见a=3的修改，因为a=3还没同步过来，还是看到了a=1；
 * volatile强制线程去读取最新的值，写数据时去刷新到主内存中
 */
public class FieldVisibility {

    private int a = 1;
    /**
     * 不是每个变量都要加volatile，只要修改了b，那么之前的代码（如对a的修改）就课件
     */
    private volatile int b = 2;

    /**
     * 只要b读到是3，就可有由happens-before原则，保证了其它线程读取到的都是3而不可能读取到1
     */
    private void change() {
        a = 3;
        b = a;
    }

    /**
     * 因为System.out.println(“a=” + a + " b=" + b)这一句不是原子的，所以执行期间是可以被切换的：先读取
     * a的值为1并打印后，线程被切换走执行change()，此时b的值刚刚发生了变化（打印a的时候，b的值其实是
     * 2，但是现在变成了3），于是就会出现a=1,b=3。
     * 但是如果先打印b再打印a，那么打印了b之后，由于volatile的保证，
     * 可以确定a=3已经执行了并且最新值可以被所有线程看到，所以就不会出现b=3;a=1的情况，
     */
    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            //线程1
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();
            //线程2
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }

    }


}
