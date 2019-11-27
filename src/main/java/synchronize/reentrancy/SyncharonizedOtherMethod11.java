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
 * 证明可重入不要求是同一个方法
 * 可重入粒度测试，调用类内部另外的方法
 */
public class SyncharonizedOtherMethod11 {

    private synchronized void method1() {
        System.out.println("这是method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("这是method2");
    }

    public static void main(String[] args) {
        SyncharonizedOtherMethod11 syncharonizedRecursion11 = new SyncharonizedOtherMethod11();
        syncharonizedRecursion11.method1();
    }

}
