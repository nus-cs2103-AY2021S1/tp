package seedu.address.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.BUY_FLOWER_POTS;
import static seedu.address.testutil.TypicalEntries.BUY_ROSE_SEEDS;
import static seedu.address.testutil.TypicalEntries.SELL_FLOWER_POTS;
import static seedu.address.testutil.TypicalEntries.SELL_SUNFLOWER;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;

import org.junit.jupiter.api.Test;

import seedu.address.model.account.entry.exceptions.EntryNotFoundException;


public class ActiveAccountManagerTest {
    private static final String GENERAL_ACC_NAME = "General account";
    private static final Account GENERAL_ACC = new Account(new Name(GENERAL_ACC_NAME));

    private ActiveAccountManager activeAccountManager = new ActiveAccountManager(GENERAL_ACC);

    @Test
    public void constructor() {
        assertEquals(GENERAL_ACC, new Account(activeAccountManager.getAccount()));
    }

    @Test
    public void setActiveAccount() {
        Account account = GENERAL_ACC;
        activeAccountManager.setActiveAccount(account);
        assertEquals(account, activeAccountManager.getAccount());
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.hasExpense(null));
    }

    @Test
    public void hasRevenue_nullRevenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.hasRevenue(null));
    }

    @Test
    public void hasEntry_expenseNotInAccount_returnsFalse() {
        assertFalse(activeAccountManager.hasEntry(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasEntry_revenueNotInAccount_returnsFalse() {
        assertFalse(activeAccountManager.hasEntry(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasExpense_expenseNotInAccount_returnsFalse() {
        assertFalse(activeAccountManager.hasExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasRevenue_revenueNotInAccount_returnsFalse() {
        assertFalse(activeAccountManager.hasRevenue(SELL_SUNFLOWER));
    }

    @Test
    public void hasEntry_expenseInAccount_returnTrue() {
        activeAccountManager.addExpense(BUY_ROSE_SEEDS);
        assertTrue(activeAccountManager.hasEntry(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasEntry_revenueInAccount_returnTrue() {
        activeAccountManager.addRevenue(SELL_SUNFLOWER);
        assertTrue(activeAccountManager.hasEntry(SELL_SUNFLOWER));
    }

    @Test
    public void hasExpense_expenseInAccount_returnsTrue() {
        activeAccountManager.addExpense(BUY_ROSE_SEEDS);
        assertTrue(activeAccountManager.hasExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void hasRevenue_revenueInAccount_returnsTrue() {
        activeAccountManager.addRevenue(SELL_SUNFLOWER);
        assertTrue(activeAccountManager.hasRevenue(SELL_SUNFLOWER));
    }

    @Test
    public void deleteExpense_expenseNotInAccount_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> activeAccountManager.deleteExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void deleteRevenue_revenueNotInAccount_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> activeAccountManager.deleteRevenue(SELL_SUNFLOWER));
    }

    @Test
    public void deleteExpense_expenseInAccount_returnsTrue() {
        activeAccountManager.addExpense(BUY_ROSE_SEEDS);
        activeAccountManager.deleteExpense(BUY_ROSE_SEEDS);
        assertFalse(activeAccountManager.hasExpense(BUY_ROSE_SEEDS));
    }

    @Test
    public void deleteRevenue_revenueInAccount_returnsTrue() {
        activeAccountManager.addRevenue(SELL_SUNFLOWER);
        activeAccountManager.deleteRevenue(SELL_SUNFLOWER);
        assertFalse(activeAccountManager.hasRevenue(SELL_SUNFLOWER));
    }

    @Test
    public void setExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.setExpense(null, BUY_ROSE_SEEDS));
        assertThrows(NullPointerException.class, () -> activeAccountManager.setExpense(BUY_ROSE_SEEDS, null));
        assertThrows(NullPointerException.class, () -> activeAccountManager.setExpense(null, null));
    }

    @Test
    public void setRevenue_nullRevenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.setRevenue(null, SELL_SUNFLOWER));
        assertThrows(NullPointerException.class, () -> activeAccountManager.setRevenue(SELL_SUNFLOWER, null));
        assertThrows(NullPointerException.class, () -> activeAccountManager.setRevenue(null, null));
    }

    @Test
    public void setExpense_validExpense_returnsTrue() {
        activeAccountManager.addExpense(BUY_ROSE_SEEDS);
        activeAccountManager.setExpense(BUY_ROSE_SEEDS, BUY_FLOWER_POTS);
        assertTrue(activeAccountManager.hasExpense(BUY_FLOWER_POTS));
    }

    @Test
    public void setRevenue_validRevenue_returnsTrue() {
        activeAccountManager.addRevenue(SELL_SUNFLOWER);
        activeAccountManager.setRevenue(SELL_SUNFLOWER, SELL_FLOWER_POTS);
        assertTrue(activeAccountManager.hasRevenue(SELL_FLOWER_POTS));
    }

    @Test
    public void getFilteredExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                activeAccountManager.getFilteredExpenseList().remove(0));
    }

    @Test
    public void getFilteredRevenueList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                activeAccountManager.getFilteredRevenueList().remove(0));
    }

    @Test
    void updateFilteredExpenseList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.updateFilteredExpenseList(null));
    }

    @Test
    void updateFilteredRevenueList_nullPredicate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> activeAccountManager.updateFilteredRevenueList(null));
    }

    @Test
    public void equals() {
        Account account = getTypicalAccount();
        Account differentAccount = GENERAL_ACC;

        // same values -> returns true
        activeAccountManager = new ActiveAccountManager(account);
        ActiveAccountManager activeAccountManagerCopy = new ActiveAccountManager(account);
        assertTrue(activeAccountManagerCopy.equals(activeAccountManager));

        // same object -> returns true
        assertTrue(activeAccountManager.equals(activeAccountManager));

        // null -> returns false
        assertFalse(activeAccountManager.equals(null));

        // different types -> returns false
        assertFalse(activeAccountManager.equals(5));

        // different account -> returns false
        assertFalse(activeAccountManager.equals(new ActiveAccountManager(differentAccount)));
    }

}
