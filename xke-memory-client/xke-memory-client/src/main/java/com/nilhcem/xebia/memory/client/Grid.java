package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class Grid {

    int size;
    Card[][] grid;
    Random random;

    @Inject
    public Grid(Config config, Random random) {
        this.random = random;
        size = config.getGridSize();

        grid = new Card[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Card(x, y);
            }
        }
    }

    public Card[] getCardsToPlay() {
        Card cards[] = getCardsThatMatches();
        if (cards == null) {
            cards = getRandomCards();
        }
        return cards;
    }

    private Card[] getCardsThatMatches() {


        return null;
    }

    Card[] getRandomCards() {
        List<Card> unplayed = getAllUnplayedCards();
        Card card1 = getRandomCardFrom(unplayed);
        unplayed.remove(card1);
        Card card2 = getRandomCardFrom(unplayed);
        return new Card[]{card1, card2};
    }

    Card getRandomCardFrom(List<Card> cards) {
        return cards.get(random.getBetweenInclusive(0, cards.size() - 1));
    }

    List<Card> getAllUnplayedCards() {
        List<Card> unplayed = new ArrayList<Card>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Card card = grid[x][y];
                if (card.getColor() == null) {
                    unplayed.add(card);
                }
            }
        }
        return unplayed;
    }
}
