package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CocktailService<Y, T> {
    List <CocktailWebOutput> getAllCocktails();

    CocktailWebOutput getCocktailById(Long id);

    void createCocktail(CocktailWeb cocktailWeb);

    void deleteCocktailById(long id);

    Cocktail createCocktailToCocktailWeb(CocktailWeb cocktailWeb);

    CocktailWebOutput createCocktailWebOutputToCocktail(Cocktail cocktail);

    List <CocktailWebOutput> getCocktailByIngredient(String nameIngredient);

    List <CocktailWebOutput> getCocktailByAuthor(String nameauthor);

    void updateCocktail(CocktailWebOutput recipe, Long id);

    List <CocktailWebOutput> getCocktailByTitle(String title);

    Y convertTheeCocktailsIntoAnotherEmbodiment(T t, Class <Y> refactoryclass);


}