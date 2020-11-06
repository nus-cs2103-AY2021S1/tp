package jimmy.mcgymmy.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

class DateTest {
    private static final String INVALID_DATE_STRING_1 = "31/13/2020";
    private static final String INVALID_DATE_STRING_2 = "20/04/20";
    private static final String INVALID_DATE_STRING_3 = "20//04/2020";
    private static final String EXPECTED_DATE_STRING_1_DD_MMM_YYYY = "20 Apr 2020";
    private static final String EXPECTED_DATE_STRING_2_D_MMM_YYYY = "2 Sep 2020";
    private static final String EXPECTED_DATE_STRING_1_YYYY_MM_YYYY = "2020-04-20";
    private static final String EXPECTED_DATE_STRING_3_LEAP_YEAR_FEB = "2020-02-29";
    private static final String EXPECTED_DATE_STRING_3_NON_LEAP_YEAR_FEB = "2019-02-30";
    private static final String EXPECTED_DATE_STRING_1_NON_EXISTENT_DATE = "2019-04-31";

    @Test
    void currentDate_returnsCorrectCurrentDate() throws IllegalValueException {
        assertEquals(Date.currentDate(), new Date(LocalDate.now().toString()));
    }

    @Test
    void leapYearValidTest() throws IllegalValueException {
        //Valid leap year Date
        new Date(EXPECTED_DATE_STRING_3_LEAP_YEAR_FEB);

        //Invalid leap year date
        assertThrows(IllegalValueException.class, () -> new Date(EXPECTED_DATE_STRING_3_NON_LEAP_YEAR_FEB));

    }

    @Test
    void constructorAndToString_supportedFormat_createsDateObject() throws IllegalValueException {
        // format: "yyyy-M-dd"
        assertEquals(new Date("2020-4-20").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "yyyy-M-d"
        assertEquals(new Date("2020-9-2").toString(), EXPECTED_DATE_STRING_2_D_MMM_YYYY);
        // format: "dd-MM-yyyy"
        assertEquals(new Date("20-04-2020").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "dd-M-yyyy"
        assertEquals(new Date("20-4-2020").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "d-M-yyyy"
        assertEquals(new Date("2-9-2020").toString(), EXPECTED_DATE_STRING_2_D_MMM_YYYY);
        // format: "yyyy/MM/dd"
        assertEquals(new Date("2020/04/20").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "dd/MM/yyyy"
        assertEquals(new Date("20/04/2020").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "dd/M/yyyy"
        assertEquals(new Date("20/4/2020").toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "d/M/yyyy",
        assertEquals(new Date("2/9/2020").toString(), EXPECTED_DATE_STRING_2_D_MMM_YYYY);
        // format: "d MMM yyyy"
        assertEquals(new Date(EXPECTED_DATE_STRING_1_DD_MMM_YYYY).toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        // format: "yyyy-MM-dd"
        assertEquals(new Date(EXPECTED_DATE_STRING_1_YYYY_MM_YYYY).toString(), EXPECTED_DATE_STRING_1_DD_MMM_YYYY);

    }

    @Test
    void constructor_unsupportedFormat_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new Date(INVALID_DATE_STRING_1));
        assertThrows(IllegalValueException.class, () -> new Date(INVALID_DATE_STRING_2));
        assertThrows(IllegalValueException.class, () -> new Date(INVALID_DATE_STRING_3));
        assertThrows(IllegalValueException.class, () -> new Date(EXPECTED_DATE_STRING_1_NON_EXISTENT_DATE));
    }

    @Test
    void equals_returnsCorrectComparison() throws IllegalValueException {
        Date testDate1 = new Date(EXPECTED_DATE_STRING_1_DD_MMM_YYYY);
        Date testDate2 = new Date(EXPECTED_DATE_STRING_2_D_MMM_YYYY);

        // identical -> returns true
        assertEquals(testDate1, testDate1);

        // same date -> returns true
        assertEquals(new Date(EXPECTED_DATE_STRING_1_YYYY_MM_YYYY), testDate1);

        // different date -> returns false
        assertNotEquals(testDate2, testDate1);

        // not instance of Date -> returns false
        assertNotEquals(EXPECTED_DATE_STRING_1_DD_MMM_YYYY, testDate1);
    }

    @Test
    void hasCode_returnsCorrectHashCode() throws IllegalValueException {
        Date actual = new Date(EXPECTED_DATE_STRING_1_YYYY_MM_YYYY);
        LocalDate expected = LocalDate.parse(EXPECTED_DATE_STRING_1_YYYY_MM_YYYY);
        assertEquals(actual.hashCode(), expected.hashCode());
    }
}
