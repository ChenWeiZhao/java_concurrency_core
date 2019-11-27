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
 * 证明同一个方法是可重入的
 * 可重入粒度测试，递归调用本方法
 */
public class SyncharonizedRecursion10 {
    int a = 0;

    public static void main(String[] args) {
        SyncharonizedRecursion10 syncharonizedRecursion10 = new SyncharonizedRecursion10();
        syncharonizedRecursion10.method();
    }

    private synchronized void method() {
        System.out.println("这是method方法, a=" + a);
        if (a == 0) {
            a++;
            method();
        }
    }
}
