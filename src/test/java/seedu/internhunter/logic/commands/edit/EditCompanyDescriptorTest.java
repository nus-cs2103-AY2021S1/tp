package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.logic.commands.edit.EditCompanyCommand.EditCompanyDescriptor;
import static seedu.internhunter.logic.commands.util.company.SampleEditCompanyDescriptor.EDIT_DESC_GOLDMAN;
import static seedu.internhunter.logic.commands.util.company.SampleEditCompanyDescriptor.EDIT_DESC_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.util.company.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals_multipleTests_success() {
        // same value -> return true
        EditCompanyDescriptor descriptorGoogle = new EditCompanyDescriptor(EDIT_DESC_GOOGLE);
        assertEquals(EDIT_DESC_GOOGLE, descriptorGoogle);

        // same object -> return true
        assertEquals(descriptorGoogle, descriptorGoogle);

        // null -> return false
        assertFalse(descriptorGoogle.equals(null));

        // different types -> return false
        assertFalse(descriptorGoogle.equals(0.5f));

        // different values -> return false
        assertFalse(descriptorGoogle.equals(EDIT_DESC_GOLDMAN));

        // different address -> return false
        EditCompanyDescriptor editedGoogleAddress =
            new EditCompanyDescriptorBuilder(EDIT_DESC_GOOGLE).withAddress(VALID_ADDRESS_FACEBOOK).build();
        assertFalse(descriptorGoogle.equals(editedGoogleAddress));

        // different phone -> return false
        EditCompanyDescriptor editedGooglePhone =
            new EditCompanyDescriptorBuilder(EDIT_DESC_GOOGLE).withPhone(VALID_PHONE_FACEBOOK).build();
        assertFalse(descriptorGoogle.equals(editedGooglePhone));

        // different email -> return false
        EditCompanyDescriptor editedGoogleEmail =
            new EditCompanyDescriptorBuilder(EDIT_DESC_GOOGLE).withEmail(VALID_EMAIL_FACEBOOK).build();
        assertFalse(descriptorGoogle.equals(editedGoogleEmail));

        // different industries -> return false
        EditCompanyDescriptor editedGoogleIndustires =
            new EditCompanyDescriptorBuilder(EDIT_DESC_GOOGLE).withIndustries(VALID_INDUSTRY_SOCIAL_MEDIA).build();
        assertFalse(descriptorGoogle.equals(editedGoogleIndustires));
    }
}
