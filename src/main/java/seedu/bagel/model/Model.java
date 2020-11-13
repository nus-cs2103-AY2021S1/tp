package seedu.bagel.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.bagel.commons.core.GuiSettings;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.FlashcardSet;

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
     * Returns the user prefs' Bagel file path.
     */
    Path getBagelFilePath();

    /**
     * Sets the user prefs' Bagel file path.
     */
    void setBagelFilePath(Path bagelFilePath);

    /**
     * Replaces Bagel data with the data in {@code bagel}.
     */
    void setBagel(ReadOnlyBagel bagel);

    /** Returns the Bagel */
    ReadOnlyBagel getBagel();

    /**
     * Returns true if a flashcard with the same information as {@code flashcard} exists in the Bagel.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the Bagel.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the Bagel.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the list of flashcards.
     * The flashcard information of {@code editedFlashcard} must not be the same as another existing flashcard
     * in the Bagel.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /**
     * Displays the required flashcard by updating the filter flashcard list.
     */
    void viewFlashcard(Predicate<Flashcard> predicate);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Sorts the flashcard list with the given comparator.
     */
    void sortFlashcardList(Comparator<Flashcard> comparator);

    boolean isEmpty();

    /**
     * Returns true if a flashcard set with the same information as {@code flashcardSet} exists in the Bagel.
     */
    boolean hasFlashcardSet(FlashcardSet flashcardSet);

}
