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
        return 6;
    }

    public int getSleepTimeBetweenWebCalls() {
        return 300;
    }

    public String getEmail() {
        return "my_email@example.org";
    }
}
