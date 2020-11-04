package seedu.fma.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fma.logic.Logic;
import seedu.fma.logic.LogicManager;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.storage.JsonLogBookStorage;
import seedu.fma.storage.JsonUserPrefsStorage;
import seedu.fma.storage.StorageManager;

class ResultDisplayTest {

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonLogBookStorage logbookStorage =
                new JsonLogBookStorage(temporaryFolder.resolve("logbook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(logbookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    void returnAutoCompleteResult_singleWordInput_returnsAllMatchingCommands() {
        assertEquals(ResultDisplay
                .getAutoCompleteResult("add", logic.getCommandSuggestionList()),
                "add e/<exercise> r/<reps> c/<comment> \n"
                        + "addex e/<exercise> c/<calories per rep>");
    }

    @Test
    void returnAutoCompleteResult_multiWordInput_returnsOnlyOneCommandThatMatches() {
        assertEquals(ResultDisplay
                .getAutoCompleteResult("add e/", logic.getCommandSuggestionList()),
                "add e/<exercise> r/<reps> c/<comment>");
    }

}
