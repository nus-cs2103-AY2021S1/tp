package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.BLANK_CATEGORY;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_CATEGORY_CASING;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_CATEGORY_TYPE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_ACHIEVEMENT;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_EXPERIENCE;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_CATEGORY_SKILL;

import org.junit.jupiter.api.Test;

public class ProfileItemCategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ProfileItemCategory.valueOf(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(BLANK_CATEGORY));
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(INVALID_CATEGORY_TYPE));
        assertThrows(IllegalArgumentException.class, () -> ProfileItemCategory.valueOf(INVALID_CATEGORY_CASING));
    }

    @Test
    public void toString_validFormats_success() {
        ProfileItemCategory achievement = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(VALID_CATEGORY_ACHIEVEMENT, achievement.toString());
        ProfileItemCategory skill = ProfileItemCategory.valueOf(VALID_CATEGORY_SKILL);
        assertEquals(VALID_CATEGORY_SKILL, skill.toString());
        ProfileItemCategory experience = ProfileItemCategory.valueOf(VALID_CATEGORY_EXPERIENCE);
        assertEquals(VALID_CATEGORY_EXPERIENCE, experience.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory, profileItemCategoryTwo);
    }

    @Test
    public void hashCode_equalityTest_success() {
        ProfileItemCategory profileItemCategory = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        ProfileItemCategory profileItemCategoryTwo = ProfileItemCategory.valueOf(VALID_CATEGORY_ACHIEVEMENT);
        assertEquals(profileItemCategory.hashCode(), profileItemCategoryTwo.hashCode());
    }
}
