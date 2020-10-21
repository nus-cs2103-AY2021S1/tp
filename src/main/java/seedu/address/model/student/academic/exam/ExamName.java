package seedu.address.model.student.academic.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an exam's name in an Exam.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamName(String)}
 */
public class ExamName {

    public static final String MESSAGE_CONSTRAINTS =
            "Exam names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the exam name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String examName;

    /**
     * Constructs a {@code ExamName}.
     *
     * @param name A valid exam name.
     */
    public ExamName(String name) {
        requireNonNull(name);
        checkArgument(isValidExamName(name), MESSAGE_CONSTRAINTS);
        examName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidExamName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return examName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExamName // instanceof handles nulls
                && examName.equals(((ExamName) other).examName)); // state check
    }

    @Override
    public int hashCode() {
        return examName.hashCode();
    }
}
