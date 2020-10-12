package seedu.expense.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.ALICE;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.expense.model.budget.Budget;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.exceptions.DuplicateExpenseException;
import seedu.expense.testutil.ExpenseBuilder;

public class ExpenseBookTest {

    private final ExpenseBook expenseBook = new ExpenseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), expenseBook.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExpenseBook_replacesData() {
        ExpenseBook newData = getTypicalExpenseBook();
        expenseBook.resetData(newData);
        assertEquals(newData, expenseBook);
    }

    @Test
    public void resetData_withDuplicateExpenses_throwsDuplicateExpenseException() {
        // Two expenses with the same identity fields
        Expense editedAlice = new ExpenseBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Expense> newExpenses = Arrays.asList(ALICE, editedAlice);
        ExpenseBookStub newData = new ExpenseBookStub(newExpenses);

        assertThrows(DuplicateExpenseException.class, () -> expenseBook.resetData(newData));
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseBook.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInExpenseBook_returnsFalse() {
        assertFalse(expenseBook.hasExpense(ALICE));
    }

    @Test
    public void hasExpense_expenseInExpenseBook_returnsTrue() {
        expenseBook.addExpense(ALICE);
        assertTrue(expenseBook.hasExpense(ALICE));
    }

    @Test
    public void hasExpense_expenseWithSameIdentityFieldsInExpenseBook_returnsTrue() {
        expenseBook.addExpense(ALICE);
        Expense editedAlice = new ExpenseBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(expenseBook.hasExpense(editedAlice));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseBook.getExpenseList().remove(0));
    }

    /**
     * A stub ReadOnlyExpenseBook whose expenses list can violate interface constraints.
     */
    private static class ExpenseBookStub implements ReadOnlyExpenseBook {

        private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
        private final Budget budget = new Budget();

        ExpenseBookStub(Collection<Expense> expenses) {
            this.expenses.setAll(expenses);
        }

        @Override
        public ObservableList<Expense> getExpenseList() {
            return expenses;
        }

        @Override
        public Budget getBudget() {
            return budget;
        }
    }

}
