package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

public class RecipePrecursor {

    private final int id;

    private final List<IngredientPrecursor> ingredientPrecursors;
    private final String productName;
    private final ProductQuantity productQuantity;
    private final String description;

    public RecipePrecursor(int id, List<IngredientPrecursor> ingredientPrecursors, String productName,
                           ProductQuantity productQuantity, String description) {
        requireAllNonNull(id, ingredientPrecursors, productName, productQuantity, description);
        this.id = id;
        this.ingredientPrecursors = ingredientPrecursors;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public List<IngredientPrecursor> getIngredientPrecursors() {
        return ingredientPrecursors;
    }

    public String getProductName() {
        return productName;
    }

    public ProductQuantity getProductQuantity() {
        return productQuantity;
    }

    public String getDescription() {
        return description;
    }

    public Recipe toRecipe(int productId, IngredientList ingredients) {
        return new Recipe(id, ingredients, productId, productName, productQuantity, description, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecipePrecursor)) {
            return false;
        }

        RecipePrecursor otherRecipe = (RecipePrecursor) other;
        return otherRecipe.getId() == getId()
                && otherRecipe.getIngredientPrecursors().equals(getIngredientPrecursors())
                && otherRecipe.getProductName().equals(getProductName())
                && otherRecipe.getProductQuantity().equals(getProductQuantity())
                && otherRecipe.getDescription().equals(getDescription());
    }
}
