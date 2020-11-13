package seedu.address.ui.statebinder;

import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonNusaveStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.ui.InfoBox;

public class InfoBoxStateBinderTest {

    @TempDir
    public Path temporaryFolder;

    private Logic logic;
    private InfoBox infoBox;

    @BeforeEach
    void setup() {
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveStorage(temporaryFolder.resolve("nusave.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);
        ModelManager modelManager = new ModelManager(getTypicalNusave(), new UserPrefs());
        Logic logic = new LogicManager(modelManager, storage);
        infoBox = new InfoBox();
    }
}
