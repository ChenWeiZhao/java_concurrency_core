/**
 * ***************************************************************************
 * 工程：IntelliJ IDEA v1.0
 * All Rights Reserved.
 * <p>       类
 *
 * @author chenweizhao
 * 创建日期：2019/11/26 22:37
 * 版 本 号： 1.0
 * <p>
 * ****************************************************************************
 */
package synchronize.reentrancy;

/**
 * 证明可重入不要求是同一个类中的
 * 可重入粒度测试，调用父类的方法
 */
public class SyncharonizedSupperClass12 {

    public synchronized void doSomething() {
        System.out.println("我是父类方法");
    }


    public static void main(String[] args) {
        SyncharonizedSupperClass12 syncharonizedRecursion11 = new SyncharonizedSupperClass12();
        syncharonizedRecursion11.doSomething();
    }

}

class TestClass extends SyncharonizedSupperClass12 {
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类的方法");
        super.doSomething();
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.doSomething();
    }
}