package quickcache.model.flashcard;

import java.util.Optional;

/**
 * Represents a question.
 */
public interface Question {

    public static final String VALIDATION_REGEX = "[^\\s].*";

    String MESSAGE_CONSTRAINTS =
            "Questions should only contain alphanumeric characters and spaces, and it should not be blank";


    String getValue();

    String getFormatQuestion();

    Optional<Choice[]> getChoices();

    Answer getAnswer();

    Answer getAnswerOrIndex();

    boolean checkAnswer(Answer answer);

    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Question copyQuestion(String question, Answer answer);
}
