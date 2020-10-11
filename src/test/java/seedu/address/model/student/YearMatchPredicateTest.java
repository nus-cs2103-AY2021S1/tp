package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class YearMatchPredicateTest {

    private YearMatchPredicate yearMatchPredicateOne = new YearMatchPredicate(Arrays.asList("sec", "1"));
    private YearMatchPredicate yearMatchPredicateTwo = new YearMatchPredicate(Collections.singletonList("2"));

    private YearMatchPredicate yearMatchPredicateOneDuplicate =
            new YearMatchPredicate(Arrays.asList("sec", "1"));

    private Student yearOneStudent = new StudentBuilder().withName("Alice").withSchool("Changi Junior College")
            .withYear("secondary 1").build();
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

        // User guide test cases
        YearMatchPredicate predicate = new YearMatchPredicate(Arrays.asList("sec", "3"));
        assertTrue(predicate.test(new StudentBuilder().withYear("sec 3").build()));
        assertTrue(predicate.test(new StudentBuilder().withYear("Secondary 3").build()));

    }

    @Test
    public void test_notMatchingYear_returnsFalse() {
        assertFalse(yearMatchPredicateOne.test(yearTwoStudent));

        // User guide test cases
        YearMatchPredicate predicate = new YearMatchPredicate(Arrays.asList("sec", "3"));
        assertFalse(predicate.test(new StudentBuilder().withYear("sec 4").build()));
    }
}
