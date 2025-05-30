package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class Task2 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(100)
                .toList();

        log.info("Integers: {}", integers);

        Counter counter = new CounterBasic();
        List<Thread> threads = new ArrayList<>(integers.size());
        for (int i : integers) {
            Thread thread = new Thread(() -> {
                log.info("result: {}", Computable.computeIntensive(integers.get(i)));
                counter.increment();
            });
            thread.setName("Test-thread-" + i);
            thread.setDaemon(true);
            threads.add(thread);
        }
        log.info("Threads created");
        for (Thread thread : threads) {
            thread.run();
        }

        while (counter.getCount() < integers.size()) {
            log.info("Counter: {}", counter.getCount());
            Utils.sleep(500);
        }
        log.info("Final counter: {}", counter.getCount());
        log.info("Elapsed time: {}", stopwatch.stop());
    }

}