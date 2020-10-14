package seedu.flashcard.model;

import javafx.collections.ObservableList;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an flashcard deck.
 */
public interface ReadOnlyFlashcardDeck {

    /**
     * Returns an unmodifiable view of the flashcard list.
     * This list will not contain any duplicate flashcards.
     */
    ObservableList<Flashcard> getFlashcardList();

}
