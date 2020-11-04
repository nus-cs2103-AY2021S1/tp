package seedu.address.logic.commands.todolistcommands;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
// import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

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

public class FindTaskCommandTest {

    //private Model model = new ModelManager(new ModuleList(), new ArchivedModuleList(),
    //        new ContactList(), getTypicalTodoList(), new EventList(), new UserPrefs());
    //private Model expectedModel = new ModelManager(new ModuleList(), new ArchivedModuleList(),
    //        new ContactList(), getTypicalTodoList(), new EventList(), new UserPrefs());

    @Test
    public void constructor_nullFindTaskCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindTaskCommand(null));
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        // TaskNameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //        .withNamePredicate(predicate).build();
        //FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList( ), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        // TaskNameContainsKeywordsPredicate predicate = prepareNamePredicate("  ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //         .withNamePredicate(predicate).build();
        // FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Collections.emptyList(), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        // TaskContainsTagsPredicate predicate = prepareTagPredicate("  ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //        .withTagPredicate(predicate).build();
        // FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList( ), model.getFilteredTodoList());
    }

    @Test
    public void execute_multipleTagKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        // TaskContainsTagsPredicate predicate = prepareTagPredicate("  ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //        .withTagPredicate(predicate).build();
        // FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Collections.emptyList(), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchDate_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        // TaskMatchesDatePredicate predicate = prepareDatePredicate("  ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //        .withDatePredicate(predicate).build();
        // FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList( ), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchDate_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        // TaskMatchesDatePredicate predicate = prepareDatePredicate("  ");
        // FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
        //         .withDatePredicate(predicate).build();
        // FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList( ), model.getFilteredTodoList());
    }

    @Test
    public void execute_validSearchPriority_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskMatchesPriorityPredicate predicate = preparePriorityPredicate();
        FindTaskCriteria findTaskCriteria = new FindTaskCriteriaBuilder()
                .withPriorityPredicate(predicate).build();
        FindTaskCommand command = new FindTaskCommand(findTaskCriteria);
        // expectedModel.updateFilteredTodoList(predicate);
        // assertCommandSuccess(command, model, expectedMessage, expectedModel);
        // assertEquals(Arrays.asList( ), model.getFilteredTodoList());
    }


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
