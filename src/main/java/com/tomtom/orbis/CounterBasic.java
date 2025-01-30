package com.tomtom.orbis;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class CounterBasic implements Counter {

    private volatile int count = 0;

    @Override
    public void increment() {
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }

}
