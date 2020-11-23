package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ConcurrentModificationException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.deck.entry.UniqueEntryList;
import seedu.address.model.play.Leitner;
import seedu.address.model.play.Score;
import seedu.address.model.play.scoring.BinaryScoring;
import seedu.address.model.play.scoring.QuizAttempt;
import seedu.address.model.view.CurrentView;
import seedu.address.model.view.View;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final WordBank wordBank;
    private final UserPrefs userPrefs;
    private CurrentView currentView;
    private final FilteredList<Deck> filteredDecks;
    private Optional<Index> currentDeckIndex;
    private Deck observedDeck;
    private Leitner leitner;
    private int quizLength = 2;
    private int currentIndex = 0;
    private int statisticsDeckIndex = -1; // sentinel value of -1 to indicate we show stats of all decks
    private QuizAttempt currentQuizAttempt;


    /**
     * Initializes a ModelManager with the given wordBank and userPrefs.
     */
    public ModelManager(ReadOnlyWordBank addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine(
            "Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.wordBank = new WordBank(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        /*filteredEntries = new FilteredList<>(this.wordBank.getEntryList());*/
        filteredDecks = new FilteredList<>(this.wordBank.getDeckList());
        currentDeckIndex = Optional.empty();
        this.currentView = new CurrentView(View.START_VIEW);
    }

    public ModelManager() {
        this(new WordBank(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }
    //=========== Current View =============================================================================

    @Override
    public void setCurrentView(View view) {
        this.currentView.setView(view);
    }

    @Override
    public int setStatisticsDeckId(int deckIndex) {
        if (deckIndex < 0 || deckIndex >= filteredDecks.size()) {
            deckIndex = -1;
        }
        return (this.statisticsDeckIndex = deckIndex);
    }

    @Override
    public int getStatisticsDeckId() {
        return this.statisticsDeckIndex;
    }

    @Override
    public View getCurrentView() {
        return this.currentView.getView();
    }
    //=========== Word Bank ================================================================================

    @Override
    public void setWordBank(ReadOnlyWordBank wordBank) {
        this.wordBank.resetData(wordBank);
        this.currentDeckIndex = Optional.empty();
    }

    @Override
    public ReadOnlyWordBank getWordBank() {
        return wordBank;
    }

    @Override
    public boolean hasEntry(Entry entry) {
        requireNonNull(entry);
        Deck currentDeck = getCurrentDeck();
        return currentDeck.hasEntry(entry);
    }

    @Override
    public void deleteEntry(Entry target) {
        requireNonNull(target);
        Deck currentDeck = getCurrentDeck();
        currentDeck.removeEntry(target);
        wordBank.getObservedEntries().remove(target);
    }

    /**
     * This function takes the entry and adds it to the deck entry list as well as the
     * observedEntries in the WordBank
     *
     * @param entry refers to the entry inputted by the user
     */

    @Override
    public void addEntry(Entry entry) {
        requireNonNull(entry);
        Deck currentDeck = getCurrentDeck();
        currentDeck.addEntry(entry);
        wordBank.getObservedEntries().add(entry);
        updateFilteredEntryList(PREDICATE_SHOW_ALL_ENTRIES);
    }

    @Override
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);
        Deck currentDeck = getCurrentDeck();
        currentDeck.setEntry(target, editedEntry);
    }

    @Override
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return wordBank.hasDeck(deck);
    }

    @Override
    public void removeDeck(Deck target) {
        if (target == getCurrentDeck()) {
            currentDeckIndex = Optional.empty();
            clearEntryList();
            setCurrentView(View.START_VIEW);
        }
        wordBank.removeDeck(target);
        this.currentDeckIndex = Optional.empty();
    }


    @Override
    public void addDeck(Deck deck) {
        wordBank.addDeck(deck);
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
    }

    @Override
    public void selectDeck(Index index) {
        currentDeckIndex = Optional.of(index);
    }

    /**
     * replaceEntryList deletes what is on the GUI and replaces it with the next entries in the
     * selected deck. To replace the observedEntry in Addressbook.java that controls the GUI, a copy
     * of it has to be created first. This avoids the concurrent modification exception.
     */
    @Override
    public void replaceEntryList() throws ConcurrentModificationException {
        try {
            UniqueEntryList newEntryList = getCurrentDeck().getEntries();
            wordBank.resetEntryList();
            wordBank.replaceEntryList(newEntryList);
        } catch (ConcurrentModificationException e) {
            throw e;
        }
    }

    @Override
    public void clearEntryList() {
        wordBank.resetEntryList();
    }


    @Override
    public Deck getCurrentDeck() {
        if (currentDeckIndex.equals(Optional.empty())) {
            logger.info("Current deck index is 0");
            return null;
        }
        assert (getFilteredDeckList().size() > 0);
        return filteredDecks.get(currentDeckIndex.get().getZeroBased());
    }

    //=========== Filtered Entry List Accessors =============================================================

    /**
     * Returns a default deck as memory is not fixed yet. During initialisation, the
     * observedEntryList value is passed as the WordBank.javas uniqueEntryList. I.e the GUI now
     * watches for any changes in the WordBank,java field observedEntries {@code
     * versionedAddressBook}
     */
    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        if (this.getCurrentDeck() == null) {
            logger.info("Current Deck is null");
            return wordBank.getFilteredEntries();
        }
        return wordBank.getFilteredEntries();
    }

    @Override
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        Deck currentDeck = getCurrentDeck();
        currentDeck.updateFilteredEntryList(predicate);
        wordBank.resetEntryList();
        wordBank.replaceEntryList(currentDeck.getEntries());
    }

    /**
     * Returns an unmodifiable view of the list of {@code Deck} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return filteredDecks;
    }

    @Override
    public void updateFilteredDeckList(Predicate<Deck> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
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

        //state check
        ModelManager other = (ModelManager) obj;
        return wordBank.equals(other.wordBank)
            && userPrefs.equals(other.userPrefs)
            && filteredDecks.equals(other.filteredDecks);
    }

    //====Games=====
    @Override
    public void newGame() {
        leitner = new Leitner(getCurrentDeck());
        quizLength = leitner.getEntries().size();
        currentIndex = 0;
        wordBank.resetEntryList();
        wordBank.replaceEntryListLeitner(leitner.getUniqueEntryList());

        currentQuizAttempt = new QuizAttempt(new BinaryScoring());
    }

    @Override
    public Score endGame() {
        replaceEntryList();
        this.currentView.setView(View.SCORE_VIEW);
        currentQuizAttempt.endQuiz(quizLength);

        if (checkScoreTwo()) {
            // update deck's attempt list iff end game due to last question
            getCurrentDeck().addQuizAttempt(currentQuizAttempt);
        }

        return currentQuizAttempt.getScore();
    }

    @Override
    public void playGame(String guess) { // answer a question
        leitner.addGuess(guess);
        String correctAnswer = leitner.getAnswers().get(currentIndex).toString();
        Entry entryToAdd = leitner.getEntries().get(currentIndex);
        Entry entryToRemove = wordBank.getObservedEntries().get(currentIndex);

        logger.info(String.format("You have answered %s.", guess));

        if (currentIndex == quizLength) {
            replaceEntryList();
        } else if (correctAnswer.equals(guess)) {
            currentQuizAttempt.answerQuestion(correctAnswer, guess);
            logger.info(String
                .format("Answer given was %s, the correct answer was %s, Correct answer given",
                    guess, correctAnswer));
        } else {
            currentQuizAttempt.answerQuestion(correctAnswer, guess);
            logger.info(
                String.format("Answer given was %s, the correct answer was %s, Wrong answer given",
                    guess, correctAnswer));
        }

        wordBank.setEntry(entryToRemove, entryToAdd); //swaps entry in GUI
        currentIndex++;
    }

    @Override
    public Leitner getLeitner() {
        return this.leitner;
    }

    @Override
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    @Override
    public boolean checkScore() {
        return currentIndex == quizLength - 1;
    }

    @Override
    public boolean checkScoreTwo() {
        return currentIndex == quizLength;
    }

    @Override
    public QuizAttempt getQuizAttempt() {
        return currentQuizAttempt;
    }
}
