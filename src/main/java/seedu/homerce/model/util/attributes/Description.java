package seedu.homerce.model.util.attributes;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.util.AppUtil.checkArgument;

public class Description {

    public static final String MESSAGE_CONSTRAINTS = "Description should not be empty or more than 50 characters long.";

    public final String value;

    /**
     * Represents the description of any model object
     * @param description
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    public static boolean isValidDescription(String test) {
        return ((test.trim().equals("")) || test.length() > 50) ? false : true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
