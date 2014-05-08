package com.nilhcem.xebia.memory.client.rest;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.nilhcem.xebia.memory.client.Config;
import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.model.PlayResponse;
import com.nilhcem.xebia.memory.client.rest.api.MemoryApi;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.nilhcem.xebia.memory.client.AppTest.WIREMOCK_PORT;
import static org.fest.assertions.Assertions.assertThat;

public class RetrofitServiceTest {

    private RetrofitService service;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WIREMOCK_PORT);

    @Before
    public void setup() {
        Config config = Mockito.mock(Config.class);
        Mockito.when(config.getMemoryServerEndpoint()).thenReturn("http://localhost:" + WIREMOCK_PORT);
        service = new RetrofitService(config);
    }

    @Test
    public void should_save_services_in_memory_after_instantiating_them() {
        // Given
        service.retrofitServiceMap.clear();

        // When
        MemoryApi memoryApi = service.get(MemoryApi.class);

        // Then
        assertThat(service.retrofitServiceMap).hasSize(1);
        assertThat(service.retrofitServiceMap.get(MemoryApi.class)).isSameAs(memoryApi);
    }

    @Test
    public void should_deserialize_json_when_calling_service() {
        // Given
        int[][] coords = new int[][]{new int[]{1, 2}, new int[]{1, 2}};

        // When
        PlayResponse response = service.get(MemoryApi.class).play(coords);

        // Then
        PlayResponse.Turn turn = response.getTurn();
        List<Card> cards = turn.getCards();
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        assertThat(response.getGameId()).isEqualTo(1);
        assertThat(response.getProgress()).isEqualTo(5.0f);
        assertThat(response.getGameScore()).isEqualTo(7);
        assertThat(turn.getTurnScore()).isEqualTo(2);
        assertThat(turn.getMessage()).isEqualTo("warning");
        assertThat(card1.getColor()).isEqualTo("blue");
        assertThat(card1.getSymbol()).isEqualTo("coat");
        assertThat(card1.isFound()).isFalse();
        assertThat(card2.getColor()).isEqualTo("green");
        assertThat(card2.getSymbol()).isEqualTo("dog");
        assertThat(card2.isFound()).isTrue();
    }
}
