package threadcoreknowledge.stopthreads;

/**
 * 描述：     如果while里面放try/catch，会导致中断失效
 * 情况：while内try/catch住Thread.sleep()，即使循环条件有Thread.currentTread().isInterrupted()条件也无法停止线程。
 *
 * 原因：设计sleep函数时候，一但响应中断，就会把线程的interrupt标记位清除。
 * 所以Thread.currentThread().isInterrupted()检查不到被中断
 *
 */
public class CanNotInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    //异常抛出被catch住
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
