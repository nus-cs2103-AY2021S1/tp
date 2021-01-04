package seedu.homerce.model.appointment.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentWeekPredicateTest {
    @Test
    public void equals() {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.set(2020, Calendar.NOVEMBER, 25);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.set(2020, Calendar.NOVEMBER, 25);
        Calendar thirdCalendar = Calendar.getInstance();
        thirdCalendar.set(2020, Calendar.DECEMBER, 25);
        AppointmentWeekPredicate firstPredicate = new AppointmentWeekPredicate(firstCalendar);
        AppointmentWeekPredicate secondPredicate = new AppointmentWeekPredicate(secondCalendar);
        AppointmentWeekPredicate thirdPredicate = new AppointmentWeekPredicate(thirdCalendar);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        // same values -> returns true
        assertEquals(firstPredicate, secondPredicate);
        // Different types -> return false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, null);
        // Different dates -> return false
        assertNotEquals(firstPredicate, thirdPredicate);
    }

    @Test
    public void test_appointmentMatchesWeek_returnsTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 25);
        AppointmentWeekPredicate predicate = new AppointmentWeekPredicate(calendar);
        assertTrue(predicate.test(new AppointmentBuilder().withDate("25-11-2020").build())); // same date
        assertTrue(predicate.test(new AppointmentBuilder().withDate("23-11-2020").build())); // monday
        assertTrue(predicate.test(new AppointmentBuilder().withDate("29-11-2020").build())); // sunday

    }

    @Test
    public void test_appointmentMatchesWeek_returnsFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 25);
        AppointmentWeekPredicate predicate = new AppointmentWeekPredicate(calendar);
        assertFalse(predicate.test(new AppointmentBuilder().withDate("22-11-2020").build())); // previous week
        assertFalse(predicate.test(new AppointmentBuilder().withDate("30-11-2020").build())); // next week
        assertFalse(predicate.test(new AppointmentBuilder().withDate("15-10-2020").build())); // different month
        assertFalse(predicate.test(new AppointmentBuilder().withDate("25-10-2021").build())); // different year
    }

    @Test
    public void test_appointmentToString_matchesFormat() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 25);
        AppointmentWeekPredicate predicate = new AppointmentWeekPredicate(calendar);
        assertEquals(predicate.toString(), "23 Nov 2020 to 29 Nov 2020");
        calendar.set(2020, Calendar.JANUARY, 11);
        predicate = new AppointmentWeekPredicate(calendar);
        assertEquals(predicate.toString(), "06 Jan 2020 to 12 Jan 2020");
    }
}
