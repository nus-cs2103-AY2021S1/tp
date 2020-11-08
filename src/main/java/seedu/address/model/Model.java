package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Flashcard;
import seedu.address.model.quiz.Attempt;
import seedu.address.model.quiz.Question;
import seedu.address.model.quiz.Response;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARD = unused -> true;

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
     * Returns the user prefs' flashcard book file path.
     */
    Path getFlashcardBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setFlashcardBookFilePath(Path flashcardBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setFlashcardBook(ReadOnlyFlashcardBook flashcardBook);

    /** Returns the AddressBook */
    ReadOnlyFlashcardBook getFlashcardBook();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the address book.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the address book.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Flips the given flashcard.
     * The flashcard must exist in the address book.
     * @param target the flashcard to be flipped.
     */
    void flipFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the address book.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the address book.
     * The flashcard identity of {@code editedFlashcard} must not be the same as another existing flashcard
     * in the address book.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /** Sorts the flashcard list. */
    void sortFilteredFlashcardList(String sortOrder);


    /** Returns an boolean indicating whether the application is in quiz mode */
    boolean getIsQuizMode();

    /** Returns a boolean indicating whether a current quiz attempt is ongoing. */
    boolean hasCurrentAttempt();

    /** Starts a new quiz attempt. */
    void startAttempt();

    /** Ends a current quiz attempt. */
    boolean endAttempt();

    /** Records a new response to current attempt. */
    void recordResponse(Response response);

    /** Allows flipping the boolean isQuizMode in model */
    void flipQuizMode();

    /** Replace responseList field with the attempt's own responseList*/
    void showAttempt(Attempt attempt);

    /** Set the selectedIndex field in the target Question*/
    void setSelectedIndex(Question target, String response);

    /** Sets the selected index of all questions in quizBook */
    void setAllSelectedIndex(int index);

    /** Get QuizList */
    ObservableList<Question> getQuizList();

    /** Get AttemptList */
    ObservableList<Attempt> getAttemptList();

    /** Get ResponseList */
    ObservableList<Response> getResponseList();

    /** Saves Performance in performance book **/
    void savePerformance() throws IOException;
}
