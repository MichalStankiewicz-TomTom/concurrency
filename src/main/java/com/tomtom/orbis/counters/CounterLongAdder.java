package com.tomtom.orbis.counters;

import com.tomtom.orbis.Counter;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.LongAdder;

@ThreadSafe
public class CounterLongAdder implements Counter {

    private final LongAdder count = new LongAdder();

    @Override
    public void increment() {
        count.increment();
    }

    @Override
    public int getCount() {
        return count.intValue();
    }

    /*
    Atomic uses compare and swap (CAS) operation to increment the value.
     */

}
