package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

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

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);
}
