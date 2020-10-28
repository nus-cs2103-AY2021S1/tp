package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.stock.commons.core.GuiSettings;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonStockBookStorage stockBookStorage = new JsonStockBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        JsonSerialNumberSetsBookStorage serialNumberSetsBookStorage =
                new JsonSerialNumberSetsBookStorage(getTempFilePath("serial"));
        storageManager = new StorageManager(stockBookStorage, userPrefsStorage, serialNumberSetsBookStorage);
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
    public void stockBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStockBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStockBookStorageTest} class.
         */
        StockBook original = getTypicalStockBook();
        storageManager.saveStockBook(original);
        ReadOnlyStockBook retrieved = storageManager.readStockBook().get();
        assertEquals(original, new StockBook(retrieved));
    }

    @Test
    public void getStockBookFilePath() {
        assertNotNull(storageManager.getStockBookFilePath());
    }

}
