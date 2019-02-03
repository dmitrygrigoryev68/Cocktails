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
    private List <RecepiStep> instructions;
    private List <Taxonomy> tags;
    private int prepTimeMinute;
    private int cookingTime;
    private Rate rate;

    public RecipeWeb(String title, String announce, Person author, List <Ingredient> ingredients, List <RecepiStep> instructions, List <Taxonomy> tags, int prepTimeMinute, int cookingTime, Rate rate) {
        this.title = title;
        this.announce = announce;
        this.author = author;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.rate = rate;
    }
}

