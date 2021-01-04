package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.service.predicate.ServiceCodePredicate;
import seedu.homerce.testutil.service.ServiceBuilder;

public class ServiceCodePredicateTest {

    @Test
    public void test_serviceCodeEquals_returnsTrue() {
        ServiceCode firstServiceCode = new ServiceCode("SC000");
        Service firstService = new ServiceBuilder().withServiceCode("SC000").build();

        ServiceCodePredicate firstPredicate = new ServiceCodePredicate(firstServiceCode);

        // same service code -> returns true
        assertTrue(firstPredicate.test(firstService));
    }

    @Test
    public void test_serviceCodeNotEquals_returnsFalse() {
        ServiceCode firstServiceCode = new ServiceCode("SC001");
        Service firstService = new ServiceBuilder().withServiceCode("SC000").build();

        ServiceCodePredicate firstPredicate = new ServiceCodePredicate(firstServiceCode);

        // different service code -> returns
        assertFalse(firstPredicate.test(firstService));

        // null -> returns false
        assertFalse(firstPredicate.test(null));
    }

    @Test
    public void equals() {
        ServiceCode firstServiceCode = new ServiceCode("SC000");
        ServiceCode secondServiceCode = new ServiceCode("SC001");

        ServiceCodePredicate firstPredicate = new ServiceCodePredicate(firstServiceCode);
        ServiceCodePredicate secondPredicate = new ServiceCodePredicate(secondServiceCode);


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ServiceCodePredicate firstPredicateCopy = new ServiceCodePredicate(firstServiceCode);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different service code -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
