package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import com.tomtom.orbis.counters.CounterAtomic;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@Slf4j
public class Task8 {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(10_000)
                .toList();

        log.info("Integers: {}", integers);

        Counter counter = new CounterAtomic();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        List<Integer> results = Collections.synchronizedList(new ArrayList<>());
        List<Future<?>> futures = new ArrayList<>();
        for (int i : integers) {
            futures.add(executor.submit(() -> {
                results.add(Computable.failing(integers.get(i)));
                counter.increment();
            }));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                log.error("Exception: {}", e.getMessage());
                executor.shutdownNow();
                executor.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS);
            }
        }

        while (counter.getCount() < integers.size()) {
            log.info("Counter: {}", counter.getCount());
            Utils.sleep(500);
        }
        log.info("Counter: {}", counter.getCount());
        if (results.size() != counter.getCount()) {
            log.error("Results: {}", results.size());
        } else {
            log.info("Results: {}", results);
        }
        log.info("Elapsed time: {}", stopwatch.stop());
    }

}