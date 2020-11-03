package seedu.expense.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.testutil.TypicalExpenses.BUS;
import static seedu.expense.testutil.TypicalExpenses.FEL_BDAY;

import org.junit.jupiter.api.Test;

import seedu.expense.testutil.ExpenseBuilder;

public class ExpenseTest {

    @Test
    public void isSameExpense() {
        // same object -> returns true
        assertTrue(FEL_BDAY.isSameExpense(FEL_BDAY));

        // null -> returns false
        assertFalse(FEL_BDAY.isSameExpense(null));

        // different amount and date -> returns false
        Expense editedFelBD = new ExpenseBuilder(FEL_BDAY)
                .withAmount(VALID_AMOUNT_BUS)
                .withDate(VALID_DATE_BUS).build();
        assertFalse(FEL_BDAY.isSameExpense(editedFelBD));

        // different description -> returns false
        editedFelBD = new ExpenseBuilder(FEL_BDAY).withDescription(VALID_DESCRIPTION_BUS).build();
        assertFalse(FEL_BDAY.isSameExpense(editedFelBD));

        // same description, same amount, different attributes -> returns false
        editedFelBD = new ExpenseBuilder(FEL_BDAY).withDate(VALID_DATE_BUS)
                .withTag(VALID_TAG_TRANSPORT).build();
        assertFalse(FEL_BDAY.isSameExpense(editedFelBD));

        // same description, same date, different attributes -> returns false
        editedFelBD = new ExpenseBuilder(FEL_BDAY).withAmount(VALID_AMOUNT_BUS)
                .withTag(VALID_TAG_TRANSPORT).build();
        assertFalse(FEL_BDAY.isSameExpense(editedFelBD));

        // same description, same amount, same date, different attributes -> returns true
        editedFelBD = new ExpenseBuilder(FEL_BDAY).withTag(VALID_TAG_TRANSPORT).build();
        assertTrue(FEL_BDAY.isSameExpense(editedFelBD));
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

        // different description -> returns false
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withDescription(VALID_DESCRIPTION_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));

        // different amount -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withAmount(VALID_AMOUNT_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));

        // different date -> returns false
        editedAlice = new ExpenseBuilder(FEL_BDAY).withDate(VALID_DATE_BUS).build();
        assertFalse(FEL_BDAY.equals(editedAlice));
    }
}
