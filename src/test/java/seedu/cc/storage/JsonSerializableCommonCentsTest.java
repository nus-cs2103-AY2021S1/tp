package seedu.cc.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.commons.util.JsonUtil;
import seedu.cc.model.CommonCents;
import seedu.cc.testutil.TypicalEntries;

public class JsonSerializableCommonCentsTest {
    private static final Path TEST_DATA_FOLDER = Paths.get(
        "src", "test", "data", "JsonCommonCentsAccountListTest");
    private static final Path TYPICAL_ACCOUNT_LIST_FILE = TEST_DATA_FOLDER
        .resolve("typicalAccountListCommonCents.json");
    private static final Path INVALID_ACCOUNT_LIST_FILE = TEST_DATA_FOLDER
        .resolve("invalidAccountListCommonCents.json");
    private static final Path DUPLICATE_ACCOUNT_LIST_FILE = TEST_DATA_FOLDER
        .resolve("duplicateAccountListCommonCents.json");

    @Test
    public void toModelType_typicalAccountListFile_success() throws DataConversionException, IllegalValueException {
        JsonSerializableCommonCents dataFromFile = JsonUtil.readJsonFile(TYPICAL_ACCOUNT_LIST_FILE,
            JsonSerializableCommonCents.class).get();
        CommonCents commonCentsFromFile = dataFromFile.toModelType();
        CommonCents typicalCommonCents = TypicalEntries.getTypicalCommonCents();
        assertEquals(commonCentsFromFile, typicalCommonCents);
    }

    @Test
    public void toModelType_invalidAccountListFile_throwsIllegalValueException() throws DataConversionException {
        JsonSerializableCommonCents dataFromFile = JsonUtil.readJsonFile(INVALID_ACCOUNT_LIST_FILE,
            JsonSerializableCommonCents.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    /*
    @Test
    public void toModelType_duplicateAccount_throwsIllegalValueException() throws DataConversionException {
        JsonSerializableCommonCents dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ACCOUNT_LIST_FILE,
            JsonSerializableCommonCents.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCommonCents.MESSAGE_DUPLICATE_ACCOUNT,
            dataFromFile::toModelType);
    }
    */
}
