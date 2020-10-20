package com.eva.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.commons.exceptions.DataConversionException;
import com.eva.model.ReadOnlyEvaDatabase;
import com.eva.model.ReadOnlyUserPrefs;
import com.eva.model.UserPrefs;
import com.eva.model.person.Person;
import com.eva.model.person.applicant.Applicant;
import com.eva.model.person.staff.Staff;

/**
 * Manages storage of EvaDatabase data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final EvaStorage evaStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EvaStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EvaStorage evaStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.evaStorage = evaStorage;
        this.userPrefsStorage = userPrefsStorage;
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


    // ================ EvaDatabase methods ==============================

    @Override
    public Path getPersonDatabaseFilePath() {
        return evaStorage.getPersonDatabaseFilePath();
    }

    @Override
    public Path getStaffDatabaseFilePath() {
        return evaStorage.getStaffDatabaseFilePath();
    }

    @Override
    public Path getApplicantDatabaseFilePath() {
        return evaStorage.getApplicantDatabaseFilePath();
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase() throws DataConversionException, IOException {
        return readPersonDatabase(evaStorage.getPersonDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Person>> readPersonDatabase(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return evaStorage.readPersonDatabase(filePath);
    }

    @Override
    public void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook) throws IOException {
        savePersonDatabase(addressBook, evaStorage.getPersonDatabaseFilePath());
    }

    @Override
    public void savePersonDatabase(ReadOnlyEvaDatabase<Person> addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        evaStorage.savePersonDatabase(addressBook, filePath);
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase() throws DataConversionException, IOException {
        return readStaffDatabase(evaStorage.getStaffDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Staff>> readStaffDatabase(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return evaStorage.readStaffDatabase(filePath);
    }

    @Override
    public void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> addressBook) throws IOException {
        saveStaffDatabase(addressBook, evaStorage.getStaffDatabaseFilePath());
    }

    @Override
    public void saveStaffDatabase(ReadOnlyEvaDatabase<Staff> addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        evaStorage.saveStaffDatabase(addressBook, filePath);
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Applicant>> readApplicantDatabase()
            throws DataConversionException, IOException {
        return readApplicantDatabase(evaStorage.getApplicantDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyEvaDatabase<Applicant>> readApplicantDatabase(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return evaStorage.readApplicantDatabase(filePath);
    }

    @Override
    public void saveApplicantDatabase(ReadOnlyEvaDatabase<Applicant> addressBook) throws IOException {
        saveApplicantDatabase(addressBook, evaStorage.getApplicantDatabaseFilePath());
    }

    @Override
    public void saveApplicantDatabase(ReadOnlyEvaDatabase<Applicant> addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        evaStorage.saveApplicantDatabase(addressBook, filePath);
    }
}
