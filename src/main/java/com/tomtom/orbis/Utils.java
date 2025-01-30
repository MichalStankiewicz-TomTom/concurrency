package com.tomtom.orbis;

import lombok.SneakyThrows;

public class Utils {

    @SneakyThrows
    public static void sleep(long millis) {
        Thread.sleep(millis);
    }

}
