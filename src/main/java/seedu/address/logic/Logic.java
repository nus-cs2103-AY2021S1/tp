package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.patient.Patient;
import seedu.address.model.room.Room;
import seedu.address.model.room.RoomTaskAssociation;

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
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns Covigent.
     *
     * @see seedu.address.model.Model#getPatientRecords()
     */
    ReadOnlyList<Patient> getCovigentApp();

    /** Returns an unmodifiable view of the filtered list of patients. */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the list of rooms. */
    ObservableList<Room> getRoomList();

    /** Returns an unmodifiable view of the filtered list of rooms. */
    ObservableList<Room> getFilteredRoomList();

    /** Returns an unmodifiable view of the filtered list of room-task associations. */
    ObservableList<RoomTaskAssociation> getFilteredRoomTaskRecords();

    /**
     * Returns the user prefs' Covigent file path.
     */
    Path getCovigentAppFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

}
