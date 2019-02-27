package de.recipe.service;

import de.exeption.NotFoundRecipeById;
import de.recipe.model.Recipe;
import de.recipe.repository.RecipeRepository;
import de.recipe.web.IngredientWeb;
import de.recipe.web.RecipeWeb;
import de.recipe.web.RecipeWebOutput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl<Y, T> implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List <RecipeWebOutput> getAllRecipe() {
        List <Recipe> outputList = recipeRepository.findAll();
        List <RecipeWebOutput> outputList1 = outputList.stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
        if (outputList.isEmpty()) throw new NotFoundRecipeById("Recipe is NotFound");
        return outputList1;
    }

    public RecipeWebOutput getRecipeById(Long id) {
        Optional <Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()) {
            throw new NotFoundRecipeById("This recipe does not exist");
        } else return creatRecipeWebOutputToRecipe(optionalRecipe.get());
    }
    public Y convertTheReceiptsIntoAnotherEmbodiment(Object t, Class refactoryclass) {
        ModelMapper modelMapper = new ModelMapper();
        Y y = (Y) modelMapper.map(t, refactoryclass);
        return y;
    }

    public List <T> refactoryObjectListToObjectwebList(List list, Class tClass) {
        ModelMapper modelMapper = new ModelMapper();
        return (List <T>) list.stream().map(a -> modelMapper.map(a, tClass)).collect(Collectors.toList());
    }
    public void creatRecipe(RecipeWeb recipeWeb) {
        Recipe recipe = creatRecipeToRecipeWeb(recipeWeb);
        recipeRepository.save(recipe);
    }

    public void deleteRecipeById(long id) {
        if (!recipeRepository.existsById(id)) throw new NotFoundRecipeById("This recipe does not exist");
        recipeRepository.deleteById(id);
    }

    public void deleteRecipeByIngredients(String ingredientName) {
        List <Recipe> list = recipeRepository.findByIngredientsNameIn(ingredientName);
        for (Recipe s : list) {
            recipeRepository.deleteById(s.getId());
        }
    }

    public void deleteIngredientsToRecipes(String ingredientName) {
        List <Recipe> list = recipeRepository.findByIngredientsNameIn(ingredientName);
        List <RecipeWebOutput> recipeWebOutputList = (List <RecipeWebOutput>) refactoryObjectListToObjectwebList(list, RecipeWebOutput.class);
        for (RecipeWebOutput x : recipeWebOutputList) {
            for (IngredientWeb s : x.getIngredients()) {
                if (s.getName().equals(ingredientName)) x.getIngredients().remove(s);
            }
            updateRecipe(x,x.getId());
        }
    }

    public Recipe creatRecipeToRecipeWeb(RecipeWeb recipeWeb) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(recipeWeb, Recipe.class);
    }


    public RecipeWebOutput creatRecipeWebOutputToRecipe(Recipe recipe) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(recipe, RecipeWebOutput.class);
    }

    public List <RecipeWebOutput> findByIngredientsContaining(String nameIngredient) {
        List <Recipe> byIngredients = recipeRepository.findByIngredientsNameIn(nameIngredient);
        if (byIngredients.isEmpty()) throw new NotFoundRecipeById("This recipe name does not exist");
        return byIngredients.stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
    }

    public List <RecipeWebOutput> findbyAuthor(String nameauthor) {
        List <Recipe> byAuthorName = recipeRepository.findByAuthorName(nameauthor);
        if (byAuthorName.isEmpty()) throw new NotFoundRecipeById("This  author does not exist");
        return byAuthorName.stream().map(this::creatRecipeWebOutputToRecipe).collect(Collectors.toList());
    }

    public void updateRecipe(RecipeWebOutput recipe, Long id) {
        Optional <Recipe> byId = recipeRepository.findById(id);
        Recipe recipe1  = (Recipe) convertTheReceiptsIntoAnotherEmbodiment(recipe, RecipeWebOutput.class);
        if (byId.isPresent()) {
            recipe.setId(id);
            recipeRepository.saveAndFlush(recipe1);        }
        recipe.setId(id);
        recipeRepository.save(recipe1);

    }

    public RecipeWebOutput findByTitle(String title) {
        Recipe recipe = recipeRepository.findByTitle(title);
        return creatRecipeWebOutputToRecipe(recipe);
    }
}

