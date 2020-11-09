package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.cc.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.cc.commons.core.GuiSettings;
import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.model.UserPrefs;

public class JsonUserPrefsStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserPrefsStorageTest");
    private static final String NON_EXISTENT_FILE_STRING = "NonExistentFile.json";
    private static final String NOT_JSON_FORMAT_FILE_STRING = "notJsonFormatUserPrefs.json";
    private static final String SOME_FILE_STRING = "SomeFile.json";
    private static final String TYPICAL_USER_PREF = "TypicalUserPref.json";
    private static final String EMPTY_USER_PREF = "EmptyUserPrefs.json";
    private static final String EXTRA_USER_PREF = "ExtraValuesUserPref.json";
    private static final String COMMON_CENTS_DATA_FILEPATH = "data/CommonCents.json";
    private static final String TEMP_PREFS = "TempPrefs.json";
    private static final String ERROR_MESSAGE = "There should not be an error writing to the file.";
    private static final GuiSettings TEST_GUI = new GuiSettings(1000, 500, 300, 100);
    private static final GuiSettings FIRST_SAVE_GUI = new GuiSettings(1200, 200, 0, 2);
    private static final GuiSettings SECOND_SAVE_GUI = new GuiSettings(5, 5, 5, 5);

    @TempDir
    public Path testFolder;

    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserPrefs(null));
    }

    private Optional<UserPrefs> readUserPrefs(String userPrefsFileInTestDataFolder) throws DataConversionException {
        Path prefsFilePath = addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
        return new JsonUserPrefsStorage(prefsFilePath).readUserPrefs(prefsFilePath);
    }

    @Test
    public void readUserPrefs_missingFile_emptyResult() throws DataConversionException {
        assertFalse(readUserPrefs(NON_EXISTENT_FILE_STRING).isPresent());
    }

    @Test
    public void readUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readUserPrefs(NOT_JSON_FORMAT_FILE_STRING));
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readUserPrefs_fileInOrder_successfullyRead() throws DataConversionException {
        UserPrefs expected = getTypicalUserPrefs();
        UserPrefs actual = readUserPrefs(TYPICAL_USER_PREF).get();
        assertEquals(expected, actual);
    }

    @Test
    public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataConversionException {
        UserPrefs actual = readUserPrefs(EMPTY_USER_PREF).get();
        assertEquals(new UserPrefs(), actual);
    }

    @Test
    public void readUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataConversionException {
        UserPrefs expected = getTypicalUserPrefs();
        UserPrefs actual = readUserPrefs(EXTRA_USER_PREF).get();

        assertEquals(expected, actual);
    }

    private UserPrefs getTypicalUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(TEST_GUI);
        userPrefs.setCommonCentsFilePath(Paths.get(COMMON_CENTS_DATA_FILEPATH));
        return userPrefs;
    }

    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(null, SOME_FILE_STRING));
    }

    @Test
    public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(new UserPrefs(), null));
    }

    /**
     * Saves {@code userPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveUserPrefs(UserPrefs userPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonUserPrefsStorage(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                    .saveUserPrefs(userPrefs);
        } catch (IOException ioe) {
            throw new AssertionError(ERROR_MESSAGE, ioe);
        }
    }

    @Test
    public void saveUserPrefs_allInOrder_success() throws DataConversionException, IOException {

        UserPrefs original = new UserPrefs();
        original.setGuiSettings(FIRST_SAVE_GUI);

        Path prefsFilePath = testFolder.resolve(TEMP_PREFS);
        JsonUserPrefsStorage jsonUserPrefsStorage = new JsonUserPrefsStorage(prefsFilePath);

        //Try writing when the file doesn't exist
        jsonUserPrefsStorage.saveUserPrefs(original);
        UserPrefs readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(SECOND_SAVE_GUI);
        jsonUserPrefsStorage.saveUserPrefs(original);
        readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);
    }

}
