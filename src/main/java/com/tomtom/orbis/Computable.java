package com.tomtom.orbis;

import java.util.concurrent.atomic.AtomicLong;

public class Computable {

    private static final AtomicLong counter = new AtomicLong(0);

    public static int failing(int input) {
        if (counter.incrementAndGet() % 1_000 == 0) {
            throw new RuntimeException("Calculation failed");
        }
        return input;
    }

    public static int fast(int input) {
        return input;
    }

    public static int computeExtensive(int input) {
        long startTime = System.nanoTime();

        int result = 0;
        while (System.nanoTime() - startTime < 1_000_000_000L) {
            result += (input * 31) ^ (result + 7);
        }

        return result;
    }

    public static int ioExtensive(int input) {
        Utils.sleep(1000);
        return input * 31 ^ 7;
    }

}
