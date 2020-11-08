package seedu.flashcard.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the flashcard-deck level
 * Duplicates are not allowed (by .isSameQuestion comparison)
 */
public class FlashcardDeck implements ReadOnlyFlashcardDeck {

    private final UniqueFlashcardList flashcards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
    }

    public FlashcardDeck() {}

    /**
     * Creates an FlashcardDeck using the Flashcards in the {@code toBeCopied}
     */
    public FlashcardDeck(ReadOnlyFlashcardDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code FlashcardDeck} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashcardDeck newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashcard deck.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the flashcard deck.
     * The flashcard must not already exist in the flashcard deck.
     */
    public void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashcard deck.
     * The flashcard identity of {@code editedFlashcard} must not be the same as another existing flashcard
     * in the flashcard deck.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashcardDeck}.
     * {@code key} must exist in the flashcard deck.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " flashcards";
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardDeck // instanceof handles nulls
                && flashcards.equals(((FlashcardDeck) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
