package quickcache.model.flashcard;

import java.util.Optional;

/**
 * Represents an open ended question.
 */
public class OpenEndedQuestion implements Question {

    public static final String TYPE = "OEQ";

    public static final String MESSAGE_CONSTRAINTS = "OpenEndedQuestion can take any values, "
            + "and it should not be blank";

    private final String value;
    private final Answer answer;

    /**
     * A constructor to create OpenEndedQuestion object.
     */
    public OpenEndedQuestion(String question, Answer answer) {
        this.value = question;
        this.answer = answer;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Question copyQuestion(String question, Answer answer) {
        return new OpenEndedQuestion(question, answer);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getFormatQuestion() {
        return this.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof OpenEndedQuestion) {
            OpenEndedQuestion temp = (OpenEndedQuestion) o;
            return this.toString().equals(temp.toString())
                    && this.answer.equals(temp.answer);
        }
        return false;
    }

    public Optional<Choice[]> getChoices() {
        return Optional.empty();
    }

    @Override
    public Answer getAnswer() {
        return this.answer;
    }

    @Override
    public Answer getAnswerOrIndex() {
        return this.answer;
    }

    @Override
    public boolean checkAnswer(Answer answer) {
        return this.answer.checkAnswerIgnoreCase(answer);
    }


    @Override
    public String toString() {
        return value;
    }
}
