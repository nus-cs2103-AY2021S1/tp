package seedu.jarvis.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.storage.JsonAdaptedConsultation.INVALID_DATE_TIME_FORMAT;
import static seedu.jarvis.storage.JsonAdaptedConsultation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.jarvis.storage.JsonAdaptedConsultation.NAME_OF_STUDENT_FIELD;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalConsultations.TEST_CONSULTATION_ONE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.exceptions.IllegalValueException;
import seedu.jarvis.model.consultation.Consultation;

public class JsonAdaptedConsultationTest {

    private static final String INVALID_DATE_ONE = ""; // empty string
    private static final String INVALID_DATE_TWO = "0"; // not in correct format
    private static final String INVALID_DATE_THREE = "2019-13-27T00:51"; // invalid month - over 12
    private static final String INVALID_DATE_FOUR = "2019-02-30T00:51"; // invalid date - over 28 for Feb
    private static final String INVALID_DATE_FIVE = "2019-02-27T25:51"; // invalid hour - over 24
    private static final String INVALID_DATE_SIX = "2019-02-27T25:61"; // invalid minute - over 60

    private static final String INVALID_DATE_SEVEN = "999-02-27T00:51"; // invalid year - wrong format (YYY)
    private static final String INVALID_DATE_EIGHT = "99999-02-27T00:51"; // invalid year - wrong format (YYYYY)
    private static final String INVALID_DATE_NINE = "2019-3-27T00:51"; // invalid month - wrong format
    private static final String INVALID_DATE_TEN = "2019-03-2T00:51"; // invalid date - wrong format
    private static final String INVALID_DATE_ELEVEN = "2019-03-01T0:51"; // invalid hour - wrong format
    private static final String INVALID_DATE_TWELVE = "2019-03-01T00:5"; // invalid minute - wrong format

    private static final String VALID_NAME_ONE = TEST_CONSULTATION_ONE.getStudentName();
    private static final String VALID_NAME_TWO = "@";
    // any string can be considered as the name, including special characters
    private static final String VALID_NAME_THREE = "2342";
    // any string can be considered as the name, including numerical values

    private static final String VALID_DATE = TEST_CONSULTATION_ONE.getDateAndTime().toString();

    @Test
    public void toModelType_validConsultationDetails_returnsConsultation() throws Exception {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(TEST_CONSULTATION_ONE);
        assertEquals(TEST_CONSULTATION_ONE, consultation.toModelType());
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(null, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NAME_OF_STUDENT_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_validConsultationDetailsWithSpecialCharName_returnsConsultation() throws Exception {
        JsonAdaptedConsultation jsonConsultation = new JsonAdaptedConsultation(VALID_NAME_TWO, VALID_DATE);
        Consultation consultation = new Consultation(VALID_NAME_TWO, LocalDateTime.parse(VALID_DATE));
        assertEquals(consultation, jsonConsultation.toModelType());
    }

    @Test
    public void toModelType_validConsultationDetailsWithNumericalName_returnsConsultation() throws Exception {
        JsonAdaptedConsultation jsonConsultation = new JsonAdaptedConsultation(VALID_NAME_THREE, VALID_DATE);
        Consultation consultation = new Consultation(VALID_NAME_THREE, LocalDateTime.parse(VALID_DATE));
        assertEquals(consultation, jsonConsultation.toModelType());
    }

    @Test
    public void toModelType_invalidDateEmptyString_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_ONE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateIncorrectFormat_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_TWO);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidMonth_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_THREE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidDate_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_FOUR);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidHour_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_FIVE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidMinute_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_SIX);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidYearFormatYyy_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_SEVEN);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidYearFormatYyyy_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_EIGHT);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidMonthFormat_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_NINE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidDateFormat_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_TEN);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidHourFormat_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_ELEVEN);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidMinuteFormat_throwsIllegalValueException() {
        JsonAdaptedConsultation consultation = new JsonAdaptedConsultation(VALID_NAME_ONE, INVALID_DATE_TWELVE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, consultation::toModelType);
    }
}
