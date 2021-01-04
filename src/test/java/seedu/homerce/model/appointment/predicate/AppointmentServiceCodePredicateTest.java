package seedu.homerce.model.appointment.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.service.TypicalServices.HAIR_TREATMENT;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentServiceCodePredicateTest {
    @Test
    public void equals() {
        ServiceCode firstServiceCode = new ServiceCode("SC001");
        ServiceCode secondServiceCode = new ServiceCode("SC001");
        ServiceCode thirdServiceCode = new ServiceCode("SC002");

        AppointmentServiceCodePredicate firstPredicate = new AppointmentServiceCodePredicate(firstServiceCode);
        AppointmentServiceCodePredicate secondPredicate = new AppointmentServiceCodePredicate(secondServiceCode);
        AppointmentServiceCodePredicate thirdPredicate = new AppointmentServiceCodePredicate(thirdServiceCode);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        // same values -> returns true
        assertEquals(firstPredicate, secondPredicate);
        // Different types -> return false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, null);
        // Different phone -> return false
        assertNotEquals(thirdPredicate, secondPredicate);
    }

    @Test
    public void test_appointmentMatchesServiceCode_returnsTrue() {
        ServiceCode serviceCode = new ServiceCode("SC002");
        AppointmentServiceCodePredicate predicate = new AppointmentServiceCodePredicate(serviceCode);
        assertTrue(predicate.test(new AppointmentBuilder().withService(MANICURE).build()));
    }

    @Test
    public void test_appointmentMatchesServiceCode_returnsFalse() {
        ServiceCode serviceCode = new ServiceCode("SC002");
        AppointmentServiceCodePredicate predicate = new AppointmentServiceCodePredicate(serviceCode);
        assertFalse(predicate.test(new AppointmentBuilder().withService(HAIR_TREATMENT).build()));
        assertFalse(predicate.test(new AppointmentBuilder().withService(LASH_LIFT).build()));
    }
}
