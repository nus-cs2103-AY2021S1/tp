package seedu.forgetfulnus.model;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.forgetfulnus.commons.core.GuiSettings;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.score.Score;

/**
 * Represents the in-memory model of the glossary data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private Glossary glossary;
    private Glossary backupGlossary;
    private final UserPrefs userPrefs;
    private FilteredList<FlashCard> filteredFlashCards;

    private Predicate<FlashCard> predicate = PREDICATE_SHOW_ALL_FLASHCARDS;

    private boolean isQuizMode = false;
    private boolean isRandomQuiz = false;

    private Score quizScore;
    private ScoreList scoreList;

    /**
     * Initialises a ModelManager with the given glossary and userPrefs.
     */
    public ModelManager(ReadOnlyGlossary initialData, ReadOnlyScoreList initialScores, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(initialData, initialScores, userPrefs);

        logger.fine("Initialising with glossary: " + initialData + ", scores: "
                + initialScores + " and user prefs " + userPrefs);

        this.glossary = new Glossary(initialData);
        this.userPrefs = new UserPrefs(userPrefs);
        this.scoreList = new ScoreList(initialScores);
        filteredFlashCards = new FilteredList<>(this.glossary.getFlashCardList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGlossaryFilePath() {
        return userPrefs.getGlossaryFilePath();
    }

    @Override
    public void setGlossaryFilePath(Path glossaryFilePath) {
        requireNonNull(glossaryFilePath);
        userPrefs.setGlossaryFilePath(glossaryFilePath);
    }

    @Override
    public Path getScoreFilePath() {
        return userPrefs.getScoresFilePath();
    }

    @Override
    public void setScoreFilePath(Path scoreFilePath) {
        requireNonNull(scoreFilePath);
        userPrefs.setScoresFilePath(scoreFilePath);
    }

    //=========== Glossary ================================================================================

    @Override
    public void setGlossary(ReadOnlyGlossary glossary) {
        this.glossary.resetData(glossary);
    }

    @Override
    public ReadOnlyGlossary getGlossary() {
        return glossary;
    }

    @Override
    public boolean hasFlashCard(FlashCard flashCard) {
        requireNonNull(flashCard);
        return glossary.hasFlashCard(flashCard);
    }

    @Override
    public void deleteFlashCard(FlashCard target) {
        glossary.removeFlashCard(target);
    }

    @Override
    public void addFlashCard(FlashCard flashCard) {
        glossary.addFlashCard(flashCard);
        updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
    }

    @Override
    public void setFlashCard(FlashCard target, FlashCard editedFlashCard) {
        requireAllNonNull(target, editedFlashCard);

        glossary.setFlashCard(target, editedFlashCard);
    }

    //=========== Score List =================================================================================

    @Override
    public void setScoreList(ScoreList scoreList) {
        this.scoreList.resetData(scoreList);
    }

    @Override
    public ScoreList getScoreList() {
        return scoreList;
    }
    //=========== Filtered FlashCard List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code FlashCard} backed by the internal list of
     * {@code versionedGlossary}
     */
    @Override
    public ObservableList<FlashCard> getFilteredFlashCardList() {
        return filteredFlashCards;
    }

    @Override
    public void updateFilteredPhraseList(Predicate<FlashCard> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
        filteredFlashCards.setPredicate(predicate);
    }

    @Override
    public void updateFilteredPhraseList() {
        requireAllNonNull(predicate);
        filteredFlashCards.setPredicate(predicate);
    }

    //=========== Quiz Mode =============================================================

    @Override
    public int getQuizScore() {
        return quizScore.getScore();
    }

    @Override
    public int getQuizTotalQuestions() {
        return quizScore.getNumQuestions();
    }

    /**
     * Resets the program at the end of a quiz.
     */
    @Override
    public void resetQuiz() {
        int quizSize = filteredFlashCards.size();
        quizScore = new Score(0, quizSize, new ArrayList<>());
    }

    @Override
    public void addCardToScore(FlashCard next) {
        quizScore.addFlashcard(next);
    }

    @Override
    public void updateWithCorrectAttempt() {
        quizScore.incrementScore();
    }

    @Override
    public int getQuizModeIndex() {
        return quizScore.getIndex();
    }

    @Override
    public void saveScore() {
        scoreList.addScore(quizScore);
    }

    @Override
    public void setQuizMode(boolean isQuizMode) {
        this.isQuizMode = isQuizMode;
        if (isQuizMode) {
            resetQuiz();
        }
    }

    @Override
    public boolean isQuizMode() {
        return isQuizMode;
    }

    @Override
    public void setRandomQuizMode(boolean isRandomQuiz) {
        this.isRandomQuiz = isRandomQuiz;
        if (isRandomQuiz) {
            backupGlossary = new Glossary(glossary);
        } else {
            setGlossary(new Glossary(backupGlossary));
            updateFilteredPhraseList(PREDICATE_SHOW_ALL_FLASHCARDS);
        }
    }

    @Override
    public boolean isRandomQuizMode() {
        return isRandomQuiz;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return glossary.equals(other.glossary)
                && userPrefs.equals(other.userPrefs)
                && filteredFlashCards.equals(other.filteredFlashCards);
    }

}
