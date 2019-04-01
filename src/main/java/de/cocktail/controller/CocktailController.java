package de.cocktail.controller;

import de.cocktail.service.CocktailService;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }


    @GetMapping( "/cocktails/ingredients/{name_ingredient}" )
    public ResponseEntity <List <CocktailWebOutput>> findCocktailsByIngredient(@PathVariable String name_ingredient) {
        return ResponseEntity.ok().body(cocktailService.
                getCocktailByIngredient(name_ingredient));
    }

    @GetMapping( "/cocktails/author/{name_author}" )
    public ResponseEntity <List <CocktailWebOutput>> findCocktailsByAuthor(@PathVariable String name_author) {
        return ResponseEntity.
                ok().body(cocktailService.
                getCocktailByAuthor(name_author));
    }


    @GetMapping( "/cocktails/" )
    public ResponseEntity <List <CocktailWebOutput>> getAllCocktails() {
        return ResponseEntity.
                ok().body(cocktailService.getAllCocktails());

    }

    @PostMapping( "cocktails/" )
    public ResponseEntity saveCocktail(@RequestBody CocktailWeb cocktailWeb) {
        cocktailService.createCocktail(cocktailWeb);
        return ResponseEntity.ok().build();
    }

    @GetMapping( "/cocktails/{id}" )
    public ResponseEntity <CocktailWebOutput> getCocktailById(@PathVariable Long id) {
        return ResponseEntity.
                ok().
                body(cocktailService.getCocktailById(id));

    }

    @DeleteMapping( "/cocktails/{id}" )
    public void deleteCocktailByID(@PathVariable Long id) {
        cocktailService.deleteCocktailById(id);
    }


    @GetMapping( "/cocktails/title/{title}" )
    public ResponseEntity <List <CocktailWebOutput>> findCocktailByTitle(@PathVariable String title) {
        return ResponseEntity.ok().body(cocktailService.getCocktailByTitle(title));
    }

    @PutMapping( "/cocktails/{id}" )
    public ResponseEntity <CocktailWebOutput> updateCocktailById(@RequestBody CocktailWebOutput cocktailWebOutput, @PathVariable Long id) {
        cocktailService.updateCocktail(cocktailWebOutput, id);
        return ResponseEntity.ok().body(cocktailService.getCocktailById(id));
    }
}