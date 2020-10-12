package chopchop.model.ingredient;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import chopchop.model.FoodEntry;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Name;

/**
 * Represents an Ingredient in the recipe manager.
 */
public class Ingredient extends FoodEntry {

    // Identity fields
    private final ExpiryDate expiryDate;

    // Data fields
    private final Quantity quantity;

    public Ingredient(Name name, Quantity quantity) {
        super(name);
        requireNonNull(quantity);
        this.quantity = quantity;
        this.expiryDate = null;
    }

    public Ingredient(Name name, Quantity quantity, ExpiryDate expiryDate) {
        super(name);
        requireAllNonNull(quantity, expiryDate);
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Optional<ExpiryDate> getExpiryDate() {
        return Optional.ofNullable(expiryDate);
    }


    /**
     * Returns true if both ingredients of the same name and expiry date.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherInd = (Ingredient) other;

        return otherInd.getName().equals(getName())
            && otherInd.getExpiryDate().equals(getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, expiryDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getName())
                .append(" Quantity: ")
                .append(getQuantity());

        getExpiryDate().ifPresent(expiryDate -> builder.append(" Expiry Date: ").append(expiryDate));
        return builder.toString();
    }

}
