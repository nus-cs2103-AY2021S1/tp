package seedu.address.flashcard;

import java.util.List;
import java.util.Optional;

/**
 * Represents a question.
 */
public interface Question {

    public static final String MESSAGE_CONSTRAINTS =
            "Questions should only contain alphanumeric characters and spaces, and it should not be blank";

    List<String> getQuestion();

    String getOnlyQuestion();

    String getFormatQuestion();

    Optional<String[]> getChoices();

}
