package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Reeve;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableReeveTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsReeve.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentReeve.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentReeve.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableReeve dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableReeve.class).get();
        Reeve reeveFromFile = dataFromFile.toModelType();
        Reeve typicalPersonsReeve = TypicalStudents.getTypicalAddressBook();
        assertEquals(reeveFromFile, typicalPersonsReeve);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableReeve dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableReeve.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableReeve dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableReeve.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableReeve.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
