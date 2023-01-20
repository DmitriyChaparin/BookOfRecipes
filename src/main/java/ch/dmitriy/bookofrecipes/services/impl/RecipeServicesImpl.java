package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Recipe;
import ch.dmitriy.bookofrecipes.services.RecipeServices;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RecipeServicesImpl implements RecipeServices {

    private static Map<Long, Recipe> recipes = new LinkedHashMap<>();
    private Long recipeId = 1L;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        recipes.put(recipeId, recipe);
        recipeId++;
        return recipe;
    }

    @Override
    public Recipe getRecipe(Long recipeId) {
        return recipes.get(recipeId);
    }

    @Override
    public String getAllRecipe() {
        return recipes.toString();
    }

    @Override
    public Recipe editRecipe(long recipeId, Recipe recipe) {
        if (recipes.containsKey(recipeId)) {
            recipes.put(recipeId, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(long recipeId) {
        if (recipes.containsKey(recipeId)) {
            recipes.remove(recipeId);
            return true;
        }return false;
    }

    @Override
    public void deleteAllRecipe() {
        recipes = new LinkedHashMap<>();
    }
}
