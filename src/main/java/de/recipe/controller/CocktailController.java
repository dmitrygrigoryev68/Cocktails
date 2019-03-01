package de.recipe.controller;
import de.recipe.service.CocktailService;
import de.recipe.web.CocktailWeb;
import de.recipe.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/cocktails")

public class CocktailController {

    private CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;

    }

    @GetMapping( value = "/ " )
    public List getAllRecipe() {
        return cocktailService.getAllCocktail();
    }

    @PostMapping( value = "/ " )
    public CocktailWebOutput creatRecipeController(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.creatCocktail(cocktailWeb);
        return (CocktailWebOutput) cocktailService.convertTheeCoctailsIntoAnotherEmbodiment( cocktailWeb,CocktailWebOutput.class);
    }

    @GetMapping( "/{id} " )
    public CocktailWebOutput getCocktailById(@PathVariable Long id) {
        return cocktailService.getCocktailById(id);
    }

    @DeleteMapping( value = "/{id} " )
    public void delletCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);

           }

    @GetMapping( "/ingredient/{name_ingredient} " )
    public List findByIngredient(@PathVariable String name_ingredient) {
        return cocktailService.findByIngredientsContaining(name_ingredient);
    }

    @GetMapping( path = "/author/{name_author} " )
    public List <CocktailWebOutput> findBYAuthor(@PathVariable String name_author) {
        return cocktailService.findbyAuthor(name_author);
    }

    @GetMapping( path = "/title/{title} " )
    public CocktailWebOutput findByTitle(@PathVariable String title) {
        return cocktailService.findByTitle(title);

    }

    @PutMapping( value = "/{id} " )
    public void updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
    }

}
