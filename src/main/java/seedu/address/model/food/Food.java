package seedu.address.model.food;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Food in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Food {

    // Identity fields
    protected final String name;
    protected final double price;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Food(String name, double price, Set<Tag> tags) {
        requireAllNonNull(name, price, tags);
        this.name = name;
        this.price = price;
        this.tags.addAll(tags);
    }

    /**
     * Checks whether the price of the food is valid.
     */
    public static boolean isValidPrice(double price) {
        if (price <= 0) {
            return false;
        }

        String priceString = String.valueOf(price);
        String[] priceArray = priceString.split("\\.");

        // Price with 0 as fractional portion
        if (priceArray.length == 1) {
            return true;
        }
        assert priceArray.length == 2;
        return priceArray[1].length() <= 2;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Formats the price to 2 decimal places
     */
    public String getPriceString() {
        return String.format("$%.2f", price);
    }

    public abstract Set<Tag> getTags();

    public abstract void setTags(Set<Tag> tags);

    /**
     * Returns true if both foods have the same identity and data fields.
     * This defines a stronger notion of equality between two foods.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;
        return otherFood.getName().equals(getName())
                && otherFood.getPrice() == getPrice()
                && otherFood.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, price, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Price: ")
                .append(getPriceString())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
