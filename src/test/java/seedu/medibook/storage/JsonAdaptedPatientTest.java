package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.model.patient.Patient.OPTIONAL_FIELD_EMPTY_MESSAGE;
import static seedu.medibook.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.Bmi;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Record;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.testutil.PatientBuilder;

public class JsonAdaptedPatientTest {
    private static final String INVALID_IC = "A12345R7H";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DOB = "31/12/95";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HEIGHT = "17S";
    private static final String INVALID_WEIGHT = "10.12";
    private static final String INVALID_BMI = "10.12";
    private static final String INVALID_BLOOD_TYPE = "D+";
    private static final String INVALID_ALLERGY = "[];";
    private static final String INVALID_CONDITION = "*^£";
    private static final String INVALID_TREATMENT = "/+=";

    private static final String EMPTY_OPTIONAL_FIELD = OPTIONAL_FIELD_EMPTY_MESSAGE;

    private static final String VALID_IC = BENSON.getIc().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DOB = BENSON.getDateOfBirth().getInputString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getStringEmail();
    private static final String VALID_ADDRESS = BENSON.getStringAddress();
    private static final String VALID_HEIGHT = BENSON.getStringHeight();
    private static final String VALID_WEIGHT = BENSON.getStringWeight();
    private static final String VALID_BMI = BENSON.getStringBmi();
    private static final String VALID_BLOOD_TYPE = BENSON.getStringBloodType();
    private static final JsonAdaptedRecord VALID_RECORD = new JsonAdaptedRecord(BENSON.getRecord());
    private static final List<JsonAdaptedAllergy> VALID_ALLERGIES = BENSON.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedCondition> VALID_CONDITIONS = BENSON.getConditions().stream()
            .map(JsonAdaptedCondition::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTreatment> VALID_TREATMENTS = BENSON.getTreatments().stream()
            .map(JsonAdaptedTreatment::new)
            .collect(Collectors.toList());
    /* Medical note list data of patient should not be loaded at point of program start-up, hence expected
    behaviour is that all these patients have an empty medical note list instead of the one initialised and loaded
    in TypicalPatients.java for other testing purposes.
     */
    private static final MedicalNoteList emptyMedicalNoteList = new MedicalNoteList();

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        Patient expectedBensonWithEmptyMedicalNoteList = new PatientBuilder(BENSON).build();
        expectedBensonWithEmptyMedicalNoteList.setMedicalNoteList(emptyMedicalNoteList);
        assertEquals(expectedBensonWithEmptyMedicalNoteList, patient.toModelType());
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(INVALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(null, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, INVALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, null, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, INVALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyEmail_returnPatientWithEmptyEmailField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                EMPTY_OPTIONAL_FIELD, VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getEmail().isEmpty());
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, null,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyAddress_returnPatientWithEmptyAddressField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                VALID_EMAIL, EMPTY_OPTIONAL_FIELD, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getAddress().isEmpty());
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                null, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyHeight_returnPatientWithEmptyHeightField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, EMPTY_OPTIONAL_FIELD, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getHeight().isEmpty());
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, INVALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyWeight_returnPatientWithEmptyWeightField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_HEIGHT, EMPTY_OPTIONAL_FIELD, VALID_BMI, VALID_BLOOD_TYPE,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getWeight().isEmpty());
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, INVALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, null, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyBmi_returnPatientWithEmptyBmiField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, EMPTY_OPTIONAL_FIELD, VALID_BLOOD_TYPE,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getBmi().isEmpty());
    }

    @Test
    public void toModelType_invalidBmi_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, INVALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = Bmi.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBmi_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, null, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Bmi.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_emptyBloodType_returnPatientWithEmptyBloodTypeField() throws IllegalValueException {
        JsonAdaptedPatient jsonAdaptedPatient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, EMPTY_OPTIONAL_FIELD,
                VALID_RECORD, VALID_ALLERGIES, VALID_CONDITIONS, VALID_TREATMENTS);
        Patient patient = jsonAdaptedPatient.toModelType();
        assertTrue(patient.getBloodType().isEmpty());
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, INVALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, null, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<JsonAdaptedAllergy> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new JsonAdaptedAllergy(INVALID_ALLERGY));
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, invalidAllergies,
            VALID_CONDITIONS, VALID_TREATMENTS);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidConditions_throwsIllegalValueException() {
        List<JsonAdaptedCondition> invalidConditions = new ArrayList<>(VALID_CONDITIONS);
        invalidConditions.add(new JsonAdaptedCondition(INVALID_CONDITION));
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
            invalidConditions, VALID_TREATMENTS);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidTreatments_throwsIllegalValueException() {
        List<JsonAdaptedTreatment> invalidTreatments = new ArrayList<>(VALID_TREATMENTS);
        invalidTreatments.add(new JsonAdaptedTreatment(INVALID_TREATMENT));
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, VALID_RECORD, VALID_ALLERGIES,
                VALID_CONDITIONS, invalidTreatments);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_nullRecord_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BMI, VALID_BLOOD_TYPE, null, VALID_ALLERGIES,
                VALID_CONDITIONS, VALID_TREATMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Record.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

}
