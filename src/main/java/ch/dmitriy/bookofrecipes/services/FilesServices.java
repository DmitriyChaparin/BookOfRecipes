package ch.dmitriy.bookofrecipes.services;

public interface FilesServices {
    boolean saveRecipeToFile(String json);

    boolean saveIngredientToFile(String json);

    String readFromFileRecipes();

    String readFromFileIngredients();
}
