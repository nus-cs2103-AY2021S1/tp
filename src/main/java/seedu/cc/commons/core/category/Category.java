package seedu.cc.commons.core.category;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.AppUtil.checkArgument;

/**
 * Represents the category of an add command.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategory(String)}
 */
public class Category {
    public static final String MESSAGE_CONSTRAINTS = "Category can only be e, r, expense or revenue and "
            + "its not case sensitive.";
    private static final String VALIDATION_REGEX =
            "([Ee]([Xx][Pp][Ee][Nn][Ss][Ee])?|([Rr]([Ee][Vv][Ee][Nn][Uu][Ee])?))";
    private static final String EXPENSE_REGEX = "[Ee]([Xx][Pp][Ee][Nn][Ss][Ee])?";
    private static final String REVENUE_REGEX = "([Rr]([Ee][Vv][Ee][Nn][Uu][Ee])?)";
    private final String value;


    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category to determine Entry type.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        this.value = category;
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isExpense() {
        return value.matches(EXPENSE_REGEX);
    }

    public boolean isRevenue() {
        return value.matches(REVENUE_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && value.equals(((Category) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
