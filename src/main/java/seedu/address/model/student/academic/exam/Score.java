package seedu.address.model.student.academic.exam;

import java.text.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an exam's score in an Exam.
 * Guarantees: immutable; is valid as declared in {@link #isValidExamScore(String)}
 */
public class Score {

    public static final String MESSAGE_CONSTRAINTS =
            "Scores should be in the form int/int";

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
    public static boolean isValidExamScore(String score) {
        String[] splitScore = score.split("/");

        if (splitScore.length != 2) {
            return false;
        }

        int firstInt;
        int secInt;
        try {
            firstInt = Integer.parseInt(splitScore[0]);
            secInt = Integer.parseInt(splitScore[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return firstInt <= secInt;
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
