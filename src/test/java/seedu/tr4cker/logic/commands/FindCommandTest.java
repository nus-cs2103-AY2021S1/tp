package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.tr4cker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tr4cker.testutil.TypicalTasks.TASK3;
import static seedu.tr4cker.testutil.TypicalTasks.TASK5;
import static seedu.tr4cker.testutil.TypicalTasks.TASK6;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.task.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalTr4cker(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPendingTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPendingTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Task3 Task5 Task6");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPendingTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TASK3, TASK5, TASK6), model.getFilteredPendingTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
