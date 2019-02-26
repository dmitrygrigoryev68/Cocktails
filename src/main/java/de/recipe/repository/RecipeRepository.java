package de.recipe.repository;


import de.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long> {

    Recipe findByTitle(String title);

    List <Recipe> findByIngredientsNameIn(String name);

    // List <Recipe> findByIngredientsNameIngredientNameIngredientNameIngredient(String nameIngredient1, String nameIngredient2, String nameIngredient3);

    List <Recipe> findByAuthorName(String nameAuthor);


}