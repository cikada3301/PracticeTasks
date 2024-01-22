package org.practicetask.task1.solution1.printer;

public class SharedResource {
    private volatile int number;

    public SharedResource (int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void increment() {
        number++;
    }
}
