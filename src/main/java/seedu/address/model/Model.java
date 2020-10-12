package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<seedu.address.model.account.Account> PREDICATE_SHOW_ALL_ACCOUNTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getCommonCentsFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setCommonCentsFilePath(Path commonCentsFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setCommonCents(ReadOnlyCommonCents commonCents);

    /** Returns the CommonCents */
    ReadOnlyCommonCents getCommonCents();

    /**
     * Returns true if a account with the same identity as {@code account} exists in the CommonCents.
     */
    boolean hasAccount(seedu.address.model.account.Account account);

    /**
     * Deletes the given account.
     * The account must exist in the CommonCents.
     */
    void deleteAccount(seedu.address.model.account.Account target);

    /**
     * Adds the given account.
     * {@code account} must not already exist in the CommonCents.
     */
    void addAccount(seedu.address.model.account.Account account);

    /**
     * Replaces the given person {@code target} with {@code editedAccount}.
     * {@code target} must exist in the address book.
     * The account identity of {@code editedAccount} must not be the same as another existing account
     * in the CommonCents.
     */
    void setAccount(seedu.address.model.account.Account target, seedu.address.model.account.Account editedAccount);

    /**
     * Replaces the account in the list with the same name as {@code editedAccount}.
     * The account with the same name must exist in the money-tracker.
     */
    void setAccount(seedu.address.model.account.Account editedAccount);


    /** Returns an unmodifiable view of the filtered account list */
    ObservableList<seedu.address.model.account.Account> getFilteredAccountList();

    /**
     * Updates the filter of the filtered account list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAccountList(Predicate<seedu.address.model.account.Account> predicate);

}
