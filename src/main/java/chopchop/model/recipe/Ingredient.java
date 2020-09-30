package chopchop.model.recipe;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

public class Ingredient {

    // Identity fields
    private final Name name;

    // Data fields
    private final int quantity;
    private final LocalDate expiryDate;

    /**
     * Every field must be present and not null.
     */
    public Ingredient(Name name, int quantity, LocalDate expiryDate) {
        requireAllNonNull(name, quantity, expiryDate);
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Name getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

}
