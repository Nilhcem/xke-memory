package com.nilhcem.xebia.memory.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayResponse {

    @JsonProperty int gameId;
    @JsonProperty float progress;
    @JsonProperty Turn turn;
    @JsonProperty int gameScore;

    public int getGameId() {
        return gameId;
    }

    public float getProgress() {
        return progress;
    }

    public Turn getTurn() {
        return turn;
    }

    public int getGameScore() {
        return gameScore;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Turn {

        @JsonProperty int turnScore;
        @JsonProperty List<Card> cards;
        @JsonProperty String message;

        public int getTurnScore() {
            return turnScore;
        }

        public List<Card> getCards() {
            return cards;
        }

        public String getMessage() {
            return message;
        }
    }
}
