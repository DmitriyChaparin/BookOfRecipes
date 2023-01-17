package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Recipe;
import ch.dmitriy.bookofrecipes.services.RecipeServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServicesImpl implements RecipeServices {

    private static Map<Long, Recipe> recipes = new HashMap<>();
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

}
