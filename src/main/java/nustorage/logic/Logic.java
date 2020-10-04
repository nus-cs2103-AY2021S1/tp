package nustorage.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.logic.commands.CommandResult;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.ReadOnlyAddressBook;
import nustorage.model.person.Person;
import nustorage.model.record.InventoryRecord;

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
     * Returns the AddressBook.
     *
     * @see nustorage.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<InventoryRecord> getFilteredInventory();

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
}
