package jimmy.mcgymmy.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import jimmy.mcgymmy.commons.exceptions.IllegalValueException;

class DateTest {
    private static final String EXPECTED_DATE_STRING_1 = "20 Apr 2020";
    private static final String EXPECTED_DATE_STRING_2 = "2 Sep 2020";


    @Test
    void currentDate_returnsCorrectCurrentDate() throws IllegalValueException {
        assertEquals(Date.currentDate(), new Date(LocalDate.now().toString()));
    }

    @Test
    void constructorAndToString_supportedFormat_createsDateObject() throws IllegalValueException {
        // format: "yyyy-MM-dd"
        assertEquals(new Date("2020-04-20").toString(), EXPECTED_DATE_STRING_1);
        // format: "yyyy-M-dd"
        assertEquals(new Date("2020-4-20").toString(), EXPECTED_DATE_STRING_1);
        // format: "yyyy-M-d"
        assertEquals(new Date("2020-9-2").toString(), EXPECTED_DATE_STRING_2);
        // format: "dd-MM-yyyy"
        assertEquals(new Date("20-04-2020").toString(), EXPECTED_DATE_STRING_1);
        // format: "dd-M-yyyy"
        assertEquals(new Date("20-4-2020").toString(), EXPECTED_DATE_STRING_1);
        // format: "d-M-yyyy"
        assertEquals(new Date("2-9-2020").toString(), EXPECTED_DATE_STRING_2);
        // format: "yyyy/MM/dd"
        assertEquals(new Date("2020/04/20").toString(), EXPECTED_DATE_STRING_1);
        // format: "dd/MM/yyyy"
        assertEquals(new Date("20/04/2020").toString(), EXPECTED_DATE_STRING_1);
        // format: "dd/M/yyyy"
        assertEquals(new Date("20/4/2020").toString(), EXPECTED_DATE_STRING_1);
        // format: "d/M/yyyy",
        assertEquals(new Date("2/9/2020").toString(), EXPECTED_DATE_STRING_2);
        // format: "d MMM yyyy"
        assertEquals(new Date("20 Apr 2020").toString(), EXPECTED_DATE_STRING_1);
    }

    @Test
    void constructor_unsupportedFormat_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new Date("31/13/2020"));
        assertThrows(IllegalValueException.class, () -> new Date("20/04/20"));
    }

    @Test
    void equals_returnsCorrectComparison() throws IllegalValueException {
        Date testDate1 = new Date(EXPECTED_DATE_STRING_1);
        Date testDate2 = new Date(EXPECTED_DATE_STRING_2);
        // identical -> returns true
        assertEquals(testDate1, testDate1);
        // same date -> returns true
        assertEquals(new Date("2020-04-20"), testDate1);
        // different date -> returns false
        assertFalse(testDate1.equals(testDate2));
        // not instance of Date -> returns false
        assertFalse(testDate1.equals(EXPECTED_DATE_STRING_1));
    }
}
