package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GenderMatchPredicateTest {
    @Test
    public void equals() {
        Gender firstGender = new Gender(VALID_GENDER_AMY);
        Gender secondGender = new Gender(VALID_GENDER_BOB);

        GenderMatchPredicate firstPredicate =
                new GenderMatchPredicate(firstGender);
        GenderMatchPredicate secondPredicate =
                new GenderMatchPredicate(secondGender);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderMatchPredicate firstPredicateCopy =
                new GenderMatchPredicate(firstGender);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different gender -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test() {
        // same gender -> returns true
        GenderMatchPredicate predicate =
                new GenderMatchPredicate(new Gender(VALID_GENDER_AMY));
        assertTrue(predicate
                .test(new PersonBuilder().withGender(VALID_GENDER_AMY).build()));

        // different block -> returns false
        predicate = new GenderMatchPredicate(new Gender(VALID_GENDER_BOB));
        assertFalse(predicate
                .test(new PersonBuilder().withGender(VALID_GENDER_AMY).build()));
    }
}
