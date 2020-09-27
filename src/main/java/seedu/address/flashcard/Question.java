package seedu.address.flashcard;

/**
 * Represents a question.
 */
public interface Question {

    public static final String MESSAGE_CONSTRAINTS =
            "Questions should only contain alphanumeric characters and spaces, and it should not be blank";

    String getQuestion();

}
