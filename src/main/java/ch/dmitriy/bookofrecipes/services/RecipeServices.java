package ch.dmitriy.bookofrecipes.services;

import ch.dmitriy.bookofrecipes.model.Recipe;

import java.util.Collection;

public interface RecipeServices {
    Recipe createRecipe(Recipe recipe);

    Recipe getRecipe(Long recipeId);

    Collection<Recipe> getAllRecipe();

    Recipe editRecipe(long recipeId, Recipe recipe);

    boolean deleteRecipe(long recipeId);

    void deleteAllRecipe();
}
