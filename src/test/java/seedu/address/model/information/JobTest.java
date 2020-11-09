package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.FACEBOOK;
import static seedu.address.testutil.TypicalJobs.GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Job job = new JobBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> job.getTags().remove(0));
    }

    @Test
    public void isSameJob() {
        // same object -> returns true
        assertTrue(FACEBOOK.isSameJob(FACEBOOK));

        // null -> returns false
        assertFalse(FACEBOOK.isSameJob(null));

        // different job title and company name -> returns false
        Job editedFacebook = new JobBuilder(FACEBOOK).withCompanyName(VALID_COMPANY_NAME_IRAS)
                .withJobTitle(VALID_JOB_TITLE_IRAS).build();
        assertFalse(FACEBOOK.isSameJob(editedFacebook));

        // different job title -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withJobTitle(VALID_JOB_TITLE_IRAS).build();
        assertFalse(FACEBOOK.isSameJob(editedFacebook));

        // same job title, same company name, different phone number, email, address and tags -> returns true
        editedFacebook = new JobBuilder(FACEBOOK).withPhone(VALID_PHONE_IRAS).withEmail(VALID_EMAIL_IRAS)
                .withAddress(VALID_ADDRESS_IRAS).withTags(VALID_TAG_IRAS).build();
        assertTrue(FACEBOOK.isSameJob(editedFacebook));

        // same job title, same company name, same email, same address different phone number and tags -> returns true
        editedFacebook = new JobBuilder(FACEBOOK).withPhone(VALID_PHONE_IRAS).withTags(VALID_TAG_IRAS).build();
        assertTrue(FACEBOOK.isSameJob(editedFacebook));

        // same name, same phone, same tags, different phone number, email and address -> returns true
        editedFacebook = new JobBuilder(FACEBOOK).withPhone(VALID_PHONE_IRAS).withEmail(VALID_EMAIL_IRAS)
                .withAddress(VALID_ADDRESS_IRAS).build();
        assertTrue(FACEBOOK.isSameJob(editedFacebook));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Job facebookCopy = new JobBuilder(FACEBOOK).build();
        assertTrue(FACEBOOK.equals(facebookCopy));

        // same object -> returns true
        assertTrue(FACEBOOK.equals(FACEBOOK));

        // null -> returns false
        assertFalse(FACEBOOK.equals(null));

        // different type -> returns false
        assertFalse(FACEBOOK.equals(5));

        // different person -> returns false
        assertFalse(FACEBOOK.equals(GOOGLE));

        // different job title -> returns false
        Job editedFacebook = new JobBuilder(FACEBOOK).withJobTitle(VALID_JOB_TITLE_IRAS).build();
        assertFalse(FACEBOOK.equals(editedFacebook));

        // different company name -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withCompanyName(VALID_COMPANY_NAME_IRAS).build();
        assertFalse(FACEBOOK.equals(editedFacebook));

        // different phone -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withPhone(VALID_PHONE_IRAS).build();
        assertFalse(FACEBOOK.equals(editedFacebook));

        // different email -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withEmail(VALID_EMAIL_IRAS).build();
        assertFalse(FACEBOOK.equals(editedFacebook));

        // different address -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withAddress(VALID_ADDRESS_IRAS).build();
        assertFalse(FACEBOOK.equals(editedFacebook));

        // different tags -> returns false
        editedFacebook = new JobBuilder(FACEBOOK).withTags(VALID_TAG_MAYBANK).build();
        assertFalse(FACEBOOK.equals(editedFacebook));
    }
}
