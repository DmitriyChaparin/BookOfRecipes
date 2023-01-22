package ch.dmitriy.bookofrecipes.services;

import ch.dmitriy.bookofrecipes.model.Ingredient;

import java.util.Collection;

public interface IngredientServices {
    Ingredient creatIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long ingredientId);

   Collection<Ingredient> getAllIngredient();

    Ingredient editIngredient(long ingredientId, Ingredient ingredient);

    boolean deleteIngredient(long ingredientId);

    void deleteAllIngredients();
}
