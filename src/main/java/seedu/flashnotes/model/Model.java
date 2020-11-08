package seedu.flashnotes.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashnotes.commons.core.GuiSettings;
import seedu.flashnotes.model.deck.Deck;
import seedu.flashnotes.model.deck.UniqueDeckList;
import seedu.flashnotes.model.flashcard.Flashcard;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Deck> PREDICATE_SHOW_ALL_DECKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' flashnotes file path.
     */
    Path getFlashNotesFilePath();

    /**
     * Sets the user prefs' flashnotes file path.
     */
    void setFlashNotesFilePath(Path flashNotesFilePath);

    /**
     * Returns the user prefs' review card limit.
     */
    long getReviewCardLimit();

    /**
     * Sets the user prefs' review card limit.
     */
    void setReviewCardLimit(long reviewCardLimit);

    /**
     * Replaces flashnotes data with the data in {@code flashNotes}.
     */
    void setFlashNotes(ReadOnlyFlashNotes flashNotes);

    /** Returns the FlashNotes */
    ReadOnlyFlashNotes getFlashNotes();

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the flashnotes.
     */
    boolean hasDeck(Deck deck);

    /**
     * Deletes the given Deck.
     * The Deck must exist in the flashnotes.
     */
    void deleteDeck(Deck target);

    /**
     * Adds the given deck.
     * {@code deck} must not already exist in the flashnotes.
     */
    void addDeck(Deck deck);

    /**
     * Replaces the given deck {@code target} with {@code editedDeck}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedDeck} must not be the same
     * as another existing deck in the flashnotes.
     */
    void setDeck(Deck target, Deck editedDeck);

    boolean getIsInDeck();

    void setIsInDeckTrue();

    void setIsInDeckFalse();

    void setCurrentDeckName(String deckName);

    String getCurrentDeckName();


    /** Returns an unmodifiable view of the filtered deck list */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Updates the filter of the filtered deck list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDeckList(Predicate<Deck> predicate);

    static String getDefaultDeckName() {
        return FlashNotes.getDefaultDeckName();
    }

    static String getReservedDeckName() {
        return FlashNotes.getReservedDeckName();
    }

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashnotes.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the flashnotes.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the flashnotes.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the flashnotes.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the flashnotes.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFlashcardsToReview();

    /** Adds flashcard to list of flashcards to be reviewed */
    public void addFlashcardToReview();

    /** Returns the modified list of flashcards to be reviewed */
    ObservableList<Flashcard> getModifiedFlashcardsToReview();

    /** Updates the flashcard being reviewed in the review mode */
    public void updateFlashcardBeingReviewed(int result);

    /** Reset flashcard being reviewed back to false */
    public void resetFlipOfFlashcardBeingReviewed();

    /** Checks whether the flashcard being reviewed is flipped */
    public boolean getIsFlashcardFlipped();

    /** Gets the flashcard being reviewed in the review mode and returns it. */
    public Flashcard getFlashcardBeingReviewed();

    /** Flip the flashcard currently being reviewed */
    public void carryOutFlipCommand();

    /** Marks flashcard being reviewed as correct or wrong */
    public void markFlashcardBeingReviewed(Flashcard flashcard, int result);

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    boolean getIsReviewMode();

    void setIsReviewModeTrue();

    void setIsReviewModeFalse();

    /**
     * Sets up the flashcards for the review session.
     */
    void setUpReviewList();

    /**
     * Update the user's review score for deck used in review.
     */
    void updateDeckPerformanceScore(Double reviewScore, String deckName);

    /** Returns an unmodifiable view of the unique deck list */
    UniqueDeckList getUniqueDeckList();
}
