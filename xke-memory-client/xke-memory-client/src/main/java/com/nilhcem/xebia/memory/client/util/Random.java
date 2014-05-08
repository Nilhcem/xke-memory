package com.nilhcem.xebia.memory.client.util;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Random {

    java.util.Random random = new java.util.Random();

    @Inject
    public Random() {
    }

    public int getBetweenInclusive(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
