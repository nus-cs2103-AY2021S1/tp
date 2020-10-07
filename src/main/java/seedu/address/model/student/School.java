package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's school in Reeve.
 * Guarantees: immutable; is valid as declared in {@link #isValidSchool(String)}
 */
public class School {

    public static final String MESSAGE_CONSTRAINTS =
            "School names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String school;

    /**
     * Constructs a {@code Name}.
     *
     * @param school A valid school.
     */
    public School(String school) {
        requireNonNull(school);
        checkArgument(isValidSchool(school), MESSAGE_CONSTRAINTS);
        this.school = school;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSchool(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return school;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof School // instanceof handles nulls
                && school.equals(((School) other).school)); // state check
    }

    @Override
    public int hashCode() {
        return school.hashCode();
    }

}

