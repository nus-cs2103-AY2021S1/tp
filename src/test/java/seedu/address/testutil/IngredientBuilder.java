package seedu.address.testutil;

import seedu.address.model.recipe.Ingredient;

/**
 * A utility class to help with building Ingredient objects.
 */
public class IngredientBuilder {

    public static final String DEFAULT_VALUE = "food";

    private String value;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public IngredientBuilder() {
        value = DEFAULT_VALUE;
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public IngredientBuilder(Ingredient ingredientToCopy) {
        value = ingredientToCopy.getValue();
    }

    /**
     * Sets the {@code value} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Builds Ingredient
     * @return built Ingredient
     */
    public Ingredient build() {
        return new Ingredient(value);
    }

}
