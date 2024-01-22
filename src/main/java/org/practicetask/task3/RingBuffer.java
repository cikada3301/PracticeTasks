package org.practicetask.task3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RingBuffer<T> {
    private final T[] buffer;
    private final int capacity;
    private int size;
    private int readIndex;
    private int writeIndex;
    private final Lock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        buffer = (T[]) new Object[capacity];
        size = 0;
        readIndex = 0;
        writeIndex = 0;
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (size == capacity) {
                notFull.await();
            }
            buffer[writeIndex] = item;
            writeIndex = (writeIndex + 1) % capacity;
            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            T item = buffer[readIndex];
            readIndex = (readIndex + 1) % capacity;
            size--;
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
