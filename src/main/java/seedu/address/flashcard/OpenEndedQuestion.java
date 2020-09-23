package seedu.address.flashcard;

/**
 * Represents an open ended question.
 */
public class OpenEndedQuestion implements Question {
    private final String question;

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "OpenEndedQeustion can take any values, "
            + "and it should not be blank";

    public OpenEndedQuestion(String question) {
        this.question = question;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getQuestion() {
        return question;
    }
}
