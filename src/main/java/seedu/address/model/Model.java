package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.flashcard.Flashcard;

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
     * Returns the user prefs' address book file path.
     */
    Path getQuickCacheFilePath();

    /**
     * Sets the user prefs' address book file path.
     * @param quickCacheFilePath
     */
    void setQuickCacheFilePath(Path quickCacheFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     * @param quickCache
     */
    void setQuickCache(ReadOnlyQuickCache quickCache);

    /** Returns the QuickCache */
    ReadOnlyQuickCache getQuickCache();

    /**
     * Returns true if a flashcard with the same identity as {@code person} exists in the address book.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the address book.
     */
    void deleteFlashcard(Flashcard target);
    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the address book.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the address book.
     * The Flashcard of {@code editedFlashcard} must not be the same as another existing Flashcard in the address book.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Flashcard> getFilteredFlashcardList();

}
