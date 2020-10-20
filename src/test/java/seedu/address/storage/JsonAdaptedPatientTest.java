package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.MedicalRecord;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_NRIC = "1234567";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_URL = "ww.c";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_NRIC = BENSON.getNric().value;
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedAllergy> VALID_TAGS = BENSON.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedAppointment> VALID_APPOINTMENTS = BENSON.getAppointments().stream()
            .map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList());
    private static final String VALID_URL = BENSON.getMedicalRecord().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient person = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(INVALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(null, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, INVALID_NRIC, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                null, VALID_TAGS, VALID_APPOINTMENTS, VALID_URL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidUrl_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TAGS, VALID_APPOINTMENTS, INVALID_URL);
        String expectedMessage = MedicalRecord.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullUrl_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_APPOINTMENTS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MedicalRecord.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedAllergy> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedAllergy(INVALID_TAG));
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_NAME, VALID_NRIC, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        invalidTags, VALID_APPOINTMENTS, VALID_URL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
