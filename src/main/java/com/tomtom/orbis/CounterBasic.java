package com.tomtom.orbis;

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
