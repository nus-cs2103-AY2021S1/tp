package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a question a Student has for a tutor in Reeve.
 */
public class Question {

    public static final String MESSAGE_CONSTRAINTS =
            "Questions should at least contain a non-whitespace character";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String question;
    public final boolean isResolved;

    /**
     * Constructs a new Question object. This is the default constructor for adding
     * a new unresolved Question to a student.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
        this.isResolved = false;
    }

    /**
     * Constructs a new Question object.
     */
    public Question(String question, boolean isResolved) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
        this.isResolved = isResolved;
    }

    /**
     * Returns true if the given String is valid.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both questions are the same, regardless of resolution status.
     * This is a weaker notion of equality between questions.
     */
    public boolean isSameQuestion(Question other) {
        return question.equals(other.question);
    }

    /**
     * Returns true if both questions have similar details and resolution status.
     * This is a stronger notion of equality between questions.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // short circuit if same object
            return true;
        }

        if (!(obj instanceof Question)) { // instanceof handles nulls
            return false;
        }

        // state check
        Question other = (Question) obj;
        return question.equals(other.question) && (isResolved == other.isResolved);
    }

    @Override
    public String toString() {
        String status = isResolved ? "(\u2713)" : "(\u2718)";
        return String.format("[%1$s %2$s]", status, question);
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

}
