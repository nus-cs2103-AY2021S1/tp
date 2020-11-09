package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyPersonAddressBook;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;

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
     * Returns the PersonAddressBook.
     *
     * @see seedu.address.model.Model#getPersonAddressBook()
     */
    ReadOnlyPersonAddressBook getPersonAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Job> getFilteredJobList();

    /** Returns an unmodifiable view of the sorted list of persons */
    ObservableList<Person> getSortedPersonList();

    /** Returns an unmodifiable view of the sorted list of jobs */
    ObservableList<Job> getSortedJobList();

    /** Returns the person to be displayed on the right panel */
    Person getDisplayedPerson();

    /** Returns the job to be displayed on the right panel */
    Job getDisplayedJob();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPersonAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
