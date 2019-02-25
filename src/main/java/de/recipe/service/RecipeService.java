package de.recipe.service;

import de.recipe.model.Recipe;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;

import java.util.List;

public interface RecipeService {
    List <RecipeWebOutput> getAllRecipe();
    RecipeWebOutput getRecipeById(Long id);
     void creatRecipe(RecipeWeb recipeWeb);
    void deleteRecipeById(long id);
    void deleteRecipieByRecipie(RecipeWeb recipeWeb);
    void deleteRecipeByIngredients(String ingredient);
    Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb);
    RecipeWebOutput creatRecipeWebOutputToRecipe(Recipe recipe);
    List <RecipeWebOutput> findByIngredientsContaining(String nameIngredient);
    List <RecipeWebOutput> findbyAuthor(String nameauthor);
    void updateRecipe(Recipe recipe, Long id);
    RecipeWebOutput findByTitle(String title);
}
