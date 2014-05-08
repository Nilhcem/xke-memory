package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.model.PlayResponse;
import com.nilhcem.xebia.memory.client.rest.RetrofitService;
import com.nilhcem.xebia.memory.client.rest.api.MemoryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class MemoryClient {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private MemoryApi api;
    private Grid grid;

    @Inject
    public MemoryClient(RetrofitService retrofitService, Grid grid) {
        this.api = retrofitService.get(MemoryApi.class);
        this.grid = grid;
    }

    public void start() {
        LOG.debug("Starting Memory Client");

        LOG.debug("Finished");
    }

    PlayResponse play(Card[] toPlay) {
        LOG.debug("Playing cards: {}", toPlay);
        return api.play(new int[][]{new int[]{toPlay[0].getX(), toPlay[0].getY()}, new int[]{toPlay[1].getX(), toPlay[1].getY()}});
    }
}
