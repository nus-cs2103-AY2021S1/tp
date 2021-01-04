package seedu.homerce.model.appointment.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.BENSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class AppointmentNamePredicateTest {
    @Test
    public void equals() {
        List<String> keywordsOne = Arrays.asList("Benson Tan".split("\\s+"));
        List<String> keywordsTwo = Arrays.asList("Carlson Lim".split("\\s+"));
        AppointmentNamePredicate firstPredicate = new AppointmentNamePredicate(keywordsOne);
        AppointmentNamePredicate secondPredicate = new AppointmentNamePredicate(keywordsTwo);
        AppointmentNamePredicate thirdPredicate = new AppointmentNamePredicate(keywordsTwo);
        // same object -> return true
        assertEquals(firstPredicate, firstPredicate);
        // same values -> return true
        assertEquals(secondPredicate, thirdPredicate);
        // different types -> return false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, null);
        // different values -> return false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_appointmentContainsName_returnsTrue() {
        List<String> keywords = Arrays.asList("Benson".split("\\s+"));
        AppointmentNamePredicate predicate = new AppointmentNamePredicate(keywords);
        assertTrue(predicate.test(new AppointmentBuilder().withClient(BENSON).build()));
    }

    @Test
    public void test_appointmentContainsName_returnsFalse() {
        List<String> keywords = Arrays.asList("Benson Meier".split("\\s+"));
        AppointmentNamePredicate predicate = new AppointmentNamePredicate(keywords);
        assertFalse(predicate.test(new AppointmentBuilder().withClient(ALICE).build()));
    }
}
