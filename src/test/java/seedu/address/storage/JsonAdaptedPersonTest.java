package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = "1";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_EXPERIENCE = "twenty five";
    private static final String INVALID_DATE = "12 July 2020";
    private static final String INVALID_BLACKLIST_STATUS = "yes";
    private static final String INVALID_URL_LINK = " ";
    private static final String INVALID_SALARY = "-90";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddressOptional().toString();
    private static final String VALID_EXPERIENCE = BENSON.getExperience().toString();
    private static final String VALID_DATE = BENSON.getDateOfApplication().toString();
    private static final String VALID_BLACKLIST_STATUS = BENSON.getBlacklistStatus().toString();
    private static final String VALID_URL_LINK = BENSON.getUrlLinkOptional().get().toString();
    private static final String VALID_SALARY = BENSON.getSalaryOptional().get().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE,
                        VALID_DATE, VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE,
                VALID_DATE, VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidExperience_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Experience.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullExperience_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Experience.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, null,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date of Application");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, INVALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBlacklistStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                null, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Blacklist Status");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBlacklistStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        INVALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = BlacklistStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, INVALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidUrlLink_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, INVALID_URL_LINK, VALID_SALARY, VALID_TAGS);
        String expectedMessage = UrlLink.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, INVALID_SALARY, VALID_TAGS);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_EXPERIENCE, VALID_DATE,
                        VALID_BLACKLIST_STATUS, VALID_ADDRESS, VALID_URL_LINK, VALID_SALARY, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
