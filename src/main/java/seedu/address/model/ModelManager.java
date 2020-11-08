package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Represents the in-memory model of the Hospify data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HospifyBook hospifyBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;

    /**
     * Initializes a ModelManager with the given hospify and userPrefs.
     */
    public ModelManager(ReadOnlyHospifyBook hospify, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(hospify, userPrefs);

        logger.fine("Initializing with Hospify: " + hospify + " and user prefs " + userPrefs);

        this.hospifyBook = new HospifyBook(hospify);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.hospifyBook.getPatientList());
    }

    public ModelManager() {
        this(new HospifyBook(), new UserPrefs());
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
    public Path getHospifyFilePath() {
        return userPrefs.getHospifyFilePath();
    }

    @Override
    public void setHospifyFilePath(Path hospifyFilePath) {
        requireNonNull(hospifyFilePath);
        userPrefs.setHospifyFilePath(hospifyFilePath);
    }

    //=========== HospifyBook ================================================================================

    @Override
    public void setHospifyBook(ReadOnlyHospifyBook hospifyBook) {
        this.hospifyBook.resetData(hospifyBook);
    }

    @Override
    public ReadOnlyHospifyBook getHospifyBook() {
        return hospifyBook;
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return hospifyBook.hasPatient(patient);
    }

    @Override
    public boolean hasPatientWithNric(Nric nric) {
        requireNonNull(nric);
        return hospifyBook.hasPatientWithNric(nric);
    }

    @Override
    public boolean hasPatientWithMrUrl(MedicalRecord url) {
        requireNonNull(url);
        return hospifyBook.hasPatientWithMrUrl(url);
    }

    @Override
    public void deletePatient(Patient target) {
        hospifyBook.removePatient(target);
    }

    @Override
    public void addPatient(Patient patient) {
        hospifyBook.addPatient(patient);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        hospifyBook.setPatient(target, editedPatient);
    }

    @Override
    public int count() {
        return hospifyBook.count();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    /**
     * Sorts the patientList based on the predicate in ascending order.
     */
    @Override
    public void sort(Comparator<Patient> comparator) {
        hospifyBook.sort(comparator);
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
        return hospifyBook.equals(other.hospifyBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
    }

}
