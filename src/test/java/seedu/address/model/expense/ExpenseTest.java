package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.BUS;
import static seedu.address.testutil.TypicalExpenses.FEL_BDAY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class ExpenseTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Expense expense = new ExpenseBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> expense.getTags().remove(0));
    }

    @Test
    public void isSameExpense() {
        // same object -> returns true
        assertTrue(FEL_BDAY.isSameExpense(FEL_BDAY));

        // null -> returns false
        assertFalse(FEL_BDAY.isSameExpense(null));

        // different phone and email -> returns false
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY)
                .withAmount(VALID_AMOUNT_BUS).withDate(VALID_DATE_BUS).build();
        assertFalse(FEL_BDAY.isSameExpense(editedAlice));

        // different name -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withDescription(VALID_DESCRIPTION_BUS).build();
        assertFalse(FEL_BDAY.isSameExpense(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new ExpenseBuilder(FEL_BDAY).withDate(VALID_DATE_BUS)
                .withTags(VALID_TAG_TRANSPORT).build();
        assertTrue(FEL_BDAY.isSameExpense(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new ExpenseBuilder(FEL_BDAY).withAmount(VALID_AMOUNT_BUS)
                .withTags(VALID_TAG_TRANSPORT).build();
        assertTrue(FEL_BDAY.isSameExpense(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new ExpenseBuilder(FEL_BDAY).withTags(VALID_TAG_TRANSPORT).build();
        assertTrue(FEL_BDAY.isSameExpense(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Expense aliceCopy = new ExpenseBuilder(FEL_BDAY).build();
        assertTrue(FEL_BDAY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(FEL_BDAY.equals(FEL_BDAY));

        // null -> returns false
        assertFalse(FEL_BDAY.equals(null));

        // different type -> returns false
        assertFalse(FEL_BDAY.equals(5));

        // different expense -> returns false
        assertFalse(FEL_BDAY.equals(BUS));

        // different name -> returns false
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withDescription(VALID_DESCRIPTION_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withAmount(VALID_AMOUNT_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withDate(VALID_DATE_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withTags(VALID_TAG_TRANSPORT).build();
        assertFalse(FEL_BDAY.equals(editedAlice));
    }
}
