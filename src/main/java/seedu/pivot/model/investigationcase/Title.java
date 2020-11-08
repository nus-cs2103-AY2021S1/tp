package seedu.pivot.model.investigationcase;

/**
 * Represents a Title of a Case in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title extends Alphanumeric {

    public static final String MESSAGE_CONSTRAINTS =
            "Titles should only contain alphanumeric characters and spaces, and it should not be blank";
    private static final boolean CAN_BE_BLANK = false;

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        super(title, CAN_BE_BLANK);
    }

    public static boolean isValidTitle(String title) {
        return isValidAlphanum(title, CAN_BE_BLANK);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && getAlphaNum().equals(((Title) other).getAlphaNum())); // state check
    }
}
