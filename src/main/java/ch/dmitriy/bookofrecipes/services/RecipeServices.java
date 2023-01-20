package ch.dmitriy.bookofrecipes.services;

import ch.dmitriy.bookofrecipes.model.Recipe;

public interface RecipeServices {
    Recipe createRecipe(Recipe recipe);

    Recipe getRecipe(Long recipeId);

    abstract String getAllRecipe();

    Recipe editRecipe(long recipeId, Recipe recipe);

    boolean deleteRecipe(long recipeId);

    void deleteAllRecipe();
}
