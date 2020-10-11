package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;
//import java.util.HashSet;
//import java.util.Set;

import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final ArrayList<Ingredient> DEFAULT_INGREDIENTS =
            new ArrayList<>(List.of(new Ingredient("Veggies", ""), new Ingredient("Snakes", "")));
    public static final Integer DEFAULT_CALORIES = 10;
    public static final String DEFAULT_INSTRUCTION = "instruction";
    public static final String DEFAULT_RECIPE_IMAGE = "images/healthy1.jpg";

    private Name name;
    private ArrayList<Ingredient> ingredients;
    private Calories calories;
    private String instruction;
    private String recipeImage;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        ingredients = DEFAULT_INGREDIENTS;
        calories = new Calories(DEFAULT_CALORIES);
        instruction = DEFAULT_INSTRUCTION;
        recipeImage = DEFAULT_RECIPE_IMAGE;
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
        ingredients = recipeToCopy.getIngredient();
        calories = recipeToCopy.getCalories();
        instruction = recipeToCopy.getInstruction();
        recipeImage = recipeToCopy.getRecipeImage();
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
    public RecipeBuilder withIngredient(String ingredientString, String ingredientQuantity) {
        String[] ingredientsToken = ingredientString.split(",");
        String[] ingredientsQuantity = ingredientQuantity.split(",");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsToken.length; i++) {
            ingredients.add(new Ingredient(ingredientsToken[i].trim(), ingredientsQuantity[i].trim()));
        }
        this.ingredients = ingredients;
        return this;
    }

    /**
     * Sets the {@code Calories} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withCalories(Integer calories) {
        this.calories = new Calories(calories);
        return this;
    }

    /**
     * Sets the instruction of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withInstruction(String instruction) {
        this.instruction = instruction;
        return this;
    }

    /**
     * Sets the recipe image of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
        return this;
    }

    /**
     * Builds Recipe
     * @return built Recipe
     */
    public Recipe build() {
        return new Recipe(name, instruction, recipeImage, ingredients, calories);
    }

}
