package seedu.address.testutil;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.account.Account;


public class ModelStub implements Model {
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
        return false;
    }

    @Override
    public void deleteAccount(Account target) {

    }

    @Override
    public void addAccount(Account account) {

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
    public ObservableList<Account> getFilteredAccountList() {
        return null;
    }

    @Override
    public void updateFilteredAccountList(Predicate<Account> predicate) {

    }
}
