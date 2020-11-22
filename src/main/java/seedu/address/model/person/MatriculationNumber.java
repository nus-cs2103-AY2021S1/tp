package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's matriculation number in Hall-y.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatriculationNumber(String)}
 */
public class MatriculationNumber {

    public static final String MESSAGE_CONSTRAINTS = "Matriculation numbers "
            + "should follow 'A0000000X' format.\nIt begins with a character 'A', "
            + "follows by 7 numeric characters and ends with an upper case alphabetic character.";

    /*
     * The first character of the matriculation number must be 'A',
     * follows by 7 numeric characters and ends with an upper case alphabetic character.
     */
    public static final String VALIDATION_REGEX = "^A[0-9]{7}[A-Z]";

    public final String value;

    /**
     * Constructs an {@code MatriculationNumber}.
     *
     * @param matriculationNumber A valid matriculation number.
     */
    public MatriculationNumber(String matriculationNumber) {
        requireNonNull(matriculationNumber);
        checkArgument(isValidMatriculationNumber(matriculationNumber), MESSAGE_CONSTRAINTS);
        value = matriculationNumber;
    }

    /**
     * Returns true if a given string is a valid matriculationNumber.
     */
    public static boolean isValidMatriculationNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumber // instanceof handles nulls
                && value.equals(((MatriculationNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
