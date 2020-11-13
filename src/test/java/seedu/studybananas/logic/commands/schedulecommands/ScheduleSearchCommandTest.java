package seedu.studybananas.logic.commands.schedulecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.studybananas.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.studybananas.logic.commands.commandtestutils.ScheduleCommandTestUtil.assertCommandSuccess;
import static seedu.studybananas.testutil.SampleTasks.CS2100_FINAL;
import static seedu.studybananas.testutil.SampleTasks.CS2100_TUTORIAL_HOMEWORK;
import static seedu.studybananas.testutil.SampleTasks.ST2334_ASSIGNMENT;
import static seedu.studybananas.testutil.SampleTasks.getSampleSchedule;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.studybananas.model.ScheduleModel;
import seedu.studybananas.model.ScheduleModelManager;
import seedu.studybananas.model.task.InfoContainsKeywordsPredicate;

public class ScheduleSearchCommandTest {
    private ScheduleModel model = new ScheduleModelManager(getSampleSchedule());
    private ScheduleModel expectedModel = new ScheduleModelManager(getSampleSchedule());


    @Test
    public void equals() {
        InfoContainsKeywordsPredicate firstPredicate =
                new InfoContainsKeywordsPredicate(Collections.singletonList("first"));
        InfoContainsKeywordsPredicate secondPredicate =
                new InfoContainsKeywordsPredicate(Collections.singletonList("second"));

        ScheduleSearchCommand findFirstCommand = new ScheduleSearchCommand(firstPredicate);
        ScheduleSearchCommand findSecondCommand = new ScheduleSearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ScheduleSearchCommand findFirstCommandCopy = new ScheduleSearchCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        InfoContainsKeywordsPredicate predicate = preparePredicate(" ");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleTitleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        InfoContainsKeywordsPredicate predicate = preparePredicate("CS2100");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100_TUTORIAL_HOMEWORK, CS2100_FINAL), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        InfoContainsKeywordsPredicate predicate = preparePredicate("Pipeline homework");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CS2100_TUTORIAL_HOMEWORK), model.getFilteredTaskList());
    }

    @Test
    public void execute_multipleDescriptionKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        InfoContainsKeywordsPredicate predicate = preparePredicate("Pipeline Assignment");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    @Test
    public void execute_dateTimeKeywords_oneTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        InfoContainsKeywordsPredicate predicate = preparePredicate("2020-10-01 23:00");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ST2334_ASSIGNMENT), model.getFilteredTaskList());
    }

    @Test
    public void execute_dateTimeKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        InfoContainsKeywordsPredicate predicate = preparePredicate("2020-10-01 1:00");
        ScheduleSearchCommand command = new ScheduleSearchCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    /**
     * Parses {@code userInput} into a {@code TitleContainsKeywordsPredicate}.
     */
    private InfoContainsKeywordsPredicate preparePredicate(String userInput) {
        return new InfoContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
