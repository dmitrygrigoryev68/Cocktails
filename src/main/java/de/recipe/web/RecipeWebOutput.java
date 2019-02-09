package de.recipe.web;

import de.recipe.model.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecipeWebOutput {
    private String title;
    private String announce;
    private Date publicationDate;
    private Personweb author;
    private List <IngredientWeb> ingredients;
    private List <RecepiStepWeb> instructions;
    private List <TaxonomyWeb> tags;
    private Comment comment;
    private int prepTimeMinute;
    private int cookingTime;
    private RateWeb rate;

    public RecipeWebOutput() {
    }
}
