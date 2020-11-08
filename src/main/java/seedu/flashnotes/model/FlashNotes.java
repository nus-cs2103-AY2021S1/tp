package seedu.flashnotes.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.UniqueDeckList;
import seedu.flashnotes.model.flashcard.Flashcard;
import seedu.flashnotes.model.flashcard.UniqueFlashcardList;
import seedu.flashnotes.model.tag.Tag;

/**
 * Wraps all data at the flashnotes level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashNotes implements ReadOnlyFlashNotes {

    private final UniqueFlashcardList flashcards;
    private final UniqueDeckList decks;
    private boolean isReviewMode;
    private boolean isInDeck;
    private String currentDeckName;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
        decks = new UniqueDeckList();
        isReviewMode = false;
        isInDeck = false;
        currentDeckName = null;
    }

    public FlashNotes() {}

    /**
     * Creates an FlashNotes using the Flashcards in the {@code toBeCopied}
     */
    public FlashNotes(ReadOnlyFlashNotes toBeCopied) {
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
     * Replaces the contents of the deck list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    public void setDecks(List<Deck> decks) {
        this.decks.setDecks(decks);
    }

    /**
     * Resets the existing data of this {@code FlashNotes} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashNotes newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());

        setDecks(newData.getDeckList());
    }

    //// flashcard-level operations

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the flashnotes.
     * The flashcard must not already exist in the flashnotes.
     */
    public void addFlashcard(Flashcard card) {
        flashcards.add(card);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashNotes}.
     * {@code key} must exist in the flashnotes.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    /**
     * Removes all flashcards with the specified tag.
     * @param tag
     */
    public void removeFlashcardByTag(Tag tag) {
        ArrayList<Flashcard> toBeRemoved = new ArrayList<>();
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getTag().equals(tag)) {
                toBeRemoved.add(flashcard);
            }
        }

        for (int i = 0; i < toBeRemoved.size(); i++) {
            removeFlashcard(toBeRemoved.get(i));
        }
    }


    //// Deck-level operations

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the flashnotes.
     */
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a deck to the flashnotes.
     * The deck must not already exist in the flashnotes.
     */
    public void addDeck(Deck deck) {
        decks.add(deck);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the flashnotes.
     * The deck identity of {@code editedDeck} must not be the same
     * as another existing deck in the flashnotes.
     */
    public void setDeck(Deck target, Deck editedDeck) {
        requireNonNull(editedDeck);
        decks.setDeck(target, editedDeck);
    }

    /**
     * Removes {@code keyDeck} from this {@code FlashNotes}.
     * {@code keyDeck} must exist in the flashnotes.
     */
    public void removeDeck(Deck keyDeck) {
        removeFlashcardByTag(new Tag(keyDeck.getDeckName()));
        decks.remove(keyDeck);
    }

    public boolean getIsInDeck() {
        return isInDeck;
    }

    public void setIsInDeckTrue() {
        isInDeck = true;
    }

    public void setIsInDeckFalse() {
        isInDeck = false;
    }

    public void setCurrentDeckName(String deckName) {
        this.currentDeckName = deckName;
    }

    public String getCurrentDeckName() {
        if (!isInDeck) {
            return null;
        }
        return currentDeckName;
    }

    public static String getDefaultDeckName() {
        return Deck.getDefaultDeckName();
    }

    public static String getReservedDeckName() {
        return Deck.getReservedDeckName();
    }

    /**
     * Update the user's review score for deck used in review.
     * @param reviewScore Integer value of user's review session score.
     * @param deckName String value of deck to update
     */
    public void updateDeckPerformanceScore(Double reviewScore, String deckName) {
        requireNonNull(reviewScore);
        // Fetch the right deck to update
        Deck currentDeck = decks.findDeck(deckName);
        // Make sure it is an existing deck
        if (currentDeck != null) {
            // Update the deck's statistics
            currentDeck.setResultStatistics(String.format("%.1f", reviewScore));
        }
    }

    /** Returns the unique deck list */
    public UniqueDeckList getUniqueDeckList() {
        return decks;
    }

    //// Review methods

    public boolean getIsReviewMode() {
        return isReviewMode;
    }

    public void setIsReviewModeTrue() {
        isReviewMode = true;
    }

    public void setIsReviewModeFalse() {
        isReviewMode = false;
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().toString() + decks.asUnmodifiableObservableList().toString();
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Deck> getDeckList() {
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashNotes // instanceof handles nulls
                && flashcards.equals(((FlashNotes) other).flashcards))
                && decks.equals(((FlashNotes) other).decks);
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
