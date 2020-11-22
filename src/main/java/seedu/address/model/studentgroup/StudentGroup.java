package seedu.address.model.studentgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student group in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidStudentGroupName(String)}
 */
public class StudentGroup {

    public static final String MESSAGE_CONSTRAINTS = "Student groups names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String studentGroupName;

    /**
     * Constructs a {@code StudentGroup}.
     *
     * @param studentGroupName A valid student group name.
     */
    public StudentGroup(String studentGroupName) {
        requireNonNull(studentGroupName);
        checkArgument(isValidStudentGroupName(studentGroupName), MESSAGE_CONSTRAINTS);
        this.studentGroupName = studentGroupName.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid student group name.
     */
    public static boolean isValidStudentGroupName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentGroup // instanceof handles nulls
                && studentGroupName.equals(((StudentGroup) other).studentGroupName)); // state check
    }

    @Override
    public int hashCode() {
        return studentGroupName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + studentGroupName + ']';
    }

}
