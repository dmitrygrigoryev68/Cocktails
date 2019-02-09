package de.recipe.web;

import lombok.Data;

@Data
public class RateWeb {
    private String rate;

    public RateWeb(String rate) {
        this.rate = rate;
    }

    public RateWeb() {
    }
}
