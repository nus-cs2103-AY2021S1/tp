package seedu.address.model.recipe;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.InventoryComponent;
import seedu.address.model.item.Item;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Represents an Recipe in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Recipe extends InventoryComponent {
    private static int idCounter = 0;

    // Identity fields
    private final int id;

    // Data fields
    private final IngredientList ingredients;
    private final int productId;
    private final String productName;
    private final ProductQuantity productQuantity;
    private final String description;

    /**
     * Every field must be present and not null.
     */
    public Recipe(int id, IngredientList ingredients, int productId, String productName,
                  ProductQuantity productQuantity, String description) {
        requireAllNonNull(id, ingredients, productId, productQuantity, description);
        this.id = id;
        this.ingredients = ingredients;
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.description = description;
        idCounter++;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    /**
     * Used only when parsing Recipes to populate
     * list for initialization of application
     * @param id id counter for next recipe.
     */
    public static void setIdCounter(int id) {
        assert id >= 0 : "Recipe ID should never be negative";
        idCounter = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns an {@code IngredientList}.
     */
    public IngredientList getIngredients() {
        return ingredients;
    }

    public int getProductId() {
        return productId;
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

    /**
     * Generates a printable recipe for display.
     * @param filteredItemList The current item list which ingredients reference to.
     * @return Printable Recipe with the same fields as this recipe.
     */
    public PrintableRecipe print(ObservableList<Item> filteredItemList) {
        return new PrintableRecipe(this.id, this.ingredients, this.productId, this.productName,
                this.productQuantity, this.description, filteredItemList);
    }

    /**
     * Creates a new recipe with same fields and updated product name. Used for editing items.
     */
    public Recipe setProductName(String updatedProductName) {
        return new Recipe(id, ingredients, productId, updatedProductName, productQuantity, description);
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
                && ((otherRecipe.getId() == getId()) || (otherRecipe.getIngredients().equals(getIngredients())
                && otherRecipe.getProductName().equals(getProductName())
                && otherRecipe.getProductQuantity().equals(getProductQuantity())
                && otherRecipe.description.equals(getDescription())));
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
                && otherRecipe.getProductName().equals(getProductName())
                && otherRecipe.getProductQuantity().equals(getProductQuantity())
                && otherRecipe.description.equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredients, productId, productQuantity, description);
    }

    @Override
    public String toString() {
        return String.format("Recipe for: %s. %s", productName, description);
    }

    public DisplayedInventoryType getType() {
        return DisplayedInventoryType.RECIPES;
    }
}
