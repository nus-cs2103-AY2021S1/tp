package seedu.internhunter.model;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.LogsCenter;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.tab.Tab;
import seedu.internhunter.model.tab.TabManager;
import seedu.internhunter.model.view.View;
import seedu.internhunter.model.view.ViewManager;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Represents the in-memory model of the InternHunter app data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FilterableItemList<CompanyItem> companyList;
    private final FilterableItemList<ApplicationItem> applicationList;
    private final FilterableItemList<ProfileItem> profileList;
    private final UserPrefs userPrefs;
    private final Tab tabControl;
    private final View viewControl;

    /**
     * Initializes a ModelManager with the given InternHunter and userPrefs.
     */
    public ModelManager(
            ReadOnlyItemList<CompanyItem> companyList,
            ReadOnlyItemList<ApplicationItem> applicationList,
            ReadOnlyItemList<ProfileItem> profileList,
            ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(companyList, applicationList, profileList, userPrefs);

        logger.fine("Initializing InternHunter: "
                + " and company list " + companyList
                + " and application list " + applicationList
                + " and profile list " + profileList
                + " and user prefs " + userPrefs);

        this.companyList = new ItemListManager<>(new ItemList<>(companyList));
        this.applicationList = new ItemListManager<>(new ItemList<>(applicationList));
        this.profileList = new ItemListManager<>(new ItemList<>(profileList));
        this.userPrefs = new UserPrefs(userPrefs);
        this.tabControl = new TabManager();
        this.viewControl = new ViewManager();
    }

    /**
     * Initializes a ModelManager with empty lists and with the de facto user prefs of InternHunter.
     */
    public ModelManager() {
        this(new ItemList<>(), new ItemList<>(), new ItemList<>(), new UserPrefs());
    }

    //=========== Company Methods ============================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public FilterableItemList<CompanyItem> getCompanyList() {
        return companyList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<CompanyItem> getFilteredCompanyList() {
        return companyList.getFilteredItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFilteredCompanyListSize() {
        return companyList.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemList<CompanyItem> getUnfilteredCompanyList() {
        return companyList.getUnfilteredItemList();
    }

    /**
     * Returns the company item list
     */
    @Override
    public ObservableList<CompanyItem> getCompanyItemList() {
        return companyList.getItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCompany(CompanyItem companyItem) {
        return companyList.hasItem(companyItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCompany(CompanyItem target) {
        companyList.deleteItem(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCompany(CompanyItem companyItem) {
        companyList.addItem(companyItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompany(CompanyItem target, CompanyItem editedCompanyItem) {
        companyList.setItem(target, editedCompanyItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate) {
        companyList.updateFilteredItemList(predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompanyList(ItemList<CompanyItem> companyList) {
        this.companyList.setItemList(companyList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompanyItem getCompanyItemFromFilteredList(int index) {
        return companyList.getItemFromFilteredItemList(index);
    }

    //=========== Application Methods ========================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public FilterableItemList<ApplicationItem> getApplicationList() {
        return applicationList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<ApplicationItem> getFilteredApplicationList() {
        return applicationList.getFilteredItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFilteredApplicationListSize() {
        return applicationList.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemList<ApplicationItem> getUnfilteredApplicationList() {
        return applicationList.getUnfilteredItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasApplication(ApplicationItem applicationItem) {
        return applicationList.hasItem(applicationItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteApplication(ApplicationItem target) {
        applicationList.deleteItem(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSameApplication(ApplicationItem target) {
        applicationList.deleteSameItem(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addApplication(ApplicationItem applicationItem) {
        applicationList.addItem(applicationItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem) {
        applicationList.setItem(target, editedApplicationItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate) {
        applicationList.updateFilteredItemList(predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationList(ItemList<ApplicationItem> applicationList) {
        this.applicationList.setItemList(applicationList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<ApplicationItem> getApplicationItemList() {
        return applicationList.getItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationItem getApplicationItemFromFilteredList(int index) {
        return applicationList.getItemFromFilteredItemList(index);
    }

    //=========== Profile Methods ============================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public FilterableItemList<ProfileItem> getProfileList() {
        return profileList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<ProfileItem> getFilteredProfileList() {
        return profileList.getFilteredItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFilteredProfileListSize() {
        return profileList.getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getUnFilteredCompanyListSize() {
        return getCompanyItemList().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getUnFilteredApplicationListSize() {
        return getApplicationItemList().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getUnFilteredProfileListSize() {
        return getProfileItemList().size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemList<ProfileItem> getUnfilteredProfileList() {
        return profileList.getUnfilteredItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableList<ProfileItem> getProfileItemList() {
        return profileList.getItemList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasProfileItem(ProfileItem profileItem) {
        return profileList.hasItem(profileItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProfileItem(ProfileItem target) {
        profileList.deleteItem(target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProfileItem(ProfileItem profileItem) {
        profileList.addItem(profileItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfileItem(ProfileItem target, ProfileItem editedProfileItem) {
        profileList.setItem(target, editedProfileItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFilteredProfileList(Predicate<? super ProfileItem> predicate) {
        profileList.updateFilteredItemList(predicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfileList(ItemList<ProfileItem> profileList) {
        this.profileList.setItemList(profileList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfileItem getProfileItemFromFilteredList(int index) {
        return profileList.getItemFromFilteredItemList(index);
    }
    //=========== UserPrefs ==================================================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    //=========== Tab Control Accessors =============================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public TabName getTabName() {
        return tabControl.getTabName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTabName(TabName tabName) {
        tabControl.setTabName(tabName);
    }

    //=========== View Control Accessors =============================================================

    /**
     * {@inheritDoc}
     */
    @Override
    public Index getCompanyViewIndex() {
        return viewControl.getCompanyViewIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Index getApplicationViewIndex() {
        return viewControl.getApplicationViewIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Index getProfileViewIndex() {
        return viewControl.getProfileViewIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCompanyViewIndex(Index index) {
        viewControl.setCompanyViewIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationViewIndex(Index index) {
        viewControl.setApplicationViewIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProfileViewIndex(Index index) {
        viewControl.setProfileViewIndex(index);
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
        return companyList.equals(other.companyList)
                && applicationList.equals(other.applicationList)
                && profileList.equals(other.profileList)
                && userPrefs.equals(other.userPrefs)
                && tabControl.equals(other.tabControl)
                && viewControl.equals(other.viewControl);
    }
}
