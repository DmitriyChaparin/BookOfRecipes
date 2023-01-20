package ch.dmitriy.bookofrecipes.services;

import ch.dmitriy.bookofrecipes.model.Ingredient;

public interface IngredientServices {
    Ingredient creatIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long ingredientId);

    String getAllIngredient();

    Ingredient editIngredient(long ingredientId, Ingredient ingredient);

    boolean deleteIngredient(long ingredientId);

    void deleteAllIngredients();
}
