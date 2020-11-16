package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedConsultation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalConsults.BENSON_GROUP_CONSULT;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.consultation.Address;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.Day;
import seedu.address.model.consultation.Time;
import seedu.address.model.consultation.Type;
import seedu.address.model.person.Name;
import seedu.address.testutil.TypicalConsults;

public class JsonAdaptedConsultationTest {
    private static final String INVALID_NAME = "B@ns0n";
    private static final String INVALID_DAY = "next Monday";
    private static final String INVALID_TIME = "25:00";

    private static final Consultation BENSON_CONSULT = BENSON_GROUP_CONSULT;
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DAY = TypicalConsults.BENSON_DAY;
    private static final String VALID_TIME = TypicalConsults.BENSON_TIME;
    private static final String VALID_LOCATION = TypicalConsults.BENSON_LOCATION;
    private static final String VALID_CONSULT_TYPE = TypicalConsults.BENSON_CONSULT_TYPE;


    @Test
    public void toModelType_validConsultationDetails_returnsConsultation() throws Exception {
        Consultation consultation = new JsonAdaptedConsultation(BENSON_CONSULT).toModelType();
        assertEquals(consultation, BENSON_CONSULT);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(INVALID_NAME, VALID_DAY, VALID_TIME,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(null, VALID_DAY, VALID_TIME,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, INVALID_DAY, VALID_TIME,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullDay_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, null, VALID_TIME,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, VALID_DAY, INVALID_TIME,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, VALID_DAY, null,
            VALID_LOCATION, VALID_CONSULT_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, VALID_DAY, VALID_TIME,
            null, VALID_CONSULT_TYPE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullConsultType_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME, VALID_DAY, VALID_TIME,
            VALID_LOCATION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }
}
