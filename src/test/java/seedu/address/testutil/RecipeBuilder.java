package seedu.address.testutil;

import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.Recipe;

public class RecipeBuilder {
    public static final int DEFAULT_ID = 1;
    public static final String DEFAULT_PRODUCT_QUANTITY = "1";
    public static final String DEFAULT_DESCRIPTION = "Craftable Material";

    private int id;
    private ProductQuantity quantity;
    private IngredientList ingredients;
    private String description;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        this.id = DEFAULT_ID;
        this.quantity = new ProductQuantity(DEFAULT_PRODUCT_QUANTITY);
        this.ingredients = TypicalIngredients.getTypicalIngredientList();
        this.description = DEFAULT_DESCRIPTION;
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code recipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        id = recipeToCopy.getId();
        quantity = recipeToCopy.getProductQuantity();
        ingredients = recipeToCopy.getIngredients();
        description = recipeToCopy.getDescription();
    }

    /**
     * Sets the id of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withQuantity(String quantity) {
        this.quantity = new ProductQuantity(quantity);
        return this;
    }

    /**
     * Sets the {@code Ingredients} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withIngredients(IngredientList ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Builds a recipe.
     *
     * @return a sample Recipe
     */
    public Recipe build() {
        return new Recipe(id, ingredients, 0, "Test",
                quantity, description, false);
    }
}
