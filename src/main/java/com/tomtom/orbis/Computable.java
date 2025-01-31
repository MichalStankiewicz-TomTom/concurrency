package com.tomtom.orbis;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
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
        while (System.nanoTime() - startTime < 1_000_000_000L && !Thread.currentThread().isInterrupted()) {
            result += (input * 31) ^ (result + 7);
        }

        return result;
    }

    public static int ioExtensive(int input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Thread interrupted");
        }
        return input * 31 ^ 7;
    }

}
