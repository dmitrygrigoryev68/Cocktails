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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
