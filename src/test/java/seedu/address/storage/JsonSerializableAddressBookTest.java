package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAnimals;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_ANIMALS_FILE = TEST_DATA_FOLDER.resolve("typicalAnimalsAddressBook.json");
    private static final Path INVALID_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("invalidAnimalAddressBook.json");
    private static final Path DUPLICATE_ANIMAL_FILE = TEST_DATA_FOLDER.resolve("duplicateAnimalAddressBook.json");

    @Test
    public void toModelType_typicalAnimalsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ANIMALS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAnimalsAddressBook = TypicalAnimals.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalAnimalsAddressBook);
    }

    @Test
    public void toModelType_invalidAnimalFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_ANIMAL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAnimals_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ANIMAL_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ANIMAL,
                dataFromFile::toModelType);
    }

}
