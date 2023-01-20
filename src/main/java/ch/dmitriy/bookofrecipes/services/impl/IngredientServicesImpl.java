package ch.dmitriy.bookofrecipes.services.impl;

import ch.dmitriy.bookofrecipes.model.Ingredient;
import ch.dmitriy.bookofrecipes.services.IngredientServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServicesImpl implements IngredientServices {
    private  Map<Long, Ingredient> ingredients = new HashMap<>();
    private Long ingredientId = 1L;

    @Override
    public Ingredient creatIngredient(Ingredient ingredient) {
        ingredients.put(ingredientId, ingredient);
        ingredientId++;
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Long ingredientId) {
        return ingredients.get(ingredientId);

    }

    @Override
    public String getAllIngredient() {
        return ingredients.toString();

    }



    @Override
    public Ingredient editIngredient(long ingredientId, Ingredient ingredient) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(long ingredientId) {
        if (ingredients.containsKey(ingredientId)) {
            ingredients.remove(ingredientId);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllIngredients() {
        ingredients = new HashMap<>();
    }
}

