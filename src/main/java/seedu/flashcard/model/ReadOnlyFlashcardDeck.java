package seedu.flashcard.model;

import javafx.collections.ObservableList;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFlashcardDeck {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Flashcard> getFlashcardList();

}
