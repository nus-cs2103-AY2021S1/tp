// IngredientEditDescriptor.java

package chopchop.logic.edit;

import java.util.Optional;

import chopchop.model.attributes.Quantity;

public class IngredientEditDescriptor extends EditDescriptor {

    private final Optional<Quantity> ingredientQuantity;
    private final String ingredientName;

    /**
     * Creates a new descriptor representing ingredient editing.
     *
     * @param editType the type of edit
     * @param name     the name of the ingredient to edit
     * @param qty      the new quantity of the ingredient; should only be present iff type is EDIT or ADD
     */
    public IngredientEditDescriptor(EditOperationType editType, String name, Optional<Quantity> qty) {

        super(editType);

        assert editType == EditOperationType.ADD
            || editType == EditOperationType.EDIT
            || editType == EditOperationType.DELETE;

        if (editType == EditOperationType.EDIT || editType == EditOperationType.ADD) {
            assert qty.isPresent();
        } else {
            assert qty.isEmpty();
        }

        this.ingredientName = name;
        this.ingredientQuantity = qty;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public Optional<Quantity> getIngredientQuantity() {
        return this.ingredientQuantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof IngredientEditDescriptor)) {
            return false;
        } else {
            var other = (IngredientEditDescriptor) obj;
            return this.getEditType() == other.getEditType()
                && this.ingredientQuantity.equals(other.ingredientQuantity)
                && this.ingredientName.equalsIgnoreCase(other.ingredientName);
        }
    }
}
