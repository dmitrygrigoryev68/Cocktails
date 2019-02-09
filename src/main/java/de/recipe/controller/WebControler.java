package de.recipe.controller;

import de.recipe.model.*;
import de.recipe.service.RecipeServiceImpl;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class WebControler {

@Autowired
RecipeServiceImpl service;


    // Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/recipeall" }, method = RequestMethod.GET)
    public String personList(Model model) {
List<RecipeWebOutput>recipeList=service.getAllRecipe();
        model.addAttribute("recipeall", recipeList);

        return "recipeall";
    }

    @RequestMapping(value = { "/addrecipe" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {

        RecipeWeb recipeWeb=new RecipeWeb();

        model.addAttribute("recipeWeb", recipeWeb);

        return "addrecipeweb";
    }

    @RequestMapping(value = { "/addrecipe" }, method = RequestMethod.POST)
    public String savePerson(Model model, //
                             @ModelAttribute("recipeweb") RecipeWeb recipeWeb) {

        String title = recipeWeb.getTitle();
        String anounce = recipeWeb.getAnnounce();
         Person author =recipeWeb.getAuthor();
         List <Ingredient> ingredients=recipeWeb.getIngredients();
         List <RecepiStep> instructions=recipeWeb.getInstructions();
        List <Taxonomy> tags=recipeWeb.getTags();
         int prepTimeMinute=recipeWeb.getPrepTimeMinute();
         int cookingTime=recipeWeb.getCookingTime();
         Rate rate=recipeWeb.getRate();

        if (title != null && title.length() > 0 //
                && anounce != null && title.length() > 0) {
            recipeWeb = new RecipeWeb(title, anounce,author,ingredients,instructions,tags,prepTimeMinute,cookingTime,rate);
           service.creatRecipe(recipeWeb);

            return "redirect:/recipeall";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "addrecipeweb";
    }

}