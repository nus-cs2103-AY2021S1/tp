package seedu.homerce.storage.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.testutil.expense.ExpenseBuilder;
import seedu.homerce.testutil.expense.TypicalExpenses;

public class JsonSerializableExpenseTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Expense",
            "JsonSerializableExpenseTrackerTest");
    private static final Path TYPICAL_EXPENSE_FILE = TEST_DATA_FOLDER.resolve("typicalExpenseTracker.json");
    private static final Path INVALID_EXPENSE_FILE = TEST_DATA_FOLDER.resolve("invalidExpenseTracker.json");

    @Test
    public void toModelType_typicalExpenseFile_success() throws Exception {
        JsonSerializableExpenseTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPENSE_FILE,
                JsonSerializableExpenseTracker.class).get();
        ExpenseTracker expenseTrackerFromFile = dataFromFile.toModelType();
        ExpenseTracker typicalExpensesExpenseTracker = TypicalExpenses.getTypicalExpenseTracker();

        assertEquals(expenseTrackerFromFile, typicalExpensesExpenseTracker);
    }

    @Test
    public void toModelType_invalidExpenseFile_throwsIllegalValueException() throws Exception {
        JsonSerializableExpenseTracker dataFromFile = JsonUtil.readJsonFile(INVALID_EXPENSE_FILE,
                JsonSerializableExpenseTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void createDuplicateExpense() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        JsonSerializableExpenseTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPENSE_FILE,
                JsonSerializableExpenseTracker.class).get();
        Expense recurringExpense = new ExpenseBuilder().withIsFixed("y").build();
        Expense duplicateExpense = new Expense(new Description(recurringExpense.getDescription().value),
                new IsFixed("y"),
                new Amount(recurringExpense.getValue().value.doubleValue()),
                new Date(recurringExpense.getDate().getLocalDate().plusMonths(1).format(formatter)),
                new Tag(recurringExpense.getTag().tagName));
        assertEquals(dataFromFile.createDuplicateExpense(recurringExpense,
                recurringExpense.getDate().getLocalDate()), duplicateExpense);
    }

    @Test
    public void isSameMonth() throws Exception {
        JsonSerializableExpenseTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_EXPENSE_FILE,
                JsonSerializableExpenseTracker.class).get();
        assertEquals(dataFromFile.isSameMonth(CONDITIONER, CONDITIONER.getDate().getLocalDate()), true);
    }
}
