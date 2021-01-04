package seedu.homerce.storage.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenses.AIRCON;
import static seedu.homerce.testutil.expense.TypicalExpenses.CHAIR;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;

public class JsonExpenseStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Expense", "JsonExpenseStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExpenseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExpenseTracker(null));
    }

    private java.util.Optional<ReadOnlyExpenseTracker> readExpenseTracker(String filePath) throws Exception {
        return new JsonExpenseStorage(Paths.get(filePath)).readExpenseTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExpenseTracker("NonExistentFile.json").isPresent());
    }
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExpenseTracker("notJsonFormatExpense.json"));
    }

    @Test
    public void readExpenseTracker_invalidExpenseTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseTracker("invalidExpenseTracker.json"));
    }

    @Test
    public void readExpenseTracker_invalidAndValidExpenseTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseTracker("invalidAndValidExpenseTracker.json"));
    }

    @Test
    public void readAndSaveExpenseTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempExpenseTracker.json");
        ExpenseTracker original = getTypicalExpenseTracker();
        JsonExpenseStorage jsonExpenseStorage = new JsonExpenseStorage(filePath);

        // Save in new file and read back
        jsonExpenseStorage.saveExpenseTracker(original, filePath);
        ReadOnlyExpenseTracker readBack = jsonExpenseStorage.readExpenseTracker(filePath).get();
        assertEquals(original, new ExpenseTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpense(CHAIR);
        original.removeExpense(CONDITIONER);
        jsonExpenseStorage.saveExpenseTracker(original, filePath);
        readBack = jsonExpenseStorage.readExpenseTracker(filePath).get();
        assertEquals(original, new ExpenseTracker(readBack));

        // Save and read without specifying file path
        original.addExpense(AIRCON);
        jsonExpenseStorage.saveExpenseTracker(original); // file path not specified
        readBack = jsonExpenseStorage.readExpenseTracker().get(); // file path not specified
        assertEquals(original, new ExpenseTracker(readBack));
    }

    @Test
    public void saveExpenseTracker_nullExpenseTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code expenseTracker} at the specified {@code filePath}.
     */
    private void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, String filePath) {
        try {
            new JsonExpenseStorage(Paths.get(filePath))
                    .saveExpenseTracker(expenseTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExpenseTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseTracker(new ExpenseTracker(), null));
    }
}
