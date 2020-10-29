package seedu.address.testutil;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.Name;
import seedu.address.model.account.ReadOnlyAccount;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;


public class ActiveAccountStub implements ActiveAccount {
    @Override
    public void setActiveAccount(ReadOnlyAccount newActiveAccount) {

    }

    @Override
    public void setPreviousState() {
    }

    @Override
    public Optional<ActiveAccount> getPreviousState() {
        return Optional.empty();
    }

    @Override
    public ActiveAccount getCopy() {
        return null;
    }

    @Override
    public void returnToPreviousState() {
    }
    @Override
    public boolean hasNoPreviousState() {
        return true;
    }

    @Override
    public void removePreviousState() {
    }

    @Override
    public void setName(Name name){}

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public boolean hasEntry(Entry entry) {
        return false;
    }

    @Override
    public boolean hasExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean hasRevenue(Revenue revenue) {
        return false;
    }

    @Override
    public void deleteExpense(Expense target) {

    }

    @Override
    public void deleteRevenue(Revenue target) {

    }

    @Override
    public void addExpense(Expense expense) {

    }

    @Override
    public void addRevenue(Revenue revenue) {

    }

    @Override
    public void setExpense(Expense target, Expense editedExpense) {

    }

    @Override
    public void setRevenue(Revenue target, Revenue editedRevenue) {

    }

    @Override
    public void clearExpenses() {

    }

    @Override
    public void clearRevenues() {

    }

    @Override
    public ObservableList<Expense> getFilteredExpenseList() {
        return null;
    }

    @Override
    public ObservableList<Revenue> getFilteredRevenueList() {
        return null;
    }


    @Override
    public void updateFilteredExpenseList(Predicate<Expense> predicate) {

    }

    @Override
    public void updateFilteredRevenueList(Predicate<Revenue> predicate) {

    }

    @Override
    public double getTotalExpenses() {
        return 0.00;
    }

    @Override
    public double getTotalRevenue() {
        return 0.00;
    }

    @Override
    public double getProfits() {
        return 0.00;
    }
}
