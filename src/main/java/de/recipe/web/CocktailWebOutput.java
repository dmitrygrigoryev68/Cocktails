package de.recipe.web;

import de.recipe.model.Ingredient;
import de.recipe.model.Photo;
import de.recipe.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CocktailWebOutput {

    private Long id;

    private String title;

    private String announce;

    private Date publicationDate;

    private Userweb author;

    private List <IngredientWeb> ingredients;

    private int prepTimeMinute;

    private int cookingTime;

    private List <PhotoWeb> image;

    public CocktailWebOutput() {
    }

    public CocktailWebOutput(Long id, String title, String announce, Userweb author, List <IngredientWeb> ingredients, int prepTimeMinute, int cookingTime, List <PhotoWeb> image) {
        this.id = id;
        this.title = title;
        this.announce = announce;
        this.author = author;
        this.ingredients = ingredients;
        this.prepTimeMinute = prepTimeMinute;
        this.cookingTime = cookingTime;
        this.image = image;
    }
}
