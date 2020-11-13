package seedu.internhunter.testutil.profile;

import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_EXPERIENCE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_SKILL;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_COMPETITION;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_DEVELOP;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_FIX;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_GOVTECH;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_HTML;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_SOLVE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_COMPETITION;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_GOVTECH_INTERNSHIP;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_GRAPHQL;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_HACKATHON;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_HTML;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_INTERNSHIP;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_NUS_MODS;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_PARTICIPATE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;

/**
 * A utility class containing a list of {@code ProfileItem} objects to be used in tests.
 */
public abstract class SampleProfileItems {

    public static final ProfileItem HTML_SKILL = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_HTML)
            .withCategory(VALID_CATEGORY_SKILL)
            .withDescriptors(VALID_DESCRIPTOR_HTML)
            .build();

    public static final ProfileItem HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_COMPETITION)
            .withCategory(VALID_CATEGORY_ACHIEVEMENT)
            .withDescriptors(VALID_DESCRIPTOR_COMPETITION)
            .build();

    public static final ProfileItem GOVTECH_EXPERIENCE = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_GOVTECH_INTERNSHIP)
            .withCategory(VALID_CATEGORY_EXPERIENCE)
            .withDescriptors(VALID_DESCRIPTOR_GOVTECH, VALID_DESCRIPTOR_DEVELOP)
            .build();

    public static final ProfileItem GRAPHQL_SKILL = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_GRAPHQL)
            .withCategory(VALID_CATEGORY_SKILL)
            .build();

    public static final ProfileItem NUS_MODS_EXPERIENCE = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_NUS_MODS)
            .withCategory(VALID_CATEGORY_EXPERIENCE)
            .withDescriptors(VALID_DESCRIPTOR_FIX)
            .build();

    public static final ProfileItem MS_HACKATHON_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_PARTICIPATE)
            .withCategory(VALID_CATEGORY_ACHIEVEMENT)
            .withDescriptors(VALID_DESCRIPTOR_SOLVE)
            .build();

    // Manually added - ProfileItem's details found in {@code ProfileCommandTestUtil}
    public static final ProfileItem BYTEDANCE_INTERN = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_INTERNSHIP)
            .withCategory(VALID_CATEGORY_EXPERIENCE).build();

    public static final ProfileItem ORBITAL_ACHIEVEMENT = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_HACKATHON)
            .withCategory(VALID_CATEGORY_ACHIEVEMENT)
            .withDescriptors(VALID_DESCRIPTOR_LEARN).build();

    public static final ProfileItem R_SKILL = new ProfileItemBuilder()
            .withTitle(VALID_TITLE_R)
            .withCategory(VALID_CATEGORY_SKILL)
            .withDescriptors(VALID_DESCRIPTOR_LEARN).build();

    /**
     * Returns an {@code ProfileItem} with all the sample profile items.
     */
    public static ItemList<ProfileItem> getSampleProfileItemList() {
        ItemList<ProfileItem> profileItemList = new ItemList<>();
        for (ProfileItem profileItem : getProfileItems()) {
            profileItemList.addItem(profileItem);
        }
        return profileItemList;
    }

    public static List<ProfileItem> getProfileItems() {
        return new ArrayList<>(Arrays.asList(HTML_SKILL, GOVTECH_EXPERIENCE, NUS_MODS_EXPERIENCE,
                MS_HACKATHON_ACHIEVEMENT));
    }
}
