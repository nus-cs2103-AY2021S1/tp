package chopchop.model.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import chopchop.model.Entry;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Tag;
import chopchop.model.attributes.units.Count;

/**
 * A reference to an ingredient. Not the actual {@code Ingredient}, and knows nothing about it.
 */
public class IngredientReference extends Entry {
    private final Quantity quantity;

    /**
     * Constructs an ingredient reference with the given name and quantity.
     */
    public IngredientReference(String name, Quantity quantity) {
        super(name);
        requireNonNull(quantity);
        this.quantity = quantity;
    }

    /**
     * Constructs an ingredient reference with the given name and quantity.
     */
    public IngredientReference(String name, Optional<Quantity> quantity) {
        this(name, quantity.orElse(Count.of(1)));
    }

    public Quantity getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.quantity);
    }

    @Override
    public Set<Tag> getTags() {
        return null;
    }

    @Override
    public Optional<ExpiryDate> getExpiryDate() {
        return Optional.empty();
    }

    @Override
    public Optional<List<ExpiryDate>> getExpiryDates() {
        return Optional.empty();
    }

    @Override
    public boolean isSame(Entry other) {
        return other == this
                || (other instanceof IngredientReference
                && this.name.equals(((IngredientReference) other).name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof IngredientReference
                && this.name.equals(((IngredientReference) other).name)
                && this.quantity.equals(((IngredientReference) other).quantity));
    }
}
