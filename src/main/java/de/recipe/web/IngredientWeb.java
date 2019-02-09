package de.recipe.web;

import lombok.Data;

import javax.persistence.Column;
@Data
public class IngredientWeb {
    private String nameIngredient;
    private String descriptions;

    public IngredientWeb() {
    }
}
