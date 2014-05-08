package com.nilhcem.xebia.memory.client;

import javax.inject.Inject;

public class Config {

    @Inject
    public Config() {
    }

    public String getMemoryServerEndpoint() {
        return "http://localhost:3000";
    }

    public int getGridSize() {
        return 8;
    }

    public int getSleepTimeBetweenWebCalls() {
        return 300;
    }
}
