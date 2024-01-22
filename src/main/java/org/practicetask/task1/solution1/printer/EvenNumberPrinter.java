package org.practicetask.task1.solution1.printer;

import org.practicetask.task1.solution1.Main;

public class EvenNumberPrinter implements NumberPrinter {

    private final int maxValue;
    private final SharedResource lock;

    public EvenNumberPrinter(int maxValue, SharedResource lock) {
        this.maxValue = maxValue;
        this.lock = lock;
    }


    @Override
    public void run() {
        print();
    }

    @Override
    public void print() {
        while (lock.getNumber() <= maxValue) {
            synchronized (lock) {
                if (lock.getNumber() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " printed " + lock.getNumber());
                    lock.increment();
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
