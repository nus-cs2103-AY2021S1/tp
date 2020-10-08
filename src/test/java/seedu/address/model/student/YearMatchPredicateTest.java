package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class YearMatchPredicateTest {

    private YearMatchPredicate yearMatchPredicateOne = new YearMatchPredicate(Collections.singletonList("1"));
    private YearMatchPredicate yearMatchPredicateTwo = new YearMatchPredicate(Collections.singletonList("2"));

    private YearMatchPredicate yearMatchPredicateOneDuplicate =
            new YearMatchPredicate(Collections.singletonList("1"));

    private Student yearOneStudent = new StudentBuilder().withName("Alice").withSchool("Changi Junior College")
            .withYear("sec 1").build();
    private Student yearTwoStudent = new StudentBuilder().withName("Pikachu").withSchool("Trainers School")
            .withYear("2").build();

    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(yearMatchPredicateOne, yearMatchPredicateOne);

        // different type -> return false
        assertNotEquals("1", yearMatchPredicateOne);

        // null -> returns false
        assertNotEquals(yearMatchPredicateOne, null);

        // same year -> returns true
        assertTrue(yearMatchPredicateOne.equals(yearMatchPredicateOneDuplicate));

        // different year -> returns false
        assertFalse(yearMatchPredicateOne.equals(yearMatchPredicateTwo));

    }

    @Test
    public void test_matchingYear_returnsTrue() {
        assertTrue(yearMatchPredicateOne.test(yearOneStudent));
    }

    @Test
    public void test_notMatchingYear_returnsFalse() {
        assertFalse(yearMatchPredicateOne.test(yearTwoStudent));
    }
}
