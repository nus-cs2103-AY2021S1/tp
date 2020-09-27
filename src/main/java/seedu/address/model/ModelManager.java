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
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CliniCal cliniCal;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;

    /**
     * Initializes a ModelManager with the given cliniCal and userPrefs.
     */
    public ModelManager(ReadOnlyCliniCal cliniCal, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(cliniCal, userPrefs);

        logger.fine("Initializing with address book: " + cliniCal + " and user prefs " + userPrefs);

        this.cliniCal = new CliniCal(cliniCal);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.cliniCal.getPatientList());
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
                && filteredPatients.equals(other.filteredPatients);
    }

}
