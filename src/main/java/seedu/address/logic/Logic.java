package seedu.address.logic;

import java.io.File;
import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

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
     * Executes profile picture transfer and returns the result.
     * @param patient The patient
     * @param profilePic The profile picture
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws IllegalValueException If an error occurs during parsing.
     */
    CommandResult runImageTransfer(Patient patient, File profilePic) throws CommandException, IllegalValueException;

    /**
     * Returns the CliniCal.
     *
     * @see seedu.address.model.Model#getCliniCal()
     */
    ReadOnlyCliniCal getCliniCal();

    /**
     * Returns an unmodifiable view of the filtered list of patients.
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Returns an unmodifiable view of the list of appointments.
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the user prefs' CliniCal application file path.
     */
    Path getCliniCalFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
