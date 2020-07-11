package com.zjc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintOddAndEven2 {
    private int value = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();


    private void printOdd() {
        lock.lock();
        while (value <= 100) {
            if (value % 2 == 1) {
                System.out.println(Thread.currentThread() + ": - " + value++);
                condition.signal();
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }

    // 打印偶数
    private void printEven() {
        lock.lock();
        while (value <= 100) {
            if (value % 2 == 0) {
                System.out.println(Thread.currentThread() + ": - " + value++);
                condition.signal();
            } else {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOddAndEven2 even2 = new PrintOddAndEven2();
        Thread t1 = new Thread(() -> even2.printOdd());
        Thread t2 = new Thread(() -> even2.printEven());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
