package seedu.simplykitchen.model.food;

import static seedu.simplykitchen.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.simplykitchen.model.tag.Tag;

/**
 * Represents a Food item in the Food inventory.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Food {

    // Identity fields
    private final Description description;
    private final Priority priority;
    private final ExpiryDate expiryDate;

    // Data fields
    private final Quantity quantity;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Food(Description description, Priority priority, ExpiryDate expiryDate,
                Quantity quantity, Set<Tag> tags) {
        requireAllNonNull(description, priority, expiryDate, quantity, tags);
        this.description = description;
        this.priority = priority;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.tags.addAll(tags);
    }

    public Description getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both food items of the same description have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two food items.
     */
    public boolean isSameFood(Food otherFood) {
        if (otherFood == this) {
            return true;
        }

        return otherFood != null
                && otherFood.getDescription().equals(getDescription())
                && otherFood.getExpiryDate().equals(getExpiryDate())
                && otherFood.getTags().equals(getTags());
    }

    /**
     * Returns true if both food items have the same identity and data fields.
     * This defines a stronger notion of equality between two food items.
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
        return otherFood.getDescription().equals(getDescription())
                && otherFood.getPriority().equals(getPriority())
                && otherFood.getExpiryDate().equals(getExpiryDate())
                && otherFood.getTags().equals(getTags())
                && otherFood.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, priority, expiryDate, quantity, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Description: " + getDescription()).append("\n  ")
                .append("Expiry Date: " + getExpiryDate()).append("\n  ")
                .append("Priority: " + getPriority()).append("\n  ")
                .append("Quantity: " + getQuantity()).append("\n  ")
                .append("Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
