package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.statistics.StatisticsManager;
import seedu.address.model.ReadOnlyWordBank;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.entry.Entry;
import seedu.address.model.play.Leitner;
import seedu.address.model.view.View;

/**
 * API of the Logic component
 */
public interface Logic {

    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Switches logic manager to play mode by modifying the PlayMode object and returning a Play
     * Command.
     *
     * @return a Play Command.
     * @throws CommandException If there is no deck currently selected or if the deck is empty.
     * @throws ParseException   If an error occurs during parsing.
     */
    Command initialisePlayMode() throws CommandException, ParseException;

    /**
     * Creates a Answer Command or a Stop Command depending on the input. If a Stop Command is
     * created, isPlayMode is set to false
     *
     * @return a Answer Command or a Stop Command.
     * @throws CommandException If LogicManager if already in play mode.
     * @throws ParseException   If an error occurs during parsing.
     */
    Command createAnswerOrStopCommands(String commandText) throws CommandException, ParseException;

    /**
     * Returns the WordBank.
     *
     * @see seedu.address.model.Model#getWordBank()
     */
    ReadOnlyWordBank getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of entries
     */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Returns an unmodifiable view of the filtered list of entries
     */
    ObservableList<Entry> getFilteredEntryList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Cleanup tasks that run right before app closes.
     */
    void doCleanup();

    /**
     * Returns the current view of the system to the UI
     */
    View getCurrentView();

    /**
     * Return the current shuffled quiz questions (Leitner)
     */
    Leitner getLeitner();

    /**
     * Returns the statisticsManager object
     */
    StatisticsManager getStatisticsManager();

    /**
     * Returns the current questions that the user is at
     */
    int getCurrentIndex();

    /**
     * Returns the current statistics deck id being displayed
     */
    int getStatisticsDeckId();

    /**
     * Returns the score of the most recent quiz taken by the user
     */
    double getLastScore();

}
