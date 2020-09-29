package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.flashcard.Flashcard;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyQuickCache {

    ObservableList<Flashcard> getFlashcardList();

}
