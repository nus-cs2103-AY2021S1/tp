package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyPersonBook;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;

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
     * Returns the PersonBook.
     *
     * @see seedu.address.model.Model#getPersonBook()
     */
    ReadOnlyPersonBook getPersonBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of bookings */
    ObservableList<Booking> getFilteredBookingList();

    /* Returns an unmodifiable view of full list of bookings */
    ObservableList<Booking> getBookingList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getPersonBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
