package seedu.internhunter.logic;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Retrieves an unmodifiable view of the filtered application list.
     *
     * @return an unmodifiable view of the filtered application list.
     */
    ObservableList<ApplicationItem> getFilteredApplicationItemList();

    /**
     * Retrieves an unmodifiable view of the filtered company list.
     *
     * @return an unmodifiable view of the filtered company list.
     */
    ObservableList<CompanyItem> getFilteredCompanyItemList();

    /**
     * Retrieves an unmodifiable view of the filtered profile list.
     *
     * @return an unmodifiable view of the filtered profile list.
     */
    ObservableList<ProfileItem> getFilteredProfileItemList();

    /**
     * Retrieves the user prefs' GUI settings.
     *
     * @return the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings with {@code guiSettings}.
     *
     * @param guiSettings The new gui settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Retrieves the current tab name.
     *
     * @return the current tab name.
     */
    TabName getTabName();

    /**
     * Sets the current tab name with {@code tabName}.
     *
     * @param tabName The new TabName to change to.
     */
    void setTabName(TabName tabName);

    /**
     * Retrieves the current company view Index.
     *
     * @return the current company view Index.
     */
    Index getCompanyViewIndex();

    /**
     * Retrieves the current application view Index.
     *
     * @return the current application view Index.
     */
    Index getApplicationViewIndex();

    /**
     * Retrieves the current profile view Index.
     *
     * @return the current profile view Index.
     */
    Index getProfileViewIndex();

    /**
     * Sets the current company view index with {@code index}.
     *
     * @param index The new Index for company view index.
     */
    void setCompanyViewIndex(Index index);

    /**
     * Sets the current application view index with {@code index}.
     *
     * @param index The new Index for application view index.
     */
    void setApplicationViewIndex(Index index);

    /**
     * Sets the current profile view index with {@code index}.
     *
     * @param index The new Index for profile view index.
     */
    void setProfileViewIndex(Index index);
}
