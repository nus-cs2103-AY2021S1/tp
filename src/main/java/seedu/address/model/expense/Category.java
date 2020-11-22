package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an expense's Category in the expense list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategory(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Category names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the category name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String categoryName;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category name.
     */
    public Category(String category) {
        assert category != null : "Need to include the category of the expenses";
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        categoryName = category;
    }
    /**
     * Constructs a {@code Category}.
     *
     */
    public Category() {
        categoryName = "";
    }
    public boolean isEmpty() {
        return categoryName.equals("");
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return categoryName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && categoryName.equals(((Category) other).categoryName)); // state check
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }

}

