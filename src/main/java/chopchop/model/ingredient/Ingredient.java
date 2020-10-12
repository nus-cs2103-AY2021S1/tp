package chopchop.model.ingredient;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import chopchop.model.FoodEntry;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents an Ingredient in the recipe manager.
 */
public class Ingredient extends FoodEntry {

    // Identity fields
    private final ExpiryDate expiryDate;

    // Data fields
    private final Quantity quantity;

    /**
     * Every field must be present and not null.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Ingredient(Name name, Quantity quantity, ExpiryDate expiryDate, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(quantity, expiryDate);
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.tags.addAll(tags);
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Returns true if both ingredients of the same name.
     *
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
            && otherInd.getExpiryDate().equals(getExpiryDate())
            && otherInd.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.name, quantity, expiryDate, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Quantity: ")
            .append(getQuantity())
            .append(" Expiry Date: ")
            .append(getExpiryDate())
            .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
