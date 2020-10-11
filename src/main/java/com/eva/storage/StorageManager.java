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

/**
 * Manages storage of EvaDatabase data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EvaStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EvaStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EvaStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
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
    public Path getEvaDatabaseFilePath() {
        return addressBookStorage.getEvaDatabaseFilePath();
    }

    @Override
    public Optional<ReadOnlyEvaDatabase> readEvaDatabase() throws DataConversionException, IOException {
        return readEvaDatabase(addressBookStorage.getEvaDatabaseFilePath());
    }

    @Override
    public Optional<ReadOnlyEvaDatabase> readEvaDatabase(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readEvaDatabase(filePath);
    }

    @Override
    public void saveEvaDatabase(ReadOnlyEvaDatabase addressBook) throws IOException {
        saveEvaDatabase(addressBook, addressBookStorage.getEvaDatabaseFilePath());
    }

    @Override
    public void saveEvaDatabase(ReadOnlyEvaDatabase addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveEvaDatabase(addressBook, filePath);
    }

}
