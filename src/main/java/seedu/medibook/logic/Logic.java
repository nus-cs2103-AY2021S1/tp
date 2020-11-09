package seedu.medibook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.logic.commands.CommandResult;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.patient.Patient;

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
     * Returns the MediBook.
     *
     * @see seedu.medibook.model.Model#getMediBook()
     */
    ReadOnlyMediBook getMediBook();

    /** Returns an unmodifiable view of the filtered list of patients */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Returns the user prefs' medi book file path.
     */
    Path getMediBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the current patient accessed by medi book.
     */
    Optional<Patient> getPatientToAccess();

    /**
     * Checks if the input username and password match any of the accounts saved.
     */
    void processLoginInfo(String username, String password) throws DataConversionException, IllegalLoginException,
            IllegalValueException;

    /**
     * Creates a new account with declared username, password, doctor's name and doctor's mcr.
     */
    void createAccount(String username, String password, String doctorName, String doctorMcr) throws
            IllegalValueException, DataConversionException, IOException;

    /**
     * Removes the active user in the model.
     */
    void logout();
}
