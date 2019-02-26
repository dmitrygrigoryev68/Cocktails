package de.recipe.web;

import lombok.Data;

@Data
public class IngredientWeb {
    private String name;
    private String description;

    public IngredientWeb(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public IngredientWeb() {
    }
}
