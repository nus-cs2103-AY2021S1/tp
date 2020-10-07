package chopchop.testutil;

import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Step;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.Recipe;

import java.util.*;

public class RecipeBuilder {

    public static final String DEFAULT_NAME = "Egg";
    public static final String DEFAULT_STEP = "Heat it for 15 minutes.";

    private Name name;
    private List<Ingredient> ingredients;
    private List<Step> steps;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        ingredients = new ArrayList<>(Arrays.asList(new IngredientBuilder().build()));
        steps = new ArrayList<>(Arrays.asList(new Step(DEFAULT_STEP)));
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recToCopy}.
     */
    public RecipeBuilder(Recipe recToCopy) {
        name = recToCopy.getName();
        ingredients = recToCopy.getIngredients();
        steps = recToCopy.getSteps();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public RecipeBuilder withIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public RecipeBuilder withSteps(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public Recipe build() {
        return new Recipe(name, ingredients, steps);
    }

}
