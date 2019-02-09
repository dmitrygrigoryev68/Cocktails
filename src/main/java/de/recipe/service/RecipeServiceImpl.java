package de.recipe.service;

import de.exeption.NotFoundRecipeById;
import de.recipe.model.Ingredient;
import de.recipe.model.Recipe;
import de.recipe.repository.RecipeRepository;
import de.recipe.web.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
public class RecipeServiceImpl<T, Y> implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List <RecipeWebOutput> getAllRecipe() {
        return recipeRepository.findAll().stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
    }

    public RecipeWebOutput getRecipeById(Long id) {
        Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundRecipeById("This recipe does not exist");
        } else return creatRecipeWebOutputToRecipe(optionalRecipe.get());
    }

    public void creatRecipe(RecipeWeb recipeWeb) {
        Recipe recipe = creatRecipeToRecipeWeb(recipeWeb);
        recipeRepository.save(recipe);
    }

    public void deleteRecipeById(long id) {
        if (!recipeRepository.existsById(id)) throw new NotFoundRecipeById("This recipe does not exist");
        recipeRepository.deleteById(id);
    }

    public void deleteRecipieByRecipie(Recipe recipe) {
        Long id = recipe.getId();
        recipeRepository.deleteById(id);
    }

    public void deleteRecipeByIngredients(Ingredient ingredient) {
        recipeRepository.deleteByIngredientsIn(ingredient);
    }

    private Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(recipeWeb, Recipe.class);
    }

    private RecipeWebOutput creatRecipeWebOutputToRecipe(Recipe recipe) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(recipe,RecipeWebOutput.class);
    }

   private List <T> refactoryObjectListToObjectwebList(List <Y> list, Class <T> tClass) {
        ModelMapper modelMapper = new ModelMapper();
        return list.stream().map(a -> modelMapper.map(a, tClass)).collect(Collectors.toList());
    }

    public List <RecipeWebOutput> findByIngredientsContaining(String nameIngredient) {
        List <Recipe> byIngredients = recipeRepository.findByIngredientsNameIngredientIn(nameIngredient);
        if (byIngredients.isEmpty()) throw new NotFoundRecipeById("This recipe name does not exist");
        return byIngredients.stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
    }

    public List <RecipeWebOutput> findbyAuthor(String nameauthor) {
        List <Recipe> byAuthorNameaAuthor = recipeRepository.findByAuthorNameaAuthor(nameauthor);
        if (byAuthorNameaAuthor.isEmpty()) throw new NotFoundRecipeById("This  author does not exist");
        return byAuthorNameaAuthor.stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
    }

    public void updateRecipe(Recipe recipe, Long id) {
        Optional <Recipe> recipe1Optional = recipeRepository.findById(id);

        if (!recipe1Optional.isPresent()) {
            throw new NotFoundRecipeById("This  recipe does not exist");
        }
        recipe.setId(id);
        recipeRepository.save(recipe);

    }


}

