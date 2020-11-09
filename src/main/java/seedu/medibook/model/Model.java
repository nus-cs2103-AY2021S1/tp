package seedu.medibook.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.model.patient.Patient;

/**
 * The API of the Model component.
 */
public interface Model extends Context {
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
     * Returns the user prefs' medi book file path.
     */
    Path getMediBookFilePath();

    /**
     * Sets the user prefs' medi book file path.
     */
    void setMediBookFilePath(Path mediBookFilePath);

    /**
     * Replaces medi book data with the data in {@code mediBook}.
     */
    void setMediBook(ReadOnlyMediBook mediBook);

    /** Returns the MediBook */
    ReadOnlyMediBook getMediBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the medi book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the medi book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the medi book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the medi book.
     * The patient identity of {@code editedPatient} must not be the same as
     * another existing patient in the medi book.
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
