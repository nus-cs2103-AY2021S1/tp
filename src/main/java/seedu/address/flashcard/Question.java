package seedu.address.flashcard;

import java.util.Optional;

/**
 * Represents a question.
 */
public interface Question {

    String MESSAGE_CONSTRAINTS =
            "Questions should only contain alphanumeric characters and spaces, and it should not be blank";

    String getValue();

    String getFormatQuestion();

    Optional<Choice[]> getChoices();

}
