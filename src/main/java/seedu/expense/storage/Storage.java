package seedu.expense.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.expense.commons.exceptions.DataConversionException;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.ReadOnlyUserPrefs;
import seedu.expense.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ExpenseBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getExpenseBookFilePath();

    @Override
    Optional<ReadOnlyExpenseBook> readExpenseBook() throws DataConversionException, IOException;

    @Override
    void saveExpenseBook(ReadOnlyExpenseBook expenseBook) throws IOException;

}
