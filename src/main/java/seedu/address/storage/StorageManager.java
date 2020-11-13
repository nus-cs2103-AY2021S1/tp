package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of NUSave data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NusaveStorage nusaveStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NusaveStorage nusaveStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.nusaveStorage = nusaveStorage;
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

    // ================ Nusave methods ==============================

    @Override
    public Path getNusaveFilePath() {
        return nusaveStorage.getNusaveFilePath();
    }

    @Override
    public Optional<ReadOnlyNusave> readNusave() throws DataConversionException, IOException {
        return readNusave(nusaveStorage.getNusaveFilePath());
    }

    @Override
    public Optional<ReadOnlyNusave> readNusave(Path filePath) throws DataConversionException, IOException {
        return nusaveStorage.readNusave(filePath);
    }

    @Override
    public void saveNusave(ReadOnlyNusave nusave) throws IOException {
        saveNusave(nusave, nusaveStorage.getNusaveFilePath());
    }

    @Override
    public void saveNusave(ReadOnlyNusave nusave, Path filePath) throws IOException {
        nusaveStorage.saveNusave(nusave, filePath);
    }
}
