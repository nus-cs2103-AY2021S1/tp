package tp.acecs2103.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WeekNumberTest {

    private WeekNumber wk = new WeekNumber("1");
    private WeekNumber wk1 = new WeekNumber("1");
    private WeekNumber wk2 = new WeekNumber("2");

    @Test
    public void constructor() {
        assertThrows(IllegalArgumentException.class, () -> new WeekNumber("0"));
        assertThrows(IllegalArgumentException.class, () -> new WeekNumber("14"));
    }

    @Test
    public void isValidWeekNumber_stringInput_returnTrue() {
        assertTrue(WeekNumber.isValidWeekNumber("1"));
        assertTrue(WeekNumber.isValidWeekNumber("5"));
        assertTrue(WeekNumber.isValidWeekNumber("13"));
    }

    @Test
    public void isValidWeekNumber_stringInput_returnFalse() {
        assertFalse(WeekNumber.isValidWeekNumber("-2"));
        assertFalse(WeekNumber.isValidWeekNumber("0"));
        assertFalse(WeekNumber.isValidWeekNumber("14"));
        assertFalse(WeekNumber.isValidWeekNumber("20"));
    }

    @Test
    public void getWeekValueInt_null_weekNumberInt() {
        assertEquals(1, wk1.getWeekValueInt());
    }

    @Test
    public void testToString_null_weekNumberString() {
        assertEquals("1", wk1.value);
    }

    @Test
    public void testEquals_objectToCompare_returnTrue() {
        assertTrue(wk.equals(wk1));
    }

    @Test
    public void testEquals_objectToCompare_returnFalse() {
        assertFalse(wk1.equals(wk2));
        assertFalse(wk1.equals("1"));
        assertFalse(wk1.equals(null));
    }
}
