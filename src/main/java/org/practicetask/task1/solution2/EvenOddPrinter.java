package org.practicetask.task1.solution2;

public class EvenOddPrinter {
    private static final int MAX_NUMBER = 10;
    private static int number = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread evenThread = new Thread(EvenOddPrinter::printEvenNumbers);
        Thread oddThread = new Thread(EvenOddPrinter::printOddNumbers);

        evenThread.start();
        oddThread.start();
    }

    private static void printEvenNumbers() {
        while (number <= MAX_NUMBER) {
            synchronized (lock) {
                if (number % 2 == 0) {
                    System.out.println("Even Thread: " + number);
                    number++;
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

    private static void printOddNumbers() {
        while (number <= MAX_NUMBER) {
            synchronized (lock) {
                if (number % 2 != 0) {
                    System.out.println("Odd Thread: " + number);
                    number++;
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