package seedu.expense.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.EZ_LINK;
import static seedu.expense.testutil.TypicalExpenses.FEL_BDAY;
import static seedu.expense.testutil.TypicalExpenses.MOVIE;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.expense.commons.exceptions.DataConversionException;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.ReadOnlyExpenseBook;

public class JsonExpenseBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonExpenseBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readExpenseBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readExpenseBook(null));
    }

    private java.util.Optional<ReadOnlyExpenseBook> readExpenseBook(String filePath) throws Exception {
        return new JsonExpenseBookStorage(Paths.get(filePath)).readExpenseBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readExpenseBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readExpenseBook("notJsonFormatLedger.json"));
    }

    @Test
    public void readExpenseBook_invalidExpenseExpenseBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseBook("invalidExpenseLedger.json"));
    }

    @Test
    public void readExpenseBook_invalidAndValidExpenseExpenseBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readExpenseBook("invalidAndValidExpenseLedger.json"));
    }

    @Test
    public void readAndSaveExpenseBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempExpenseBook.json");
        ExpenseBook original = getTypicalExpenseBook();
        JsonExpenseBookStorage jsonExpenseBookStorage = new JsonExpenseBookStorage(filePath);

        // Save in new file and read back
        jsonExpenseBookStorage.saveExpenseBook(original, filePath);
        ReadOnlyExpenseBook readBack = jsonExpenseBookStorage.readExpenseBook(filePath).get();
        assertEquals(original, new ExpenseBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addExpense(MOVIE);
        original.removeExpense(FEL_BDAY);
        jsonExpenseBookStorage.saveExpenseBook(original, filePath);
        readBack = jsonExpenseBookStorage.readExpenseBook(filePath).get();
        assertEquals(original, new ExpenseBook(readBack));

        // Save and read without specifying file path
        original.addExpense(EZ_LINK);
        jsonExpenseBookStorage.saveExpenseBook(original); // file path not specified
        readBack = jsonExpenseBookStorage.readExpenseBook().get(); // file path not specified
        assertEquals(original, new ExpenseBook(readBack));

    }

    @Test
    public void saveExpenseBook_nullExpenseBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code expenseBook} at the specified {@code filePath}.
     */
    private void saveExpenseBook(ReadOnlyExpenseBook expenseBook, String filePath) {
        try {
            new JsonExpenseBookStorage(Paths.get(filePath))
                    .saveExpenseBook(expenseBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveExpenseBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveExpenseBook(new ExpenseBook(), null));
    }
}
