package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.util.ProfileItemUtil.CATEGORY_OUTPUT_NAME;
import static seedu.internhunter.model.util.ProfileItemUtil.DESCRIPTORS_OUTPUT_NAME;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_EXPERIENCE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_SKILL;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_IMPLEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_LEARN;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_GOVTECH_INTERNSHIP;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_HTML;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_INTERNSHIP;
import static seedu.internhunter.testutil.profile.SampleProfileItems.GOVTECH_EXPERIENCE;
import static seedu.internhunter.testutil.profile.SampleProfileItems.HTML_SKILL;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.DESCRIPTORS_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TITLE_DISPLAY_NAME;
import static seedu.internhunter.ui.panel.PanelDisplayKeyword.TYPE_DISPLAY_NAME;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import seedu.internhunter.testutil.profile.ProfileItemBuilder;

class ProfileItemTest {

    @Test
    public void descriptors_invalidDataType_throwsUnsupportedOperationException() {
        ProfileItem profileItem = new ProfileItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> profileItem.getDescriptors().remove(0));
    }

    @Test
    public void isSameProfile_true_success() {
        // same object -> returns true
        assertTrue(HTML_SKILL.isSameItem(HTML_SKILL));

        ProfileItem editedExperience;

        // different object, same values -> returns true
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE).build();
        assertTrue(GOVTECH_EXPERIENCE.isSameItem(editedExperience));

        // same title, same category, different descriptors -> returns true
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                .withDescriptors(VALID_DESCRIPTOR_IMPLEMENT, VALID_DESCRIPTOR_LEARN)
                .build();
        assertTrue(editedExperience.isSameItem(GOVTECH_EXPERIENCE));

    }

    @Test
    public void isSameProfile_false_success() {
        // null -> returns false
        assertFalse(HTML_SKILL.isSameItem(null));

        // different category and title -> returns false
        ProfileItem editedExperience =
                new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                        .withTitle(VALID_TITLE_HTML)
                        .withCategory(VALID_CATEGORY_SKILL).build();
        assertFalse(editedExperience.isSameItem(GOVTECH_EXPERIENCE));

        // different title -> returns false
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                        .withTitle(VALID_TITLE_INTERNSHIP).build();
        assertFalse(editedExperience.isSameItem(GOVTECH_EXPERIENCE));

        // different category -> returns false
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                        .withCategory(VALID_CATEGORY_ACHIEVEMENT).build();
        assertFalse(editedExperience.isSameItem(GOVTECH_EXPERIENCE));
    }

    @Test
    public void equals_true_success() {
        // same object -> returns true
        assertEquals(GOVTECH_EXPERIENCE, GOVTECH_EXPERIENCE);

        // different object, same values -> returns true
        ProfileItem govtechCopy = new ProfileItemBuilder(GOVTECH_EXPERIENCE).build();
        assertEquals(govtechCopy, GOVTECH_EXPERIENCE);
    }

    @Test
    public void equals_false_success() {
        // null -> returns false
        assertNotEquals(GOVTECH_EXPERIENCE, null);

        // different type -> returns false
        assertNotEquals(GOVTECH_EXPERIENCE, 5);

        // different profileItem -> returns false
        assertNotEquals(GOVTECH_EXPERIENCE, HTML_SKILL);

        // different name -> returns false
        ProfileItem editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                .withTitle(VALID_TITLE_INTERNSHIP).build();
        assertNotEquals(editedExperience, GOVTECH_EXPERIENCE);

        // different category -> returns false
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                .withCategory(VALID_CATEGORY_ACHIEVEMENT).build();
        assertNotEquals(editedExperience, GOVTECH_EXPERIENCE);

        // different descriptors -> returns false
        editedExperience = new ProfileItemBuilder(GOVTECH_EXPERIENCE)
                .withDescriptors(VALID_DESCRIPTOR_IMPLEMENT).build();
        assertNotEquals(editedExperience, GOVTECH_EXPERIENCE);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(GOVTECH_EXPERIENCE.hashCode(), GOVTECH_EXPERIENCE.hashCode());
        ProfileItem govtechCopy = new ProfileItemBuilder(GOVTECH_EXPERIENCE).build();
        assertEquals(GOVTECH_EXPERIENCE.hashCode(), govtechCopy.hashCode());
    }

    @Test
    public void toString_nonEmptyIndustriesAndNonEmptyInternships_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(VALID_TITLE_GOVTECH_INTERNSHIP)
                .append(CATEGORY_OUTPUT_NAME)
                .append(VALID_CATEGORY_EXPERIENCE)
                .append(DESCRIPTORS_OUTPUT_NAME)
                .append(GOVTECH_EXPERIENCE.getDescriptors());
        assertEquals(builder.toString(), GOVTECH_EXPERIENCE.toString());
    }

    @Test
    public void getMapping_correctOrdering_success() {
        LinkedHashMap<String, Object> mapping = HTML_SKILL.getMapping();
        Iterator<Object> fields = mapping.values().iterator();
        assertEquals(fields.next(), mapping.get(TITLE_DISPLAY_NAME));
        assertEquals(fields.next(), mapping.get(TYPE_DISPLAY_NAME));
        assertEquals(fields.next(), mapping.get(DESCRIPTORS_DISPLAY_NAME));
    }
}
