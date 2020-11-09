package seedu.expense.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.expense.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.commons.util.JsonUtil;
import seedu.expense.model.ExpenseBook;
import seedu.expense.testutil.TypicalExpenses;

public class JsonSerializableExpenseBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableExpenseBookTest");
    private static final Path TYPICAL_EXPENSES_FILE = TEST_DATA_FOLDER.resolve("typicalExpensesLedger.json");
    private static final Path INVALID_EXPENSE_FILE = TEST_DATA_FOLDER.resolve("invalidExpenseLedger.json");
    private static final Path DUPLICATE_EXPENSE_FILE = TEST_DATA_FOLDER.resolve("duplicateExpenseLedger.json");

    @Test
    public void toModelType_typicalExpensesFile_success() throws Exception {
        JsonSerializableExpenseBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPENSES_FILE,
                JsonSerializableExpenseBook.class).get();
        ExpenseBook expenseBookFromFile = dataFromFile.toModelType();
        ExpenseBook typicalExpensesExpenseBook = TypicalExpenses.getTypicalExpenseBook();
        assertEquals(expenseBookFromFile, typicalExpensesExpenseBook);
    }

    @Test
    public void toModelType_invalidExpenseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExpenseBook dataFromFile = JsonUtil.readJsonFile(INVALID_EXPENSE_FILE,
                JsonSerializableExpenseBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateExpenses_throwsIllegalValueException() throws Exception {
        JsonSerializableExpenseBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_EXPENSE_FILE,
                JsonSerializableExpenseBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableExpenseBook.MESSAGE_DUPLICATE_EXPENSE,
                dataFromFile::toModelType);
    }

}
