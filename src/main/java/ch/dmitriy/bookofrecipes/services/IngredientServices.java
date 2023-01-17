package ch.dmitriy.bookofrecipes.services;

import ch.dmitriy.bookofrecipes.model.Ingredient;

public interface IngredientServices {
    Ingredient creatIngredient(Ingredient ingredient);

    Ingredient getIngredient(Long ingredientId);
}
