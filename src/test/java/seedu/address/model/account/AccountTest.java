package seedu.address.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.BUY_ROSE_SEEDS;
import static seedu.address.testutil.TypicalEntries.SELL_SUNFLOWER;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;

import java.util.Collections;

import org.junit.jupiter.api.Test;

/**
 * Tests for Account class.
 * isSameAccount tests will be implemented.
 */
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
        assertFalse(account.hasExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasRevenue_revenueNotInAccount_returnsFalse() {
        assertFalse(account.hasRevenue(SELL_SUNFLOWER));
    }

    @Test
    public void hasExpense_expenseInAccount_returnsTrue() {
        account.addExpense(BUY_ROSE_SEEDS);
        assertTrue(account.hasExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasRevenue_revenueInAccount_returnsTrue() {
        account.addRevenue(SELL_SUNFLOWER);
        assertTrue(account.hasRevenue(SELL_SUNFLOWER));
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
