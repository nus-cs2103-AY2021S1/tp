package seedu.address.model.recipe;

import seedu.address.model.item.Quantity;

/**
 * Represents a Recipe's product's quantity number in the inventory.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuantity(String)}
 */
public class ProductQuantity extends Quantity {

    /**
     * Constructs a {@code Quantity}.
     *
     * @param quantity A valid quantity number.
     */
    public ProductQuantity(String quantity) {
        super(quantity);
    }
}
