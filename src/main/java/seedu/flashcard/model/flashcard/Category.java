package seedu.flashcard.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.commons.util.AppUtil.checkArgument;

/**
 * Represents the Category the Flashcard is in.
 */
public class Category {
    public static final String MESSAGE_CONSTRAINTS = "Categories names should be alphanumeric,"
                                                        + " not more than 50 characters, and only have 1 space "
                                                        + "between words";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$";
    private String category;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        this.category = category;
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.length() <= 50 && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return category;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && category.toLowerCase().equals(((Category) other).category.toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
