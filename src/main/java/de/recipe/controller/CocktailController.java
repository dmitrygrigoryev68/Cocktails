package de.cocktail.controller;
import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class CocktailController {
  private  CocktailService cocktailService;

    @Autowired
    public CocktailController( CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }


    @GetMapping( value = "/cocktails " )
    public List getAllRecipe() {
        return cocktailService.getAllCocktail();
    }

    @PostMapping( value = "/cocktails" )
    public CocktailWebOutput creatRecipeController(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.creatCocktail(cocktailWeb);
        return (CocktailWebOutput) cocktailService.convertTheeCoctailsIntoAnotherEmbodiment( cocktailWeb,CocktailWebOutput.class);
    }

    @GetMapping( value = "/cocktails?id={id} " )
    public CocktailWebOutput getCocktailById(@PathVariable Long id) {
        return cocktailService.getCocktailById(id);
    }

    @DeleteMapping( value = "/cocktails/{id} " )
    public void delletCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);

           }

    @GetMapping(value = "/cocktails?ingredient={name_ingredient} " )
    public List findByIngredient(@PathVariable String name_ingredient) {
        return cocktailService.findByIngredientsContaining(name_ingredient);
    }

    @GetMapping( value = "/cocktails?author={name_author} " )
    public List <CocktailWebOutput> findBYAuthor(@PathVariable String name_author) {
        return cocktailService.findbyAuthor(name_author);
    }

    @GetMapping( value = "/cocktails?title={title} " )
    public CocktailWebOutput findByTitle(@PathVariable String title) {
        return cocktailService.findByTitle(title);

    }

    @PutMapping( value = "/cocktails/{id} " )
    public void updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
    }

}
