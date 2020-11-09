package seedu.flashcard.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.flashcard.commons.core.GuiSettings;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

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
     * Returns the user prefs' flashcard deck file path.
     */
    Path getFlashcardDeckFilePath();

    /**
     * Sets the user prefs' flashcard deck file path.
     */
    void setFlashcardDeckFilePath(Path flashcardDeckFilePath);

    /**
     * Replaces flashcard deck data with the data in {@code flashcardDeck}.
     */
    void setFlashcardDeck(ReadOnlyFlashcardDeck flashcardDeck);

    /** Returns the FlashcardDeck */
    ReadOnlyFlashcardDeck getFlashcardDeck();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashcard list.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the flashcard list.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the flashcard list.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the flashcard list.
     * The flashcard identity of {@code editedFlashcard} must not be the same as another existing flashcard
     * in the flashcard list.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Sorts the filtered flashcard list to sort by the given {@code criteria}.
     * @throws NullPointerException if {@code criteria} is null.
     */
    void sortFilteredFlashcardList(Comparator<Flashcard> comparator);
}
