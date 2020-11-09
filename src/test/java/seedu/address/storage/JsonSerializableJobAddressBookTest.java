package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.JobAddressBook;
import seedu.address.testutil.TypicalJobs;

public class JsonSerializableJobAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableJobAddressBookTest");
    private static final Path TYPICAL_JOBS_FILE = TEST_DATA_FOLDER.resolve("typicalJobsAddressBook.json");
    private static final Path INVALID_JOB_FILE = TEST_DATA_FOLDER.resolve("invalidJobAddressBook.json");
    private static final Path DUPLICATE_JOB_FILE = TEST_DATA_FOLDER.resolve("duplicateJobAddressBook.json");

    @Test
    public void toModelType_typicalJobsFile_success() throws Exception {
        JsonSerializableJobAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_JOBS_FILE,
                JsonSerializableJobAddressBook.class).get();
        JobAddressBook addressBookFromFile = dataFromFile.toModelType();
        JobAddressBook typicalJobsAddressBook = TypicalJobs.getTypicalJobAddressBook();
        assertEquals(addressBookFromFile, typicalJobsAddressBook);
    }

    @Test
    public void toModelType_invalidJobFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJobAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_JOB_FILE,
                JsonSerializableJobAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateJobs_throwsIllegalValueException() throws Exception {
        JsonSerializableJobAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_JOB_FILE,
                JsonSerializableJobAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJobAddressBook.MESSAGE_DUPLICATE_JOB,
                dataFromFile::toModelType);
    }

}
