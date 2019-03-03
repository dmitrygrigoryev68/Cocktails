package de.cocktail.controller;

import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController

public class CocktailController {
    @Autowired

    private CocktailService cocktailService;


    @GetMapping( "/recipes/ingredients/{name_ingredient}" )
    public List findByIngredient1(@PathVariable String name_ingredient) {
        return cocktailService.findByIngredientsContaining(name_ingredient);
    }

    @RequestMapping( path = "/cocktails ", method = RequestMethod.GET, params = "" )
    public List getAllRecipe() {
        return cocktailService.getAllCocktail();
    }

    @PostMapping( value = "/cocktails" )
    public void creatRecipeController(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.creatCocktail(cocktailWeb);
    }

//    @PostMapping
//    @PatchMapping( "/cocktails/id?id={id} " )
//    public Response getCocktailById(@PathParam( "id" ) Long id) {
//        return Response
//                .status(Response.Status.OK)
//                .entity(cocktailService.getCocktailById(id))
//                .build();
//
//    }

    @DeleteMapping( "/cocktails/{id}" )
    public void deleteCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);

    }

    @PostMapping
    @PatchMapping( value = "/cocktails/ingredients?ingredient={name_ingredient}/ " )
    public Response findByIngredient(@PathParam( "name_ingredient" ) String name_ingredient) {
        return Response.status(Response.Status.OK)
                .entity(cocktailService.findByIngredientsContaining(name_ingredient))
                .build();

    }

    @GetMapping( "/cocktails/author?author={name_author}/ " )
    public List <CocktailWebOutput> findBYAuthor(@PathVariable String name_author) {
        return cocktailService.findbyAuthor(name_author);
    }

    @GetMapping( "/cocktails/title?title={title}/ " )
    public CocktailWebOutput findByTitle(@PathVariable String title) {
        return cocktailService.findByTitle(title);

    }

    @PutMapping( "/cocktails/{id} " )
    public void updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
    }

}
