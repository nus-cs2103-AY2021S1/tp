package seedu.address.ui.statebinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonNusaveStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class StateBinderListTest {

    @TempDir
    public Path temporaryFolder;

    private Logic logic;
    private StateBinderList stateBinderList;

    @BeforeEach
    void setup() {
        JsonNusaveStorage nusaveStorage =
                new JsonNusaveStorage(temporaryFolder.resolve("nusave.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nusaveStorage, userPrefsStorage);
        ModelManager modelManager = new ModelManager(getTypicalNusave(), new UserPrefs());
        Logic logic = new LogicManager(modelManager, storage);
    }

    @Test
    void constructor_test() {
        StateBinder first = new InfoBoxStateBinder();
        StateBinder second = new TitleStateBinder();

        List<StateBinder> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);

        assertEquals(expected, new StateBinderList(first, second).getStateBinderAsList());
    }


}
