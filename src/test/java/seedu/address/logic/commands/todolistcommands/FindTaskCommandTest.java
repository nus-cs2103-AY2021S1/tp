package seedu.address.logic.commands.todolistcommands;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.FindTaskCriteriaBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindTaskCommandTest {


    @Test
    public void equals() {
        TaskNameContainsKeywordsPredicate namePredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("first"));
        //TaskMatchesPriorityPredicate priorityPredicate =
        //        new TaskMatchesPriorityPredicate();

        FindTaskCriteria firstCriteria = new FindTaskCriteriaBuilder()
                .withNamePredicate(namePredicate).build();
        //FindContactCriteria secondCriteria = new FindContactCriteriaBuilder()
        //        .withTagPredicate(tagPredicate).build();

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstCriteria);
        // FindTaskCommand findSecondCommand = new FindTaskCommand(secondCriteria);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same predicate value -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstCriteria);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(8));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate value -> returns false
        // assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code TaskNameContainsKeywordsPredicate}.
     */
    private TaskNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new TaskNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsTagsPredicate}.
     */
    private TaskContainsTagsPredicate prepareTagPredicate(String userInput) {
        Set<Tag> tags = SampleDataUtil.getTagSet(userInput.split("\\s+"));
        return new TaskContainsTagsPredicate(tags);
    }

    /**
     * Parses {@code userInput} into a {@code TaskMatchesDatePredicate}.
     */
    private TaskMatchesDatePredicate prepareDatePredicate(String userInput) {
        return new TaskMatchesDatePredicate(new Date(userInput));
    }

    /**
     * Parses {@code userInput} into a {@code TaskMatchesPriorityPredicate}.
     */
    private TaskMatchesPriorityPredicate preparePriorityPredicate() {
        return new TaskMatchesPriorityPredicate(Priority.HIGH);
    }
}
