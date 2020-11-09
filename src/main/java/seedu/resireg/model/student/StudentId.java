package seedu.resireg.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's identification number in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {


    public static final String MESSAGE_CONSTRAINTS =
        "A student's identification number should begin with an \'E0\' followed by 6 digits.";
    public static final String VALIDATION_REGEX = "E0[0-9]{6}";
    public final String value;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param studentId A valid student identification number.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId;
    }

    /**
     * Returns true if a given string is a valid phone number.
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
