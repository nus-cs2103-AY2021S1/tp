package seedu.jarvis.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.storage.JsonAdaptedConsultation.NAME_OF_STUDENT_FIELD;
import static seedu.jarvis.storage.JsonAdaptedMasteryCheck.HASPASSED_FIELD;
import static seedu.jarvis.storage.JsonAdaptedMasteryCheck.INVALID_DATE_TIME_FORMAT;
import static seedu.jarvis.storage.JsonAdaptedMasteryCheck.INVALID_HASPASSED_FORMAT;
import static seedu.jarvis.storage.JsonAdaptedMasteryCheck.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMasteryChecks.TEST_MASTERY_CHECK_ONE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.exceptions.IllegalValueException;
import seedu.jarvis.model.masterycheck.MasteryCheck;

public class JsonAdaptedMasteryCheckTest {

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

    private static final String VALID_NAME_ONE = TEST_MASTERY_CHECK_ONE.getStudentName();
    private static final String VALID_NAME_TWO = "@";
    // any string can be considered as the name, including special characters
    private static final String VALID_NAME_THREE = "2342";
    // any string can be considered as the name, including numerical values

    private static final String VALID_DATE = TEST_MASTERY_CHECK_ONE.getDateAndTime().toString();

    private static final String VALID_HASPASSED_FALSE = "F";
    private static final String VALID_HASPASSED_TRUE = "T";

    private static final String INVALID_HASPASSED_ONE = ""; // empty string
    private static final String INVALID_HASPASSED_TWO = "ASD"; // any other string than F and T

    @Test
    public void toModelType_validMasteryCheckDetails_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck masteryCheck = new JsonAdaptedMasteryCheck(TEST_MASTERY_CHECK_ONE);
        assertEquals(TEST_MASTERY_CHECK_ONE, masteryCheck.toModelType());
    }

    @Test
    public void toModelType_nullStudentName_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(null, VALID_DATE, VALID_HASPASSED_FALSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NAME_OF_STUDENT_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, null, VALID_HASPASSED_FALSE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_nullHasPassed_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck = new JsonAdaptedMasteryCheck(VALID_NAME_ONE, VALID_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, HASPASSED_FIELD);
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_validMasteryCheckDetailsWithSpecialCharName_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck jsonMasteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_TWO, VALID_DATE, VALID_HASPASSED_FALSE);
        MasteryCheck masteryCheck = new MasteryCheck(VALID_NAME_TWO, LocalDateTime.parse(VALID_DATE));
        assertEquals(masteryCheck, jsonMasteryCheck.toModelType());
    }

    @Test
    public void toModelType_validMasteryCheckDetailsWithNumericalName_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck jsonMasteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_THREE, VALID_DATE, VALID_HASPASSED_FALSE);
        MasteryCheck masteryCheck = new MasteryCheck(VALID_NAME_THREE, LocalDateTime.parse(VALID_DATE));
        assertEquals(masteryCheck, jsonMasteryCheck.toModelType());
    }

    @Test
    public void toModelType_validMasteryCheckDetailsWithHasPassedTrue_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck jsonMasteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, VALID_DATE, VALID_HASPASSED_TRUE);
        MasteryCheck masteryCheck = MasteryCheck.createFullMarkMC(VALID_NAME_ONE, LocalDateTime.parse(VALID_DATE));
        assertEquals(masteryCheck, jsonMasteryCheck.toModelType());
    }

    @Test
    public void toModelType_validMasteryCheckDetailsWithHasPassedFalse_returnsMasteryCheck() throws Exception {
        JsonAdaptedMasteryCheck jsonMasteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, VALID_DATE, VALID_HASPASSED_FALSE);
        MasteryCheck masteryCheck = new MasteryCheck(VALID_NAME_ONE, LocalDateTime.parse(VALID_DATE));
        assertEquals(masteryCheck, jsonMasteryCheck.toModelType());
    }

    @Test
    public void toModelType_invalidDateEmptyString_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_ONE, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateIncorrectFormat_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_TWO, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidMonth_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_THREE, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidDate_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_FOUR, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidHour_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_FIVE, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateNumericallyInvalidMinute_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_SIX, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidYearFormatYyy_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_SEVEN, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidYearFormatYyyy_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_EIGHT, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidMonthFormat_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_NINE, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidDateFormat_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_TEN, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidDateInvalidHourFormat_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_ELEVEN, VALID_HASPASSED_FALSE);
        String expectedMessage = INVALID_DATE_TIME_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidHasPassedEmptyString_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_TWELVE, INVALID_HASPASSED_ONE);
        String expectedMessage = INVALID_HASPASSED_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }

    @Test
    public void toModelType_invalidHasPassedAnyOtherString_throwsIllegalValueException() {
        JsonAdaptedMasteryCheck masteryCheck =
                new JsonAdaptedMasteryCheck(VALID_NAME_ONE, INVALID_DATE_TWELVE, INVALID_HASPASSED_TWO);
        String expectedMessage = INVALID_HASPASSED_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, masteryCheck::toModelType);
    }
}
