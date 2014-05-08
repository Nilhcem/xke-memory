package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GridTest {

    private Grid grid;

    @Mock
    private Config config;

    @Before
    public void setup() {
        initMocks(this);
        when(config.getGridSize()).thenReturn(2);
        grid = new Grid(config);
    }

    @Test
    public void should_initialize_grid_with_cards_when_instantiating() {
        // Given
        int gridSize = 3;
        when(config.getGridSize()).thenReturn(gridSize);

        // When
        grid = new Grid(config);

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
}
