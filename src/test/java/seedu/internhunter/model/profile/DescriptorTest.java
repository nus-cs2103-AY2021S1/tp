package seedu.internhunter.model.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_BLANK_DESCRIPTOR;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_DESCRIPTOR_AMPERSAND;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.INVALID_DESCRIPTOR_PARENTHESIS;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_GOVTECH;
import static seedu.internhunter.testutil.profile.ProfileItemFieldsUtil.VALID_DESCRIPTOR_PUNCTUATION;

import org.junit.jupiter.api.Test;

class DescriptorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Descriptor(null));
    }

    @Test
    public void constructor_invalidDescriptor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(INVALID_DESCRIPTOR_PARENTHESIS));
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(INVALID_DESCRIPTOR_AMPERSAND));
        assertThrows(IllegalArgumentException.class, () -> new Descriptor(INVALID_BLANK_DESCRIPTOR));
    }

    @Test
    public void toString_validFormats_success() {
        Descriptor descriptor = new Descriptor(VALID_DESCRIPTOR);
        Descriptor descriptorPunctuation = new Descriptor(VALID_DESCRIPTOR_PUNCTUATION);
        assertEquals(VALID_DESCRIPTOR, descriptor.toString());
        assertEquals(VALID_DESCRIPTOR_PUNCTUATION, descriptorPunctuation.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        Descriptor descriptorOne = new Descriptor(VALID_DESCRIPTOR);
        Descriptor descriptorTwo = new Descriptor(VALID_DESCRIPTOR);

        // same object -> return true
        assertTrue(descriptorOne.equals(descriptorOne));

        // same value -> return true
        assertTrue(descriptorOne.equals(descriptorTwo));

        // null -> return false
        assertFalse(descriptorOne.equals(null));

        // different types -> return false
        assertFalse(descriptorOne.equals(0.5f));

        // different value -> return false
        assertFalse(descriptorOne.equals(new Descriptor(VALID_DESCRIPTOR_GOVTECH)));
    }

    @Test
    public void hashCode_equalityTest_success() {
        Descriptor descriptorOne = new Descriptor(VALID_DESCRIPTOR);
        Descriptor descriptorTwo = new Descriptor(VALID_DESCRIPTOR);
        assertEquals(descriptorOne.hashCode(), descriptorTwo.hashCode());
    }
}
