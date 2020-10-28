package seedu.address.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.BUY_FLOWER_POTS;
import static seedu.address.testutil.TypicalEntries.BUY_ROSE_SEEDS;
import static seedu.address.testutil.TypicalEntries.BUY_SHOVEL;
import static seedu.address.testutil.TypicalEntries.BUY_STRING;
import static seedu.address.testutil.TypicalEntries.SELL_FLOWER_POTS;
import static seedu.address.testutil.TypicalEntries.SELL_FLOWER_SEEDS;
import static seedu.address.testutil.TypicalEntries.SELL_HANDICRAFT;
import static seedu.address.testutil.TypicalEntries.SELL_SUNFLOWER;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.account.entry.ExpenseList;
import seedu.address.model.account.entry.RevenueList;

public class AccountTest {
    private static final String ACCOUNT_NAME = "General account";
    private Account account;

    @BeforeEach
    public void initializeAccount() {
        account = new Account(new Name(ACCOUNT_NAME));
    }
    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), account.getExpenseList());
        assertEquals(Collections.emptyList(), account.getRevenueList());
    }

    @Test
    public void setExpenses() {
        ExpenseList expenseList = new ExpenseList();
        expenseList.add(BUY_ROSE_SEEDS);
        expenseList.add(BUY_FLOWER_POTS);
        expenseList.add(BUY_SHOVEL);
        expenseList.add(BUY_STRING);
        account.setExpenses(expenseList);
        assertEquals(account.getExpenseList(), expenseList.asUnmodifiableObservableList());
    }

    @Test
    public void setRevenues() {
        RevenueList revenueList = new RevenueList();
        revenueList.add(SELL_FLOWER_POTS);
        revenueList.add(SELL_FLOWER_SEEDS);
        revenueList.add(SELL_HANDICRAFT);
        revenueList.add(SELL_SUNFLOWER);
        account.setRevenues(revenueList);
        assertEquals(account.getRevenueList(), revenueList.asUnmodifiableObservableList());
    }

    @Test
    public void clearExpenses() {
        account.addExpense(BUY_ROSE_SEEDS);
        account.addExpense(BUY_FLOWER_POTS);
        account.addExpense(BUY_SHOVEL);
        account.addExpense(BUY_STRING);
        account.clearExpenses();
        assertTrue(account.getExpenseList().isEmpty());
    }

    @Test
    public void clearRevenues() {
        account.addRevenue(SELL_FLOWER_POTS);
        account.addRevenue(SELL_FLOWER_SEEDS);
        account.addRevenue(SELL_HANDICRAFT);
        account.addRevenue(SELL_SUNFLOWER);
        account.clearRevenues();
        assertTrue(account.getRevenueList().isEmpty());
    }

    @Test
    public void isSameAccount_accountWithDifferentName_false() {
        Account anotherAccount = new Account(new Name("Different account"));
        assertFalse(account.isSameAccount(anotherAccount));
    }

    @Test
    public void isSameAccount_accountWithSameName_true() {
        Account anotherAccount = new Account(new Name(ACCOUNT_NAME));
        assertTrue(account.isSameAccount(anotherAccount));
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
