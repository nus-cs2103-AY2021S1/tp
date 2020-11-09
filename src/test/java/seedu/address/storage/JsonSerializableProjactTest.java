package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Projact;
import seedu.address.testutil.TypicalProjact;

public class JsonSerializableProjactTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableProjactTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsProjact.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonProjact.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonProjact.json");
    private static final Path INVALID_TAG_FILE = TEST_DATA_FOLDER.resolve("invalidTagProjact.json");
    private static final Path DUPLICATE_TAG_FILE = TEST_DATA_FOLDER.resolve("duplicateTagProjact.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableProjact dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableProjact.class).get();
        Projact projactFromFile = dataFromFile.toModelType();
        Projact typicalPersonsProjact = TypicalProjact.getTypicalProjact();
        assertEquals(projactFromFile, typicalPersonsProjact);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProjact dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableProjact.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableProjact dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableProjact.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableProjact.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidTagFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProjact dataFromFile = JsonUtil.readJsonFile(INVALID_TAG_FILE,
                JsonSerializableProjact.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTags_throwsIllegalValueException() throws Exception {
        JsonSerializableProjact dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAG_FILE,
                JsonSerializableProjact.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableProjact.MESSAGE_DUPLICATE_TAG,
                dataFromFile::toModelType);
    }

}
