package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Slf4j
public class Task4 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(1000)
                .toList();

        log.info("Integers: {}", integers);

        Counter counter = new CounterBasic();
        ExecutorService executor = Executors.newFixedThreadPool(30);
        for (int i : integers) {
            executor.submit(() -> {
//                log.info("result: {}", Computable.computeExtensive(integers.get(i)));
                counter.increment();
            });
        }

        while (counter.getCount() < integers.size()) {
            log.info("Counter: {}", counter.getCount());
            Utils.sleep(500);
        }
        executor.shutdown();
        log.info("Final counter: {}", counter.getCount());
        log.info("Elapsed time: {}", stopwatch.stop());
    }

}