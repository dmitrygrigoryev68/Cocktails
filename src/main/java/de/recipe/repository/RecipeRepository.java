package de.repository;
import de.model.Ingredient;
import de.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long> {

    List <Recipe> findByIngredientsNameIngredientIn(String nameIngredient);
    List <Recipe> findByAuthorNameaAuthor(String nameAuthor);
    void deleteByIngredientsIn(Ingredient ingredient);


}