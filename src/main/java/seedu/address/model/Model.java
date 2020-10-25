package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
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
     * Returns the user prefs' CliniCal application file path.
     */
    Path getCliniCalFilePath();

    /**
     * Sets the user prefs' CliniCal application file path.
     */
    void setCliniCalFilePath(Path cliniCalFilePath);

    /**
     * Replaces CliniCal application data with the data in {@code cliniCal}.
     */
    void setCliniCal(ReadOnlyCliniCal cliniCal);

    /** Returns the CliniCal */
    ReadOnlyCliniCal getCliniCal();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the CliniCal application.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the CliniCal application.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the CliniCal application.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the CliniCal application.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient
     * in the CliniCal application.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Returns an unmodifiable view of the filtered patient list.
     */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the CliniCal application.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the CliniCal application.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the CliniCal application.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given patient {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the CliniCal application.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing appointment
     * in the CliniCal application.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Returns an unmodifable view of the appointment list.
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null/
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Saves the current CliniCal application state in its history.
     */
    void commitCliniCal(String command);


    /**
     * Checks if there are any valid commands to undo.
     */
    boolean canUndoCliniCal();

    /**
     * Restores the previous CliniCal application state from its history.
     */
    void undoCliniCal();

    /**
     * Returns the command that was undone successfully.
     */
    String getUndoCommand();

    /**
     * Returns the command that was redone successfully.
     */
    String getRedoCommand();

    /**
     * Checks if there are any valid commands to redo.
     */
    boolean canRedoCliniCal();

    /**
     *  Restores a previously undone CliniCal application state from its history.
     */
    void redoCliniCal();
}
