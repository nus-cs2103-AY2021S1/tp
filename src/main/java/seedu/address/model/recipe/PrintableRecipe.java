package seedu.address.model.recipe;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;

/**
 * Represents a recipe ready for display in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PrintableRecipe extends Recipe{

    public String printableIngredientList;

    /**
     * Every field must be present and not null.
     */
    public PrintableRecipe(int id, IngredientList ingredients, int productId, String productName,
                           ProductQuantity productQuantity, String description, boolean isDeleted, ObservableList<Item> filteredItemList) {
        super(id, ingredients, productId, productName, productQuantity, description, isDeleted);
        StringBuilder sb = new StringBuilder();
        ingredients.forEach(ingredient -> sb.append(ingredient.toString(filteredItemList)).append(" "));
        this.printableIngredientList = sb.toString();
    }

    public String getPrintableIngredients() {
        return this.printableIngredientList;
    }
}
