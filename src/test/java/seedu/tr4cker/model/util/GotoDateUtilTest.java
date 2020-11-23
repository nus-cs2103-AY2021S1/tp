package seedu.tr4cker.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

class GotoDateUtilTest {

    @Test
    public void testIsValidGotoDate_success() {
        assertTrue(GotoDateUtil.isValidGotoDate("18-10-2020"));
        assertTrue(GotoDateUtil.isValidGotoDate("18-Oct-2020"));
        assertTrue(GotoDateUtil.isValidGotoDate("29-Feb-2020"));
        assertTrue(GotoDateUtil.isValidGotoDate("30-Apr-2020"));
        assertTrue(GotoDateUtil.isValidGotoDate("30-Jun-2020"));
        assertTrue(GotoDateUtil.isValidGotoDate("31-Jan-2021"));
        assertTrue(GotoDateUtil.isValidGotoDate("31-Dec-2021"));
    }

    @Test
    public void testIsValidGotoDate_failure() {
        assertFalse(GotoDateUtil.isValidGotoDate("10-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("Oct-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("32-Oct-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("22-hhh-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("29-Feb-2021"));
        assertFalse(GotoDateUtil.isValidGotoDate("30-Feb-2021"));
        assertFalse(GotoDateUtil.isValidGotoDate("31-04-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("31-06-2020"));
        assertFalse(GotoDateUtil.isValidGotoDate("31-09-2022"));
        assertFalse(GotoDateUtil.isValidGotoDate("31-11-2024"));
        assertFalse(GotoDateUtil.isValidGotoDate("32-11-2024"));
    }

    @Test
    public void testIsValidGotoMonth_success() {
        assertTrue(GotoDateUtil.isValidGotoMonth("Oct-2020"));
        assertTrue(GotoDateUtil.isValidGotoMonth("10-2020"));
    }

    @Test
    public void testIsValidGotoMonth_failure() {
        assertFalse(GotoDateUtil.isValidGotoMonth("AAA-2020"));
        assertFalse(GotoDateUtil.isValidGotoMonth("13-2020"));
        assertFalse(GotoDateUtil.isValidGotoMonth("20-12-2020"));
        assertFalse(GotoDateUtil.isValidGotoMonth("20-Dec-2020"));
    }

    @Test
    public void testSplitGotoDay_success() {
        assertEquals(LocalDate.of(2020, 10, 10), GotoDateUtil.splitGotoDay("10-Oct-2020"));
        assertEquals(LocalDate.of(2020, 10, 10), GotoDateUtil.splitGotoDay("10-10-2020"));
    }

    @Test
    public void testSplitGotoMonth_success() {
        assertEquals(YearMonth.of(2020, 10), GotoDateUtil.splitGotoMonth("Oct-2020"));
        assertEquals(YearMonth.of(2020, 10), GotoDateUtil.splitGotoMonth("10-2020"));
    }

    @Test
    public void testParseGotoDay_success() {
        assertEquals("10-Oct-2020", GotoDateUtil.parseGotoDay(LocalDate.of(2020, 10, 10)));
    }

    @Test
    public void testParseGotoMonth_success() {
        assertEquals("Oct-2020", GotoDateUtil.parseGotoMonth(YearMonth.of(2020, 10)));
    }

    @Test
    public void testCheckToday_success() {
        assertTrue(GotoDateUtil.checkToday("today"));
        assertTrue(GotoDateUtil.checkToday("tdy"));
    }

    @Test
    public void testCheckToday_failure() {
        assertFalse(GotoDateUtil.checkToday("todayyyy"));
        assertFalse(GotoDateUtil.checkToday("jin tian"));
    }

    @Test
    public void testCheckTomorrow_success() {
        assertTrue(GotoDateUtil.checkTomorrow("tomorrow"));
        assertTrue(GotoDateUtil.checkTomorrow("tmr"));
    }
    @Test
    public void testCheckTomorrow_failure() {
        assertFalse(GotoDateUtil.checkTomorrow("tomorrowwww"));
        assertFalse(GotoDateUtil.checkTomorrow("ming tian"));
    }

    @Test
    public void testGetToday_success() {
        assertEquals(LocalDate.now(), GotoDateUtil.getToday());
    }

    @Test
    public void testGetTomorrow_success() {
        assertEquals(LocalDate.now().plusDays(1), GotoDateUtil.getTomorrow());
    }

}
