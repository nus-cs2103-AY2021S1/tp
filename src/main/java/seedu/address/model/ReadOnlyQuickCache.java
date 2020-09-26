package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.flashcard.Flashcard;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyQuickCache {

    ObservableList<Flashcard> getFlashcardList();

}
