package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.UserAccountsList;
import seedu.medibook.testutil.TypicalAccounts;

public class JsonSerializableUserAccountsListTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableUserAccountsListTest");
    private static final Path TYPICAL_ACCOUNTS =
            TEST_DATA_FOLDER.resolve("typicalAccounts.json");
    private static final Path INVALID_ACCOUNTS =
            TEST_DATA_FOLDER.resolve("invalidAccounts.json");

    @Test
    public void toModelType_typicalAccountsFile_success() throws Exception {
        JsonSerializableUserAccountsList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ACCOUNTS,
                JsonSerializableUserAccountsList.class).get();
        UserAccountsList accountsFromFile = dataFromFile.toModelType();
        UserAccountsList typicalAccounts = TypicalAccounts.getTypicalAccountsList();
        assertEquals(accountsFromFile, typicalAccounts);
    }

    @Test
    public void toModelType_invalidAccountsFile_throwsIllegalValueException() throws Exception {
        JsonSerializableUserAccountsList dataFromFile = JsonUtil.readJsonFile(INVALID_ACCOUNTS,
                JsonSerializableUserAccountsList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }
}
