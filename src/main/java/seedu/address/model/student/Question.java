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

    /**
     * Constructs a new Question object.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
    }

    /**
     * Returns true if the given String is valid.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("[%s]", question);
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) // short circuit if same object
                || (obj instanceof Question) // instanceof handles nulls
                && question.equals(((Question) obj).question); // state check
    }
}
