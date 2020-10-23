// IngredientEditDescriptor.java

package chopchop.logic.edit;

import java.util.Optional;

import chopchop.logic.edit.exceptions.IllegalEditOperation;
import chopchop.model.attributes.Quantity;

public class IngredientEditDescriptor {

    private final EditOperationType editType;

    private final Optional<Quantity> ingredientQuantity;
    private final String ingredientName;

    /**
     * Creates a new descriptor representing ingredient editing.
     *
     * @param editType the type of edit
     * @param name     the name of the ingredient to edit
     * @param qty      the new quantity of the ingredient; should only be present iff type is EDIT or ADD
     * @throws IllegalEditOperation if the quantity was provided when not required, or vice-versa
     */
    public IngredientEditDescriptor(EditOperationType editType, String name, Optional<Quantity> qty)
        throws IllegalEditOperation {

        this.editType = editType;
        this.ingredientName = name;
        this.ingredientQuantity = qty;

        if ((this.editType == EditOperationType.EDIT || this.editType == EditOperationType.ADD)
            && qty.isEmpty()) {

            throw new IllegalEditOperation("quantity cannot be empty when editing ingredient in recipe");
        } else if (qty.isPresent()) {
            throw new IllegalEditOperation("edit to delete a recipe should not have a quantity");
        }
    }

    public EditOperationType getEditType() {
        return this.editType;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public Optional<Quantity> getIngredientQuantity() {
        return this.ingredientQuantity;
    }
}
