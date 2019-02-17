package de.recipe.controller;

import de.recipe.service.RecipeServiceImpl;
import de.recipe.service.RecipeServiceWeb;
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
    RecipeServiceWeb serviceWeb;
    @RequestMapping( value = {"/", "/index"}, method = RequestMethod.GET )
    public String index() {
        return "index";
    }

    @RequestMapping( value = {"/recipeall"}, method = RequestMethod.GET )
    public String personList(Model model) {
        List <RecipeWebOutput> recipeList = service.getAllRecipe();
        model.addAttribute("recipeall", recipeList);
        return "recipeall";
    }


    @RequestMapping( value = {"/addrecipe"}, method = RequestMethod.GET )
    public String showAddRecipe(Model model) {
        model.addAttribute("recipeWebForm", new RecipeWeb());
        return "addrecipe";
    }

    @PostMapping( value = {"/addrecipe"} )
    public String savePerson(@ModelAttribute( "recipeWebForm" ) RecipeWeb recipeWeb) {
        recipeWeb=serviceWeb.creatIngredientsTuString(recipeWeb);
        service.creatRecipe(recipeWeb);
        return "redirect:/recipeall";
    }

    @GetMapping( value = "recipe/{author}" )
    public String searchByAuthor(@PathVariable String author, Model model) {
        List <RecipeWebOutput> recipeWebOutputList = service.findbyAuthor(author);
        model.addAttribute("recipeByAuthor", recipeWebOutputList);
        return "recipeByTitle";
    }

    @GetMapping( value = "/recipebyid" )
    public String searchByIdPage() {
        return "recipeByTitle";
    }

    @GetMapping( value = "recipeid/{id}" )
    public String searchById(@PathVariable String id, Model model) {
        Long id1 = Long.parseLong(id);
        RecipeWebOutput recipeWebOutput = service.getRecipeById(id1);
        model.addAttribute("recipeById", recipeWebOutput);
        return "recipeByTitle";
    }

    @GetMapping( value = "recipebytitle/{title}" )
    public String searchByTitle(@PathVariable String title, Model model) {
        RecipeWebOutput recipeWebOutputList = service.findByTitle(title);
        model.addAttribute("recipeByAuthor", recipeWebOutputList);
        return "recipeByTitle";
    }

    @GetMapping( value = "recipebyingredient/{ingredient}" )
    public String searchByIngredient(@PathVariable String ingredient, Model model) {
        List <RecipeWebOutput> byIngredientsContaining = service.findByIngredientsContaining(ingredient);
        model.addAttribute("recipeByAuthor", byIngredientsContaining);
        return "recipeByTitle";
    }
    @PostMapping( value = {"/addcomment"} )
    public String saveComent(@ModelAttribute( "recipeWebForm" ) RecipeWeb recipeWeb,String comment) {
        recipeWeb=serviceWeb.creatIngredientsTuString(recipeWeb);
        RecipeWebOutput recipe= service.findByTitle(recipeWeb.getTitle());
        recipe.getComment().add(new CommentWeb(comment));
        service.creatRecipe(recipeWeb);
        return "redirect:/recipeall";
    }
}