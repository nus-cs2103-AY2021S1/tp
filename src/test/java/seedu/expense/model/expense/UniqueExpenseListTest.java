package seedu.expense.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.BUS;
import static seedu.expense.testutil.TypicalExpenses.FEL_BDAY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.expense.model.expense.exceptions.DuplicateExpenseException;
import seedu.expense.model.expense.exceptions.ExpenseNotFoundException;
import seedu.expense.testutil.ExpenseBuilder;

public class UniqueExpenseListTest {

    private final UniqueExpenseList uniqueExpenseList = new UniqueExpenseList();

    @Test
    public void contains_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.contains(null));
    }

    @Test
    public void contains_expenseNotInList_returnsFalse() {
        assertFalse(uniqueExpenseList.contains(FEL_BDAY));
    }

    @Test
    public void contains_expenseInList_returnsTrue() {
        uniqueExpenseList.add(FEL_BDAY);
        assertTrue(uniqueExpenseList.contains(FEL_BDAY));
    }

    @Test
    public void contains_expenseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueExpenseList.add(FEL_BDAY);
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withTag(VALID_TAG_TRANSPORT)
                .build();
        assertTrue(uniqueExpenseList.contains(editedAlice));
    }

    @Test
    public void add_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.add(null));
    }

    @Test
    public void add_duplicateExpense_throwsDuplicateExpenseException() {
        uniqueExpenseList.add(FEL_BDAY);
        assertThrows(DuplicateExpenseException.class, () -> uniqueExpenseList.add(FEL_BDAY));
    }

    @Test
    public void setExpense_nullTargetExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpense(null, FEL_BDAY));
    }

    @Test
    public void setExpense_nullEditedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpense(FEL_BDAY, null));
    }

    @Test
    public void setExpense_targetExpenseNotInList_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> uniqueExpenseList.setExpense(FEL_BDAY, FEL_BDAY));
    }

    @Test
    public void setExpense_editedExpenseIsSameExpense_success() {
        uniqueExpenseList.add(FEL_BDAY);
        uniqueExpenseList.setExpense(FEL_BDAY, FEL_BDAY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(FEL_BDAY);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasSameIdentity_success() {
        uniqueExpenseList.add(FEL_BDAY);
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withTag(VALID_TAG_TRANSPORT)
                .build();
        uniqueExpenseList.setExpense(FEL_BDAY, editedAlice);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(editedAlice);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasDifferentIdentity_success() {
        uniqueExpenseList.add(FEL_BDAY);
        uniqueExpenseList.setExpense(FEL_BDAY, BUS);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(BUS);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasNonUniqueIdentity_throwsDuplicateExpenseException() {
        uniqueExpenseList.add(FEL_BDAY);
        uniqueExpenseList.add(BUS);
        assertThrows(DuplicateExpenseException.class, () -> uniqueExpenseList.setExpense(FEL_BDAY, BUS));
    }

    @Test
    public void remove_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.remove(null));
    }

    @Test
    public void remove_expenseDoesNotExist_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> uniqueExpenseList.remove(FEL_BDAY));
    }

    @Test
    public void remove_existingExpense_removesExpense() {
        uniqueExpenseList.add(FEL_BDAY);
        uniqueExpenseList.remove(FEL_BDAY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpenses_nullUniqueExpenseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpenses((UniqueExpenseList) null));
    }

    @Test
    public void setExpenses_uniqueExpenseList_replacesOwnListWithProvidedUniqueExpenseList() {
        uniqueExpenseList.add(FEL_BDAY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(BUS);
        uniqueExpenseList.setExpenses(expectedUniqueExpenseList);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpenses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpenses((List<Expense>) null));
    }

    @Test
    public void setExpenses_list_replacesOwnListWithProvidedList() {
        uniqueExpenseList.add(FEL_BDAY);
        List<Expense> expenseList = Collections.singletonList(BUS);
        uniqueExpenseList.setExpenses(expenseList);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(BUS);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpenses_listWithDuplicateExpenses_throwsDuplicateExpenseException() {
        List<Expense> listWithDuplicateExpenses = Arrays.asList(FEL_BDAY, FEL_BDAY);
        assertThrows(DuplicateExpenseException.class, () -> uniqueExpenseList.setExpenses(listWithDuplicateExpenses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueExpenseList.asUnmodifiableObservableList().remove(0));
    }
}
