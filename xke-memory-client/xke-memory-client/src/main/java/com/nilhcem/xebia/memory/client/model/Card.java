package com.nilhcem.xebia.memory.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    @JsonProperty private String symbol;
    @JsonProperty private String color;
    @JsonProperty private boolean found;

    public Card() {
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
}
