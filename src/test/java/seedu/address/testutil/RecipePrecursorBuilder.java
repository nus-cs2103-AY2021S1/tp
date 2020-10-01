package seedu.address.testutil;

import java.util.List;

import seedu.address.model.recipe.IngredientPrecursor;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.RecipePrecursor;

public class RecipePrecursorBuilder {
    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_PRODUCT_QUANTITY = "1";
    public static final String DEFAULT_DESCRIPTION = "Craftable Material";

    private int id;
    private ProductQuantity quantity;
    private List<IngredientPrecursor> ingredients;
    private String description;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipePrecursorBuilder() {
        this.id = DEFAULT_ID;
        this.quantity = new ProductQuantity(DEFAULT_PRODUCT_QUANTITY);
        this.ingredients = TypicalIngredientPrecursors.getTypicalIngredientList();
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipePrecursorBuilder(RecipePrecursor recipeToCopy) {
        id = recipeToCopy.getId();
        quantity = recipeToCopy.getProductQuantity();
        ingredients = recipeToCopy.getIngredientPrecursors();
        description = recipeToCopy.getDescription();
    }

    /**
     * Sets the id of the {@code RecipePrecursor} that we are building.
     */
    public RecipePrecursorBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code RecipePrecursor} that we are building.
     */
    public RecipePrecursorBuilder withQuantity(String quantity) {
        this.quantity = new ProductQuantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Ingredients} of the {@code RecipePrecursor} that we are building.
     */
    public RecipePrecursorBuilder withIngredients(List<IngredientPrecursor> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code RecipePrecursor} that we are building.
     */
    public RecipePrecursorBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds a recipe precursor.
     *
     * @return a sample RecipePrecursor
     */
    public RecipePrecursor build() {
        return new RecipePrecursor(id, ingredients, "Test",
                quantity, description);
    }
}
