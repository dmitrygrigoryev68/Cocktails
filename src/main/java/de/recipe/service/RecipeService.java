package de.recipe.service;

import de.recipe.model.Recipe;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;

import java.util.List;

public interface RecipeService<Y, T> {
    public List <RecipeWebOutput> getAllRecipe();

    public RecipeWebOutput getRecipeById(Long id);

    public void creatRecipe(RecipeWeb recipeWeb);

    public void deleteRecipeById(long id);

    public void deleteRecipeByIngredients(String ingredient);

    public Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb);

    public RecipeWebOutput creatRecipeWebOutputToRecipe(Recipe recipe);

    public List <RecipeWebOutput> findByIngredientsContaining(String nameIngredient);

    public List <RecipeWebOutput> findbyAuthor(String nameauthor);

    public RecipeWebOutput updateRecipe(Recipe recipe, Long id);

    public RecipeWebOutput findByTitle(String title);

    public void deleteIngredientsToRecipes(String ingredientName);

    public Y convertTheReceiptsIntoAnotherEmbodiment(T t, Class <Y> refactoryclass);

    public List <T> refactoryObjectListToObjectwebList(List <Y> list, Class <T> tClass);
}
