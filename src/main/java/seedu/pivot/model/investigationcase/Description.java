package seedu.pivot.model.investigationcase;

/**
 * Represents an Investigation Case's description in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description extends Alphanumeric {

    //TODO: Might want to change validation check
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces";
    private static final boolean CAN_BE_BLANK = true;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        super(description, CAN_BE_BLANK);
    }

    /**
     * Returns true if a given string is a valid description.
     * Can be blank.
     */
    public static boolean isValidDescription(String test) {
        return isValidAlphanum(test, CAN_BE_BLANK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && getAlphaNum().equals(((Description) other).getAlphaNum())); // state check
    }
}
