package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.account.Account;


/**
 * Represents the in-memory model of the Common Cents data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CommonCents commonCents;
    private final UserPrefs userPrefs;
    private final FilteredList<Account> filteredAccounts;

    /**
     * Initializes a ModelManager with the given CommonCents and userPrefs.
     */
    public ModelManager(ReadOnlyCommonCents commonCents, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(commonCents, userPrefs);

        logger.fine("Initializing with common cents data: " + commonCents + " and user prefs " + userPrefs);

        this.commonCents = new CommonCents(commonCents);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAccounts = new FilteredList<>(this.commonCents.getAccountList());
    }

    public ModelManager() {
        this(new CommonCents(), new UserPrefs());
    }



    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCommonCentsFilePath() {
        return userPrefs.getCommonCentsFilePath();
    }

    @Override
    public void setCommonCentsFilePath(Path commonCentsFilePath) {
        requireNonNull(commonCentsFilePath);
        userPrefs.setCommonCentsFilePath(commonCentsFilePath);
    }

    //=========== CommonCents ================================================================================

    @Override
    public void setCommonCents(ReadOnlyCommonCents commonCents) {
        this.commonCents.resetData(commonCents);
    }

    @Override
    public ReadOnlyCommonCents getCommonCents() {
        return commonCents;
    }

    @Override
    public boolean hasAccount(Account account) {
        requireNonNull(account);
        return commonCents.hasAccount(account);
    }

    @Override
    public void deleteAccount(Account target) {
        commonCents.removeAccount(target);
    }

    @Override
    public void addAccount(Account account) {
        commonCents.addAccount(account);
        updateFilteredAccountList(PREDICATE_SHOW_ALL_ACCOUNTS);
    }

    @Override
    public void setAccount(Account target, Account editedAccount) {
        requireAllNonNull(target, editedAccount);

        commonCents.setAccount(target, editedAccount);
    }

    @Override
    public void setAccount(Account editedAccount) {
        requireAllNonNull(editedAccount);

        commonCents.setAccount(editedAccount);
    }

    //=========== Filtered Account List Accessors =============================================================

    @Override
    public boolean hasNoAccount() {
        return filteredAccounts.isEmpty();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Account} backed by the internal list of
     * {@code versionedCommonCents}
     */
    @Override
    public ObservableList<Account> getFilteredAccountList() {
        return filteredAccounts;
    }

    @Override
    public void updateFilteredAccountList(Predicate<Account> predicate) {
        requireNonNull(predicate);
        filteredAccounts.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return commonCents.equals(other.commonCents)
                && userPrefs.equals(other.userPrefs)
                && filteredAccounts.equals(other.filteredAccounts);
    }

}
