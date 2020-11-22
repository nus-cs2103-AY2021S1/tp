package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.CLOTHES;
import static seedu.address.testutil.TypicalExpenses.SNACKS;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.testutil.ExpenseBookBuilder;

class ExpenseModelManagerTest {

    private ExpenseModelManager modelManager = new ExpenseModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ExpenseBook(), new ExpenseBook(modelManager.getExpenseBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setExpenseBookFilePath(Paths.get("expense/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setExpenseBookFilePath(Paths.get("new/expense/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setExpenseBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setExpenseBookFilePath(null));
    }

    @Test
    public void setExpenseBookFilePath_validPath_setsExpenseBookFilePath() {
        Path path = Paths.get("expense/book/file/path");
        modelManager.setExpenseBookFilePath(path);
        assertEquals(path, modelManager.getExpenseBookFilePath());
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInExpenseBook_returnsFalse() {
        assertFalse(modelManager.hasExpense(CLOTHES));
    }

    @Test
    public void hasExpense_expenseInExpenseBook_returnsTrue() {
        modelManager.addExpense(CLOTHES);
        assertTrue(modelManager.hasExpense(CLOTHES));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenseList().remove(0));
    }

    @Test
    public void equals() {
        ExpenseBook expenseBook = new ExpenseBookBuilder().withExpense(CLOTHES).withExpense(SNACKS).build();
        ExpenseBook differentExpenseBook = new ExpenseBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ExpenseModelManager(expenseBook, userPrefs);
        ExpenseModelManager modelManagerCopy = new ExpenseModelManager(expenseBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different expenseBook -> returns false
        assertFalse(modelManager.equals(new ExpenseModelManager(differentExpenseBook, userPrefs)));
        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setExpenseBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ExpenseModelManager(expenseBook, differentUserPrefs)));
    }

}
