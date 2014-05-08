package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.model.PlayResponse;
import com.nilhcem.xebia.memory.client.rest.RetrofitService;
import com.nilhcem.xebia.memory.client.rest.api.MemoryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class MemoryClient {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private Config config;
    private MemoryApi api;
    private Grid grid;

    @Inject
    public MemoryClient(Config config, RetrofitService retrofitService, Grid grid) {
        this.config = config;
        this.api = retrofitService.get(MemoryApi.class);
        this.grid = grid;
    }

    public void start() {
        LOG.debug("Starting Memory Client");
        float progress = 0f;

        for (int turn = 0; progress < 100; turn++) {
            LOG.debug("Turn #{}", turn);

            // Choose cards and call the WS
            Card[] cardsToPlay = grid.getCardsToPlay();
            PlayResponse response = play(cardsToPlay);

            // Update the grid
            List<Card> responseCards = response.getTurn().getCards();
            grid.updateGrid(cardsToPlay[0], responseCards.get(0));
            grid.updateGrid(cardsToPlay[1], responseCards.get(1));

            // Update the progress
            progress = response.getProgress();
            LOG.debug("Progress #{}", progress);
            try {
                Thread.sleep(config.getSleepTimeBetweenWebCalls());
            } catch (InterruptedException e) {
                LOG.error("", e);
            }
        }
        LOG.debug("Finished");
    }

    PlayResponse play(Card[] toPlay) {
        LOG.debug("Playing cards: {} - {}", toPlay[0], toPlay[1]);
        return api.play(new int[][]{new int[]{toPlay[0].getX(), toPlay[0].getY()}, new int[]{toPlay[1].getX(), toPlay[1].getY()}});
    }
}
