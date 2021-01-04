package seedu.homerce.model.expense;

import static java.util.Objects.requireNonNull;

import seedu.homerce.commons.util.AppUtil;

public class IsFixed {
    public static final String MESSAGE_CONSTRAINTS = "Is Fixed must be in 'y' or 'n' format.";

    public final boolean value;
    private boolean isRecurring;

    /**
     * Represents whether the expense is fixed.
     * @param isFixed
     */
    public IsFixed(String isFixed) {
        requireNonNull(isFixed);
        AppUtil.checkArgument(isValidIsFixed(isFixed), MESSAGE_CONSTRAINTS);
        boolean isExpenseFixed = isFixed.equals("y");
        value = isExpenseFixed;
        isRecurring = isExpenseFixed;
    }

    /**
     * Represents whether the expense is fixed and is recurring.
     * @param isFixed
     * @param isRecurring
     */
    public IsFixed(String isFixed, boolean isRecurring) {
        requireNonNull(isFixed);
        AppUtil.checkArgument(isValidIsFixed(isFixed), MESSAGE_CONSTRAINTS);
        value = isFixed.equals("y");
        this.isRecurring = isRecurring;
    }

    /**
     * Mark IsFixed as recurred.
     */
    public void markAsRecurred() {
        this.isRecurring = false;
    }

    public boolean getIsRecurring() {
        return this.isRecurring;
    }

    public static boolean isValidIsFixed(String test) {
        return (test.equals("y") || test.equals("n"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFixed// instanceof handles nulls
                && value == ((IsFixed) other).value
                && isRecurring == ((IsFixed) other).getIsRecurring()); // state check
    }

    @Override
    public String toString() {
        return value ? "Yes" : "No";
    }
}
