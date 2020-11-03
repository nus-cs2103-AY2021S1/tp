package seedu.expense.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.FEL_BDAY;
import static seedu.expense.testutil.TypicalExpenses.GRAB_HOME;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.core.GuiSettings;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.testutil.ExpenseBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

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
        assertFalse(modelManager.hasExpense(FEL_BDAY));
    }

    @Test
    public void hasExpense_expenseInExpenseBook_returnsTrue() {
        modelManager.addCategory(FEL_BDAY.getTag());
        modelManager.addExpense(FEL_BDAY);
        assertTrue(modelManager.hasExpense(FEL_BDAY));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredExpenseList().remove(0));
    }

    @Test
    public void equals() {
        ExpenseBook expenseBook = new ExpenseBookBuilder().withExpense(FEL_BDAY).withExpense(GRAB_HOME).build();
        ExpenseBook differentExpenseBook = new ExpenseBook();
        UserPrefs userPrefs = new UserPrefs();
        AliasMap aliasMap = new AliasMap();

        // same values -> returns true
        modelManager = new ModelManager(expenseBook, userPrefs, aliasMap);
        ModelManager modelManagerCopy = new ModelManager(expenseBook, userPrefs, aliasMap);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different expenseBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentExpenseBook, userPrefs, aliasMap)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setExpenseBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(expenseBook, differentUserPrefs, aliasMap)));
    }
}
