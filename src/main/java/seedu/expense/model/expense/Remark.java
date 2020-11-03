package seedu.expense.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.core.Messages.MESSAGE_INPUT_OVERLIMIT;
import static seedu.expense.commons.util.AppUtil.checkArgument;

/**
 * Represents an Expense's remark in the expense book.
 * Guarantees: immutable; is always valid
 */
public class Remark {

    public static final int MAX_CHARACTERS = 200;
    public static final String CLASS_NAME = "Remark";
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A remark.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        checkArgument(isValidLength(remark), String.format(MESSAGE_INPUT_OVERLIMIT,
            CLASS_NAME, MAX_CHARACTERS));
        value = remark;
    }

    /**
     * Returns true if a given string is of valid length.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_CHARACTERS;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
