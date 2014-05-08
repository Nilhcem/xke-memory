package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.model.PlayResponse;
import com.nilhcem.xebia.memory.client.rest.RetrofitService;
import com.nilhcem.xebia.memory.client.rest.api.MemoryApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import retrofit.http.Body;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MemoryClientTest {

    private MemoryClient client;
    private TestMemoryApi api = new TestMemoryApi();

    @Mock
    private Config config;

    @Mock
    private RetrofitService service;

    @Mock
    private Grid grid;

    @Before
    public void setup() {
        initMocks(this);
        when(service.get(MemoryApi.class)).thenReturn(api);
        client = new MemoryClient(config, service, grid);
    }

    @Test
    public void should_call_api_with_coords_from_cards() {
        // Given
        Card card1 = new Card(2, 4);
        Card card2 = new Card(3, 1);

        // When
        client.play(new Card[]{card1, card2});

        // Then
        assertThat(api.cardsPositions[0][0]).isEqualTo(2);
        assertThat(api.cardsPositions[0][1]).isEqualTo(4);
        assertThat(api.cardsPositions[1][0]).isEqualTo(3);
        assertThat(api.cardsPositions[1][1]).isEqualTo(1);
    }

    private static class TestMemoryApi implements MemoryApi {

        int[][] cardsPositions;

        @Override
        public PlayResponse play(@Body int[][] cardsPositions) {
            this.cardsPositions = cardsPositions;
            return new PlayResponse();
        }
    }
}
