package deadlock;

import java.util.Random;
import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

/**
 * 描述：     演示活锁问题
 * 同时拿起左边的餐叉，等待5分钟又同时放下，再等5分钟又同时拿起
 * 解决办法：等待时间随机
 */
public class LiveLock {

    static class Spoon {

        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use() {
            System.out.printf("%s吃完了!", owner.name);


        }
    }

    static class Diner {

        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    spoon.setOwner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.println(name + ": 我吃完了");
                spoon.setOwner(spouse);

            }
        }
    }


    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);

        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon, wife);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon, husband);
            }
        }).start();
    }
}
