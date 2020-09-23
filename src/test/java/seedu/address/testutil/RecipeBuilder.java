package seedu.address.testutil;

import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;

import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientString;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_INGREDIENT_STRING = "Something, something";

    private Name name;
    private Ingredient[] ingredients;
    private IngredientString ingredientString;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        ingredientString = new IngredientString(DEFAULT_INGREDIENT_STRING);
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        ingredientString = new IngredientString(Arrays.stream(recipeToCopy.getIngredient())
                .map(item -> item.value)
                .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a));
    }

    /**
     * Sets the {@code Name} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }


    /**
     * Sets the {@code Ingredient} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withIngredient(String ingredients) {
        this.ingredients = new Ingredient[]{new Ingredient(ingredients)};
        return this;
    }

    /**
     * Builds Recipe
     * @return built Recipe
     */
    public Recipe build() {
        String[] ingredientsToken = ingredientString.value.split(",");
        Ingredient[] ingredients = new Ingredient[ingredientsToken.length];
        for (int i = 0; i < ingredientsToken.length; i++) {
            ingredients[i] = new Ingredient(ingredientsToken[i].trim());
        }
        return new Recipe(name, ingredients);
    }

}
