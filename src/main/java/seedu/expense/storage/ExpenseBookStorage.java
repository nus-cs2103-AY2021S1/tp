package seedu.expense.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.expense.commons.exceptions.DataConversionException;
import seedu.expense.model.ExpenseBook;
import seedu.expense.model.ReadOnlyExpenseBook;

/**
 * Represents a storage for {@link ExpenseBook}.
 */
public interface ExpenseBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExpenseBookFilePath();

    /**
     * Returns ExpenseBook data as a {@link ReadOnlyExpenseBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyExpenseBook> readExpenseBook() throws DataConversionException, IOException;

    /**
     * @see #getExpenseBookFilePath()
     */
    Optional<ReadOnlyExpenseBook> readExpenseBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExpenseBook} to the storage.
     *
     * @param expenseBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExpenseBook(ReadOnlyExpenseBook expenseBook) throws IOException;

    /**
     * @see #saveExpenseBook(ReadOnlyExpenseBook)
     */
    void saveExpenseBook(ReadOnlyExpenseBook expenseBook, Path filePath) throws IOException;

}
