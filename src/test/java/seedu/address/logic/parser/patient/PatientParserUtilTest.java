package seedu.address.logic.parser.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Age;
import seedu.address.model.patient.Comment;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PeriodOfStay;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Temperature;
import seedu.address.model.patient.TemperatureRange;

public class PatientParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_AGE = "twenty-two";
    private static final String INVALID_TEMPERATURE = "37.h";
    private static final String INVALID_PERIOD_OF_STAY = "20201919-20191817";
    private static final String INVALID_TEMP_RANGE = "37.x-3";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_AGE = "22";
    private static final String VALID_TEMPERATURE = "36.7";
    private static final String VALID_PERIOD_OF_STAY = "20201001-20201014";
    private static final String VALID_TEMP_RANGE = "36.7-37.0";
    private static final String COMMENT = "Is asthmatic";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, PatientParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, PatientParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseAge_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parseAge((String) null));
    }

    @Test
    public void parseAge_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parseAge(INVALID_AGE));
    }

    @Test
    public void parseAge_validValueWithoutWhitespace_returnsAge() throws Exception {
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, PatientParserUtil.parseAge(VALID_AGE));
    }

    @Test
    public void parseAge_validValueWithWhitespace_returnsTrimmedAge() throws Exception {
        String ageWithWhitespace = WHITESPACE + VALID_AGE + WHITESPACE;
        Age expectedAge = new Age(VALID_AGE);
        assertEquals(expectedAge, PatientParserUtil.parseAge(ageWithWhitespace));
    }

    @Test
    public void parseTempRange_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parseTemperatureRange((String) null));
    }

    @Test
    public void parseTempRange_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parseTemperatureRange(INVALID_TEMP_RANGE));
    }

    @Test
    public void parseTempRange_validValueWithoutWhitespace_returnsTempRange() throws Exception {
        TemperatureRange expectedTempRange = new TemperatureRange(VALID_TEMP_RANGE);
        assertEquals(expectedTempRange, PatientParserUtil.parseTemperatureRange(VALID_TEMP_RANGE));
    }

    @Test
    public void parseTempRange_validValueWithWhitespace_returnsTrimmedTempRange() throws Exception {
        String tempRangeWithWhitespace = WHITESPACE + VALID_TEMP_RANGE + WHITESPACE;
        TemperatureRange expectedTempRange = new TemperatureRange(VALID_TEMP_RANGE);
        assertEquals(expectedTempRange, PatientParserUtil.parseTemperatureRange(tempRangeWithWhitespace));
    }

    @Test
    public void parseTemperature_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parseTemperature(INVALID_TEMPERATURE));
    }

    @Test
    public void parseTemperature_validValueWithoutWhitespace_returnsTemperature() throws Exception {
        Temperature expectedTemperature = new Temperature(VALID_TEMPERATURE);
        assertEquals(expectedTemperature, PatientParserUtil.parseTemperature(VALID_TEMPERATURE));
    }

    @Test
    public void parseTemperature_validValueWithWhitespace_returnsTrimmedTemperature() throws Exception {
        String tempWithWhitespace = WHITESPACE + VALID_TEMPERATURE + WHITESPACE;
        Temperature expectedTemperature = new Temperature(VALID_TEMPERATURE);
        assertEquals(expectedTemperature, PatientParserUtil.parseTemperature(tempWithWhitespace));
    }

    @Test
    public void parseTemperature_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parseTemperature((String) null));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, PatientParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, PatientParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parsePeriodOfStay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> PatientParserUtil.parsePeriodOfStay((String) null));
    }

    @Test
    public void parsePeriodOfStay_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> PatientParserUtil.parsePeriodOfStay(INVALID_PERIOD_OF_STAY));
    }

    @Test
    public void parsePeriodOfStay_validValueWithoutWhitespace_returnsPeriodOfStay() throws Exception {
        PeriodOfStay expectedPeriodOfStay = new PeriodOfStay(VALID_PERIOD_OF_STAY);
        assertEquals(expectedPeriodOfStay, PatientParserUtil.parsePeriodOfStay(VALID_PERIOD_OF_STAY));
    }

    @Test
    public void parsePeriodOfStay_validValueWithWhitespace_returnsTrimmedPeriodOfStay() throws Exception {
        String periodOfStayWithWhitespace = WHITESPACE + VALID_PERIOD_OF_STAY + WHITESPACE;
        PeriodOfStay expectedPeriodOfStay = new PeriodOfStay(VALID_PERIOD_OF_STAY);
        assertEquals(expectedPeriodOfStay, PatientParserUtil.parsePeriodOfStay(periodOfStayWithWhitespace));
    }

    @Test
    public void parseComment_null_returnsEmptyComment() {
        Comment emptyComment = new Comment("-");
        assertEquals(emptyComment, PatientParserUtil.parseComment(null));
    }

    @Test
    public void parseComment_validComment_returnsComment() {
        Comment expectedComment = new Comment(COMMENT);
        assertEquals(expectedComment, PatientParserUtil.parseComment(COMMENT));
    }

}
