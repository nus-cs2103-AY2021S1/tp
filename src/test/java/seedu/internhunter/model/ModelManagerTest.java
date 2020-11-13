package seedu.internhunter.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.commons.core.GuiSettings;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.application.ApplicationNameContainsKeyWordsPredicate;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.model.profile.ProfileItemContainsKeywordPredicate;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class ModelManagerTest {

    private ModelManager modelManager;
    private CompanyItemBuilder companyItemBuilder;
    private ApplicationItemBuilder applicationItemBuilder;
    private ProfileItemBuilder profileItemBuilder;
    private ObservableList<CompanyItem> companyItemList;
    private ObservableList<ApplicationItem> applicationItemList;
    private ObservableList<ProfileItem> profileItemList;
    private ObservableList<CompanyItem> emptyCompanyItemList;
    private ObservableList<ApplicationItem> emptyApplicationItemList;
    private ObservableList<ProfileItem> emptyProfileItemList;

    @BeforeEach
    public void setUp() {
        modelManager = new ModelManager();

        companyItemBuilder = new CompanyItemBuilder();
        applicationItemBuilder = new ApplicationItemBuilder();
        profileItemBuilder = new ProfileItemBuilder();

        companyItemList = FXCollections.observableArrayList();
        companyItemList.add(companyItemBuilder.build());

        applicationItemList = FXCollections.observableArrayList();
        applicationItemList.add(applicationItemBuilder.build());

        profileItemList = FXCollections.observableArrayList();
        profileItemList.add(profileItemBuilder.build());

        emptyProfileItemList = FXCollections.observableArrayList();
        emptyApplicationItemList = FXCollections.observableArrayList();
        emptyCompanyItemList = FXCollections.observableArrayList();
    }

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ItemListManager<CompanyItem>(), modelManager.getCompanyList());
        assertEquals(new ItemListManager<ApplicationItem>(), modelManager.getApplicationList());
        assertEquals(new ItemListManager<ProfileItem>(), modelManager.getProfileList());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setApplicationItemListFilePath(Paths.get("app/item/list/file/path"));
        userPrefs.setCompanyItemListFilePath(Paths.get("com/item/list/file/path"));
        userPrefs.setProfileItemListFilePath(Paths.get("app/me/list/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setApplicationItemListFilePath(Paths.get("new/app/item/list/file/path"));
        userPrefs.setCompanyItemListFilePath(Paths.get("new/com/item/list/file/path"));
        userPrefs.setProfileItemListFilePath(Paths.get("new/app/me/list/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void hasCompany_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCompany(null));
    }

    @Test
    public void hasApplication_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasApplication(null));
    }

    @Test
    public void hasProfile_nullProfile_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasProfileItem(null));
    }

    @Test
    public void hasCompany_companyNotInCompanyList_returnsFalse() {
        assertFalse(modelManager.hasCompany(companyItemBuilder.build()));
    }

    @Test
    public void hasApplication_applicationNotInApplicationList_returnsFalse() {
        assertFalse(modelManager.hasApplication(applicationItemBuilder.build()));
    }

    @Test
    public void hasProfileItem_profileItemNotInProfileItemList_returnsFalse() {
        assertFalse(modelManager.hasProfileItem(profileItemBuilder.build()));
    }

    @Test
    public void addCompany_addCompanyItemToList_returnsEquals() {
        modelManager.addCompany(companyItemBuilder.build());
        assertEquals(companyItemList, modelManager.getFilteredCompanyList());
    }

    @Test
    public void addApplication_addApplicationItemToList_returnsEquals() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertEquals(applicationItemList, modelManager.getFilteredApplicationList());
    }

    @Test
    public void addProfile_addProfileItemToList_returnsEquals() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(profileItemList, modelManager.getFilteredProfileList());
    }

    @Test
    public void hasCompany_companyInCompanyList_returnsTrue() {
        modelManager.addCompany(companyItemBuilder.build());
        assertTrue(modelManager.hasCompany(companyItemBuilder.build()));
    }

    @Test
    public void hasApplication_applicationInApplicationList_returnsTrue() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertTrue(modelManager.hasApplication(applicationItemBuilder.build()));
    }

    @Test
    public void hasProfileItem_profileItemInProfileItemList_returnsTrue() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertTrue(modelManager.hasProfileItem(profileItemBuilder.build()));
    }

    @Test
    public void getFilteredCompanyList_modifyCompanyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCompanyList()
            .remove(0));
    }

    @Test
    public void getFilteredApplicationList_modifyApplicationList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredApplicationList()
            .remove(0));
    }

    @Test
    public void getFilteredProfileList_modifyProfileList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredProfileList()
            .remove(0));
    }

    @Test
    public void getUnFilteredCompanyListSize_true() {
        modelManager.addCompany(companyItemBuilder.build());
        assertEquals(1, modelManager.getUnFilteredCompanyListSize());
    }

    @Test
    public void getUnFilteredApplicationListSize_true() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertEquals(1, modelManager.getUnFilteredApplicationListSize());
    }

    @Test
    public void getUnfilteredProfileListSize_true() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(1, modelManager.getUnFilteredProfileListSize());
    }

    @Test
    public void getFilteredCompanyListSize_getCompanyListSize_returnsEqual() {
        modelManager.addCompany(companyItemBuilder.build());
        CompanyNameContainsKeyWordsPredicate predicate =
            new CompanyNameContainsKeyWordsPredicate(Arrays.asList("empty"));
        modelManager.updateFilteredCompanyList(predicate);
        assertEquals(0, modelManager.getFilteredCompanyListSize());
    }

    @Test
    public void getFilteredApplicationListSize_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        ApplicationNameContainsKeyWordsPredicate predicate =
            new ApplicationNameContainsKeyWordsPredicate(Arrays.asList("empty"));
        modelManager.updateFilteredApplicationList(predicate);
        assertEquals(0, modelManager.getFilteredApplicationListSize());
    }

    @Test
    public void getFilteredProfileListSize_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        ProfileItemContainsKeywordPredicate predicate =
            new ProfileItemContainsKeywordPredicate(Arrays.asList("empty"));
        modelManager.updateFilteredProfileList(predicate);
        assertEquals(0, modelManager.getFilteredProfileListSize());
    }

    @Test
    public void getCompanyItemList_testIfEqualCompanyList_returnsEqual() {
        modelManager.addCompany(companyItemBuilder.build());
        assertEquals(companyItemList, modelManager.getCompanyItemList());
    }

    @Test
    public void getProfileItemList_testIfEqualProfileList_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        assertEquals(profileItemList, modelManager.getProfileItemList());
    }

    @Test
    public void getApplicationItemList_testIfEqualApplicationList_returnsEquals() {
        modelManager.addApplication(applicationItemBuilder.build());
        assertEquals(applicationItemList, modelManager.getApplicationItemList());
    }

    @Test
    public void deleteCompany_deleteCompanyFromList_returnsEqual() {
        modelManager.addCompany(companyItemBuilder.build());
        modelManager.deleteCompany(companyItemBuilder.build());
        assertEquals(emptyCompanyItemList, modelManager.getFilteredCompanyList());
    }

    @Test
    public void deleteApplication_deleteApplicationFromList_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        modelManager.deleteApplication(applicationItemBuilder.build());
        assertEquals(emptyApplicationItemList, modelManager.getFilteredApplicationList());
    }

    @Test
    public void deleteProfileItem_deleteProfileItemFromList_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        modelManager.deleteProfileItem(profileItemBuilder.build());
        assertEquals(emptyProfileItemList, modelManager.getFilteredProfileList());
    }

    @Test
    public void getUnfilteredCompanyList_equals_true() {
        modelManager.addCompany(companyItemBuilder.build());
        ItemList<CompanyItem> companyItemItemList = new ItemList<>();
        companyItemItemList.addItem(companyItemBuilder.build());
        assertEquals(companyItemItemList, modelManager.getUnfilteredCompanyList());
    }

    @Test
    public void getUnfilteredProfileList_equals_true() {
        modelManager.addProfileItem(profileItemBuilder.build());
        ItemList<ProfileItem> profileItemItemList = new ItemList<>();
        profileItemItemList.addItem(profileItemBuilder.build());
        assertEquals(profileItemItemList, modelManager.getUnfilteredProfileList());
    }

    @Test
    public void getUnfilteredApplicationList_equals_true() {
        modelManager.addApplication(applicationItemBuilder.build());
        ItemList<ApplicationItem> applicationItemItemList = new ItemList<>();
        applicationItemItemList.addItem(applicationItemBuilder.build());
        assertEquals(applicationItemItemList, modelManager.getUnfilteredApplicationList());
    }

    @Test
    public void getCompanyList_equals() {
        modelManager.addCompany(companyItemBuilder.build());
        ItemList<CompanyItem> companyItemItemList = new ItemList<>();
        companyItemItemList.addItem(companyItemBuilder.build());
        ItemListManager<CompanyItem> companyItemItemListManager = new ItemListManager<>(companyItemItemList);
        FilterableItemList<CompanyItem> companyList = modelManager.getCompanyList();
        assertEquals(companyItemItemListManager, companyList);

        // testing equality also
        ItemListManager<CompanyItem> companyItemItemListManagerSample =
            new ItemListManager<>(getSampleCompanyList());
        assertFalse(applicationItemList.equals(companyItemItemListManagerSample));
        assertTrue(companyList.equals(companyList));
        assertFalse(companyList.equals(null));
        assertFalse(companyList.equals(0.5f));
    }

    @Test
    public void getApplicationList_equals() {
        modelManager.addApplication(applicationItemBuilder.build());
        ItemList<ApplicationItem> applicationItemItemList = new ItemList<>();
        applicationItemItemList.addItem(applicationItemBuilder.build());
        ItemListManager<ApplicationItem> applicationItemItemListManager =
            new ItemListManager<>(applicationItemItemList);
        FilterableItemList<ApplicationItem> applicationList = modelManager.getApplicationList();
        assertEquals(applicationItemItemListManager, applicationList);

        // testing equality also
        ItemListManager<ApplicationItem> applicationItemItemListManagerSample =
            new ItemListManager<>(getSampleApplicationItemList());
        assertFalse(applicationList.equals(applicationItemItemListManagerSample));
        assertTrue(applicationList.equals(applicationList));
        assertFalse(applicationList.equals(null));
        assertFalse(applicationList.equals(0.5f));
    }

    @Test
    public void getProfileList_equals_true() {
        modelManager.addProfileItem(profileItemBuilder.build());
        ItemList<ProfileItem> profileItemItemList = new ItemList<>();
        profileItemItemList.addItem(profileItemBuilder.build());
        ItemListManager<ProfileItem> profileItemItemListManager = new ItemListManager<>(profileItemItemList);
        FilterableItemList<ProfileItem> profileItemList = modelManager.getProfileList();
        assertEquals(profileItemItemListManager, profileItemList);

        // testing equality also
        ItemListManager<ProfileItem> profileItemItemListManagerSample =
            new ItemListManager<>(getSampleProfileItemList());
        assertFalse(profileItemList.equals(profileItemItemListManagerSample));
        assertTrue(profileItemList.equals(profileItemList));
        assertFalse(profileItemList.equals(null));
        assertFalse(profileItemList.equals(0.5f));
    }

    @Test
    public void setCompany_changeCompanyItem_returnsEqual() {
        modelManager.addCompany(companyItemBuilder.build());
        CompanyItemBuilder secondCompanyItemBuilder = new CompanyItemBuilder();
        secondCompanyItemBuilder.withPhone("91919191");
        modelManager.setCompany(companyItemBuilder.build(), secondCompanyItemBuilder.build());
        emptyCompanyItemList.add(secondCompanyItemBuilder.build());
        assertEquals(emptyCompanyItemList, modelManager.getFilteredCompanyList());
    }

    @Test
    public void setApplication_changeApplicationItem_returnsEqual() {
        modelManager.addApplication(applicationItemBuilder.build());
        ApplicationItemBuilder secondApplicationItemBuilder = new ApplicationItemBuilder();
        secondApplicationItemBuilder.withStatus("Interview");
        modelManager.setApplication(applicationItemBuilder.build(), secondApplicationItemBuilder.build());
        emptyApplicationItemList.add(secondApplicationItemBuilder.build());
        assertEquals(emptyApplicationItemList, modelManager.getFilteredApplicationList());
    }

    @Test
    public void setProfile_changeProfileItem_returnsEqual() {
        modelManager.addProfileItem(profileItemBuilder.build());
        ProfileItemBuilder secondProfileItemBuilder = new ProfileItemBuilder();
        secondProfileItemBuilder.withTitle("HELLO WORLD");
        modelManager.setProfileItem(profileItemBuilder.build(), secondProfileItemBuilder.build());
        emptyProfileItemList.add(secondProfileItemBuilder.build());
        assertEquals(emptyProfileItemList, modelManager.getFilteredProfileList());
    }

    @Test
    public void getProfileViewIndex_equals_success() {
        assertEquals(Index.fromOneBased(1), modelManager.getProfileViewIndex());
    }

    @Test
    public void getCompanyViewIndex_equals_success() {
        assertEquals(Index.fromOneBased(1), modelManager.getCompanyViewIndex());
    }

    @Test
    public void getApplicationViewIndex_equals_success() {
        assertEquals(Index.fromOneBased(1), modelManager.getApplicationViewIndex());
    }

    @Test
    public void setProfileViewIndex_equals_success() {
        modelManager.setProfileViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), modelManager.getProfileViewIndex());
    }

    @Test
    public void setCompanyViewIndex_equals_success() {
        modelManager.setCompanyViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), modelManager.getCompanyViewIndex());
    }

    @Test
    public void setApplicationViewIndex_equals_success() {
        modelManager.setApplicationViewIndex(Index.fromOneBased(10));
        assertEquals(Index.fromOneBased(10), modelManager.getApplicationViewIndex());
    }

    @Test
    public void getTabName_equals_success() {
        assertEquals(TabName.COMPANY, modelManager.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToCompanyTestEquals_success() {
        modelManager.setTabName(TabName.COMPANY);
        assertEquals(TabName.COMPANY, modelManager.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToApplicationTestEquals_success() {
        modelManager.setTabName(TabName.APPLICATION);
        assertEquals(TabName.APPLICATION, modelManager.getTabName());
    }

    @Test
    public void setTabName_changeTabNameToProfileTestEquals_success() {
        modelManager.setTabName(TabName.PROFILE);
        assertEquals(TabName.PROFILE, modelManager.getTabName());
    }

    @Test
    public void equals() {
        ItemList<CompanyItem> companyList = new ItemList<>();
        ItemList<ApplicationItem> applicationList = new ItemList<>();
        ItemList<ProfileItem> profileList = new ItemList<>();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(companyList, applicationList, profileList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(companyList, applicationList, profileList, userPrefs);
        assertEquals(modelManager, modelManagerCopy);

        // same object -> returns true
        assertEquals(modelManager, modelManager);

        // null -> returns false
        assertNotEquals(modelManager, null);

        // different types -> returns false
        assertNotEquals(modelManager, 5);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setApplicationItemListFilePath(Paths.get("differentFilePath"));
        assertNotEquals(modelManager, new ModelManager(companyList, applicationList, profileList, differentUserPrefs));

        // different company list -> return false
        assertNotEquals(modelManager, new ModelManager(getSampleCompanyList(), applicationList, profileList,
            userPrefs));

        // different application list -> return false
        assertNotEquals(modelManager, new ModelManager(companyList, getSampleApplicationItemList(), profileList,
            userPrefs));

        // different profile list -> return false
        assertNotEquals(modelManager, new ModelManager(companyList, applicationList, getSampleProfileItemList(),
            userPrefs));

        // different tab -> return false
        ModelManager modelManagerDifferentTab = new ModelManager(companyList, applicationList, profileList, userPrefs);
        modelManagerDifferentTab.setTabName(TabName.APPLICATION);
        assertNotEquals(modelManager, modelManagerDifferentTab);

        // different view -> return false
        ModelManager modelManagerDifferentView = new ModelManager(companyList, applicationList, profileList, userPrefs);
        modelManagerDifferentView.setApplicationViewIndex(Index.fromOneBased(2));
        assertNotEquals(modelManager, modelManagerDifferentView);
    }
}
