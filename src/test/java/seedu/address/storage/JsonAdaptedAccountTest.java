package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.account.Account;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.testutil.TypicalEntries;

public class JsonAdaptedAccountTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src", "test", "data", "JsonCommonCentsAccountTest");
    private static final Path TYPICAL_ACCOUNT_FILE = TEST_DATA_FOLDER.resolve("typicalCommonCentsAccount.json");

    @Test
    public void toModelType_validTypicalAccountFile_returnAccount()
        throws DataConversionException, IllegalValueException {
        JsonAdaptedAccount jsonAdaptedAccount = JsonUtil.readJsonFile(TYPICAL_ACCOUNT_FILE, JsonAdaptedAccount.class).get();
        Account accountFromFile = jsonAdaptedAccount.toModelType();
        // Account typicalAccount = TypicalEntries.getTypicalCommonCents();
    }
}
