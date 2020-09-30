package seedu.bookmark.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.bookmark.commons.exceptions.IllegalValueException;
import seedu.bookmark.commons.util.JsonUtil;
import seedu.bookmark.model.Library;
import seedu.bookmark.testutil.TypicalPersons;

public class JsonSerializableLibraryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableLibrary.class).get();
        Library libraryFromFile = dataFromFile.toModelType();
        Library typicalPersonsLibrary = TypicalPersons.getTypicalAddressBook();
        assertEquals(libraryFromFile, typicalPersonsLibrary);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableLibrary dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableLibrary.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableLibrary.MESSAGE_DUPLICATE_BOOKS,
                dataFromFile::toModelType);
    }

}
