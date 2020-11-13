package seedu.internhunter.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_APPLIED_MIX_CASE;
import static seedu.internhunter.testutil.application.SampleApplicationItems.FACEBOOK_ACCEPTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.GOLDMAN_OFFERED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.LAZADA_REJECTED;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_AMAZON;
import static seedu.internhunter.testutil.company.SampleCompanyItems.AMAZON;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GRAPHQL_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.testutil.profile.SampleProfileItems.MS_HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.SampleProfileItems.NUS_MODS_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.ORBITAL_ACHIEVEMENT;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.exceptions.DuplicateItemException;
import seedu.internhunter.model.item.exceptions.ItemNotFoundException;
import seedu.internhunter.model.profile.ProfileItem;
import seedu.internhunter.testutil.application.ApplicationItemBuilder;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.testutil.profile.ProfileItemBuilder;

public class UniqueItemListTest {

    private UniqueItemList<CompanyItem> companyItemUniqueItemList;
    private UniqueItemList<ApplicationItem> applicationItemUniqueItemList;
    private UniqueItemList<ProfileItem> profileItemUniqueItemList;
    private UniqueItemList<CompanyItem> uniqueCompanyItemOne;
    private UniqueItemList<ApplicationItem> uniqueApplicationItemOne;
    private UniqueItemList<ProfileItem> uniqueProfileItemOne;
    private ObservableList<CompanyItem> companyItems;
    private ObservableList<ApplicationItem> applicationItems;
    private ObservableList<ProfileItem> profileItems;

    @BeforeEach
    public void setUp() {
        companyItemUniqueItemList = new UniqueItemList<>();
        companyItemUniqueItemList.add(GOOGLE);
        companyItemUniqueItemList.add(GOLDMAN);
        companyItemUniqueItemList.add(FACEBOOK);

        applicationItemUniqueItemList = new UniqueItemList<>();
        applicationItemUniqueItemList.add(GOLDMAN_OFFERED);
        applicationItemUniqueItemList.add(SHOPEE_OFFERED);
        applicationItemUniqueItemList.add(LAZADA_REJECTED);

        profileItemUniqueItemList = new UniqueItemList<>();
        profileItemUniqueItemList.add(HTML_SKILL);
        profileItemUniqueItemList.add(HACKATHON_ACHIEVEMENT);
        profileItemUniqueItemList.add(GOVTECH_EXPERIENCE);

        uniqueCompanyItemOne = new UniqueItemList<>();
        uniqueCompanyItemOne.add(GOOGLE);

        uniqueApplicationItemOne = new UniqueItemList<>();
        uniqueApplicationItemOne.add(GOLDMAN_OFFERED);

        uniqueProfileItemOne = new UniqueItemList<>();
        uniqueProfileItemOne.add(HTML_SKILL);

        companyItems = FXCollections.observableArrayList();
        companyItems.add(GOOGLE);

        applicationItems = FXCollections.observableArrayList();
        applicationItems.add(GOLDMAN_OFFERED);

        profileItems = FXCollections.observableArrayList();
        profileItems.add(HTML_SKILL);
    }

    @Test
    public void contains_true() {
        assertTrue(companyItemUniqueItemList.contains(GOOGLE));
        assertTrue(companyItemUniqueItemList.contains(GOLDMAN));
        assertTrue(companyItemUniqueItemList.contains(FACEBOOK));

        assertTrue(applicationItemUniqueItemList.contains(GOLDMAN_OFFERED));
        assertTrue(applicationItemUniqueItemList.contains(SHOPEE_OFFERED));
        assertTrue(applicationItemUniqueItemList.contains(LAZADA_REJECTED));

        assertTrue(profileItemUniqueItemList.contains(HTML_SKILL));
        assertTrue(profileItemUniqueItemList.contains(HACKATHON_ACHIEVEMENT));
        assertTrue(profileItemUniqueItemList.contains(GOVTECH_EXPERIENCE));
    }

    @Test
    public void contains_false() {
        assertFalse(companyItemUniqueItemList.contains(AMAZON));

        assertFalse(applicationItemUniqueItemList.contains(FACEBOOK_ACCEPTED));

        assertFalse(profileItemUniqueItemList.contains(GRAPHQL_SKILL));
        assertFalse(profileItemUniqueItemList.contains(NUS_MODS_EXPERIENCE));
        assertFalse(profileItemUniqueItemList.contains(MS_HACKATHON_ACHIEVEMENT));
    }

    @Test
    public void add_success() {
        // use contains to test as it is bug free
        companyItemUniqueItemList.add(AMAZON);
        applicationItemUniqueItemList.add(FACEBOOK_ACCEPTED);
        profileItemUniqueItemList.add(GRAPHQL_SKILL);

        assertTrue(companyItemUniqueItemList.contains(AMAZON));
        assertTrue(applicationItemUniqueItemList.contains(FACEBOOK_ACCEPTED));
        assertTrue(profileItemUniqueItemList.contains(GRAPHQL_SKILL));
    }

    @Test
    public void add_duplicateItem_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () -> companyItemUniqueItemList.add(GOOGLE));
        assertThrows(DuplicateItemException.class, () -> companyItemUniqueItemList.add(GOLDMAN));
        assertThrows(DuplicateItemException.class, () -> companyItemUniqueItemList.add(FACEBOOK));

        assertThrows(DuplicateItemException.class, () -> applicationItemUniqueItemList.add(GOLDMAN_OFFERED));
        assertThrows(DuplicateItemException.class, () -> applicationItemUniqueItemList.add(SHOPEE_OFFERED));
        assertThrows(DuplicateItemException.class, () -> applicationItemUniqueItemList.add(LAZADA_REJECTED));

        assertThrows(DuplicateItemException.class, () -> profileItemUniqueItemList.add(HTML_SKILL));
        assertThrows(DuplicateItemException.class, () -> profileItemUniqueItemList.add(HACKATHON_ACHIEVEMENT));
        assertThrows(DuplicateItemException.class, () -> profileItemUniqueItemList.add(GOVTECH_EXPERIENCE));
    }

    @Test
    public void setItem_success() {
        // use contains to test as it is bug free
        companyItemUniqueItemList.setItem(GOOGLE, AMAZON);
        applicationItemUniqueItemList.setItem(GOLDMAN_OFFERED, FACEBOOK_ACCEPTED);
        profileItemUniqueItemList.setItem(HTML_SKILL, GRAPHQL_SKILL);

        assertTrue(companyItemUniqueItemList.contains(AMAZON));
        assertFalse(companyItemUniqueItemList.contains(GOOGLE));

        assertTrue(applicationItemUniqueItemList.contains(FACEBOOK_ACCEPTED));
        assertFalse(applicationItemUniqueItemList.contains(GOLDMAN_OFFERED));

        assertTrue(profileItemUniqueItemList.contains(GRAPHQL_SKILL));
        assertFalse(profileItemUniqueItemList.contains(HTML_SKILL));
    }

    @Test
    public void setItem_itemNotFound_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> companyItemUniqueItemList.setItem(AMAZON, AMAZON));
        assertThrows(ItemNotFoundException.class, () -> applicationItemUniqueItemList.setItem(FACEBOOK_ACCEPTED,
                GOLDMAN_OFFERED));
        assertThrows(ItemNotFoundException.class, () -> profileItemUniqueItemList.setItem(GRAPHQL_SKILL, HTML_SKILL));
    }

    @Test
    public void setItem_itemExist_throwsDuplicateItemException() {
        assertThrows(DuplicateItemException.class, () -> companyItemUniqueItemList.setItem(GOOGLE, GOLDMAN));
        assertThrows(DuplicateItemException.class, () -> applicationItemUniqueItemList.setItem(GOLDMAN_OFFERED,
                LAZADA_REJECTED));
        assertThrows(DuplicateItemException.class, () -> profileItemUniqueItemList.setItem(HTML_SKILL,
                HACKATHON_ACHIEVEMENT));
    }

    @Test
    public void remove_success() {
        // use contains to test as it is bug free
        companyItemUniqueItemList.remove(GOOGLE);
        applicationItemUniqueItemList.remove(GOLDMAN_OFFERED);
        profileItemUniqueItemList.remove(HTML_SKILL);

        assertFalse(companyItemUniqueItemList.contains(GOOGLE));
        assertFalse(applicationItemUniqueItemList.contains(GOLDMAN_OFFERED));
        assertFalse(profileItemUniqueItemList.contains(HTML_SKILL));
    }

    @Test
    public void remove_itemNotFound_throwsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> companyItemUniqueItemList.remove(AMAZON));
        assertThrows(ItemNotFoundException.class, () -> applicationItemUniqueItemList.remove(FACEBOOK_ACCEPTED));
        assertThrows(ItemNotFoundException.class, () -> profileItemUniqueItemList.remove(ORBITAL_ACHIEVEMENT));
    }

    @Test
    public void removeSameItem_sameItem_success() {
        // use contains to test as there is successful removal

        ApplicationItem editApplicationItem =
                new ApplicationItemBuilder(GOLDMAN_OFFERED).withStatus(VALID_STATUS_APPLIED_MIX_CASE).build();
        CompanyItem editedCompanyItem = new CompanyItemBuilder(GOOGLE).withPhone(VALID_PHONE_AMAZON).build();
        ProfileItem editedProfileItem =
                new ProfileItemBuilder(HTML_SKILL).withDescriptors(VALID_DESCRIPTOR_LEARN).build();

        companyItemUniqueItemList.removeSameItem(editedCompanyItem);
        applicationItemUniqueItemList.removeSameItem(editApplicationItem);
        profileItemUniqueItemList.removeSameItem(editedProfileItem);

        assertFalse(companyItemUniqueItemList.contains(GOOGLE));
        assertFalse(applicationItemUniqueItemList.contains(GOLDMAN_OFFERED));
        assertFalse(profileItemUniqueItemList.contains(HTML_SKILL));
    }

    @Test
    public void removeSameItem_nullItem_throwsNullPointerException() {
        // use contains to test as there is successful removal
        assertThrows(NullPointerException.class, () -> companyItemUniqueItemList.removeSameItem(null));
    }

    @Test
    public void asUnmodifiableObservableList_equals() {
        assertEquals(companyItems, uniqueCompanyItemOne.asUnmodifiableObservableList());
        assertEquals(applicationItems, uniqueApplicationItemOne.asUnmodifiableObservableList());
        assertEquals(profileItems, uniqueProfileItemOne.asUnmodifiableObservableList());
    }

    @Test
    public void setItems_usingListWithNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> companyItemUniqueItemList.setItems(null));
        assertThrows(NullPointerException.class, () -> applicationItemUniqueItemList
                .setItems(null));
        assertThrows(NullPointerException.class, () -> profileItemUniqueItemList.setItems(null));
    }

    @Test
    public void setItems_usingListDuplicateItems_throwsDuplicateItemException() {
        List<CompanyItem> companyItemList = new ArrayList<>(List.of(GOOGLE, GOOGLE));
        List<ApplicationItem> applicationItemList = new ArrayList<>(List.of(GOLDMAN_OFFERED, GOLDMAN_OFFERED));
        List<ProfileItem> profileItemList = new ArrayList<>(List.of(HTML_SKILL, HTML_SKILL));

        assertThrows(DuplicateItemException.class, () -> companyItemUniqueItemList.setItems(companyItemList));
        assertThrows(DuplicateItemException.class, () -> applicationItemUniqueItemList.setItems(applicationItemList));
        assertThrows(DuplicateItemException.class, () -> profileItemUniqueItemList.setItems(profileItemList));
    }

    @Test
    public void setItems_usingListAllUnique_success() {
        List<CompanyItem> companyItemList = new ArrayList<>(List.of(GOOGLE, FACEBOOK));
        List<ApplicationItem> applicationItemList = new ArrayList<>(List.of(GOLDMAN_OFFERED, LAZADA_REJECTED));
        List<ProfileItem> profileItemList = new ArrayList<>(List.of(HTML_SKILL, HACKATHON_ACHIEVEMENT));

        companyItemUniqueItemList.setItems(companyItemList);
        applicationItemUniqueItemList.setItems(applicationItemList);
        profileItemUniqueItemList.setItems(profileItemList);

        assertTrue(companyItemUniqueItemList.contains(GOOGLE));
        assertTrue(companyItemUniqueItemList.contains(FACEBOOK));
        assertFalse(companyItemUniqueItemList.contains(GOLDMAN));

        assertTrue(applicationItemUniqueItemList.contains(GOLDMAN_OFFERED));
        assertTrue(applicationItemUniqueItemList.contains(LAZADA_REJECTED));
        assertFalse(applicationItemUniqueItemList.contains(SHOPEE_OFFERED));

        assertTrue(profileItemUniqueItemList.contains(HTML_SKILL));
        assertTrue(profileItemUniqueItemList.contains(HACKATHON_ACHIEVEMENT));
        assertFalse(profileItemUniqueItemList.contains(GOVTECH_EXPERIENCE));
    }

    @Test
    public void equals() {
        // same object -> true
        assertTrue(companyItemUniqueItemList.equals(companyItemUniqueItemList));
        assertTrue(applicationItemUniqueItemList.equals(applicationItemUniqueItemList));
        assertTrue(profileItemUniqueItemList.equals(profileItemUniqueItemList));

        // same value -> true
        UniqueItemList<CompanyItem> tempCompanyList = new UniqueItemList<>();
        UniqueItemList<ApplicationItem> tempApplicationList = new UniqueItemList<>();
        UniqueItemList<ProfileItem> tempProfileItemList = new UniqueItemList<>();

        tempCompanyList.add(GOOGLE);
        tempCompanyList.add(GOLDMAN);
        tempCompanyList.add(FACEBOOK);

        tempApplicationList.add(GOLDMAN_OFFERED);
        tempApplicationList.add(SHOPEE_OFFERED);
        tempApplicationList.add(LAZADA_REJECTED);

        tempProfileItemList.add(HTML_SKILL);
        tempProfileItemList.add(HACKATHON_ACHIEVEMENT);
        tempProfileItemList.add(GOVTECH_EXPERIENCE);

        assertTrue(companyItemUniqueItemList.equals(tempCompanyList));
        assertTrue(applicationItemUniqueItemList.equals(tempApplicationList));
        assertTrue(profileItemUniqueItemList.equals(tempProfileItemList));

        // null -> false
        assertFalse(companyItemUniqueItemList.equals(null));
        assertFalse(applicationItemUniqueItemList.equals(null));
        assertFalse(profileItemUniqueItemList.equals(null));

        // different types -> false
        assertFalse(companyItemUniqueItemList.equals(0.5f));
        assertFalse(applicationItemUniqueItemList.equals(0.5f));
        assertFalse(profileItemUniqueItemList.equals(0.5f));
    }

    @Test
    public void hashCode_equals() {
        // same UniqueItemList object , same hash -> equals
        assertEquals(companyItemUniqueItemList.hashCode(), companyItemUniqueItemList.hashCode());
        assertEquals(applicationItemUniqueItemList.hashCode(), applicationItemUniqueItemList.hashCode());
        assertEquals(profileItemUniqueItemList.hashCode(), profileItemUniqueItemList.hashCode());

        // same values for UniqueItemList, same hash -> equals
        UniqueItemList<CompanyItem> tempCompanyList = new UniqueItemList<>();
        UniqueItemList<ApplicationItem> tempApplicationList = new UniqueItemList<>();
        UniqueItemList<ProfileItem> tempProfileItemList = new UniqueItemList<>();

        tempCompanyList.add(GOOGLE);
        tempCompanyList.add(GOLDMAN);
        tempCompanyList.add(FACEBOOK);

        tempApplicationList.add(GOLDMAN_OFFERED);
        tempApplicationList.add(SHOPEE_OFFERED);
        tempApplicationList.add(LAZADA_REJECTED);

        tempProfileItemList.add(HTML_SKILL);
        tempProfileItemList.add(HACKATHON_ACHIEVEMENT);
        tempProfileItemList.add(GOVTECH_EXPERIENCE);

        assertEquals(companyItemUniqueItemList.hashCode(), tempCompanyList.hashCode());
        assertEquals(applicationItemUniqueItemList.hashCode(), tempApplicationList.hashCode());
        assertEquals(profileItemUniqueItemList.hashCode(), tempProfileItemList.hashCode());

        // different ItemList, different hash -> not equals
        assertNotEquals(companyItemUniqueItemList.hashCode(), applicationItemUniqueItemList.hashCode());
        assertNotEquals(companyItemUniqueItemList.hashCode(), profileItemUniqueItemList.hashCode());
        assertNotEquals(applicationItemUniqueItemList.hashCode(), profileItemUniqueItemList.hashCode());
    }

    @Test
    public void iterator_givesSameItem_equals() {
        assertEquals(uniqueCompanyItemOne.iterator().next(), companyItems.iterator().next());
        assertEquals(uniqueApplicationItemOne.iterator().next(), applicationItems.iterator().next());
        assertEquals(uniqueProfileItemOne.iterator().next(), profileItems.iterator().next());
    }
}
