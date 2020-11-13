package seedu.internhunter.logic.commands.add;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.FilterableItemList;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ReadOnlyUserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Model Stub which implements all of models methods.
 */
public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public FilterableItemList<CompanyItem> getCompanyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<CompanyItem> getFilteredCompanyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ItemList<CompanyItem> getUnfilteredCompanyList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<CompanyItem> getCompanyItemList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCompany(CompanyItem companyItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCompany(CompanyItem target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCompany(CompanyItem companyItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCompany(CompanyItem target, CompanyItem editedCompanyItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCompanyList(Predicate<? super CompanyItem> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCompanyList(ItemList<CompanyItem> companyList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public FilterableItemList<ApplicationItem> getApplicationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ApplicationItem> getFilteredApplicationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ItemList<ApplicationItem> getUnfilteredApplicationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasApplication(ApplicationItem applicationItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteApplication(ApplicationItem target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteSameApplication(ApplicationItem target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addApplication(ApplicationItem applicationItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setApplication(ApplicationItem target, ApplicationItem editedApplicationItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredApplicationList(Predicate<? super ApplicationItem> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setApplicationList(ItemList<ApplicationItem> applicationList) {

        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ApplicationItem> getApplicationItemList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public FilterableItemList<ProfileItem> getProfileList() {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public ObservableList<ProfileItem> getFilteredProfileList() {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public ItemList<ProfileItem> getUnfilteredProfileList() {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public ObservableList<ProfileItem> getProfileItemList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasProfileItem(ProfileItem profileItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteProfileItem(ProfileItem target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addProfileItem(ProfileItem profileItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setProfileItem(ProfileItem target, ProfileItem editedProfileItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredProfileList(Predicate<? super ProfileItem> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setProfileList(ItemList<ProfileItem> profileList) {

        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTabName(TabName tabName) {

        throw new AssertionError("This method should not be called.");
    }

    @Override
    public TabName getTabName() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setCompanyViewIndex(Index index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setApplicationViewIndex(Index index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setProfileViewIndex(Index index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getCompanyViewIndex() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getApplicationViewIndex() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Index getProfileViewIndex() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ProfileItem getProfileItemFromFilteredList(int index) {
        throw new AssertionError("This method should not be called.");

    }

    @Override
    public CompanyItem getCompanyItemFromFilteredList(int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ApplicationItem getApplicationItemFromFilteredList(int index) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getFilteredCompanyListSize() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getFilteredApplicationListSize() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getFilteredProfileListSize() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getUnFilteredCompanyListSize() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getUnFilteredApplicationListSize() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getUnFilteredProfileListSize() {
        throw new AssertionError("This method should not be called.");
    }
}
