package seedu.forgetfulnus.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that always evaluates to true */
    Predicate<FlashCard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

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
     * Returns the user prefs' glossary file path.
     */
    Path getGlossaryFilePath();

    /**
     * Sets the user prefs' glossary file path.
     */
    void setGlossaryFilePath(Path glossaryFilePath);

    /**
     * Replaces glossary data with the data in {@code glossary}.
     */
    void setGlossary(ReadOnlyGlossary glossary);

    /** Returns the Glossary */
    ReadOnlyGlossary getGlossary();

    /**
     * Returns the user prefs' score file path.
     * @return
     */
    Path getScoreFilePath();

    /**
     * Sets the user prefs' score file path.
     */
    void setScoreFilePath(Path scoreFilePath);

    /**
     * Replaces score data with the data in {@code scoreList}.
     */
    void setScoreList(ScoreList scoreList);

    /** Returns the score list */
    ScoreList getScoreList();

    /**
     * Returns true if a flashCard with the same identity as {@code flashCard} exists in the glossary.
     */
    boolean hasFlashCard(FlashCard flashCard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the glossary.
     */
    void deleteFlashCard(FlashCard target);

    /**
     * Adds the given flashCard.
     * {@code flashCard} must not already exist in the glossary.
     */
    void addFlashCard(FlashCard flashCard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashCard}.
     * {@code target} must exist in the glossary.
     * The flashcard identity of {@code editedFlashCard} must not be the same as
     * another existing flashcard in the glossary.
     */
    void setFlashCard(FlashCard target, FlashCard editedFlashCard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<FlashCard> getFilteredFlashCardList();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPhraseList(Predicate<FlashCard> predicate);

    /**
     * Updates the filter of the filtered flashcard list to filter by the predicate in the class.
     */
    void updateFilteredPhraseList();

    /**
     * Increments index of the current flashcard in the filtered list in quiz mode.
     */
    void addCardToScore(FlashCard next);

    /**
     * Gets the index of the current flashcard in the filtered list in quiz mode.
     * @return index
     */
    int getQuizModeIndex();

    void saveScore();

    /**
     * Sets the program to quiz mode or ends the quiz mode.
     *  @param quizMode true to set to quiz mode, false to end quiz mode
     */
    void setQuizMode(boolean quizMode);

    /**
     * Returns if the program is in quiz mode.
     * @return isQuizMode
     */
    boolean isQuizMode();

    /**
     * Sets the program to random quiz mode or ends the random quiz mode. Upon entering
     * random quiz mode, current glossary is backed up and upon ending random quiz mode,
     * current glossary is restored with previously backed up glossary.
     *  @param isRandomQuiz true to set to random quiz mode, false to end random quiz mode.
     */
    void setRandomQuizMode(boolean isRandomQuiz);

    /**
     * Returns if the program is in random quiz mode.
     * @return isRandomQuiz
     */
    boolean isRandomQuizMode();

    /**
     * Increments the number of correct attempts in current quiz.
     */
    void updateWithCorrectAttempt();

    /**
     * Returns the number of correct attempts in this quiz.
     * @return quizScore
     */
    int getQuizScore();

    /**
     * Returns the total questions in this quiz.
     * @return quizTotalQuestions
     */
    int getQuizTotalQuestions();

    /**
     * Resets the program at the end of a quiz.
     */
    void resetQuiz();
}
