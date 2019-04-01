package de.cocktail.service;

import de.cocktail.model.Cocktail;
import de.cocktail.repository.CocktailRepository;
import de.cocktail.web.CocktailWeb;
import de.cocktail.web.CocktailWebOutput;
import de.exeption.NotFoundCocktail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CocktailServiceImpl<Y, T> implements CocktailService {

    @Autowired
    private CocktailRepository cocktailRepository;


    public List <CocktailWebOutput> getAllCocktails() {
        List <Cocktail> outputList = cocktailRepository.findAll();
        List <CocktailWebOutput> outputList1 = outputList
                .stream()
                .map(this::createCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundCocktail("Cocktail is NotFound");
        return outputList1;
    }

    public CocktailWebOutput getCocktailById(Long id) {
        Optional <Cocktail> optionalRecipe = cocktailRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundCocktail("This cocktail does not exist");
        } else return createCocktailWebOutputToCocktail(optionalRecipe.get());
    }

    public Object convertTheeCocktailsIntoAnotherEmbodiment(Object t, Class refactoryClass) {
        ModelMapper modelMapper = new ModelMapper();
        Y y = (Y) modelMapper.map(t, refactoryClass);
        return y;
    }

    public void createCocktail(CocktailWeb cocktailWeb) {
        Cocktail cocktail = createCocktailToCocktailWeb(cocktailWeb);
        cocktailRepository.save(cocktail);
    }

    public void deleteCocktailById(long id) {
        if (!cocktailRepository.existsById(id)) throw new NotFoundCocktail("This cocktail does not exist");
        cocktailRepository.deleteById(id);
    }

    public Cocktail createCocktailToCocktailWeb(CocktailWeb cocktailWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktailWeb, Cocktail.class);
    }

    public CocktailWebOutput createCocktailWebOutputToCocktail(Cocktail cocktail) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cocktail, CocktailWebOutput.class);
    }

    public List <CocktailWebOutput> getCocktailByIngredient(String nameIngredient) {
        List <CocktailWebOutput> outputList = cocktailRepository.findByIngredients_Title(nameIngredient)
                .stream()
                .map(this::createCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundCocktail("This Ingredient not exist");
        return outputList;
    }

    public List <CocktailWebOutput> getCocktailByAuthor(String name_author) {
        List <Cocktail> byAuthorName = cocktailRepository
                .findByAuthor_Name(name_author);
        if (byAuthorName.isEmpty()) throw new NotFoundCocktail("This  author does not exist");
        return byAuthorName
        .stream()
                .map(this::createCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
    }

    public void updateCocktail(CocktailWebOutput cocktailWebOutput, Long id) {
        Optional <Cocktail> byId = cocktailRepository.findById(id);
        Cocktail cocktail1 = (Cocktail) convertTheeCocktailsIntoAnotherEmbodiment(cocktailWebOutput, Cocktail.class);
        if (byId.isPresent()) {
            cocktail1.setId(id);
            cocktailRepository.save(cocktail1);
        }
    }

    public List <CocktailWebOutput> getCocktailByTitle(String title) {
        List <Cocktail> byTitle = cocktailRepository.findByTitle(title);
        if (byTitle.isEmpty()) {
            throw new NotFoundCocktail("This title does not exist");
        }
        return byTitle
                .stream()
                .map(this::createCocktailWebOutputToCocktail)
                .collect(Collectors.toList());
    }
}