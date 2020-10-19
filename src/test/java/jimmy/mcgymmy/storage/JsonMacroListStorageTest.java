package jimmy.mcgymmy.storage;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import jimmy.mcgymmy.commons.exceptions.IllegalValueException;
import jimmy.mcgymmy.commons.util.JsonUtil;
import jimmy.mcgymmy.logic.macro.Macro;
import jimmy.mcgymmy.logic.macro.MacroList;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class JsonMacroListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMacroListStorageTest");
    private static final Path VALID_MACRO_FILE = TEST_DATA_FOLDER.resolve("validMacroList.json");
    private static final Path INVALID_MACRO_FILE = TEST_DATA_FOLDER.resolve("invalidMacroList.json");

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        JsonMacroListStorage macroListStorage = new JsonMacroListStorage(Paths.get("NonExistentFile.json"));
        assertFalse(macroListStorage.readMacroList().isPresent());
    }

    @Test
    public void validMacroList_success() throws Exception {
        JsonMacroListStorage dataFromFile = JsonUtil.readJsonFile(VALID_MACRO_FILE,
                JsonMacroListStorage.class).get();
        Optional<MacroList> result = dataFromFile.readMacroList();
        assertTrue(result.isPresent());
        MacroList macroList = result.get();
        Macro first = macroList.getMacro("first");
        Macro second = macroList.getMacro("second");
        assertArrayEquals(new String[]{"a", "b"}, first.getMacroArguments());
        assertArrayEquals(new String[]{"list", "help"}, first.getRawCommands());
        assertArrayEquals(new String[]{"c", "d"}, second.getMacroArguments());
        assertArrayEquals(new String[]{"clear"}, second.getRawCommands());
    }

    @Test
    public void toModelType_invalidFoodFile_throwsIllegalValueException() throws Exception {
        JsonMacroListStorage dataFromFile = JsonUtil.readJsonFile(INVALID_MACRO_FILE,
                JsonMacroListStorage.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::readMacroList);
    }
}
