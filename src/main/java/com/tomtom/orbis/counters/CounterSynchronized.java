package com.tomtom.orbis.counters;

import com.tomtom.orbis.Counter;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CounterSynchronized implements Counter {

    private int count = 0;

    @Override
    public synchronized void increment() {
        count++;
    }

    @Override
    public synchronized int getCount() {
        return count;
    }

    /*
    On what object does the synchronized keyword lock?
    Is it fair or unfair lock? What is faster?
     */
}
