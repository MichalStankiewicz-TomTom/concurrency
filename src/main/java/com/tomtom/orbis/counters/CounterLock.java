package com.tomtom.orbis.counters;

import com.tomtom.orbis.Counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterLock implements Counter {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();
    private int count = 0;

    @Override
    public void increment() {
        try {
            writeLock.lock();
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public int getCount() {
        try {
            readLock.lock();
            return count;
        } finally {
            readLock.unlock();
        }
    }

    /*
    Lock Types:
    1. ReentrantLock
    2. ReentrantReadWriteLock
    3. StampedLock - Optimistic Locking
     */

    /*
    Lock Methods:
    1. lock()
    2. tryLock()
    3. tryLock(long time, TimeUnit unit)
    4. lockInterruptibly()
     */
}
