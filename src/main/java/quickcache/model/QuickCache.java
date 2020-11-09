package quickcache.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class QuickCache implements ReadOnlyQuickCache {

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

    public QuickCache() {
    }

    /**
     * Creates an QuickCache using the Flashcards in the {@code toBeCopied}
     */
    public QuickCache(ReadOnlyQuickCache toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    // list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code QuickCache} with {@code newData}.
     */
    public void resetData(ReadOnlyQuickCache newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());
    }

    // flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the QuickCache.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the QuickCache.
     * The flashcard must not already exist in the QuickCache.
     */
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the QuickCache.
     * The flashcard identity of {@code editedFlashcard} must not be the same as another existing flashcard
     * in QuickCache.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code QuickCache}.
     * {@code key} must exist in the QuickCache.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    // util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " flashcards";
    }

    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof QuickCache // instanceof handles nulls
                && flashcards.equals(((QuickCache) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
