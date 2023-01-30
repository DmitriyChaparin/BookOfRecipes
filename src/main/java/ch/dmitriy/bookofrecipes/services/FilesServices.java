package ch.dmitriy.bookofrecipes.services;

import java.io.File;

public interface FilesServices {
    boolean saveRecipeToFile(String json);

    boolean saveIngredientToFile(String json);

    String readFromFileRecipes();

    String readFromFileIngredients();

    boolean cleanFileRecipe();

    boolean cleanFileIngredient();


    File getRecipesFile();

    File getIngredientsFile();
}
