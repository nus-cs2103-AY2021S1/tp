package seedu.forgetfulnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.UserPrefs;
import seedu.forgetfulnus.storage.interfaces.ObjectStorage;

/**
 * Manages storage of Glossary data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ObjectStorage<ReadOnlyGlossary> glossaryStorage;
    private ObjectStorage<ReadOnlyScoreList> scoreStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ObjectStorage<ReadOnlyGlossary>},
     * {@code ObjectStorage<ReadOnlyScoreList>} and {@code UserPrefStorage}.
     */
    public StorageManager(ObjectStorage<ReadOnlyGlossary> glossaryStorage,
                          ObjectStorage<ReadOnlyScoreList> scoreStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.glossaryStorage = glossaryStorage;
        this.scoreStorage = scoreStorage;
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


    // ================ Glossary methods ==============================

    @Override
    public Path getGlossaryFilePath() {
        return glossaryStorage.getFilePath();
    }

    @Override
    public Optional<ReadOnlyGlossary> readGlossary() throws DataConversionException, IOException {
        return readGlossary(glossaryStorage.getFilePath());
    }

    @Override
    public Optional<ReadOnlyGlossary> readGlossary(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return glossaryStorage.readFile(filePath);
    }

    @Override
    public void saveGlossary(ReadOnlyGlossary glossary) throws IOException {
        saveGlossary(glossary, glossaryStorage.getFilePath());
    }

    @Override
    public void saveGlossary(ReadOnlyGlossary glossary, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        glossaryStorage.saveFile(glossary, filePath);
    }

    // ================ Score methods ==============================

    @Override
    public Path getScoreFilePath() {
        return scoreStorage.getFilePath();
    }

    @Override
    public Optional<ReadOnlyScoreList> readScores() throws DataConversionException, IOException {
        return readScores(scoreStorage.getFilePath());
    }

    @Override
    public Optional<ReadOnlyScoreList> readScores(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scoreStorage.readFile(filePath);
    }

    @Override
    public void saveScores(ReadOnlyScoreList scoreList) throws IOException {
        saveScores(scoreList, scoreStorage.getFilePath());
    }

    @Override
    public void saveScores(ReadOnlyScoreList scoreList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scoreStorage.saveFile(scoreList, filePath);
    }
}
