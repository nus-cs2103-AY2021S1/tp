package seedu.address.model.student.academic.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an exam's score in an Exam.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamScore(String)}
 */
public class Score {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades should be in the form int/int";

    /*
     * Format of exam score is integer/integer.
     */
    public static final String VALIDATION_REGEX = "(\\d{1,2})(\\/)(\\d{1,2})";

    public final String examScore;

    /**
     * Constructs a {@code Score}.
     *
     * @param score A valid exam score.
     */
    public Score(String score) {
        requireNonNull(score);
        checkArgument(isValidExamScore(score), MESSAGE_CONSTRAINTS);
        examScore = score;
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidExamScore(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return examScore;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Score // instanceof handles nulls
                && examScore.equals(((Score) other).examScore)); // state check
    }

    @Override
    public int hashCode() {
        return examScore.hashCode();
    }
}
