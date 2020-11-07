package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialGroupId(String)}
 */
public class TutorialGroupId {

    public static final String MESSAGE_CONSTRAINTS =
            "TUTORIAL_GROUP_CODE should only contain alphanumeric characters and spaces, and it should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String id;

    /**
     * Constructs a {@code TutorialGroupId}.
     *
     * @param id A valid id.
     */
    public TutorialGroupId(String id) {
        requireNonNull(id);
        checkArgument(isValidTutorialGroupId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid ID.
     */
    public static boolean isValidTutorialGroupId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupId // instanceof handles nulls
                && id.equals(((TutorialGroupId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
