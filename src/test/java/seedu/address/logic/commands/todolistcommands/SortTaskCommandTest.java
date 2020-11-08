package seedu.address.logic.commands.todolistcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.todolist.TypicalTasks.ASSIGNMENT_01;
import static seedu.address.testutil.todolist.TypicalTasks.ASSIGNMENT_02;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_01;
import static seedu.address.testutil.todolist.TypicalTasks.LAB_02;
import static seedu.address.testutil.todolist.TypicalTasks.PROBLEM_SET_01;
import static seedu.address.testutil.todolist.TypicalTasks.PROBLEM_SET_02;
import static seedu.address.testutil.todolist.TypicalTasks.QUIZ_01;
import static seedu.address.testutil.todolist.TypicalTasks.QUIZ_02;
import static seedu.address.testutil.todolist.TypicalTasks.TUTORIAL_01;
import static seedu.address.testutil.todolist.TypicalTasks.TUTORIAL_02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.model.task.comparator.TaskComparatorByDate;
import seedu.address.model.task.comparator.TaskComparatorByName;
import seedu.address.model.task.comparator.TaskComparatorByPriority;

public class SortTaskCommandTest {

    private ModelManager model = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        new ContactList(),
        new TodoList(),
        new EventList(),
        new UserPrefs());

    private ModelManager expectedModel = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        new ContactList(),
        new TodoList(),
        new EventList(),
        new UserPrefs());

    @Test
    public void execute_byNameUnfilteredList_success() {
        model.addTask(ASSIGNMENT_01);
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(LAB_02);
        model.addTask(TUTORIAL_01);
        model.addTask(TUTORIAL_02);
        model.addTask(QUIZ_01);
        model.addTask(QUIZ_02);
        model.addTask(PROBLEM_SET_01);
        model.addTask(PROBLEM_SET_02);

        expectedModel.addTask(ASSIGNMENT_01);
        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(LAB_02);
        expectedModel.addTask(TUTORIAL_01);
        expectedModel.addTask(TUTORIAL_02);
        expectedModel.addTask(QUIZ_01);
        expectedModel.addTask(QUIZ_02);
        expectedModel.addTask(PROBLEM_SET_01);
        expectedModel.addTask(PROBLEM_SET_02);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByName();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(TUTORIAL_01);
        sortedList.add(TUTORIAL_02);
        sortedList.add(ASSIGNMENT_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(LAB_01);
        sortedList.add(LAB_02);
        sortedList.add(PROBLEM_SET_01);
        sortedList.add(PROBLEM_SET_02);
        sortedList.add(QUIZ_01);
        sortedList.add(QUIZ_02);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void execute_byNameReversedUnfilteredList_success() {
        model.addTask(ASSIGNMENT_01);
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(LAB_02);
        model.addTask(TUTORIAL_01);
        model.addTask(TUTORIAL_02);
        model.addTask(QUIZ_01);
        model.addTask(QUIZ_02);
        model.addTask(PROBLEM_SET_01);
        model.addTask(PROBLEM_SET_02);

        expectedModel.addTask(ASSIGNMENT_01);
        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(LAB_02);
        expectedModel.addTask(TUTORIAL_01);
        expectedModel.addTask(TUTORIAL_02);
        expectedModel.addTask(QUIZ_01);
        expectedModel.addTask(QUIZ_02);
        expectedModel.addTask(PROBLEM_SET_01);
        expectedModel.addTask(PROBLEM_SET_02);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByName().reversed();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(QUIZ_02);
        sortedList.add(QUIZ_01);
        sortedList.add(PROBLEM_SET_02);
        sortedList.add(PROBLEM_SET_01);
        sortedList.add(LAB_02);
        sortedList.add(LAB_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(ASSIGNMENT_01);
        sortedList.add(TUTORIAL_02);
        sortedList.add(TUTORIAL_01);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void execute_byPriorityUnfilteredList_success() {
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(TUTORIAL_01);

        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(TUTORIAL_01);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByPriority();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(LAB_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(TUTORIAL_01);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void execute_byPriorityReversedUnfilteredList_success() {
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(TUTORIAL_01);

        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(TUTORIAL_01);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByPriority().reversed();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(TUTORIAL_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(LAB_01);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void execute_byDateUnfilteredList_success() {
        model.addTask(ASSIGNMENT_01);
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(LAB_02);
        model.addTask(TUTORIAL_01);
        model.addTask(TUTORIAL_02);
        model.addTask(QUIZ_01);
        model.addTask(QUIZ_02);
        model.addTask(PROBLEM_SET_01);
        model.addTask(PROBLEM_SET_02);

        expectedModel.addTask(ASSIGNMENT_01);
        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(LAB_02);
        expectedModel.addTask(TUTORIAL_01);
        expectedModel.addTask(TUTORIAL_02);
        expectedModel.addTask(QUIZ_01);
        expectedModel.addTask(QUIZ_02);
        expectedModel.addTask(PROBLEM_SET_01);
        expectedModel.addTask(PROBLEM_SET_02);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByDate();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(LAB_01);
        sortedList.add(LAB_02);
        sortedList.add(ASSIGNMENT_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(PROBLEM_SET_01);
        sortedList.add(PROBLEM_SET_02);
        sortedList.add(QUIZ_01);
        sortedList.add(QUIZ_02);
        sortedList.add(TUTORIAL_01);
        sortedList.add(TUTORIAL_02);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void execute_byDateReversedUnfilteredList_success() {
        model.addTask(ASSIGNMENT_01);
        model.addTask(ASSIGNMENT_02);
        model.addTask(LAB_01);
        model.addTask(LAB_02);
        model.addTask(TUTORIAL_01);
        model.addTask(TUTORIAL_02);
        model.addTask(QUIZ_01);
        model.addTask(QUIZ_02);
        model.addTask(PROBLEM_SET_01);
        model.addTask(PROBLEM_SET_02);

        expectedModel.addTask(ASSIGNMENT_01);
        expectedModel.addTask(ASSIGNMENT_02);
        expectedModel.addTask(LAB_01);
        expectedModel.addTask(LAB_02);
        expectedModel.addTask(TUTORIAL_01);
        expectedModel.addTask(TUTORIAL_02);
        expectedModel.addTask(QUIZ_01);
        expectedModel.addTask(QUIZ_02);
        expectedModel.addTask(PROBLEM_SET_01);
        expectedModel.addTask(PROBLEM_SET_02);

        String expectedMessage = SortTaskCommand.MESSAGE_SUCCESS;
        Comparator<Task> comparator = new TaskComparatorByDate().reversed();
        SortTaskCommand command = new SortTaskCommand(comparator);
        expectedModel.updateSortedTodoList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        List<Task> sortedList = new ArrayList<>();
        sortedList.add(TUTORIAL_02);
        sortedList.add(TUTORIAL_01);
        sortedList.add(QUIZ_02);
        sortedList.add(QUIZ_01);
        sortedList.add(PROBLEM_SET_02);
        sortedList.add(PROBLEM_SET_01);
        sortedList.add(ASSIGNMENT_02);
        sortedList.add(ASSIGNMENT_01);
        sortedList.add(LAB_02);
        sortedList.add(LAB_01);

        assertEquals(sortedList, model.getSortedTodoList());
    }

    @Test
    public void equals() {
        Comparator<Task> firstComparator = new TaskComparatorByName();
        Comparator<Task> secondComparator = new TaskComparatorByPriority();
        Comparator<Task> thirdComparator = new TaskComparatorByDate();

        SortTaskCommand sortFirstCommand = new SortTaskCommand(firstComparator);
        SortTaskCommand findSecondCommand = new SortTaskCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortTaskCommand sortFirstCommandCopy = new SortTaskCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(sortFirstCommand.equals(findSecondCommand));
    }
}
