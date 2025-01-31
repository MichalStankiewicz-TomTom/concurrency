package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class Task11 {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(10_000)
                .toList();

        log.info("Integers: {}", integers);

        List<Integer> results = integers.parallelStream()
                .map(Computable::fast)
                .toList();

        log.info("Results: {}", results);
        log.info("Elapsed time: {}", stopwatch.stop());
    }

}