package seedu.address.model.recipe;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;

/**
 * Represents a recipe ready for display in the Inventoryinator.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PrintableRecipe extends Recipe {

    private final String printableIngredientList;
    /* Indicates if index should be altered when displayed. */
    private boolean offset;

    /**
     * Every field must be present and not null.
     */
    public PrintableRecipe(int id, IngredientList ingredients, int productId, String productName,
                           ProductQuantity productQuantity, String description,
                           ObservableList<Item> filteredItemList) {
        super(id, ingredients, productId, productName, productQuantity, description);
        StringBuilder sb = new StringBuilder();
        ingredients.forEach(ingredient -> sb.append(ingredient.toString(filteredItemList)).append(" "));
        this.printableIngredientList = sb.toString();
        offset = false;
    }

    public String getPrintableIngredients() {
        return this.printableIngredientList;
    }

    /**
     * Used only in view details command to toggle an offset of the recipe index.
     */
    public void includeOffset() {
        offset = true;
    }

    public boolean getOffset() {
        return offset;
    }
}
