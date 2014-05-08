package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.rest.RetrofitService;
import com.nilhcem.xebia.memory.client.rest.api.MemoryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class MemoryClient {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private MemoryApi api;

    @Inject
    public MemoryClient(RetrofitService retrofitService) {
        api = retrofitService.get(MemoryApi.class);
    }

    public void start() {
        LOG.debug("Starting Memory Client");

        LOG.debug("Finished");
    }
}
