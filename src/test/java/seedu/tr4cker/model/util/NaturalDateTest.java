package seedu.tr4cker.model.util;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.parser.exceptions.ParseException;

public class NaturalDateTest {

    @Test
    public void isNaturalDeadline() {
        // invalid natural date-time
        assertFalse(NaturalDateUtil.isNaturalDeadline("")); // empty string
        assertFalse(NaturalDateUtil.isNaturalDeadline(" ")); // spaces only
        assertFalse(NaturalDateUtil.isNaturalDeadline("02-12-2021 1900")); // not a natural date-time
        assertFalse(NaturalDateUtil.isNaturalDeadline("02-12-2021")); // not a natural date-time
        assertFalse(NaturalDateUtil.isNaturalDeadline("SGKK")); // less than 5 alphabets
        assertFalse(NaturalDateUtil.isNaturalDeadline("SGKK PLJJ")); // not a natural date-time
        assertFalse(NaturalDateUtil.isNaturalDeadline("TODAY!")); // contains symbols
        assertFalse(NaturalDateUtil.isNaturalDeadline("Tues 1900")); // wrong day format
        assertFalse(NaturalDateUtil.isNaturalDeadline("Tuesday 19000")); // wrong time format

        // valid natural date-time
        assertTrue(NaturalDateUtil.isNaturalDeadline("Tuesday"));
        assertTrue(NaturalDateUtil.isNaturalDeadline("TODAY"));
        assertTrue(NaturalDateUtil.isNaturalDeadline("TuEsdAy"));
        assertTrue(NaturalDateUtil.isNaturalDeadline("Tuesday 1900"));
        assertTrue(NaturalDateUtil.isNaturalDeadline("TUESDAY 1900"));
    }

    @Test
    public void convertToDateTime_throwsParseException() {
        assertThrows(ParseException.class, () -> NaturalDateUtil.convertToDateTime(
                "Tuesday 2500"));
        assertThrows(ParseException.class, () -> NaturalDateUtil.convertToDateTime(
                "SunnyDay 2300"));
        assertThrows(ParseException.class, () -> NaturalDateUtil.convertToDateTime(
                "SunnyDay"));
    }

    @Test
    public void convertToDateTime_returnsDateTimeString() throws ParseException {
        String expectedTime = " 2200";
        String expectedDate = LocalDate.now().with(next(FRIDAY))
                .format(NaturalDateUtil.DATE_TIME_FORMATTER);
        String expectedDateTime = expectedDate + expectedTime;
        assertEquals(expectedDate, NaturalDateUtil.convertToDateTime("Friday"));
        assertEquals(expectedDate, NaturalDateUtil.convertToDateTime("FrIDay"));
        assertEquals(expectedDateTime, NaturalDateUtil.convertToDateTime("Friday 2200"));
        assertEquals(expectedDateTime, NaturalDateUtil.convertToDateTime("FRIDAY 2200"));

        expectedDate = LocalDate.now().format(NaturalDateUtil.DATE_TIME_FORMATTER);
        expectedDateTime = expectedDate + expectedTime;
        assertEquals(expectedDate, NaturalDateUtil.convertToDateTime("Today"));
        assertEquals(expectedDate, NaturalDateUtil.convertToDateTime("ToDaY"));
        assertEquals(expectedDateTime, NaturalDateUtil.convertToDateTime("Today 2200"));
        assertEquals(expectedDateTime, NaturalDateUtil.convertToDateTime("ToDAY 2200"));
    }
}
