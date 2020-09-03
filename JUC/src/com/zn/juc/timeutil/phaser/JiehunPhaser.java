package com.zn.juc.timeutil.phaser;

import java.util.concurrent.Phaser;

/**
 * @author zhangning
 * @date 2020/8/11
 */
public class JiehunPhaser {
    static MarriagePhaser marriagePhaser = new MarriagePhaser();

    public static void main(String[] args) {
        int total = 7;
        marriagePhaser.bulkRegister(total);
        for (int i = 1; i <= total - 2; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("第一个阶段完事儿。所有人到齐了，共几个人：" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("第二个阶段完事儿。所有人吃完了了，共几个人：" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("第三个阶段完事儿。所有人走了了，共几个人：" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("第四个阶段完事儿。新郎新娘入洞房了，共几个人：" + registeredParties);
                    System.out.println();
                    return false;
                case 4:
                    System.out.println("最后一个阶段完事儿。新郎新娘OOXX造完孩子，共几个人：" + registeredParties);
                    System.out.println();
                    return true;//返回true释放锁
                default:
                    return true;

            }
        }
    }

    static class Person implements Runnable {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        //到达
        void arrive() {
            System.out.printf("%s 到达现场\n", name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进入下一阶段
            marriagePhaser.arriveAndAwaitAdvance();
        }

        //        吃饭
        void eat() {
            System.out.printf("%s 吃饭完毕\n", name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进入下一阶段
            marriagePhaser.arriveAndAwaitAdvance();
        }

        //        离场
        void leavf() {
            System.out.printf("%s 离场完毕\n", name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //进入下一阶段
            marriagePhaser.arriveAndAwaitAdvance();
        }

        //入洞房
        void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {

                //进入下一阶段
                System.out.println(name + "入洞房了,需要5秒");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                marriagePhaser.arriveAndAwaitAdvance();
            } else {
                System.out.printf("%s 没你事儿了", name);
                System.out.println();
//                marriagePhaser.arriveAndDeregister();
                marriagePhaser.arriveAndAwaitAdvance();
            }
        }

        // 造孩子
        void xxoo() {
            if (name.equals("新郎") || name.equals("新娘")) {
                System.out.printf("%s 造孩子中", name);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                marriagePhaser.arriveAndAwaitAdvance();
            } else {
                marriagePhaser.arriveAndDeregister();
            }
        }



        @Override
        public void run() {
            arrive();
            eat();
            leavf();
            hug();
            xxoo();
        }
    }
}
