package seedu.address.model.recipe;

import seedu.address.model.ingredient.Ingredient;

import java.util.ArrayList;

public class Content {
    private ArrayList<Ingredient> ingredients;
    private String name;
    Content(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

}
