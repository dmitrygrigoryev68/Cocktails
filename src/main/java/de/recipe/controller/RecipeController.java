package de.recipe.controller;

import de.recipe.model.Ingredient;
import de.recipe.model.Recipe;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.RecipeWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeServiceImpl recipeService;

    @GetMapping( value = "/recipes" )
    public List <Recipe> getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @PostMapping( value = "/recipes", consumes = "application/json" )
    public void creatRecipeController(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creatRecipe(recipeWeb);
    }

    @RequestMapping( "/recipes/{id}" )
    public Recipe getRecipeByIdController(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping( value = "/delete/{id}" )
    public void delletRecipeByID(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
    }

    @DeleteMapping( value = "delete/" )
    public void deleteRecipeByIngretient(@RequestBody Ingredient ingredient) {
        recipeService.deleteRecipeByIngredients(ingredient);
    }

    @DeleteMapping( value = "delete/recipes", consumes = "application/json" )
    public void deletByRecipe(@RequestBody Recipe recipe) {
        recipeService.deleteRecipieByRecipie(recipe);
    }

    @GetMapping( "/searhc/ingredient/{name_ingredient}" )
    public List <Recipe> findByIngredientController(@PathVariable String name_ingredient) {
        return recipeService.findByIngredientsContaining(name_ingredient);
    }

    @RequestMapping( "search/byAuthor/{string}" )
    public List <Recipe> findBYAuthor(@PathVariable String string) {
        return recipeService.findbyAuthor(string);
    }

    @PutMapping( "/recipe/update/{id}" )
    public void updateRecipeById(@RequestBody Recipe recipe, @PathVariable long id) {
        recipeService.updateRecipe(recipe, id);
    }
}
