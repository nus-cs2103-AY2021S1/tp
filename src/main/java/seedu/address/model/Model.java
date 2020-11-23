package seedu.address.model;

import java.nio.file.Path;
import java.util.ConcurrentModificationException;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.play.Leitner;
import seedu.address.model.play.Score;
import seedu.address.model.play.scoring.QuizAttempt;
import seedu.address.model.view.View;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Entry> PREDICATE_SHOW_ALL_ENTRIES = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code wordBank}.
     */
    void setWordBank(ReadOnlyWordBank wordBank);

    /**
     * Returns the WordBank
     */
    ReadOnlyWordBank getWordBank();

    /**
     * Returns true if an entry with the same identity as {@code entry} exists in the word bank.
     */
    boolean hasEntry(Entry entry);

    /**
     * Deletes the given entry. The entry must exist in the word bank.
     */
    void deleteEntry(Entry target);

    /**
     * Adds the given entry. {@code entry} must not already exist in the word bank.
     */
    void addEntry(Entry entry);

    /**
     * Replaces the given entry {@code target} with {@code editedEntry}. {@code target} must exist
     * in the address book. The entry identity of {@code editedEntry} must not be the same as
     * another existing entry in the word bank.
     */
    void setEntry(Entry target, Entry editedEntry);

    /**
     * Returns an unmodifiable view of the filtered entry list
     */
    ObservableList<Entry> getFilteredEntryList();

    /**
     * Updates the filter of the filtered entry list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEntryList(Predicate<Entry> predicate);

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the word bank.
     */
    boolean hasDeck(Deck deck);

    /**
     * Removes the given deck {@code target}. The deck must exist in the word bank.
     */
    void removeDeck(Deck target);

    /**
     * Adds the given deck. {@code deck} must not already exist in the word bank.
     */
    void addDeck(Deck deck);

    /**
     * Selects the deck at the specified index
     *
     * @param index of the selected deck
     */
    void selectDeck(Index index);

    /**
     * Retrieves the deck last selected by the user
     */
    Deck getCurrentDeck();

    /**
     * Returns an unmodifiable view of the filtered deck list
     */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Updates the filter of the filtered deck list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDeckList(Predicate<Deck> predicate);

    void replaceEntryList() throws ConcurrentModificationException;

    /**
     * Deletes entries on the GUI when clear command is called.
     */
    void clearEntryList();

    //game commands
    void newGame();

    Score endGame();

    void playGame(String answer);

    boolean checkScore();

    boolean checkScoreTwo();

    //view methods

    /**
     * Changes the current view of the system to the specified view {@code view}
     *
     * @param view View to change the current view to
     */
    void setCurrentView(View view);

    /**
     * Changes the current deck showing for stats pane
     */
    int setStatisticsDeckId(int deckIndex);

    /**
     * Get the current deck showing for stats pane
     */
    int getStatisticsDeckId();

    /**
     * Gets the current view of the system
     */
    View getCurrentView();

    //UI methods

    /**
     * Returns the current leitner object to be passed to logic Should only be called when the user
     * is playing a quiz.
     */
    Leitner getLeitner();

    /**
     * Returns the current quiz question that the user is at Default value is 0
     */
    int getCurrentIndex();

    QuizAttempt getQuizAttempt();
}
