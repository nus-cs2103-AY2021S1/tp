package seedu.address.flashcard;

import seedu.address.commons.core.index.Index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class MultipleChoiceQuestion implements Question {

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final String MESSAGE_CONSTRAINTS = "MultipleChoiceQuestion can take any values, "
            + "and it should not be blank";

    private final String[] options;
    private final String question;

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, String... options) {
        this.question = question;
        this.options = options;
    }

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(List<String> question) {
        int size = question.size();
        this.question = question.get(0);
        this.options = question.subList(1, size).toArray(new String[0]);
    }

    @Override
    public List<String> getQuestion() {
        List<String> questionArray = new ArrayList<String>();
        questionArray.add(question);
        questionArray.addAll(1, Arrays.asList(options));
        return questionArray;
    }

    @Override
    public String getOnlyQuestion() {
        StringBuilder sb = new StringBuilder(question);
        return sb.toString();
    }

    public Optional<String[]> getChoices() {
        return Optional.ofNullable(this.options);
    }

    @Override
    public String getFormatQuestion() {
        StringBuilder sb = new StringBuilder(question + "\n");
        int i = 1;
        for (String option : options) {
            sb.append(i).append(". ").append(option).append("\n");
            i++;
        }
        return sb.toString();
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
        String option = options[index.getZeroBased()];
        return new Answer(option);
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
        return question;
    }
}
