package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class YearMatchPredicateTest {

    private Year yearOne = new Year("1");
    private Year yearTwo = new Year("2");

    private YearMatchPredicate yearMatchPredicateOne = new YearMatchPredicate(yearOne);
    private YearMatchPredicate yearMatchPredicateTwo = new YearMatchPredicate(yearTwo);

    private YearMatchPredicate yearMatchPredicateOneDuplicate = new YearMatchPredicate(yearOne);

    private Student yearOneStudent = new StudentBuilder().withName("Alice").withSchool("Changi Junior College")
            .withYear("1").build();
    private Student yearTwoStudent = new StudentBuilder().withName("Pikachu").withSchool("Trainers School")
            .withYear("2").build();

    @Test
    public void equals() {

        // same object -> returns true
        assertEquals(yearMatchPredicateOne, yearMatchPredicateOne);

        // different type -> return false
        assertNotEquals(yearOne, yearMatchPredicateOne);

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
