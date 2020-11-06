package seedu.address.logic.commands.todolistcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.todolist.TypicalTasks.ASSIGNMENT_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;
import static seedu.address.testutil.todolist.TypicalTasks.PROBLEM_SET_01;
import static seedu.address.testutil.todolist.TypicalTasks.PROBLEM_SET_02;
import static seedu.address.testutil.todolist.TypicalTasks.QUIZ_01;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.todolist.FindTaskCriteriaBuilder;

public class FindTaskCommandTest {

    private Model model = new ModelManager(new ModuleList(), new ModuleList(),
            new ContactList(), getTypicalTodoList(), new EventList(), new UserPrefs());
    private Model expectedModel = new ModelManager(new ModuleList(), new ModuleList(),
            new ContactList(), getTypicalTodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullFindTaskCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindTaskCommand(null));
    }

    @Test
    public void execute_multipleNameKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskNameContainsKeywordsPredicate predicate = prepareNamePredicate("Lab01 Lab02 Assignment01");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withNamePredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LAB_01, LAB_02, ASSIGNMENT_01), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleNameKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskNameContainsKeywordsPredicate predicate = prepareNamePredicate("name test integration");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                 .withNamePredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        TaskContainsTagsPredicate predicate = prepareTagPredicate("Lab ProblemSet");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withTagPredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LAB_01, LAB_02, PROBLEM_SET_01, PROBLEM_SET_02), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleTagKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskContainsTagsPredicate predicate = prepareTagPredicate("easy fast difficult");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withTagPredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchDate_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 1);
        TaskMatchesDatePredicate predicate = prepareDatePredicate("2020-11-11");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withDatePredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(QUIZ_01), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchDate_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskMatchesDatePredicate predicate = prepareDatePredicate("2020-12-12");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                 .withDatePredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchPriority_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 4);
        TaskMatchesPriorityPredicate predicate = preparePriorityPredicate();
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withPriorityPredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LAB_01, ASSIGNMENT_01, PROBLEM_SET_01, QUIZ_01), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleSearchParameters_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
        TaskMatchesPriorityPredicate firstPredicate = preparePriorityPredicate();
        TaskContainsTagsPredicate secondPredicate = prepareTagPredicate("Lab ProblemSet");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withPriorityPredicate(firstPredicate).withTagPredicate(secondPredicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(firstPredicate.and(secondPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LAB_01, PROBLEM_SET_01), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleSearchParameters_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskMatchesDatePredicate firstPredicate = prepareDatePredicate("2020-12-12");
        TaskNameContainsKeywordsPredicate secondPredicate = prepareNamePredicate("Lab01 Assignment01");
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withDatePredicate(firstPredicate).withNamePredicate(secondPredicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        expectedModel.updateFilteredTodoList(firstPredicate.and(secondPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredTodoList());
    }

    @Test
    public void equals() {
        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskContainsTagsPredicate secondPredicate = new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("second"));

        FindTaskCriteria firstCriteria = new FindTaskCriteriaBuilder()
                .withNamePredicate(firstPredicate).build();
        FindTaskCriteria secondCriteria = new FindTaskCriteriaBuilder()
                .withTagPredicate(secondPredicate).build();

        FindTaskCommand findFirstCommand = new FindTaskCommand(firstCriteria);
        FindTaskCommand findSecondCommand = new FindTaskCommand(secondCriteria);

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
        assertFalse(findFirstCommand.equals(findSecondCommand));
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
