package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.logic.commands.edit.EditInternshipCommand.EditInternshipDescriptor;
import static seedu.internhunter.logic.commands.util.internship.SampleEditInternshipDescriptor.EDIT_DESC_LAZADA_DS;
import static seedu.internhunter.logic.commands.util.internship.SampleEditInternshipDescriptor.EDIT_DESC_SHOPEE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_BA;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_SUMMER;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_REQUIREMENT_HTML;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_4000;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.util.internship.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals_multipleTests_success() {
        // same value -> return true
        EditInternshipDescriptor descriptorLazadaDs = new EditInternshipDescriptor(EDIT_DESC_LAZADA_DS);
        assertEquals(EDIT_DESC_LAZADA_DS, descriptorLazadaDs);

        // same object -> return true
        assertEquals(descriptorLazadaDs, descriptorLazadaDs);

        // null -> return false
        assertFalse(descriptorLazadaDs.equals(null));

        // different types -> return false
        assertFalse(descriptorLazadaDs.equals(0.5f));

        // different values -> return false
        assertFalse(descriptorLazadaDs.equals(EDIT_DESC_SHOPEE_SWE));

        // different job title -> return false
        EditInternshipDescriptor editedLazadaDsJobTitle =
                new EditInternshipDescriptorBuilder(EDIT_DESC_LAZADA_DS).withJobTitle(VALID_JOB_TITLE_BA).build();
        assertFalse(descriptorLazadaDs.equals(editedLazadaDsJobTitle));

        // different wage -> return false
        EditInternshipDescriptor editedLazadaDsWage =
                new EditInternshipDescriptorBuilder(EDIT_DESC_LAZADA_DS).withWage(VALID_WAGE_4000).build();
        assertFalse(descriptorLazadaDs.equals(editedLazadaDsWage));

        // different period -> return false
        EditInternshipDescriptor editedLazadaDsPeriod =
                new EditInternshipDescriptorBuilder(EDIT_DESC_LAZADA_DS).withPeriod(VALID_PERIOD_SUMMER).build();
        assertFalse(descriptorLazadaDs.equals(editedLazadaDsPeriod));

        // different requirement -> return false
        EditInternshipDescriptor editedLazadaDsRequirement =
                new EditInternshipDescriptorBuilder(EDIT_DESC_LAZADA_DS)
                        .withRequirements(VALID_REQUIREMENT_HTML)
                        .build();
        assertFalse(descriptorLazadaDs.equals(editedLazadaDsRequirement));
    }
}
