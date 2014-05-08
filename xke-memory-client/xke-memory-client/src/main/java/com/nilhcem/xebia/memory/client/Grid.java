package com.nilhcem.xebia.memory.client;

import com.nilhcem.xebia.memory.client.model.Card;
import com.nilhcem.xebia.memory.client.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    Card[] getCardsThatMatches() {
        List<Card> playedCards = getAllCards().stream()
                .filter(card -> card.getColor() != null)
                .filter(card -> card.isFound() == false)
                .collect(Collectors.toList());

        for (Card curCard : playedCards) {
            Card similar = playedCards.stream()
                    .filter(card -> curCard.getX() != card.getX() || curCard.getY() != card.getY())
                    .filter(card -> curCard.getColor() == card.getColor())
                    .filter(card -> curCard.getSymbol() == card.getSymbol())
                    .findFirst().orElse(null);

            if (similar != null) {
                return new Card[]{curCard, similar};
            }
        }
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

    List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                cards.add(grid[x][y]);
            }
        }
        return cards;
    }

    List<Card> getAllUnplayedCards() {
        return getAllCards().stream()
                .filter(card -> card.getColor() == null)
                .collect(Collectors.toList());
    }

    public void updateGrid(Card cardPlayed, Card cardReturnedByWs) {
        // Set coordinates to the new card
        cardReturnedByWs.setX(cardPlayed.getX());
        cardReturnedByWs.setY(cardPlayed.getY());

        // Set the card in the grid
        grid[cardPlayed.getX()][cardPlayed.getY()] = cardReturnedByWs;
    }
}
