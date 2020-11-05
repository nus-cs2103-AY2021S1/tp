package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's id in the app.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "STUDENT_ID should begin and end with a capital letter "
                    + "and should have 7 digits (e.g. A1243567X).";

    public static final String VALIDATION_REGEX = "^[A-Z]\\d{7}[A-Z]$";

    private final String value;

    /**
     * Constructs a {@code StudentId}
     * @param studentId A valid student id.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid student id.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
