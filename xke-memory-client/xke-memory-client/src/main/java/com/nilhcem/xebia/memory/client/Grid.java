package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Grid {

    int size;
    Card[][] grid;

    @Inject
    public Grid(Config config) {
        size = config.getGridSize();

        grid = new Card[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Card(x, y);
            }
        }
    }
}
