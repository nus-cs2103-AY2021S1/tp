package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.quiz.Question;

/**
 * Unmodifiable view of a quiz book
 */
public interface ReadOnlyQuizBook {

    /**
     * Returns an unmodifiable view of the quiz questions list.
     * This list will not contain any duplicate questions.
     */
    ObservableList<Question> getQuestionList();
}
