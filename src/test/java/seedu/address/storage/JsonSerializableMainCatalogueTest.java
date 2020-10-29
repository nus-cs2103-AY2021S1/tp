package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.MainCatalogue;
import seedu.address.testutil.TypicalProjects;

public class JsonSerializableMainCatalogueTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMainCatalogueTest");
    private static final Path TYPICAL_PROJECTS_FILE = TEST_DATA_FOLDER.resolve("typicalProjectsMainCatalogue.json");
    private static final Path INVALID_PROJECT_FILE = TEST_DATA_FOLDER.resolve("invalidProjectMainCatalogue.json");
    private static final Path DUPLICATE_PROJECT_FILE = TEST_DATA_FOLDER.resolve("duplicateProjectMainCatalogue.json");

    /*
    Values from mainCatalogueFromFile and typicalProjectsMainCatalogue are the same, but throws error, not sure why yet
    TODO: Fix (LUCAS)
     */
    @Test
    public void toModelType_typicalProjectsFile_success() throws Exception {
        JsonSerializableMainCatalogue dataFromFile = JsonUtil.readJsonFile(TYPICAL_PROJECTS_FILE,
                JsonSerializableMainCatalogue.class).get();
        MainCatalogue mainCatalogueFromFile = dataFromFile.toModelType();
        MainCatalogue typicalProjectsMainCatalogue = TypicalProjects.getTypicalMainCatalogue();
        assertEquals(mainCatalogueFromFile, typicalProjectsMainCatalogue);
    }

    @Test
    public void toModelType_invalidProjectFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMainCatalogue dataFromFile = JsonUtil.readJsonFile(INVALID_PROJECT_FILE,
                JsonSerializableMainCatalogue.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateProjects_throwsIllegalValueException() throws Exception {
        JsonSerializableMainCatalogue dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PROJECT_FILE,
                JsonSerializableMainCatalogue.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMainCatalogue.MESSAGE_DUPLICATE_PROJECT,
                dataFromFile::toModelType);
    }

}
