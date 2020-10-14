package seedu.address.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.BUYROSESEEDS;
import static seedu.address.testutil.TypicalEntries.SELLSUNFLOWER;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;

import java.util.Collections;

import org.junit.jupiter.api.Test;


public class AccountTest {
    private static final String ACCOUNT_NAME = "General account";
    private final Account account = new Account(new Name(ACCOUNT_NAME));

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), account.getExpenseList());
        assertEquals(Collections.emptyList(), account.getRevenueList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAccount_replacesData() {
        Account newData = getTypicalAccount();
        account.resetData(newData);
        assertEquals(newData, account);
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.hasExpense(null));
    }

    @Test
    public void hasRevenue_nullRevenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> account.hasRevenue(null));
    }

    @Test
    public void hasExpense_expenseNotInAccount_returnsFalse() {
        assertFalse(account.hasExpense(BUYROSESEEDS));
    }

    @Test
    public void hasRevenue_revenueNotInAccount_returnsFalse() {
        assertFalse(account.hasRevenue(SELLSUNFLOWER));
    }

    @Test
    public void hasExpense_expenseInAccount_returnsTrue() {
        account.addExpense(BUYROSESEEDS);
        assertTrue(account.hasExpense(BUYROSESEEDS));
    }

    @Test
    public void hasRevenue_revenueInAccount_returnsTrue() {
        account.addRevenue(SELLSUNFLOWER);
        assertTrue(account.hasRevenue(SELLSUNFLOWER));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> account.getExpenseList().remove(0));
    }

    @Test
    public void getRevenueList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> account.getRevenueList().remove(0));
    }

}
