package seedu.zookeep.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.zookeep.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.commons.util.JsonUtil;
import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.testutil.TypicalAnimals;

public class JsonSerializableZooKeepBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableZooKeepBookTest");
    private static final Path TYPICAL_ANIMALS_FILE = TEST_DATA_FOLDER.resolve("typicalAnimalsZooKeepBook.json");
    private static final Path INVALID_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("invalidAnimalZooKeepBook.json");
    private static final Path DUPLICATE_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("duplicateAnimalZooKeepBook.json");

    @Test
    public void toModelType_typicalAnimalsFile_success() throws Exception {
        JsonSerializableZooKeepBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ANIMALS_FILE,
                JsonSerializableZooKeepBook.class).get();
        ZooKeepBook zooKeepBookFromFile = dataFromFile.toModelType();
        ZooKeepBook typicalAnimalsZooKeepBook = TypicalAnimals.getTypicalZooKeepBook();
        assertEquals(zooKeepBookFromFile, typicalAnimalsZooKeepBook);
    }

    @Test
    public void toModelType_invalidAnimalFile_throwsIllegalValueException() throws Exception {
        JsonSerializableZooKeepBook dataFromFile = JsonUtil.readJsonFile(INVALID_ANIMAL_FILE,
                JsonSerializableZooKeepBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAnimals_throwsIllegalValueException() throws Exception {
        JsonSerializableZooKeepBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ANIMAL_FILE,
                JsonSerializableZooKeepBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableZooKeepBook.MESSAGE_DUPLICATE_ANIMAL,
                dataFromFile::toModelType);
    }

}
