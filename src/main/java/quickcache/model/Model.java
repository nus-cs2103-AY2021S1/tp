package quickcache.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import quickcache.commons.core.GuiSettings;
import quickcache.model.flashcard.Flashcard;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' QuickCache file path.
     */
    Path getQuickCacheFilePath();

    /**
     * Sets the user prefs' QuickCache file path.
     *
     * @param quickCacheFilePath
     */
    void setQuickCacheFilePath(Path quickCacheFilePath);

    /**
     * Returns the QuickCache
     */
    ReadOnlyQuickCache getQuickCache();

    /**
     * Replaces QuickCache data with the data in {@code quickCache}.
     *
     * @param quickCache
     */
    void setQuickCache(ReadOnlyQuickCache quickCache);

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the QuickCache.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the QuickCache.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the QuickCache.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the QuickCache.
     * The Flashcard of {@code editedFlashcard} must not be the same as another existing Flashcard in the QuickCache.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /**
     * Returns an unmodifiable view of the filtered flashcard list
     */
    ObservableList<Flashcard> getFilteredFlashcardList();

}
