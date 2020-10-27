package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsArchivePredicateTest {

    @Test
    public void test_isInArchive_returnsTrue() {
        IsArchivePredicate predicate = new IsArchivePredicate();
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").addToArchive().build()));
    }

    @Test
    public void test_isNotInArchive_returnsFalse() {
        IsArchivePredicate predicate = new IsArchivePredicate();
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

}
