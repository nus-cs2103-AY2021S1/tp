package seedu.bagel.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.SetOfFlashcardSets;
import seedu.bagel.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the top level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class Bagel implements ReadOnlyBagel {

    private final UniqueFlashcardList flashcards;
    private SetOfFlashcardSets setOfFlashcardSets;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
        setOfFlashcardSets = new SetOfFlashcardSets();
    }

    public Bagel() {
    }

    /**
     * Creates a Bagel using the Flashcards in the {@code toBeCopied}
     */
    public Bagel(ReadOnlyBagel toBeCopied) {
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
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcardSets(SetOfFlashcardSets setOfFlashcardSets) {
        this.setOfFlashcardSets = setOfFlashcardSets;
        //TODO: to refine later (private final setOfFlashcardSets)
    }

    /**
     * Resets the existing data of this {@code Bagel} with {@code newData}.
     */
    public void resetData(ReadOnlyBagel newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());
        setFlashcardSets(newData.getSetOfFlashcardSets());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same information as {@code flashcard} exists in the list of flashcards.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the list of flashcards.
     * The flashcard must not already exist in the list of flashcards.
     */
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
        setOfFlashcardSets.add(f);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the list of flashcards.
     * The information of {@code editedFlashcard} must not be the same as another existing flashcard in the
     * list of flashcards.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
        setOfFlashcardSets.edit(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code list of flashcards}.
     * {@code key} must exist in the list of flashcards.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
        setOfFlashcardSets.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " flashcards";
        // TODO: refine later
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the set of flashcardSets.
     */
    @Override
    public SetOfFlashcardSets getSetOfFlashcardSets() {
        return setOfFlashcardSets;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Bagel // instanceof handles nulls
                && flashcards.equals(((Bagel) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
