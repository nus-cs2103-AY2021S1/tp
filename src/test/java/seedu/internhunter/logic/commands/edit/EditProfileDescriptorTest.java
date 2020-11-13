package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.logic.commands.edit.EditProfileCommand.EditProfileItemDescriptor;
import static seedu.internhunter.logic.commands.util.profile.SampleEditProfileDescriptor.EDIT_DESC_HACKATHON_ACHIEVEMENT;
import static seedu.internhunter.logic.commands.util.profile.SampleEditProfileDescriptor.EDIT_DESC_HTML_SKILL;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_COMPETITION;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_TITLE_NUS_MODS;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.util.profile.EditProfileItemDescriptorBuilder;

public class EditProfileDescriptorTest {

    @Test
    public void equals_multipleTests_success() {
        // same value -> return true
        EditProfileItemDescriptor descriptorHtmlSkill = new EditProfileItemDescriptor(EDIT_DESC_HTML_SKILL);
        assertEquals(EDIT_DESC_HTML_SKILL, descriptorHtmlSkill);

        // same object -> return true
        assertEquals(EDIT_DESC_HTML_SKILL, EDIT_DESC_HTML_SKILL);

        // null -> return false
        assertFalse(descriptorHtmlSkill.equals(null));

        // different types -> return false
        assertFalse(descriptorHtmlSkill.equals(0.5f));

        // different values -> return false
        assertFalse(descriptorHtmlSkill.equals(EDIT_DESC_HACKATHON_ACHIEVEMENT));

        // different title -> return false
        EditProfileItemDescriptor editedHtmlSkillTitle =
            new EditProfileItemDescriptorBuilder(EDIT_DESC_HTML_SKILL).withTitle(VALID_TITLE_NUS_MODS).build();
        assertFalse(descriptorHtmlSkill.equals(editedHtmlSkillTitle));

        // different category -> return false
        EditProfileItemDescriptor editedHtmlSkillCategory =
            new EditProfileItemDescriptorBuilder(EDIT_DESC_HTML_SKILL)
                .withProfileItemCategory(VALID_CATEGORY_ACHIEVEMENT)
                .build();
        assertFalse(descriptorHtmlSkill.equals(editedHtmlSkillCategory));

        // different descriptors -> return false
        EditProfileItemDescriptor editedHtmlSkillDescriptor =
            new EditProfileItemDescriptorBuilder(EDIT_DESC_HTML_SKILL)
                .withDescriptors(VALID_DESCRIPTOR_COMPETITION)
                .build();
        assertFalse(descriptorHtmlSkill.equals(editedHtmlSkillDescriptor));
    }
}
