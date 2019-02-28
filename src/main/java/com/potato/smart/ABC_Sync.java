package com.potato.smart;

/**
 * 多线程之三个线程实现循环打印10次ABC
 */
public class ABC_Sync {

    public static void main(String[] args) {
        MajorClass maj = new MajorClass("ABC_Sync");
        Thread t1 = new Thread(maj, new Thread_ABC('A'));
        Thread t2 = new Thread(maj, new Thread_ABC('B'));
        Thread t3 = new Thread(maj, new Thread_ABC('C'));
        t1.start();
        t2.start();
        t3.start();
    }

    public static class Thread_ABC implements Runnable {
        private char name;
        static int n = 1;

        Thread_ABC(char name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    if (n > 30) {
                        break;
                    }

                    if ((name == 'A' && n % 3 == 1)
                            || (name == 'B' && n % 3 == 2)
                            || (name == 'C' && n % 3 == 0)) {
                        System.out.print(name);
                        n++;
                    }
                }
            }
        }
    }

    static class MajorClass extends ThreadGroup {
        MajorClass(String name) {
            super(name);
        }
    }
}
