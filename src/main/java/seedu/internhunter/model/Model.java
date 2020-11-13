package seedu.internhunter.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     *
     * @param userPrefs The new user prefs to change to.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Retrieves the user prefs.
     *
     * @return the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

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
     * Retrieves the filterable company list.
     *
     * @return the filterable company list.
     */
    FilterableItemList<CompanyItem> getCompanyList();

    /**
     * Retrieves an unmodifiable view of the filtered company list.
     *
     * @return an unmodifiable view of the filtered company list.
     */
    ObservableList<CompanyItem> getFilteredCompanyList();

    /**
     * Retrieves the unfiltered company list.
     *
     * @return the unfiltered company list.
     */
    ItemList<CompanyItem> getUnfilteredCompanyList();

    /**
     * Retrieves the company item list.
     *
     * @return the company item list.
     */
    ObservableList<CompanyItem> getCompanyItemList();

    /**
     * Returns true if a Company with the same identity as {@code companyItem} exists in the company list.
     *
     * @param companyItem The CompanyItem to be checked.
     * @return True if a CompanyItem in the list has the same identity as the {@code companyItem}.
     */
    boolean hasCompany(CompanyItem companyItem);

    /**
     * Deletes the given Company.
     * The Company must exist in the company list.
     *
     * @param target The CompanyItem to be deleted.
     */
    void deleteCompany(CompanyItem target);

    /**
     * Adds the given Company.
     * {@code companyItem} must not already exist in the company list.
     *
     * @param companyItem The new CompanyItem to be added to the company list.
     */
    void addCompany(CompanyItem companyItem);

    /**
     * Replaces the given Company {@code target} with {@code editedCompanyItem}.
     * {@code target} must exist in the company list.
     * The Company identity of {@code editedCompanyItem} must not be the same as another existing Company in the
     * company list.
     *
     * @param target The CompanyItem to be replaced.
     * @param editedCompanyItem The new CompanyItem.
     */
    void setCompany(CompanyItem target, CompanyItem editedCompanyItem);

    /**
     * Updates the filter of the filtered Company list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to update the company list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate);

    /**
     * Replaces company list data with the data in {@code companyList}.
     *
     * @param companyList The new company item list to change to.
     */
    void setCompanyList(ItemList<CompanyItem> companyList);

    /**
     * Retrieves the filterable application list.
     *
     * @return the filterable application list.
     */
    FilterableItemList<ApplicationItem> getApplicationList();

    /**
     * Retrieves an unmodifiable view of the filtered application list.
     *
     * @return an unmodifiable view of the filtered application list.
     */
    ObservableList<ApplicationItem> getFilteredApplicationList();

    /**
     * Retrieves the unfiltered application list.
     *
     * @return the unfiltered application list.
     */
    ItemList<ApplicationItem> getUnfilteredApplicationList();

    /**
     * Returns true if an Application with the same identity as {@code applicationItem} exists in the application list.
     *
     * @param applicationItem The ApplicationItem to be checked.
     * @return True if an ApplicationItem in the list has the same identity as {@code applicationItem}.
     */
    boolean hasApplication(ApplicationItem applicationItem);

    /**
     * Deletes the given Application.
     * The Application must exist in the application list.
     *
     * @param target The ApplicationItem to be deleted.
     */
    void deleteApplication(ApplicationItem target);

    /**
     * Deletes the given Application according to the weaker notion of equality.
     * The Application may not necessarily exist in the application list.
     *
     * @param target The ApplicationItem to be deleted.
     */
    void deleteSameApplication(ApplicationItem target);

    /**
     * Adds the given Application.
     * {@code applicationItem} must not already exist in the application list.
     *
     * @param applicationItem The new ApplicationItem to be added to the application list.
     */
    void addApplication(ApplicationItem applicationItem);

    /**
     * Replaces the given Application {@code target} with {@code editedApplicationItem}.
     * {@code target} must exist in the application list.
     * The Application identity of {@code editedApplicationItem} must not be the same as another existing Application
     * in the application list.
     *
     * @param target The ApplicationItem to be replaced.
     * @param editedApplicationItem The new ApplicationItem.
     */
    void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem);

    /**
     * Updates the filter of the filtered Application list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to update the application list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate);

    /**
     * Replaces application list data with the data in {@code applicationList}.
     *
     * @param applicationList The new application item list to change to.
     */
    void setApplicationList(ItemList<ApplicationItem> applicationList);

    /**
     * Gets the application item list.
     *
     * @return Application item list.
     */
    ObservableList<ApplicationItem> getApplicationItemList();

    /**
     * Retrieves the filterable profile list.
     *
     * @return the filterable profile list.
     */
    FilterableItemList<ProfileItem> getProfileList();

    /**
     * Retrieves an unmodifiable view of the filtered profile list.
     *
     * @return an unmodifiable view of the filtered profile list.
     */
    ObservableList<ProfileItem> getFilteredProfileList();

    /**
     * Retrieves the unfiltered profile list.
     *
     * @return the unfiltered profile list.
     */
    ItemList<ProfileItem> getUnfilteredProfileList();

    /**
     * Retrieves the profile item list.
     *
     * @return the profile item list.
     */
    ObservableList<ProfileItem> getProfileItemList();

    /**
     * Returns true if a Profile item with the same identity as {@code profileItem} exists in the profile list.
     *
     * @param profileItem The ProfileItem to be checked.
     * @return True if a Profile item in the list has the same identity as {@code profileItem}.
     */
    boolean hasProfileItem(ProfileItem profileItem);

    /**
     * Deletes the given Profile item.
     * The Profile item must exist in the profile list.
     *
     * @param target The ProfileItem to be deleted.
     */
    void deleteProfileItem(ProfileItem target);

    /**
     * Adds the given Profile item.
     * {@code profileItem} must not already exist in the profile list.
     *
     * @param profileItem The new ProfileItem to be added to the profile list.
     */
    void addProfileItem(ProfileItem profileItem);

    /**
     * Replaces the given Profile item {@code target} with {@code editedProfileItem}.
     * {@code target} must exist in the profile list.
     * The Profile item identity of {@code editedProfileItem} must not be the same as another existing Profile item in
     * the profile list.
     *
     * @param target The ProfileItem to be replaced.
     * @param editedProfileItem The new ProfileItem.
     */
    void setProfileItem(ProfileItem target, ProfileItem editedProfileItem);

    /**
     * Updates the filter of the filtered Profile list to filter by the given {@code predicate}.
     *
     * @param predicate The predicate to update the profile list.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProfileList(Predicate<? super ProfileItem> predicate);

    /**
     * Replaces profile list data with the data in {@code profileList}.
     *
     * @param profileList The new Profile Item list to change to.
     */
    void setProfileList(ItemList<ProfileItem> profileList);

    /**
     * Sets the current tab name with {@code tabName}.
     *
     * @param tabName The new TabName to change to.
     */
    void setTabName(TabName tabName);

    /**
     * Retrieves the current tab name.
     *
     * @return the current tab name.
     */
    TabName getTabName();

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
     * Gets the {@code ProfileItem} at the {@code index} of the Filtered Profile list.
     *
     * @param index index of the profile item to be retrieved.
     * @return The profile item that is at that {@code index} of the profile list.
     */
    ProfileItem getProfileItemFromFilteredList(int index);

    /**
     * Gets the {@code CompanyItem} at the {@code index} of the Filtered Company list.
     *
     * @param index index of the company item to be retrieved.
     * @return The company item that is at that {@code index} of the company list.
     */
    CompanyItem getCompanyItemFromFilteredList(int index);

    /**
     * Gets the {@code ApplicationItem} at the {@code index} of the Filtered Application list.
     *
     * @param index index of the application item to be retrieved.
     * @return The application item that is at that {@code index} of the application list.
     */
    ApplicationItem getApplicationItemFromFilteredList(int index);

    /**
     * Retrieves the size of the filtered company list.
     *
     * @return the size of the filtered company list.
     */
    int getFilteredCompanyListSize();

    /**
     * Retrieves the size of the filtered application list.
     *
     * @return the size of the filtered application list.
     */
    int getFilteredApplicationListSize();

    /**
     * Retrieves the size of the filtered profile list.
     *
     * @return the size of the filtered profile list.
     */
    int getFilteredProfileListSize();

    /**
     * Retrieves the size of the unfiltered company list.
     *
     * @return the size of the unfiltered company list.
     */
    int getUnFilteredCompanyListSize();

    /**
     * Retrieves the size of the unfiltered application list.
     *
     * @return the size of the unfiltered application list.
     */
    int getUnFilteredApplicationListSize();

    /**
     * Retrieves the size of the unfiltered profile list.
     *
     * @return the size of the unfiltered profile list.
     */
    int getUnFilteredProfileListSize();

}
