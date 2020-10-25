package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the CliniCal application data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CliniCal cliniCal;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Appointment> filteredAppointments;
    private final VersionedCliniCal versionedCliniCal;

    /**
     * Initializes a ModelManager with the given cliniCal and userPrefs.
     */
    public ModelManager(ReadOnlyCliniCal cliniCal, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(cliniCal, userPrefs);

        logger.fine("Initializing with CliniCal application: " + cliniCal + " and user prefs " + userPrefs);

        this.cliniCal = new CliniCal(cliniCal);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.cliniCal.getPatientList());
        filteredAppointments = new FilteredList<>(this.cliniCal.getAppointmentList());
        this.versionedCliniCal = new VersionedCliniCal(this.cliniCal);
    }

    public ModelManager() {
        this(new CliniCal(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCliniCalFilePath() {
        return userPrefs.getCliniCalFilePath();
    }

    @Override
    public void setCliniCalFilePath(Path cliniCalFilePath) {
        requireNonNull(cliniCalFilePath);
        userPrefs.setCliniCalFilePath(cliniCalFilePath);
    }

    //=========== CliniCal ================================================================================

    @Override
    public void setCliniCal(ReadOnlyCliniCal cliniCal) {
        this.cliniCal.resetData(cliniCal);
    }

    @Override
    public ReadOnlyCliniCal getCliniCal() {
        return cliniCal;
    }

    //=========== Patient ==================================================================================

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return cliniCal.hasPatient(patient);
    }

    @Override
    public void deletePatient(Patient target) {
        cliniCal.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        cliniCal.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        cliniCal.setPatient(target, editedPatient);
    }

    //=========== Appointment ==============================================================================

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return cliniCal.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        cliniCal.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment toAdd) {
        cliniCal.addAppointment(toAdd);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        cliniCal.setAppointment(target, editedAppointment);
    }

    //=========== Undo/Redo ===============================================================================

    @Override
    public void commitCliniCal(String command) {
        versionedCliniCal.commit(cliniCal, command);
    }

    @Override
    public boolean canUndoCliniCal() {
        return versionedCliniCal.canUndoCliniCal();
    }

    @Override
    public void undoCliniCal() {
        versionedCliniCal.undo();
    }

    @Override
    public String getUndoCommand() {
        return versionedCliniCal.getUndoCommand();
    }

    @Override
    public boolean canRedoCliniCal() {
        return versionedCliniCal.canRedoCliniCal();
    }

    @Override
    public void redoCliniCal() {
        versionedCliniCal.redo();
    }

    @Override
    public String getRedoCommand() {
        return versionedCliniCal.getRedoCommand();
    }

    //=========== Filtered Patient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the internal list of
     * {@code versionedCliniCal}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    //=========== Filtered Appointment List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedCliniCal}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return cliniCal.equals(other.cliniCal)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients)
                && filteredAppointments.equals(other.filteredAppointments);
    }

}
