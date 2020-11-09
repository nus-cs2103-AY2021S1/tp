package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Birthdate {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdate must be input in this format: YYYY-MM-DD";

    public final String value;

    /**
     * Constructs a {@code Birthdate}.
     *
     * @param birthdate A valid birthdate number.
     */
    public Birthdate(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        value = birthdate;
    }

    /**
     * Returns true if a given string is a valid birthdate number.
     */
    public static boolean isValidBirthdate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && value.equals(((Birthdate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
