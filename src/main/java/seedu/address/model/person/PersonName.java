package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidPersonName(String)}
 */
public class PersonName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain letters and single space between words, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^([a-zA-Z]+\\s)*[a-zA-Z]+$";

    public final String fullPersonName;

    /**
     * Constructs a {@code PersonName}.
     *
     * @param personName A valid personName.
     */
    public PersonName(String personName) {
        requireNonNull(personName);
        checkArgument(isValidPersonName(personName), MESSAGE_CONSTRAINTS);
        fullPersonName = personName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPersonName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullPersonName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonName // instanceof handles nulls
                && fullPersonName.equals(((PersonName) other).fullPersonName)); // state check
    }

    @Override
    public int hashCode() {
        return fullPersonName.hashCode();
    }

}
