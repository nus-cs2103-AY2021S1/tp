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

    public OpenEndedQuestion(String question, Answer answer) {
        this.value = question;
        this.answer = answer;
    }

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
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
            return this.toString().equals(temp.toString());
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
        return this.answer.checkAnswer(answer);
    }


    @Override
    public String toString() {
        return value;
    }
}
