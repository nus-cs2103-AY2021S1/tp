package seedu.internhunter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.internhunter.model.util.SampleDataUtil.getSampleApplicationItemList;
import static seedu.internhunter.model.util.SampleDataUtil.getSampleCompanyItemList;
import static seedu.internhunter.model.util.SampleDataUtil.getSampleProfileItemList;
import static seedu.internhunter.testutil.application.SampleApplicationItems.GOLDMAN_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.LAZADA_REJECTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.MS_HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.NUS_MODS_EXPERIENCE;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.core.Config;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.storage.ItemListStorage;
import seedu.internhunter.storage.JsonItemListStorage;
import seedu.internhunter.storage.JsonUserPrefsStorage;
import seedu.internhunter.storage.Storage;
import seedu.internhunter.storage.StorageManager;
import seedu.internhunter.storage.UserPrefsStorage;
import seedu.internhunter.storage.application.JsonAdaptedApplicationItem;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;
import seedu.internhunter.storage.profile.JsonAdaptedProfileItem;

public class MainAppUtilTest {

    private static final Path TEST_DATA_FOLDER_APPLICATION = Paths.get("src",
        "test", "data", "JsonSerializableApplicationItemListTest");
    private static final Path SAMPLE_APPLICATION_ITEMS_FILE = TEST_DATA_FOLDER_APPLICATION.resolve(
        "sampleApplicationItemList.json");
    private static final Path INVALID_APPLICATION_ITEM_FILE = TEST_DATA_FOLDER_APPLICATION.resolve(
        "invalidApplicationItemList.json");
    private static final Path TEST_DATA_FOLDER_COMPANY = Paths.get("src",
        "test", "data", "JsonSerializableCompanyItemListTest");
    private static final Path SAMPLE_COMPANY_ITEMS_FILE = TEST_DATA_FOLDER_COMPANY.resolve(
        "sampleCompanyItemList.json");
    private static final Path INVALID_COMPANY_ITEM_FILE = TEST_DATA_FOLDER_COMPANY.resolve(
        "invalidCompanyItemList.json");
    private static final Path TEST_DATA_FOLDER_PROFILE = Paths.get("src",
        "test", "data", "JsonSerializableProfileItemListTest");
    private static final Path SAMPLE_PROFILE_ITEMS_FILE = TEST_DATA_FOLDER_PROFILE.resolve(
        "sampleProfileItemList.json");
    private static final Path INVALID_PROFILE_ITEM_FILE = TEST_DATA_FOLDER_PROFILE.resolve(
        "invalidProfileItemList.json");

    @TempDir
    public Path testFolder;

    private JsonUserPrefsStorage userPrefsStorage;
    private Storage emptyStorageManager;
    private Storage nonEmptyStorageManager;
    private Storage invalidItemStorageManager;
    private ReadOnlyItemList<ApplicationItem> applicationItemReadOnlyItemList;
    private ReadOnlyItemList<CompanyItem> companyItemReadOnlyItemList;
    private ReadOnlyItemList<ProfileItem> profileItemReadOnlyItemList;
    private ItemList<ApplicationItem> applicationItemItemList;
    private ItemList<CompanyItem> companyItemItemList;
    private ItemList<ProfileItem> profileItemItemList;

    @BeforeEach
    public void setUp() {
        userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        initEmptyStorageManager();
        initNonEmptyStorageManager();
        initInvalidStorageManager();
        applicationItemReadOnlyItemList = new ItemList<>();
        companyItemReadOnlyItemList = new ItemList<>();
        profileItemReadOnlyItemList = new ItemList<>();
        applicationItemReadOnlyItemList = new ItemList<>();
        applicationItemItemList = new ItemList<>();
        applicationItemItemList.addItem(GOLDMAN_OFFERED);
        applicationItemItemList.addItem(SHOPEE_OFFERED);
        applicationItemItemList.addItem(LAZADA_REJECTED);
        companyItemItemList = new ItemList<>();
        companyItemItemList.addItem(GOOGLE);
        companyItemItemList.addItem(GOLDMAN);
        companyItemItemList.addItem(FACEBOOK);
        profileItemItemList = new ItemList<>();
        profileItemItemList.addItem(HTML_SKILL);
        profileItemItemList.addItem(GOVTECH_EXPERIENCE);
        profileItemItemList.addItem(NUS_MODS_EXPERIENCE);
        profileItemItemList.addItem(MS_HACKATHON_ACHIEVEMENT);
    }

    public void initInvalidStorageManager() {
        ItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
            new JsonItemListStorage<>(INVALID_APPLICATION_ITEM_FILE, ApplicationItem.class,
                JsonAdaptedApplicationItem.class);
        ItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage = new JsonItemListStorage<>(
            INVALID_COMPANY_ITEM_FILE, CompanyItem.class, JsonAdaptedCompanyItem.class);
        ItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage = new JsonItemListStorage<>(
            INVALID_PROFILE_ITEM_FILE, ProfileItem.class, JsonAdaptedProfileItem.class);

        invalidItemStorageManager = new StorageManager(applicationItemListStorage, companyItemListStorage,
            profileItemListStorage, userPrefsStorage);
    }

    public void initNonEmptyStorageManager() {
        ItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
            new JsonItemListStorage<>(SAMPLE_APPLICATION_ITEMS_FILE, ApplicationItem.class,
                JsonAdaptedApplicationItem.class);
        ItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage = new JsonItemListStorage<>(
            SAMPLE_COMPANY_ITEMS_FILE, CompanyItem.class, JsonAdaptedCompanyItem.class);
        ItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage = new JsonItemListStorage<>(
            SAMPLE_PROFILE_ITEMS_FILE, ProfileItem.class, JsonAdaptedProfileItem.class);

        nonEmptyStorageManager = new StorageManager(applicationItemListStorage, companyItemListStorage,
            profileItemListStorage, userPrefsStorage);
    }

    public void initEmptyStorageManager() {
        JsonItemListStorage<ApplicationItem, JsonAdaptedApplicationItem> applicationItemListStorage =
            new JsonItemListStorage<>(getTempFilePath("app"), ApplicationItem.class,
                JsonAdaptedApplicationItem.class);
        JsonItemListStorage<CompanyItem, JsonAdaptedCompanyItem> companyItemListStorage =
            new JsonItemListStorage<>(getTempFilePath("com"), CompanyItem.class,
                JsonAdaptedCompanyItem.class);
        JsonItemListStorage<ProfileItem, JsonAdaptedProfileItem> profileItemListStorage =
            new JsonItemListStorage<>(getTempFilePath("me"), ProfileItem.class,
                JsonAdaptedProfileItem.class);

        emptyStorageManager = new StorageManager(applicationItemListStorage, companyItemListStorage,
            profileItemListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void initApplicationItemList_empty_equals() {
        assertEquals(applicationItemReadOnlyItemList, MainAppUtil.initApplicationItemList(emptyStorageManager));
    }

    @Test
    public void initCompanyItemList_empty_equals() {
        assertEquals(companyItemReadOnlyItemList, MainAppUtil.initCompanyItemList(emptyStorageManager));
    }

    @Test
    public void initProfileItemList_empty_equals() {
        assertEquals(profileItemReadOnlyItemList, MainAppUtil.initProfileItemList(emptyStorageManager));
    }

    @Test
    public void initCompanyItemList_invalidItemsResultInEmptyList_equals() {
        assertEquals(companyItemReadOnlyItemList, MainAppUtil.initCompanyItemList(invalidItemStorageManager));
    }

    @Test
    public void initApplicationItemList_invalidItemsResultInEmptyList_equals() {
        assertEquals(applicationItemReadOnlyItemList, MainAppUtil.initApplicationItemList(invalidItemStorageManager));
    }

    @Test
    public void initProfileItemList_invalidItemsResultInEmptyList_equals() {
        assertEquals(profileItemReadOnlyItemList, MainAppUtil.initProfileItemList(invalidItemStorageManager));
    }

    @Test
    public void initApplicationItemList_nonEmpty_equals() {
        ReadOnlyItemList<ApplicationItem> applicationItemReadOnlyItemList = new ItemList<>(applicationItemItemList);
        assertEquals(applicationItemReadOnlyItemList, MainAppUtil.initApplicationItemList(nonEmptyStorageManager));
    }

    @Test
    public void initCompanyItemList_nonEmpty_equals() {
        ReadOnlyItemList<CompanyItem> companyItemReadOnlyItemList = new ItemList<>(companyItemItemList);
        assertEquals(companyItemReadOnlyItemList, MainAppUtil.initCompanyItemList(nonEmptyStorageManager));
    }

    @Test
    public void initProfileItemList_nonEmpty_equals() {
        ReadOnlyItemList<ProfileItem> profileItemReadOnlyItemList = new ItemList<>(profileItemItemList);
        assertEquals(profileItemReadOnlyItemList, MainAppUtil.initProfileItemList(nonEmptyStorageManager));
    }

    @Test
    public void initModelManager_emptyList_equals() {
        Model model = new ModelManager(getSampleCompanyItemList(), getSampleApplicationItemList(),
            getSampleProfileItemList(), new UserPrefs());
        assertEquals(model, MainAppUtil.initModelManager(emptyStorageManager, new UserPrefs()));
    }

    @Test
    public void initModelManager_nonEmptyList_equals() {
        ReadOnlyItemList<ProfileItem> profileItemReadOnlyItemList = new ItemList<>(profileItemItemList);
        // Internship and Company Do not match as a result both will have 0 items
        Model model = new ModelManager(companyItemReadOnlyItemList, applicationItemReadOnlyItemList,
            profileItemReadOnlyItemList, new UserPrefs());
        assertEquals(model, MainAppUtil.initModelManager(nonEmptyStorageManager, new UserPrefs()));
    }

    /**
     * Note that we are only able to test the invalid path as valid path will have its result being overwritten.
     */
    @Test
    public void initConfig_testInvalidPath_equals() {
        assertEquals(new Config(), MainAppUtil.initConfig(getTempFilePath("invalid")));
        assertEquals(new Config(), MainAppUtil.initConfig(null));
    }

    /**
     * Note that we are only able to test the invalid storage as valid storage will have its result being overwritten.
     */
    @Test
    public void initPrefs_testPathForStorage() {
        UserPrefsStorage invalidUserPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("invalid"));
        assertEquals(new UserPrefs(), MainAppUtil.initPrefs(invalidUserPrefsStorage));
        assertThrows(NullPointerException.class, () -> MainAppUtil.initPrefs(null));
    }
}
