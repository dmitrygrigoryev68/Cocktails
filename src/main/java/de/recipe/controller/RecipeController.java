package de.recipe.controller;

import de.recipe.service.RecipeService;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;

    }

    @GetMapping( value = "/recipes/" )
    public List getAllRecipe() {
        return recipeService.getAllRecipe();
    }

    @PostMapping( value = "/recipes/" )
    public RecipeWeb creatRecipeController(@RequestBody RecipeWeb recipeWeb) {
        recipeService.creatRecipe(recipeWeb);
        return recipeWeb;
    }

    @GetMapping( "/recipes/{id}" )
    public RecipeWebOutput getRecipeById(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping( value = "/recipes/{id}" )
    public List <RecipeWebOutput> delletRecipeByID(@PathVariable Long id) {
        recipeService.deleteRecipeById(id);
        return getAllRecipe();

    }

    @GetMapping( "/recipes/ingredients/{name_ingredient}" )
    public List findByIngredient(@PathVariable String name_ingredient) {
        return recipeService.findByIngredientsContaining(name_ingredient);
    }

    @GetMapping( value = "/recipes/author/{name_author}" )
    public List <RecipeWebOutput> findBYAuthor(@PathVariable String name_author) {
        List <RecipeWebOutput> recipeWebOutputList = recipeService.findbyAuthor(name_author);
        return recipeWebOutputList;
    }

    @PutMapping( value = "/recipes/{id}" )
    public void updateRecipeById(@RequestBody RecipeWebOutput recipeWebOutput, @PathVariable Long id) {        recipeService.updateRecipe(recipeWebOutput, id);
    }

//test
    @DeleteMapping( value = "/recipes/ingredients/" )
   private void deleteIngredientsToRecipe(@RequestBody String nameIngredient) {
        recipeService.deleteIngredientsToRecipes(nameIngredient);
    }
}
.