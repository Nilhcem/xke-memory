package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GridTest {

    private Grid grid;

    @Mock
    private Config config;

    @Mock
    private Random random;

    @Before
    public void setup() {
        initMocks(this);
        when(config.getGridSize()).thenReturn(2);
        grid = new Grid(config, random);
    }

    @Test
    public void should_initialize_grid_with_cards_when_instantiating() {
        // Given
        int gridSize = 3;
        when(config.getGridSize()).thenReturn(gridSize);

        // When
        grid = new Grid(config, random);

        // Then
        assertThat(grid.size).isEqualTo(gridSize);
        Card card00 = grid.grid[0][0];
        Card card21 = grid.grid[2][1];
        Card card12 = grid.grid[1][2];
        Card card22 = grid.grid[2][2];

        assertThat(card00).isNotNull();
        assertThat(card00.getSymbol()).isNull();
        assertThat(card00.getColor()).isNull();
        assertThat(card00.isFound()).isFalse();
        assertThat(card00.getX()).isEqualTo(0);
        assertThat(card00.getY()).isEqualTo(0);

        assertThat(card21.getX()).isEqualTo(2);
        assertThat(card21.getY()).isEqualTo(1);

        assertThat(card12.getX()).isEqualTo(1);
        assertThat(card12.getY()).isEqualTo(2);

        assertThat(card22.getX()).isEqualTo(2);
        assertThat(card22.getY()).isEqualTo(2);
    }

    @Test
    public void should_return_all_unplayed_cards() {
        // Given
        Card played = Mockito.mock(Card.class);
        when(played.getColor()).thenReturn("cyan");
        when(played.getSymbol()).thenReturn("sky");
        grid.grid[0][0] = played;

        // When
        List<Card> unplayed = grid.getAllUnplayedCards();

        // Then
        assertThat(unplayed).hasSize(3);
    }

    @Test
    public void should_return_two_cards() {
        // When
        Card[] cardsToPlay = grid.getCardsToPlay();

        // Then
        assertThat(cardsToPlay).hasSize(2);
        assertThat(cardsToPlay[0]).isNotNull();
        assertThat(cardsToPlay[1]).isNotNull();
        assertThat(cardsToPlay[0]).isNotSameAs(cardsToPlay[1]);
    }

    @Test
    public void should_return_two_different_cards() {
        // Given
        Card played = mock(Card.class);
        when(played.getColor()).thenReturn("pink");
        grid.grid[0][0] = played;
        grid.grid[0][1] = played;
        when(random.getBetweenInclusive(anyInt(), anyInt())).thenReturn(0);

        // When
        Card[] randomCards = grid.getRandomCards();

        // Then
        assertThat(randomCards).hasSize(2);
        assertThat(randomCards[0]).isNotSameAs(randomCards[1]);
    }

    @Test
    public void should_return_a_random_card_from_a_list() {
        // Given
        List<Card> cards = Arrays.asList(new Card[]{new Card(2, 1), new Card(3, 2), new Card(0, 2)});
        when(random.getBetweenInclusive(anyInt(), anyInt())).thenReturn(2);

        // When
        Card card = grid.getRandomCardFrom(cards);

        // Then
        assertThat(card.getX()).isEqualTo(0);
        assertThat(card.getY()).isEqualTo(2);
    }
}
