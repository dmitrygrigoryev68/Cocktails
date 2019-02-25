package de.recipe.web;

import de.recipe.model.*;
import lombok.Data;

import java.util.List;

@Data
public class RecipeWeb {
    private String title;
    private String announce;
    private Userweb author;
    private List <IngredientWeb> ingredients;
    private List <RecepiStepWeb> instructions;
    private List <TaxonomyWeb> tags;
    private int prepTimeMinute;
    private int cookingTime;
    private RateWeb rate;
    private List <PhotoWeb> image;
    private List <VideoWeb> video;

    public RecipeWeb() {
    }

}

