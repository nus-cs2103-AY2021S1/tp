package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyTbmManager;
import seedu.address.model.client.Client;
import seedu.address.model.note.CountryNote;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Sets the boolean that corresponds to whether the country notes list panel is currently visible.
     */
    void setCountryNotesListPanelIsVisible(boolean isVisible);

    /**
     * Returns the TbmManager.
     *
     * @see seedu.address.model.Model#getTbmManager()
     */
    ReadOnlyTbmManager getTbmManager();

    /**
     * Returns the client of the widget view box.
     *
     * @return Client.
     */
    Client getWidgetClient();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /** Returns an unmodifiable view of the filtered list of country notes */
    ObservableList<CountryNote> getSortedFilteredCountryNoteList();

    /**
     * Returns the user prefs' TbmManager file path.
     */
    Path getTbmManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
