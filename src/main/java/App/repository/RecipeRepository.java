package App.repository;
import App.model.Ingredient;
import App.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long> {

    List <Recipe> findByIngredientsNameIngredientIn(String nameIngredient);
    List <Recipe> findByAuthorNameaAuthor(String nameAuthor);
    void deleteByIngredientsIn(Ingredient ingredient);


}