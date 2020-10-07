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
    private final String question;

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, Choice... choices) {
        this.question = question;
        this.choices = choices;
    }

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, List<String> choices) {
        this.question = question;
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

    @Override
    public String getQuestion() {
        StringBuilder sb = new StringBuilder(question);
        return sb.toString();
        //List<String> questionArray = new ArrayList<String>();
        //questionArray.add(question);
        //questionArray.addAll(1, Arrays.stream(choices).map(Choice::toString).collect(Collectors.toList()));
        //return questionArray;
    }

    public Optional<Choice[]> getChoices() {
        return Optional.ofNullable(this.choices);
    }

    @Override
    public String getFormatQuestion() {
        StringBuilder sb = new StringBuilder(question + "\n");
        int i = 1;
        for (Choice choice : choices) {
            sb.append(i).append(". ").append(choice.toString()).append("\n");
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

    @Override
    public String toString() {
        return question;
    }
}
