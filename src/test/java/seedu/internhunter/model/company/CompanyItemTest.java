package seedu.internhunter.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.internhunter.model.util.CompanyItemUtil.ADDRESS_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.EMAIL_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.INDUSTRIES_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.INTERNSHIPS_OUTPUT_NAME;
import static seedu.internhunter.model.util.CompanyItemUtil.PHONE_OUTPUT_NAME;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.testutil.application.SampleApplicationItems.SHOPEE_OFFERED;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_ADDRESS_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_GOOGLE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_BANKING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_CLOUD_COMPUTING;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_INDUSTRY_SOCIAL_MEDIA;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_FACEBOOK;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_GOOGLE;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOOGLE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_SWE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_SUMMER;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_4000;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_BA;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_SWE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_BA;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOOGLE_SWE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.storage.company.JsonAdaptedCompanyItem;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.testutil.internship.InternshipItemBuilder;

public class CompanyItemTest {

    @Test
    public void getCompanyName() {
        assertEquals(new CompanyName(VALID_COMPANY_NAME_GOOGLE), GOOGLE.getCompanyName());
    }

    @Test
    public void getPhone() {
        assertEquals(new Phone(VALID_PHONE_GOOGLE), GOOGLE.getPhone());
    }

    @Test
    public void getEmail() {
        assertEquals(new Email(VALID_EMAIL_GOOGLE), GOOGLE.getEmail());
    }

    @Test
    public void getAddress() {
        assertEquals(new Address(VALID_ADDRESS_GOOGLE), GOOGLE.getAddress());
    }

    @Test
    public void getCompanyNameValue() {
        assertEquals(new CompanyName(VALID_COMPANY_NAME_GOOGLE).getValue(), GOOGLE.getCompanyNameValue());
    }

    @Test
    public void getPhoneValue() {
        assertEquals(new Phone(VALID_PHONE_GOOGLE).getValue(), GOOGLE.getPhoneValue());
    }

    @Test
    public void getEmailValue() {
        assertEquals(new Email(VALID_EMAIL_GOOGLE).getValue(), GOOGLE.getEmailValue());
    }

    @Test
    public void getAddressValue() {
        assertEquals(new Address(VALID_ADDRESS_GOOGLE).getValue(), GOOGLE.getAddressValue());
    }

    @Test
    public void getIndustries() {
        Set<Industry> industries = new HashSet<>();
        industries.add(new Industry(VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE));
        industries.add(new Industry(VALID_INDUSTRY_CLOUD_COMPUTING));
        assertEquals(industries, GOOGLE.getIndustries());
    }

    @Test
    public void getInternships() {
        List<InternshipItem> internships = new ArrayList<>(Arrays.asList(FACEBOOK_BA, FACEBOOK_FE, FACEBOOK_SWE));
        assertEquals(internships, FACEBOOK.getInternships());
    }

    @Test
    public void getInternship_validIndex_correctInternshipReturned() {
        try {
            assertEquals(FACEBOOK_BA, FACEBOOK.getInternship(Index.fromOneBased(1)));
            assertEquals(FACEBOOK_FE, FACEBOOK.getInternship(Index.fromOneBased(2)));
            assertEquals(FACEBOOK_SWE, FACEBOOK.getInternship(Index.fromOneBased(3)));
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void getInternship_invalidIndex_commandExceptionThrown() {
        CommandException commandException = new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                INTERNSHIP_NAME));

        try {
            FACEBOOK.getInternship(Index.fromOneBased(4));
            fail();
        } catch (CommandException e) {
            assertEquals(e.toString(), commandException.toString());
        }
    }

    @Test
    public void addInternship_companyWithNoInternships_internshipAdded() {
        List<InternshipItem> internships = new ArrayList<>(Arrays.asList(GOOGLE_SWE));
        CompanyItem google = new CompanyItemBuilder(GOOGLE).build();
        google.addInternship(GOOGLE_SWE);
        assertEquals(internships, google.getInternships());
    }

    @Test
    public void addInternship_companyWithInternships_internshipAdded() {
        List<InternshipItem> internships = new ArrayList<>(Arrays.asList(GOLDMAN_BA, GOLDMAN_FE));
        CompanyItem goldman = new CompanyItemBuilder(GOLDMAN).build();
        goldman.addInternship(GOLDMAN_FE);
        assertEquals(internships, goldman.getInternships());
    }

    @Test
    public void removeInternship_validIndex_internshipRemoved() {
        List<InternshipItem> internships = new ArrayList<>(Arrays.asList(FACEBOOK_BA, FACEBOOK_SWE));
        CompanyItem facebook = new CompanyItemBuilder(FACEBOOK).build();
        try {
            facebook.removeInternship(Index.fromOneBased(2));
            assertEquals(internships, facebook.getInternships());
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void removeInternship_invalidIndex_commandExceptionThrown() {
        CommandException commandException = new CommandException(String.format(MESSAGE_INVALID_ITEM_DISPLAYED_INDEX,
                INTERNSHIP_NAME));

        try {
            FACEBOOK.removeInternship(Index.fromOneBased(4));
            fail();
        } catch (CommandException e) {
            assertEquals(e.toString(), commandException.toString());
        }

        try {
            GOOGLE.removeInternship(Index.fromOneBased(1));
            fail();
        } catch (CommandException e) {
            assertEquals(e.toString(), commandException.toString());
        }
    }

    @Test
    public void updateAllInternshipsCompanyName() {
        CompanyItem google = new CompanyItemBuilder(GOOGLE).build();
        InternshipItem initialInternship = new InternshipItemBuilder()
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .withJobTitle(VALID_JOB_TITLE_SWE)
                .withWage(VALID_WAGE_4000)
                .withPeriod(VALID_PERIOD_SUMMER)
                .build();
        InternshipItem editedInternship = new InternshipItemBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withJobTitle(VALID_JOB_TITLE_SWE)
                .withWage(VALID_WAGE_4000)
                .withPeriod(VALID_PERIOD_SUMMER)
                .build();
        google.addInternship(initialInternship);
        google.updateAllInternshipsCompanyName();
        try {
            assertEquals(editedInternship, google.getInternship(Index.fromOneBased(1)));
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void getNumberOfInternships() {
        assertEquals(0, GOOGLE.getNumberOfInternships());
        assertEquals(1, GOLDMAN.getNumberOfInternships());
        assertEquals(3, FACEBOOK.getNumberOfInternships());
    }

    @Test
    public void containsInternship() {
        assertFalse(GOOGLE.containsInternship(GOOGLE_SWE));
        assertFalse(GOLDMAN.containsInternship(GOOGLE_SWE));
        assertFalse(GOLDMAN.containsInternship(GOLDMAN_FE));
        assertTrue(GOLDMAN.containsInternship(GOLDMAN_BA));
        assertTrue(FACEBOOK.containsInternship(FACEBOOK_BA));
        assertTrue(FACEBOOK.containsInternship(FACEBOOK_FE));
        assertTrue(FACEBOOK.containsInternship(FACEBOOK_SWE));
    }

    @Test
    public void isSameItem_true_success() {
        // same object -> returns true
        assertTrue(GOOGLE.isSameItem(GOOGLE));

        // different object, same values -> returns true
        CompanyItem googleCopy = new CompanyItemBuilder(GOOGLE).build();
        assertTrue(GOOGLE.isSameItem(googleCopy));

        // same company name, everything else different -> same item
        CompanyItem googleOnlySameName = new CompanyItemBuilder(GOOGLE)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .withPhone(VALID_PHONE_FACEBOOK)
                .withEmail(VALID_EMAIL_FACEBOOK)
                .withIndustries(VALID_INDUSTRY_SOCIAL_MEDIA, VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
                .withInternships(GOOGLE_SWE)
                .build();
        assertTrue(GOOGLE.isSameItem(googleOnlySameName));
    }

    @Test
    public void isSameItem_false_success() {
        // different name, everything else the same -> not the same item
        CompanyItem googleDifferentName = new CompanyItemBuilder(GOOGLE)
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .build();
        assertFalse(GOOGLE.isSameItem(googleDifferentName));

        // null -> returns false
        assertFalse(GOLDMAN.isSameItem(null));

        // Two different companies -> returns false
        assertFalse(GOLDMAN.isSameItem(GOOGLE));

        // Two different items -> return false
        assertFalse(GOLDMAN.isSameItem(SHOPEE_OFFERED));
    }

    @Test
    public void equals_true_success() {
        // same object -> returns true
        assertEquals(FACEBOOK, FACEBOOK);

        // different object, same values -> returns true
        CompanyItem facebook = new CompanyItemBuilder(FACEBOOK).build();
        assertEquals(facebook, FACEBOOK);
    }

    @Test
    public void equals_false_success() {
        // null -> returns false
        assertNotEquals(GOOGLE, null);

        // different type -> returns false
        assertNotEquals(GOOGLE, 5);

        // Two different items -> return false
        assertNotEquals(GOOGLE, SHOPEE_OFFERED);

        // different companyItem -> returns false
        assertNotEquals(GOOGLE, GOLDMAN);

        // different company name -> returns false
        CompanyItem editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withCompanyName(VALID_COMPANY_NAME_FACEBOOK)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);

        // different phone -> returns false
        editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withPhone(VALID_PHONE_FACEBOOK)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);

        // different email -> returns false
        editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withEmail(VALID_EMAIL_FACEBOOK)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);

        // different address -> returns false
        editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withAddress(VALID_ADDRESS_FACEBOOK)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);

        // different industries -> returns false
        editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withIndustries(VALID_INDUSTRY_BANKING, VALID_INDUSTRY_ARTIFICIAL_INTELLIGENCE)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);

        // different internships -> returns false
        editedGoogle = new CompanyItemBuilder(GOOGLE)
                .withInternships(GOOGLE_SWE)
                .build();
        assertNotEquals(editedGoogle, GOOGLE);
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(GOOGLE.hashCode(), GOOGLE.hashCode());
        CompanyItem googleCopy = new CompanyItemBuilder(GOOGLE).build();
        assertEquals(GOOGLE.hashCode(), googleCopy.hashCode());
    }

    @Test
    public void toString_nonEmptyIndustriesAndNonEmptyInternships_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(VALID_COMPANY_NAME_FACEBOOK)
                .append(PHONE_OUTPUT_NAME)
                .append(VALID_PHONE_FACEBOOK)
                .append(EMAIL_OUTPUT_NAME)
                .append(VALID_EMAIL_FACEBOOK)
                .append(ADDRESS_OUTPUT_NAME)
                .append(VALID_ADDRESS_FACEBOOK)
                .append(INDUSTRIES_OUTPUT_NAME)
                .append(FACEBOOK.getIndustries())
                .append(INTERNSHIPS_OUTPUT_NAME)
                .append(FACEBOOK.getInternships());
        assertEquals(builder.toString(), FACEBOOK.toString());
    }

    @Test
    public void toString_noIndustriesAndNoInternships_success() {
        final StringBuilder builder = new StringBuilder();
        builder.append(VALID_COMPANY_NAME_GOOGLE)
                .append(PHONE_OUTPUT_NAME)
                .append(VALID_PHONE_GOOGLE)
                .append(EMAIL_OUTPUT_NAME)
                .append(VALID_EMAIL_GOOGLE)
                .append(ADDRESS_OUTPUT_NAME)
                .append(VALID_ADDRESS_GOOGLE)
                .append(INDUSTRIES_OUTPUT_NAME)
                .append("-")
                .append(INTERNSHIPS_OUTPUT_NAME)
                .append("-");
        CompanyItem googleNoIndustries = new CompanyItemBuilder()
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE)
                .withPhone(VALID_PHONE_GOOGLE)
                .withEmail(VALID_EMAIL_GOOGLE)
                .withAddress(VALID_ADDRESS_GOOGLE)
                .build();
        assertEquals(builder.toString(), googleNoIndustries.toString());
    }

    @Test
    public void getItemName_equalityTest_success() {
        assertEquals(COMPANY_NAME, GOOGLE.getItemName());
    }

    @Test
    public void getMapping_size_success() {
        LinkedHashMap<String, Object> mapping = GOOGLE.getMapping();
        assertEquals(6, mapping.size());
    }

    @Test
    public void getMapping_correctOrdering_success() {
        LinkedHashMap<String, Object> mapping = FACEBOOK.getMapping();
        Iterator<Object> fields = mapping.values().iterator();
        assertEquals(FACEBOOK.getCompanyName(), fields.next());
        assertEquals(FACEBOOK.getPhone(), fields.next());
        assertEquals(FACEBOOK.getEmail(), fields.next());
        assertEquals(FACEBOOK.getAddress(), fields.next());
        assertEquals(FACEBOOK.getIndustries(), fields.next());
        assertEquals(FACEBOOK.getInternships(), fields.next());
    }

    // todo implement isEquals for each json adapted item and change this to use assertEquals
    @Test
    public void getJsonAdaptedItem_nonEqualityTest_success() {
        assertNotEquals(new JsonAdaptedCompanyItem(GOOGLE), GOOGLE.getJsonAdaptedItem());
    }

}
