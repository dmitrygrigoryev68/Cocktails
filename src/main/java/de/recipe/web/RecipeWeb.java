package de.recipe.web;
import de.recipe.model.*;
import lombok.Data;
import java.util.List;

@Data
public class RecipeWeb {
    private String title;
    private String announce;
    private Person author;
    private List <Ingredient> ingredients;
    private List <RecepiSteps> instructions;
    private List <Taxonomy> tags;
    private int prepTimeMinute;
    private int cookingTime;
    private Rate rate;


}

