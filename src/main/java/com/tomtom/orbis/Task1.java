package com.tomtom.orbis;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class Task1 {
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Integer> integers = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .toList();

        log.info("Integers: {}", integers);

        List<Integer> results = new ArrayList<>(integers.size());
        int counter = 0;
        for (int i = 0; i < integers.size(); i++) {
            counter++;
            results.add(Computable.computeIntensive(integers.get(i)));
        }

        log.info("Counter: {}", counter);
        log.info("Results: {}", results);
        log.info("Elapsed time: {}", stopwatch.stop());
    }
}