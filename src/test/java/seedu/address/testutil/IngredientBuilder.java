package seedu.address.testutil;

import seedu.address.model.recipe.Ingredient;

/**
 * A utility class to help with building Ingredient objects.
 */
public class IngredientBuilder {

    public static final String DEFAULT_VALUE = "food";
    public static final String DEFAULT_QUANTITY = "2g";

    private String value;
    private String quantity;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public IngredientBuilder() {
        value = DEFAULT_VALUE;
        quantity = DEFAULT_QUANTITY;
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public IngredientBuilder(Ingredient ingredientToCopy) {
        value = ingredientToCopy.getValue();
        quantity = ingredientToCopy.getQuantity();
    }

    /**
     * Sets the {@code value} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withValue(String value) {
        this.value = value;
        this.quantity = "";
        return this;
    }

    /**
     * Sets the {@code value} of the {@code Ingredient} that we are building.
     */
    public IngredientBuilder withValues(String value, String quantity) {
        this.value = value;
        this.quantity = quantity;
        return this;
    }

    /**
     * Builds Ingredient
     * @return built Ingredient
     */
    public Ingredient build() {
        return new Ingredient(value, quantity);
    }

    /**
     * Builds Ingredient without Quantity
     * @return built Ingredient
     */
    public Ingredient buildWithoutQuantity() {
        return new Ingredient(value);
    }
}
