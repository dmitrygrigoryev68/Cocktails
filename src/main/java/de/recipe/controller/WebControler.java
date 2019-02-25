package de.recipe.controller;

import de.recipe.model.Recipe;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.service.RecipeServiceWebTechnical;
import de.recipe.web.CommentWeb;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebControler {

    @Autowired
    RecipeServiceImpl service;

    @Autowired
    RecipeServiceWebTechnical serviceWeb;

    @GetMapping( value = {"/", "/web/index"} )
    public String index() {
        return "index";
    }

    @GetMapping( value = {"/getAllRecipes/web/"})
    public String personList(Model model) {
        List <RecipeWebOutput> recipeList = service.getAllRecipe();
        model.addAttribute("recipeall", recipeList);
        return "recipeall";
    }


    @GetMapping( value = {"/addnewrecipes/web/"} )
    public String showAddRecipe(Model model) {
        model.addAttribute("recipeWebForm", new RecipeWeb());
        return "addrecipe";
    }

    @PostMapping( value = {"/addnewrecipes/web/"} )
    public String savePerson(@ModelAttribute( "recipeWebForm" ) RecipeWeb recipeWeb) {
        recipeWeb = serviceWeb.creatIngredientsToString(recipeWeb);
        recipeWeb = serviceWeb.creatRecipeStepsToString(recipeWeb);
        service.creatRecipe(recipeWeb);
        return "redirect:/getAllRecipes/web/";
    }

    @GetMapping( value = "/recipes/web/nameAuthor/{nameAuthor}" )
    public String searchByAuthor(@PathVariable String nameAuthor, Model model) {
        List <RecipeWebOutput> recipeWebOutputList = service.findbyAuthor(nameAuthor);
        model.addAttribute("recipeByAuthor", recipeWebOutputList);
        if (recipeWebOutputList.size() == 1) {
            return "recipeByTitle";
        }
        return "redirect:/getAllRecipes/web/";
    }

    @GetMapping( value = "/searches/web/" )
    public String searchByIdPage(Model model) {

        model.addAttribute("id", new RecipeWebOutput());
        return "recipebyid";
    }

    @PostMapping( value = "recipesbyid/web/" )
    public String searchById(@ModelAttribute( "id" ) Long id, Model model) {

        RecipeWebOutput recipeWebOutput = service.getRecipeById(id);
        model.addAttribute("recipeByAuthor", recipeWebOutput);
        return "recipeByTitle";
    }

    @GetMapping( value = "recipes/web/{id}" )
    public String searchByIdPatc(@ModelAttribute( "id" ) Long id, Model model) {

        RecipeWebOutput recipeWebOutput = service.getRecipeById(id);
        model.addAttribute("recipeByAuthor", recipeWebOutput);
        return "recipeByTitle";
    }

    @GetMapping( value = "recipes/web/title/{title}" )
    public String searchByTitle(@PathVariable String title, Model model) {
        RecipeWebOutput recipeWebOutputList = service.findByTitle(title);
        model.addAttribute("recipeByAuthor", recipeWebOutputList);
        return "recipeByTitle";
    }

    @PostMapping( value = "/recipes/web/title/" )
    public String searchByTitlePost(@ModelAttribute( "id" ) RecipeWebOutput recipeWebOutput1, Model model) {
        RecipeWebOutput recipeWebOutput = service.findByTitle(recipeWebOutput1.getTitle());
        model.addAttribute("recipeByAuthor", recipeWebOutput);
        return "recipeByTitle";
    }
    @PostMapping( value = "/recipes/web/author/" )
    public String searchByAuthorPost(@ModelAttribute( "id" ) RecipeWebOutput recipeWebOutput1, Model model) {
        List <RecipeWebOutput> recipeWebOutput = service.findbyAuthor(recipeWebOutput1.getAuthor().getName());
        model.addAttribute("recipeByAuthor", recipeWebOutput);
        if (recipeWebOutput.size() == 1) {
            return "recipeByTitle";
        }
        return "redirect:/getAllRecipes/web/";
    }

    @GetMapping( value = "recipes/web/ingredient/{ingredient}" )
    public String searchByIngredient(@PathVariable String ingredient, Model model) {
        List <RecipeWebOutput> byIngredientsContaining = service.findByIngredientsContaining(ingredient);
        model.addAttribute("recipeByAuthor", byIngredientsContaining);
        if (byIngredientsContaining.size() == 1) {
            return "recipeByTitle";
        }
        return "redirect:/getAllRecipes/web/";
    }

    @GetMapping( "/recipes/web/ubdate/{id}" )
    public String showUpdateForm(@PathVariable( "id" ) Long id, Model model) {
        RecipeWebOutput recipeWebOutput = service.getRecipeById(id);
        recipeWebOutput = serviceWeb.createsStringToRecipeSteps(recipeWebOutput);
        model.addAttribute("ubdate", serviceWeb.createsStringToIngredients(recipeWebOutput));
        return "ubdaterecipe";
    }

    @PutMapping( value = "/recipes/web/ubdate/" )
    public String ubdateRecipe(@ModelAttribute( "ubdate" ) RecipeWebOutput recipeWebOutput) {
        RecipeWeb recipeWeb = (RecipeWeb) serviceWeb.convertTheReceiptsIntoAnotherEmbodiment(recipeWebOutput, RecipeWeb.class);
        recipeWeb = serviceWeb.creatIngredientsToString(recipeWeb);
        recipeWeb = serviceWeb.creatRecipeStepsToString(recipeWeb);
        Recipe recipe = (Recipe) serviceWeb.convertTheReceiptsIntoAnotherEmbodiment(recipeWeb, Recipe.class);
        service.updateRecipe(recipe, recipeWebOutput.getId());
        return "redirect:/getAllRecipes/web/";
    }


    @GetMapping( "/recipe/web/{id}" )
    public String deleteById(@PathVariable Long id, Model model) {
        service.deleteRecipeById(id);
        model.addAttribute("recipeByAuthor", service.getAllRecipe());
        return "redirect:/getAllRecipes/web/";
    }
}