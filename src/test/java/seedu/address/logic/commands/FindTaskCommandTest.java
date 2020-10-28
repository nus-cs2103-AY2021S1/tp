package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;
import static seedu.address.testutil.TypicalTasks.DEADLINE2;
import static seedu.address.testutil.TypicalTasks.EVENT1;
import static seedu.address.testutil.TypicalTasks.EVENT2;
import static seedu.address.testutil.TypicalTasks.EVENT3;
import static seedu.address.testutil.TypicalTasks.getTypicalPlanus;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalPlanus(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPlanus(), new UserPrefs());

    @Test
    public void equals() {
        TaskContainsKeywordsPredicate firstPredicate = new TaskContainsKeywordsPredicate();
        firstPredicate.setKeyword(PREFIX_TITLE, "first");
        TaskContainsKeywordsPredicate secondPredicate = new TaskContainsKeywordsPredicate();
        secondPredicate.setKeyword(PREFIX_TITLE, "second");

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstPredicate);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindTaskCommand findFirstCommandCopy = new FindTaskCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different task -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 5);
        TaskContainsKeywordsPredicate predicate = preparePredicate("Developer Assignment Project");
        FindTaskCommand command = new FindTaskCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DEADLINE1, DEADLINE2, EVENT1, EVENT2, EVENT3), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TaskContainsKeywordsPredicate}.
     */
    private TaskContainsKeywordsPredicate preparePredicate(String userInput) {
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        Arrays.asList(userInput.split("\\s+")).forEach(input -> predicate.setKeyword(PREFIX_TITLE, input));
        return predicate;
    }
}
