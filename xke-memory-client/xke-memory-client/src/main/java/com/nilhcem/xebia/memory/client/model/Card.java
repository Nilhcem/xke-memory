package com.nilhcem.xebia.memory.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    @JsonProperty private String symbol;
    @JsonProperty private String color;
    @JsonProperty private boolean found;

    private int x;
    private int y;

    public Card() {
    }

    public Card(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public boolean isFound() {
        return found;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
