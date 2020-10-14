package chopchop.model.ingredient;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

import chopchop.model.Entry;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Quantity;
import chopchop.model.exceptions.IncompatibleIngredientsException;

/**
 * Represents an Ingredient in the recipe manager.
 */
public class Ingredient extends Entry {
    private final Quantity quantity;
    private final ExpiryDate expiryDate;

    /**
     * Every field must be present and not null. Use this constructor if expiry date is not present.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Ingredient(String name, Quantity quantity) {
        this(name, quantity, null);
    }

    /**
     * Every field must be present and not null. If expiry date is not present, use other constructor.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Ingredient(String name, Quantity quantity, ExpiryDate expiryDate) {
        super(name);
        requireNonNull(quantity);
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Quantity getQuantity() {
        return this.quantity;
    }

    public Optional<ExpiryDate> getExpiryDate() {
        return Optional.ofNullable(this.expiryDate);
    }

    /**
     * Combines the quantities of this ingredient and the provided ingredient.
     *
     * @param other the other ingredient
     * @return      a new {@code Ingredient} with the combined quantities
     * @throws IncompatibleIngredientsException if the units of both ingredients were not compatible
     */
    public Ingredient combine(Ingredient other) throws IncompatibleIngredientsException {

        if (!this.name.equals(other.name)) {
            throw new IncompatibleIngredientsException(String.format("cannot combine '%s' with '%s'",
                this.name, other.name));
        }

        // TODO: expiry date handling! see #58
        return this.quantity.add(other.quantity)
            .map(newQty -> new Ingredient(this.name.toString(), newQty, this.expiryDate))
            .orElseThrow(IncompatibleIngredientsException::new);
    }

    @Override
    public boolean isSame(Entry other) {
        return other == this
                || (other instanceof Ingredient
                && this.name.equals(((Ingredient) other).name));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Ingredient
                && this.name.equals(((Ingredient) other).name)
                && this.quantity.equals(((Ingredient) other).quantity)
                && this.expiryDate.equals(((Ingredient) other).expiryDate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, expiryDate);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        joiner.add(this.getName())
                .add("Quantity:")
                .add(this.getQuantity().toString());

        this.getExpiryDate().ifPresent(expiryDate -> joiner.add("Expiry Date:").add(expiryDate.toString()));
        return joiner.toString();
    }

}
