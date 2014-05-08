package com.nilhcem.xebia.memory.client;

import com.google.inject.Guice;

public final class App {

    public static void main(String[] args) throws InterruptedException {
        Guice.createInjector().getInstance(MemoryClient.class).start();
    }
}
