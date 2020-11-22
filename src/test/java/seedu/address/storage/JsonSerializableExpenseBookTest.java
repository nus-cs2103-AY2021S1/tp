package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
//import seedu.address.model.ExpenseBook;
//import seedu.address.testutil.TypicalExpenses;

public class JsonSerializableExpenseBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableExpenseBookTest");
    //private static final Path TYPICAL_EXPENSES_FILE = TEST_DATA_FOLDER.resolve("typicalExpenseExpenseBook.json");
    private static final Path INVALID_EXPENSES_FILE = TEST_DATA_FOLDER.resolve("invalidExpenseExpenseBook.json");

    //    @Test
    //    public void toModelType_typicalExpensesFile_success() throws Exception {
    //        JsonSerializableExpenseBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPENSES_FILE,
    //                JsonSerializableExpenseBook.class).get();
    //        ExpenseBook ExpenseBookFromFile = dataFromFile.toModelType();
    //        ExpenseBook typicalExpensesExpenseBook = TypicalExpenses.getTypicalExpenseBook();
    //        assertEquals(ExpenseBookFromFile, typicalExpensesExpenseBook);
    //    }

    @Test
    public void toModelType_invalidExpenseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExpenseBook dataFromFile = JsonUtil.readJsonFile(INVALID_EXPENSES_FILE,
                JsonSerializableExpenseBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
