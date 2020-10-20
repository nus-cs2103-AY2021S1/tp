package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.CliniCal;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCliniCalStorage cliniCalStorage = new JsonCliniCalStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(cliniCalStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void cliniCalReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCliniCalStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCliniCalStorageTest} class.
         */
        CliniCal original = getTypicalCliniCal();
        storageManager.saveCliniCal(original);
        ReadOnlyCliniCal retrieved = storageManager.readCliniCal().get();
        assertEquals(original, new CliniCal(retrieved));
    }

    @Test
    public void getCliniCalFilePath() {
        assertNotNull(storageManager.getCliniCalFilePath());
    }

    @Test
    public void placePlaceholderPicture() throws Exception {
        storageManager.initializePlaceholderImage();

        Path path = getTempFilePath("stock_picture.png");
        assertTrue(Files.exists(path));

        assertTrue(FileUtils.contentEquals(
                new File(this.getClass().getResource("/images/stock_picture.png").toURI()), path.toFile()));
    }

    @Test
    public void doNotOverwriteExistingPlaceholderPicture() throws Exception {
        Path path = getTempFilePath("stock_picture.png");
        Files.createFile(path);

        storageManager.initializePlaceholderImage();

        assertEquals(path.toFile().length(), 0);
    }
}
