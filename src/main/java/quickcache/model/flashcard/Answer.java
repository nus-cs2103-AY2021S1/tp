package quickcache.model.flashcard;

import static java.util.Objects.requireNonNull;

import quickcache.commons.util.AppUtil;

/**
 * Represents an Answer in a flashcard.
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS = "Answer can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Instantiates an Answer.
     *
     * @param answer to be set.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        AppUtil.checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        this.value = answer;
    }

    /**
     * Returns if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks the given userAnswer with the correct answer.
     *
     * @param userAnswer the user's answer.
     * @return true if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswer(Answer userAnswer) {
        return this.equals(userAnswer);
    }

    /**
     * Checks the given userAnswer with the correct answer.
     * This is done by strictly comparing lower case string equality.
     *
     * @param userAnswer the user's answer.
     * @return true if the user's answer is equal to the actual answer.
     */
    public boolean checkAnswerIgnoreCase(Answer userAnswer) {
        return this.value.toLowerCase().equals(userAnswer.getValue().toLowerCase());
    }

    /**
     * Gets the correct value of the answer .
     *
     * @return the correct value of the answer .
     */
    public String getValue() {
        return value;
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
        return value;
    }
}
