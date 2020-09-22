package seedu.address.model.recipe;

import javafx.util.Pair;

import java.util.*;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Recipe in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe {
    private static int idCounter = 0;

    // Identity fields
    private final int id;

    // Data fields
    private final List<Pair<Integer, Integer>> ingredients;
    private final int productId;
    private final Quantity quantityProduct;
    private final boolean isDeleted;

    /**
     * Every field must be present and not null.
     */
    public Recipe(int id, List<Pair<Integer, Integer>> ingredients, int productId, Quantity quantityProduct, boolean isDeleted) {
        requireAllNonNull(id, ingredients, productId, quantityProduct, isDeleted);
        this.id = id;
        this.ingredients = ingredients;
        this.productId = productId;
        this.quantityProduct = quantityProduct;
        this.isDeleted = isDeleted;
        idCounter++;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns an immutable list of ingredients, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Pair<Integer, Integer>> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public int getProductId() {
        return productId;
    }

    public Quantity getQuantityProduct() {
        return quantityProduct;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Returns true if both recipes have the same id.
     * This defines a weaker notion of equality between two recipes.
     */
    public boolean isSameRecipe(Recipe otherRecipe) {
        if (otherRecipe == this) {
            return true;
        }

        return otherRecipe != null
                && otherRecipe.getId() == getId();
    }

    /**
     * Returns true if both recipes have the same identity and data fields.
     * This defines a stronger notion of equality between two recipes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Recipe)) {
            return false;
        }

        Recipe otherRecipe = (Recipe) other;
        return otherRecipe.getId() == getId()
                && otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getProductId() == getProductId()
                && otherRecipe.getQuantityProduct().equals(getQuantityProduct())
                && otherRecipe.isDeleted() == isDeleted();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredients, productId, quantityProduct, isDeleted);
    }

    @Override
    public String toString() {
        return String.format("Recipe for product ID: %s", productId);
    } // <---------- TODO

}
