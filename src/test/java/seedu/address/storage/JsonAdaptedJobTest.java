package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.GOOGLE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.information.Address;
import seedu.address.model.information.Email;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Priority;
import seedu.address.model.information.Vacancy;

public class JsonAdaptedJobTest {
    private static final String INVALID_JOB_TITLE = "Software*Engineer";
    private static final String INVALID_COMPANY_NAME = "F@cebook";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#socialmedia";
    private static final String INVALID_PRIORITY = "extreme";
    private static final String INVALID_VACANCY = "two";

    private static final String VALID_JOB_TITLE = GOOGLE.getJobTitle().toString();
    private static final String VALID_COMPANY_NAME = GOOGLE.getCompanyName().toString();
    private static final String VALID_PHONE = GOOGLE.getPhone().toString();
    private static final String VALID_EMAIL = GOOGLE.getEmail().toString();
    private static final String VALID_ADDRESS = GOOGLE.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = GOOGLE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_PRIORITY = GOOGLE.getPriority().toString();
    private static final String VALID_VACANCY = GOOGLE.getVacancy().toString();

    @Test
    public void toModelType_validJobDetails_returnsJob() throws Exception {
        JsonAdaptedJob job = new JsonAdaptedJob(GOOGLE);
        assertEquals(GOOGLE, job.toModelType());
    }

    @Test
    public void toModelType_invalidJobTitle_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(INVALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, INVALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobTitle_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(null, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "title");
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "company name");
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_TAGS, VALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, invalidTags, VALID_PRIORITY, VALID_VACANCY);
        assertThrows(IllegalValueException.class, job::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, INVALID_PRIORITY, VALID_VACANCY);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, null, VALID_VACANCY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidVacancy_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, INVALID_VACANCY);
        String expectedMessage = Vacancy.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullVacancy_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_TITLE, VALID_COMPANY_NAME, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_TAGS, VALID_PRIORITY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Vacancy.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }
}
