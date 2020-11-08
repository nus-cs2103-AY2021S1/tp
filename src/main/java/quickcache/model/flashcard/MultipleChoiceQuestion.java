package quickcache.model.flashcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import quickcache.commons.core.index.Index;

/**
 * Represents a mcq question.
 * Options for the mcq will be stored in an array while the
 * question itself will be stored as a {@code String}.
 */
public class MultipleChoiceQuestion implements Question {

    public static final String TYPE = "MCQ";

    public static final String MESSAGE_CONSTRAINTS = "MultipleChoiceQuestion can take any values, "
            + "and it should not be blank";

    private final Choice[] choices;
    private final String value;
    private final Answer answer;

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, Answer answer, Choice... choices) {
        this.value = question;
        this.choices = choices;
        this.answer = answer;
    }

    /**
     * A constructor to create MCQ Question object.
     */
    public MultipleChoiceQuestion(String question, List<String> choices, Answer answer) {
        this.value = question;
        List<Choice> choicesArray = new ArrayList<Choice>();
        for (String c : choices) {
            Choice choice = new Choice(c);
            choicesArray.add(choice);
        }
        Choice[] newChoicesArray = choicesArray.toArray(new Choice[choices.size()]);
        for (int i = 0; i < choices.size(); i++) {
            newChoicesArray[i] = new Choice(choices.get(i));
        }
        this.choices = newChoicesArray;
        this.answer = answer;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Question copyQuestion(String question, Answer answer) {
        return new MultipleChoiceQuestion(question, answer, this.choices);
    }

    @Override
    public String getValue() {
        return value;
    }

    public Optional<Choice[]> getChoices() {
        return Optional.ofNullable(this.choices);
    }

    @Override
    public Answer getAnswer() {
        return this.answer;
    }

    @Override
    public Answer getAnswerOrIndex() {
        for (int i = 0; i < choices.length; i++) {
            if (this.answer.getValue().toLowerCase().equals(choices[i].getValue().toLowerCase())) {
                return new Answer(String.valueOf(i + 1));
            }
        }
        return this.answer;
    }

    @Override
    public boolean checkAnswer(Answer answer) {
        return this.answer.checkAnswer(answer);
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
        if (index.getZeroBased() >= choices.length) {
            throw new IllegalArgumentException("Index given is greater than the number of choices");
        }
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
                    && Arrays.equals(this.getChoices().get(), temp.getChoices().get())
                    && this.answer.equals(temp.answer);
        }
        return false;
    }

    @Override
    public String toString() {
        return value;
    }
}
