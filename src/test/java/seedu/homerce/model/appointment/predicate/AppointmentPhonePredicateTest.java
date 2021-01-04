package seedu.homerce.model.appointment.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.BENSON;
import static seedu.homerce.testutil.client.TypicalClients.CARL;
import static seedu.homerce.testutil.client.TypicalClients.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.client.Phone;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentPhonePredicateTest {
    @Test
    public void equals() {
        Phone firstPhone = new Phone("91234567");
        Phone secondPhone = new Phone("81287099");
        Phone thirdPhone = new Phone("81287099");

        AppointmentPhonePredicate firstPredicate = new AppointmentPhonePredicate(firstPhone);
        AppointmentPhonePredicate secondPredicate = new AppointmentPhonePredicate(secondPhone);
        AppointmentPhonePredicate thirdPredicate = new AppointmentPhonePredicate(thirdPhone);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        // same values -> returns true
        assertEquals(secondPredicate, thirdPredicate);
        // Different types -> return false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, null);
        // Different phone -> return false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_appointmentMatchesPhone_returnsTrue() {
        Phone phone = new Phone("94351253");
        AppointmentPhonePredicate predicate = new AppointmentPhonePredicate(phone);
        assertTrue(predicate.test(new AppointmentBuilder().withClient(ALICE).build()));
    }

    @Test
    public void test_appointmentMatchesPhone_returnsFalse() {
        Phone phone = new Phone("94351253");
        AppointmentPhonePredicate predicate = new AppointmentPhonePredicate(phone);
        assertFalse(predicate.test(new AppointmentBuilder().withClient(BENSON).build()));
        assertFalse(predicate.test(new AppointmentBuilder().withClient(CARL).build()));
        assertFalse(predicate.test(new AppointmentBuilder().withClient(DANIEL).build()));
    }
}
