package de.cocktail.web;

import lombok.Data;

import java.util.List;

@Data
public class CocktailWeb {

    private String title;

    private String announce;

    private Userweb author;

    private List <IngredientWeb> ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private List <PhotoWeb> image;

    public CocktailWeb() {
    }

    public CocktailWeb(String title, String announce, Userweb author, List <IngredientWeb> ingredients, int prepTimeMinute, int cookingTime, List <PhotoWeb> image) {
        this.title = title;
        this.announce = announce;
        this.author = author;
        this.ingredients = ingredients;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }


}

