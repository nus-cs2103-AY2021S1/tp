// IngredientReference.java

package chopchop.model.ingredient;

import chopchop.model.attributes.Quantity;

/**
 * A reference to an ingredient. Not the actual {@code Ingredient}, and knows nothing about it.
 */
public class IngredientReference {

    private final String name;
    private final Quantity quantity;

    /**
     * Constructs an ingredient reference with the given name and quantity.
     */
    public IngredientReference(String name, Quantity qty) {
        this.name = name;
        this.quantity = qty;
    }

    public String getName() {
        return this.name;
    }

    public Quantity getQuantity() {
        return this.quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
            || (obj instanceof IngredientReference
                && ((IngredientReference) obj).name.equals(this.name)
                && ((IngredientReference) obj).quantity.equals(this.quantity)
            );
    }
}
