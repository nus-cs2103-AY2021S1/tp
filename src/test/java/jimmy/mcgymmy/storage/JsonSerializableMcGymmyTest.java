package jimmy.mcgymmy.storage;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;
import jimmy.mcgymmy.commons.util.JsonUtil;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.testutil.TypicalFoods;

public class JsonSerializableMcGymmyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMcGymmyTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableMcGymmy dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableMcGymmy.class).get();
        McGymmy mcGymmyFromFile = dataFromFile.toModelType();
        McGymmy typicalPersonsMcGymmy = TypicalFoods.getTypicalMcGymmy();
        assertEquals(mcGymmyFromFile, typicalPersonsMcGymmy);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMcGymmy dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableMcGymmy.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableMcGymmy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableMcGymmy.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMcGymmy.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
