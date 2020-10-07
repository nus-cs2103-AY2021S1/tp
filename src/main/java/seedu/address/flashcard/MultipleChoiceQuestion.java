package seedu.address.flashcard;

import java.util.Arrays;
import java.util.Optional;

import seedu.address.commons.core.index.Index;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class MultipleChoiceQuestion implements Question {

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "MultipleChoiceQuestion can take any values, "
            + "and it should not be blank";

    private final Choice[] choices;
    private final String question;

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, Choice... choices) {
        this.question = question;
        this.choices = choices;
    }

    @Override
    public String getQuestion() {
        StringBuilder sb = new StringBuilder(question + "\n");
        int i = 1;
        for (Choice choice : choices) {
            sb.append(i).append(". ").append(choice.getContent()).append("\n");
            i++;
        }
        return sb.toString();
    }

    public String getOnlyQuestion() {
        StringBuilder sb = new StringBuilder(question);
        return sb.toString();
    }

    public Optional<Choice[]> getChoices() {
        return Optional.ofNullable(this.choices);
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Retrieves the option from {@code index} as an Answer.
     * @param index to get option from.
     * @return option as an {@code Answer}.
     */
    public Answer getAnswerFromIndex(Index index) {
        Choice choice = choices[index.getZeroBased()];
        return new Answer(choice.getContent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion temp = (MultipleChoiceQuestion) o;
            return this.toString().equals(temp.toString())
                    && Arrays.equals(this.getChoices().get(), temp.getChoices().get());
        }
        return false;
    }

    public String toString() {
        return question;
    }
}
