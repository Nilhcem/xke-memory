package com.nilhcem.xebia.memory.client.util;

public class Random {

    static final java.util.Random random = new java.util.Random();

    private Random() {
        throw new UnsupportedOperationException();
    }

    public static int getBetweenInclusive(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
