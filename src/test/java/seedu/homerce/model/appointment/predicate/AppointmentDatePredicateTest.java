package seedu.homerce.model.appointment.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentDatePredicateTest {
    @Test
    public void equals() {
        Date firstDate = new Date("25-10-2020");
        Date secondDate = new Date("27-10-2020");
        Date thirdDate = new Date("27-10-2020");

        AppointmentDatePredicate firstPredicate = new AppointmentDatePredicate(firstDate);
        AppointmentDatePredicate secondPredicate = new AppointmentDatePredicate(secondDate);
        AppointmentDatePredicate thirdPredicate = new AppointmentDatePredicate(thirdDate);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        // same values -> returns true
        assertEquals(secondPredicate, thirdPredicate);
        // Different types -> return false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, null);
        // Different dates -> return false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_appointmentMatchesDate_returnsTrue() {
        Date date = new Date("25-10-2020");
        AppointmentDatePredicate predicate = new AppointmentDatePredicate(date);
        assertTrue(predicate.test(new AppointmentBuilder().withDate("25-10-2020").build()));
    }

    @Test
    public void test_appointmentMatchesDate_returnsFalse() {
        Date date = new Date("25-10-2020");
        AppointmentDatePredicate predicate = new AppointmentDatePredicate(date);
        assertFalse(predicate.test(new AppointmentBuilder().withDate("25-11-2020").build()));
        assertFalse(predicate.test(new AppointmentBuilder().withDate("15-10-2020").build()));
        assertFalse(predicate.test(new AppointmentBuilder().withDate("25-10-2021").build()));
    }
}
