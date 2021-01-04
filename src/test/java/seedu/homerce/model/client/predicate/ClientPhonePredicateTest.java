package seedu.homerce.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.client.Phone;
import seedu.homerce.testutil.client.ClientBuilder;

public class ClientPhonePredicateTest {

    @Test
    public void equals() {
        Phone firstPhone = new Phone("91234567");
        Phone secondPhone = new Phone("98765432");

        ClientPhonePredicate firstPredicate = new ClientPhonePredicate(firstPhone);
        ClientPhonePredicate secondPredicate = new ClientPhonePredicate(secondPhone);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientPhonePredicate firstPredicateCopy = new ClientPhonePredicate(firstPhone);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_clientContainsPhone_returnsTrue() {
        Phone phone = new Phone("91234567");

        ClientPhonePredicate predicate = new ClientPhonePredicate(phone);
        assertTrue(predicate.test(new ClientBuilder().withPhone("91234567").build()));

    }

    @Test
    public void test_clientContainsPhone_returnsFalse() {
        Phone phone = new Phone("91234567");

        ClientPhonePredicate predicate = new ClientPhonePredicate(phone);
        assertFalse(predicate.test(new ClientBuilder().withPhone("98765432").build()));

    }
}
