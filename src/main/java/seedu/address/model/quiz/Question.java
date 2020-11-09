package seedu.address.model.quiz;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.quiz.exceptions.InvalidQuestionAnswerException;

/**
 * Represents a question in a quiz.
 */
public abstract class Question implements Comparable<Question> {
    public static final String MESSAGE_CONSTRAINTS = "Prompts can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String prompt;
    private int selectedIndex;

    /**
     * Every field must be present and not null.
     */
    public Question(String prompt) {
        requireNonNull(prompt);
        checkArgument(isValidPrompt(prompt), MESSAGE_CONSTRAINTS);
        this.prompt = prompt;
        selectedIndex = -1;
    }

    /**
     * Return copy of itself
     */
    public abstract Question copy();

    /**
     * Returns true if a given string is a valid prompt.
     */
    public static boolean isValidPrompt(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getPrompt() {
        return prompt;
    }

    /**
     * Returns the question prompt and options, represented in an appropriate String format.
     */
    public abstract String getQuestion();

    /**
     * Checks whether the response is correct.
     * @param response user response
     * @return true if the response is correct and false otherwise
     * @throws InvalidQuestionAnswerException if the response is not valid
     */
    public abstract boolean checkResponse(String response) throws InvalidQuestionAnswerException;

    /**
     * Returns true if Question is MCQ question, other wise returns false
     */
    public abstract boolean isMcq();

    /**
     * Sets selected option
     */
    public void setSelectedIndex(int index) {
        selectedIndex = index;
    }

    /**
     * Returns selectedOption
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    public abstract int compareTo(Question f2);

    /**
     * Returns true if both questions of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two questions.
     */
    public boolean isSameQuestion(Question otherQuestion) {
        if (otherQuestion == this) {
            return true;
        }
        return otherQuestion != null
                && otherQuestion.getPrompt().equals(getPrompt());
    }
}
