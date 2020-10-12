package seedu.address.flashcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class MultipleChoiceQuestion implements Question {

    public static final String TYPE = "MCQ";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "MultipleChoiceQuestion can take any values, "
            + "and it should not be blank";

    private final Choice[] choices;
    private final String value;

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, Choice... choices) {
        this.value = question;
        this.choices = choices;
    }

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, List<String> choices) {
        this.value = question;
        List<Choice> choicesArray = new ArrayList<Choice>();
        for (String c : choices) {
            Choice choice = new Choice(c);
            choicesArray.add(choice);
        }
        Choice[] test = choicesArray.toArray(new Choice[choices.size()]);
        for (int i = 0; i < choices.size(); i++) {
            test[i] = new Choice(choices.get(i));
        }
        this.choices = test;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getValue() {
        return value;
    }

    public Optional<Choice[]> getChoices() {
        return Optional.ofNullable(this.choices);
    }

    @Override
    public String getFormatQuestion() {
        StringBuilder sb = new StringBuilder(value + "\n");
        int i = 1;
        for (Choice choice : choices) {
            sb.append(i).append(". ").append(choice.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    /**
     * Retrieves the option from {@code index} as an Answer.
     *
     * @param index to get option from.
     * @return option as an {@code Answer}.
     */
    public Answer getAnswerFromIndex(Index index) {
        Choice choice = choices[index.getZeroBased()];
        return new Answer(choice.getValue());
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

    @Override
    public String toString() {
        return value;
    }
}
