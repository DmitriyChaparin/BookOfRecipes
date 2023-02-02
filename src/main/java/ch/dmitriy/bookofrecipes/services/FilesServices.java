package ch.dmitriy.bookofrecipes.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesServices {
    boolean saveRecipeToFile(String json);

    boolean saveIngredientToFile(String json);

    String readFromFileRecipes();

    String readFromFileIngredients();

    boolean cleanFileRecipe();

    boolean cleanFileIngredient();


    File getRecipesFile();

    File getIngredientsFile();

    Path creatTempFile(String suffix);
}
