package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.LECTURE;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalEvents.WORKSHOP;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.find.FindEventCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskMatchesFindKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindEventCommandTest {
    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
    private Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void equals() {
        TaskMatchesFindKeywordPredicate firstPredicate =
            new TaskMatchesFindKeywordPredicate(Collections.singletonList("first"));
        TaskMatchesFindKeywordPredicate secondPredicate =
            new TaskMatchesFindKeywordPredicate(Collections.singletonList("second"));

        FindEventCommand findFirstCommand = new FindEventCommand(firstPredicate);
        FindEventCommand findSecondCommand = new FindEventCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_notExistingDescription_noEventFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskMatchesFindKeywordPredicate predicate = preparePredicateDesc("hangout");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_multipleKeywords_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskMatchesFindKeywordPredicate predicate = preparePredicateDesc("meeting workshop");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(MEETING, WORKSHOP), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_notExistingTag_noEventFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskMatchesFindKeywordPredicate predicate = preparePredicateTag("Clown");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_existingTag_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskMatchesFindKeywordPredicate predicate = preparePredicateTag("cs2103t");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(MEETING, LECTURE), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_matchNameAndTag_multipleEventsFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskMatchesFindKeywordPredicate predicate = preparePredicate("meeting party lecture workshop", "cs2103T");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Arrays.asList(MEETING, LECTURE), expectedModel.getFilteredTaskList());
    }

    @Test
    public void execute_notMatchNameAndTag_noEventsFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskMatchesFindKeywordPredicate predicate = preparePredicate("lecture party", "workshop");
        FindEventCommand command = new FindEventCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, "TASK", expectedModel);
        assertEquals(Collections.emptyList(), expectedModel.getFilteredTaskList());
    }

    /**
     * Parses descInput into a {@code TaskMatchesFindKeywordPredicate}.
     */
    private TaskMatchesFindKeywordPredicate preparePredicateDesc(String descInput) {
        return new TaskMatchesFindKeywordPredicate(Arrays.asList(descInput.split("\\s+")));
    }

    /**
     * Parses descInput and tagInput into a {@code TaskMatchesFindKeywordPredicate}.
     */
    private TaskMatchesFindKeywordPredicate preparePredicate(String descInput, String tagInput) {
        return new TaskMatchesFindKeywordPredicate(Arrays.asList(descInput.split("\\s+")), tagInput);
    }

    /**
     * Parses tagInput into a {@code TaskMatchesFindKeywordPredicate}.
     */
    private TaskMatchesFindKeywordPredicate preparePredicateTag(String tagInput) {
        return new TaskMatchesFindKeywordPredicate(tagInput);
    }
}
