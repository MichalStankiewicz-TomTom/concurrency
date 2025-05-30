package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import com.tomtom.orbis.counters.CounterAtomic;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Slf4j
public class Task5 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(10_000)
                .toList();

        log.info("Integers: {}", integers);

        Counter counter = new CounterAtomic();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        List<Integer> results = new ArrayList<>();
        for (int i : integers) {
            executor.submit(() -> {
                results.add(Computable.fast(integers.get(i)));
                counter.increment();
            });
        }

        while (counter.getCount() < integers.size()) {
            log.info("Counter: {}", counter.getCount());
            Utils.sleep(500);
        }
        executor.shutdown();
        log.info("Final counter: {}", counter.getCount());
        if (results.size() != counter.getCount()) {
            log.error("counter != results.size()");
        }
        log.info("Results: {}", results);
        log.info("Results size: {}", results.size());
        log.info("Elapsed time: {}", stopwatch.stop());
    }

}