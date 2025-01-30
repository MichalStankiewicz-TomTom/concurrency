package com.tomtom.orbis.counters;

import com.tomtom.orbis.Counter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CounterAtomic implements Counter {

    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void increment() {
        count.incrementAndGet();
    }

    @Override
    public int getCount() {
        return count.get();
    }

    /*
    Atomic uses compare and swap (CAS) operation to increment the value.
     */

}
