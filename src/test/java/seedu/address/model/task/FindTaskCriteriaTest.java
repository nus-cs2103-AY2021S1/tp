package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.todolist.FindTaskCriteriaBuilder;

public class FindTaskCriteriaTest {

    @Test
    public void addPredicate_nullPredicate_throwsNullPointerException() {
        FindTaskCriteria findTaskCriteria = new FindTaskCriteria();
        assertThrows(NullPointerException.class, () -> findTaskCriteria.addPredicate(null));
    }

    @Test
    public void getFindTaskPredicate_singlePredicate_success() {
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Arrays.asList("first"));
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withNamePredicate(predicate).build();
        assertTrue(findTaskCriteria.getFindTaskPredicate().equals(predicate));
    }

    @Test
    public void equals() {

        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Arrays.asList("first"));

        FindTaskCriteria firstFindTaskCriteria = new FindTaskCriteriaBuilder()
                .withNamePredicate(predicate).build();

        FindTaskCriteria secondFindTaskCriteria = new FindTaskCriteria();

        // same object -> returns true
        assertTrue(firstFindTaskCriteria.equals(firstFindTaskCriteria));

        // same value -> returns true
        FindTaskCriteria firstFindTaskCriteriaCopy = new FindTaskCriteriaBuilder()
                .withNamePredicate(predicate).build();
        assertTrue(firstFindTaskCriteria.equals(firstFindTaskCriteriaCopy));

        // null -> returns false
        assertFalse(firstFindTaskCriteria.equals(null));

        // different type -> returns false
        assertFalse(firstFindTaskCriteria.equals(10));

        // different value -> returns false
        assertFalse(firstFindTaskCriteria.equals(secondFindTaskCriteria));

    }
}
