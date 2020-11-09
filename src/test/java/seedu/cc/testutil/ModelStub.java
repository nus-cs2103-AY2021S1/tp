package seedu.cc.testutil;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.cc.commons.core.GuiSettings;
import seedu.cc.model.Model;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;


public class ModelStub implements Model {
    private static final Account ACCOUNT_STUB = new Account(new Name("Account stub"));
    private List<Account> accountList = new ArrayList<>();

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return null;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }

    @Override
    public Path getCommonCentsFilePath() {
        return null;
    }

    @Override
    public void setCommonCentsFilePath(Path commonCentsFilePath) {

    }

    @Override
    public void setCommonCents(ReadOnlyCommonCents commonCents) {

    }

    @Override
    public ReadOnlyCommonCents getCommonCents() {
        return null;
    }

    @Override
    public boolean hasAccount(Account account) {
        return accountList.contains(account);
    }

    @Override
    public void deleteAccount(Account target) {

    }

    @Override
    public void addAccount(Account account) {
        accountList.add(account);
    }

    @Override
    public void setAccount(Account target, Account editedAccount) {

    }

    @Override
    public void setAccount(Account editedAccount) {

    }

    @Override
    public boolean hasNoAccount() {
        return true;
    };

    @Override
    public Account getAccountFromFilteredList(int index) {
        return ACCOUNT_STUB;
    }

    @Override
    public ObservableList<Account> getFilteredAccountList() {
        return null;
    }

    @Override
    public void updateFilteredAccountList(Predicate<Account> predicate) {

    }
}
