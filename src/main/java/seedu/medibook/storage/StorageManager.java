package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalLoginException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyUserPrefs;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

/**
 * Manages storage of MediBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MediBookStorage mediBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private MedicalNoteListStorage medicalNoteListStorage;
    private UserAccountsListStorage userAccountsListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MediBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MediBookStorage mediBookStorage, UserPrefsStorage userPrefsStorage,
                          MedicalNoteListStorage medicalNoteListStorage,
                          UserAccountsListStorage userAccountsListStorage) {
        super();
        this.mediBookStorage = mediBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.medicalNoteListStorage = medicalNoteListStorage;
        this.userAccountsListStorage = userAccountsListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ MediBook methods ==============================

    @Override
    public Path getMediBookFilePath() {
        return mediBookStorage.getMediBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMediBook> readMediBook() throws DataConversionException, IOException {
        return readMediBook(mediBookStorage.getMediBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMediBook> readMediBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mediBookStorage.readMediBook(filePath);
    }

    @Override
    public void saveMediBook(ReadOnlyMediBook mediBook) throws IOException {
        saveMediBook(mediBook, mediBookStorage.getMediBookFilePath());
    }

    @Override
    public void saveMediBook(ReadOnlyMediBook mediBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mediBookStorage.saveMediBook(mediBook, filePath);
    }

    // ============================== MedicalNoteList methods ==============================
    @Override
    public Path getMedicalNotesDirPath() {
        return medicalNoteListStorage.getMedicalNotesDirPath();
    }

    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Ic ic) throws DataConversionException, IOException {
        return readMedicalNoteList(medicalNoteListStorage.getMedicalNotesDirPath(), ic);
    }

    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath, Ic ic)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        try {
            return medicalNoteListStorage.readMedicalNoteList(filePath, ic);
        } catch (DataConversionException dce) {
            logger.warning(
                    "Medical notes data file not in the correct format. An empty medical notes list will be used.");
            return Optional.empty();
        }
    }

    @Override
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Ic ic) throws IOException {
        saveMedicalNoteList(medicalNoteList, medicalNoteListStorage.getMedicalNotesDirPath(), ic);
    }

    @Override
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath, Ic ic) throws IOException {
        logger.fine("Attempting to save to data file: " + filePath);
        medicalNoteListStorage.saveMedicalNoteList(medicalNoteList, filePath, ic);
    }

    @Override
    public void deleteMedicalNoteList(Ic ic) throws IOException {
        deleteMedicalNoteList(medicalNoteListStorage.getMedicalNotesDirPath(), ic);
    }

    @Override
    public void deleteMedicalNoteList(Path filePath, Ic ic) throws IOException {
        logger.fine("Attempting to delete data file: " + filePath);
        medicalNoteListStorage.deleteMedicalNoteList(filePath, ic);
    }

    @Override
    public void renameMedicalNoteList(Ic oldIc, Ic newIc) throws IOException {
        renameMedicalNoteList(medicalNoteListStorage.getMedicalNotesDirPath(), oldIc, newIc);
    }

    @Override
    public void renameMedicalNoteList(Path filePath, Ic oldIc, Ic newIc) throws IOException {
        logger.fine("Attempting to rename data file: " + filePath);
        medicalNoteListStorage.renameMedicalNoteList(filePath, oldIc, newIc);
    }

    @Override
    public void deleteAllMedicalNoteList() throws IOException {
        deleteAllMedicalNoteList(medicalNoteListStorage.getMedicalNotesDirPath());
    }

    @Override
    public void deleteAllMedicalNoteList(Path filePath) throws IOException {
        logger.fine("Attempting to clear all data files: " + filePath);
        medicalNoteListStorage.deleteAllMedicalNoteList(filePath);
    }

    // ============================== UserAccount methods ==============================

    @Override
    public Path getUserAccountFilepath() {
        return userAccountsListStorage.getUserAccountFilepath();
    }

    @Override
    public Optional<Account> login(String username, String password) throws DataConversionException,
            IllegalLoginException, IllegalValueException {
        return userAccountsListStorage.login(username, password);
    }

    @Override
    public void createAccount(String username, String password, String doctorName, String doctorMcr) throws
            DataConversionException, IOException, IllegalValueException {
        userAccountsListStorage.createAccount(username, password, doctorName, doctorMcr);
    }
}
