package seedu.internhunter.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_APPLIED_MIX_CASE;
import static seedu.internhunter.testutil.application.SampleApplicationItems.FACEBOOK_ACCEPTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.GOLDMAN_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.LAZADA_REJECTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getApplicationItems;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_AMAZON;
import static seedu.internhunter.testutil.company.SampleCompanyItems.AMAZON;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getCompanyItems;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.testutil.profile.SampleProfileItems.BYTEDANCE_INTERN;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GRAPHQL_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.MS_HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.NUS_MODS_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.ORBITAL_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getProfileItems;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;

public class ItemListTest {

    private ItemList<CompanyItem> companyItems;
    private ItemList<ApplicationItem> applicationItems;
    private ItemList<ProfileItem> profileItems;
    private List<CompanyItem> companyItemOne;
    private List<ApplicationItem> applicationItemOne;
    private List<ProfileItem> profileItemOne;
    private ObservableList<CompanyItem> companyItemObservableList;
    private ObservableList<ApplicationItem> applicationItemObservableList;
    private ObservableList<ProfileItem> profileItemObservableList;
    private List<CompanyItem> emptyCompanyList;
    private List<ApplicationItem> emptyApplicationList;
    private List<ProfileItem> emptyProfileList;

    @BeforeEach
    public void setUp() {
        companyItems = getSampleCompanyList();
        applicationItems = getSampleApplicationItemList();
        profileItems = getSampleProfileItemList();
        companyItemOne = new ArrayList<>();
        companyItemOne.add(GOOGLE);
        applicationItemOne = new ArrayList<>();
        applicationItemOne.add(GOLDMAN_OFFERED);
        profileItemOne = new ArrayList<>();
        profileItemOne.add(NUS_MODS_EXPERIENCE);
        companyItemObservableList = FXCollections.observableList(companyItemOne);
        applicationItemObservableList = FXCollections.observableList(applicationItemOne);
        profileItemObservableList = FXCollections.observableList(profileItemOne);
        emptyCompanyList = new ArrayList<>();
        emptyApplicationList = new ArrayList<>();
        emptyProfileList = new ArrayList<>();
    }

    @Test
    public void getItemList_equals_true() {
        assertEquals(getCompanyItems(), companyItems.getItemList());
        assertEquals(getApplicationItems(), applicationItems.getItemList());
        assertEquals(getProfileItems(), profileItems.getItemList());
    }

    @Test
    public void getItemList_equals_false() {
        assertNotEquals(emptyCompanyList, companyItems.getItemList());
        assertNotEquals(emptyApplicationList, applicationItems.getItemList());
        assertNotEquals(emptyProfileList, profileItems.getItemList());
    }

    @Test
    public void setItems_equals_true() {
        companyItems.setItems(companyItemOne);
        applicationItems.setItems(applicationItemOne);
        profileItems.setItems(profileItemOne);

        assertEquals(companyItemObservableList, companyItems.getItemList());
        assertEquals(applicationItemObservableList, applicationItems.getItemList());
        assertEquals(profileItemObservableList, profileItems.getItemList());
    }

    @Test
    public void setItems_equals_false() {
        companyItems.setItems(companyItemOne);
        applicationItems.setItems(applicationItemOne);
        profileItems.setItems(profileItemOne);
        ObservableList<CompanyItem> emptyCompanyItemObservableList = FXCollections.observableArrayList();
        ObservableList<ApplicationItem> emptyApplicationItemObservableList = FXCollections.observableArrayList();
        ObservableList<ProfileItem> emptyProfileItemObservableList = FXCollections.observableArrayList();

        assertNotEquals(emptyCompanyItemObservableList, companyItems.getItemList());
        assertNotEquals(emptyApplicationItemObservableList, applicationItems.getItemList());
        assertNotEquals(emptyProfileItemObservableList, profileItems.getItemList());
    }

    @Test
    public void hasItems_true() {
        assertTrue(companyItems.hasItem(GOOGLE));
        assertTrue(companyItems.hasItem(GOLDMAN));
        assertTrue(companyItems.hasItem(FACEBOOK));

        assertTrue(applicationItems.hasItem(GOLDMAN_OFFERED));
        assertTrue(applicationItems.hasItem(SHOPEE_OFFERED));
        assertTrue(applicationItems.hasItem(LAZADA_REJECTED));

        assertTrue(profileItems.hasItem(HTML_SKILL));
        assertTrue(profileItems.hasItem(GOVTECH_EXPERIENCE));
        assertTrue(profileItems.hasItem(NUS_MODS_EXPERIENCE));
        assertTrue(profileItems.hasItem(MS_HACKATHON_ACHIEVEMENT));
    }

    @Test
    public void hasItems_false() {
        assertFalse(companyItems.hasItem(AMAZON));

        assertFalse(applicationItems.hasItem(FACEBOOK_ACCEPTED));

        assertFalse(profileItems.hasItem(GRAPHQL_SKILL));
        assertFalse(profileItems.hasItem(ORBITAL_ACHIEVEMENT));
        assertFalse(profileItems.hasItem(BYTEDANCE_INTERN));
        assertFalse(profileItems.hasItem(HACKATHON_ACHIEVEMENT));
    }

    @Test
    public void addItems_success() {
        companyItems.addItem(AMAZON);
        applicationItems.addItem(FACEBOOK_ACCEPTED);
        profileItems.addItem(GRAPHQL_SKILL);

        // use hasItems to test since it is bug-free
        assertTrue(companyItems.hasItem(AMAZON));
        assertTrue(applicationItems.hasItem(FACEBOOK_ACCEPTED));
        assertTrue(profileItems.hasItem(GRAPHQL_SKILL));
    }

    @Test
    public void setItems_success() {
        companyItems.setItem(GOOGLE, AMAZON);
        applicationItems.setItem(GOLDMAN_OFFERED, FACEBOOK_ACCEPTED);
        profileItems.setItem(HTML_SKILL, GRAPHQL_SKILL);

        // use hasItems to test since it is bug-free
        assertTrue(companyItems.hasItem(AMAZON));
        assertFalse(companyItems.hasItem(GOOGLE));

        assertTrue(applicationItems.hasItem(FACEBOOK_ACCEPTED));
        assertFalse(applicationItems.hasItem(GOLDMAN_OFFERED));

        assertTrue(profileItems.hasItem(GRAPHQL_SKILL));
        assertFalse(profileItems.hasItem(HTML_SKILL));
    }

    @Test
    public void removeItem_success() {
        companyItems.removeItem(GOOGLE);
        applicationItems.removeItem(GOLDMAN_OFFERED);
        profileItems.removeItem(HTML_SKILL);

        // use hasItems to test since it is bug-free
        assertFalse(companyItems.hasItem(GOOGLE));
        assertFalse(applicationItems.hasItem(GOLDMAN_OFFERED));
        assertFalse(profileItems.hasItem(HTML_SKILL));
    }

    @Test
    public void removeSameItem_sameItem_success() {
        // use contains to test as there is successful removal

        ApplicationItem editApplicationItem =
                new ApplicationItemBuilder(GOLDMAN_OFFERED).withStatus(VALID_STATUS_APPLIED_MIX_CASE).build();
        CompanyItem editedCompanyItem = new CompanyItemBuilder(GOOGLE).withPhone(VALID_PHONE_AMAZON).build();
        ProfileItem editedProfileItem =
                new ProfileItemBuilder(HTML_SKILL).withDescriptors(VALID_DESCRIPTOR_LEARN).build();

        companyItems.removeSameItem(editedCompanyItem);
        applicationItems.removeSameItem(editApplicationItem);
        profileItems.removeSameItem(editedProfileItem);

        assertFalse(companyItems.hasItem(GOOGLE));
        assertFalse(applicationItems.hasItem(GOLDMAN_OFFERED));
        assertFalse(profileItems.hasItem(HTML_SKILL));
    }

    @Test
    public void testToString_equals_true() {
        assertEquals("3 items", companyItems.toString());
        assertEquals("3 items", applicationItems.toString());
        assertEquals("4 items", profileItems.toString());
    }

    @Test
    public void equals() {
        // same object -> true
        assertTrue(companyItems.equals(companyItems));
        assertTrue(applicationItems.equals(applicationItems));
        assertTrue(profileItems.equals(profileItems));

        // same value -> true
        assertTrue(companyItems.equals(getSampleCompanyList()));
        assertTrue(applicationItems.equals(getSampleApplicationItemList()));
        assertTrue(profileItems.equals(getSampleProfileItemList()));

        // null -> false
        assertFalse(companyItems.equals(null));
        assertFalse(applicationItems.equals(null));
        assertFalse(profileItems.equals(null));

        // different types -> false
        assertFalse(companyItems.equals(0.5f));
        assertFalse(applicationItems.equals(0.5f));
        assertFalse(profileItems.equals(0.5f));

    }

    @Test
    public void hashCodeTest_equals() {
        // same ItemList object, same hash -> equals
        assertEquals(companyItems.hashCode(), companyItems.hashCode());
        assertEquals(applicationItems.hashCode(), applicationItems.hashCode());
        assertEquals(profileItems.hashCode(), profileItems.hashCode());

        // same value item, same hash -> equals
        assertEquals(getCompanyItems().hashCode(), companyItems.hashCode());
        assertEquals(getApplicationItems().hashCode(), applicationItems.hashCode());
        assertEquals(getProfileItems().hashCode(), profileItems.hashCode());

        // different ItemList, different hash -> not equals
        assertNotEquals(companyItems.hashCode(), applicationItems.hashCode());
        assertNotEquals(companyItems.hashCode(), profileItemOne.hashCode());
        assertNotEquals(applicationItems.hashCode(), profileItemOne.hashCode());
    }
}
