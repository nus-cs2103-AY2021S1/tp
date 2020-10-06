package seedu.address.model.person;

/**
 * Represents a Title of a Case in PIVOT.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Title extends Alphanumeric {

    public static final String MESSAGE_CONSTRAINTS =
            "Titles should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code Title}.
     *
     * @param title A valid title.
     */
    public Title(String title) {
        super(title);
    }


}
