package seedu.address.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Answer in a flashcard.
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS = "Answer can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String answer;

    /**
     * Instantiates an Answer.
     * @param answer to be set.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        this.answer = normalizeAnswer(answer);
    }

    /**
     * Checks the given userAnswer with the correct answer.
     * This is done by strictly comparing lower case string equality.
     * @param userAnswer the user's answer.
     * @return true if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswer(Answer userAnswer) {
        return this.equals(userAnswer);
    }

    /**
     * Lower cases the answer.
     * @param answer to be normalized.
     * @return lower-cased answer.
     */
    private String normalizeAnswer(String answer) {
        return answer.toLowerCase();
    }

    /**
     * Gets the correct answer.
     * @return the correct answer.
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Returns if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Answer) {
            Answer temp = (Answer) object;
            return this.toString().equals(temp.toString());
        }
        return false;
    }

    @Override
    public String toString() {
        return answer;
    }
}
