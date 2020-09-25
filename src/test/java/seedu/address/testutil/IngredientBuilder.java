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
