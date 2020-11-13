package seedu.bagel.model;

import javafx.collections.ObservableList;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.SetOfFlashcardSets;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBagel {

    /**
     * Returns an unmodifiable view of the flashcards list.
     * This list will not contain any duplicate flashcards.
     */
    ObservableList<Flashcard> getFlashcardList();

    /**
     * Returns an unmodifiable view of the set of flashcardSets.
     */
    SetOfFlashcardSets getSetOfFlashcardSets();
}
