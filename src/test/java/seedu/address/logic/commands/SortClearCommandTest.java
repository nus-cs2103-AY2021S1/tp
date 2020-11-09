package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameComparator;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateComparator;

public class SortClearCommandTest {
    private static final Predicate<Person> PREDICATE_SHOW_NO_PERSONS = unused -> false;
    private static final Predicate<Task> PREDICATE_SHOW_NO_TASKS = unused -> false;
    private static final TaskDateComparator TASKCOMPARATOR = new TaskDateComparator();
    private static final PersonNameComparator PERSONCOMPARATOR = new PersonNameComparator();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalEventsTaskList());


    @Test
    public void execute_sortedUnfilteredLists_success() {
        //Sort lists using comparators to use it as an input for the test
        model.updateSortedTaskList(TASKCOMPARATOR);
        model.updateSortedPersonList(PERSONCOMPARATOR);

        SortClearCommand sortClearCommand = new SortClearCommand();
        String expectedMessage = String.format(SortClearCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        assertCommandSuccess(sortClearCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
    @Test
    public void execute_sortedEmptyFilteredLists_success() {
        //Sort lists using comparators to use it as an input for the test
        model.updateSortedTaskList(TASKCOMPARATOR);
        model.updateSortedPersonList(PERSONCOMPARATOR);
        //Filter list using predicates to use it as an input for the test
        model.updateFilteredTaskList(PREDICATE_SHOW_NO_TASKS);
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);

        SortClearCommand sortClearCommand = new SortClearCommand();
        String expectedMessage = String.format(SortClearCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        assertCommandSuccess(sortClearCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
    @Test
    public void execute_emptyTaskList_successWithPrompt() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        //Sort lists using comparators to use it as an input for the test
        model.updateSortedTaskList(TASKCOMPARATOR);
        model.updateSortedPersonList(PERSONCOMPARATOR);
        SortClearCommand sortClearCommand = new SortClearCommand();
        String expectedMessage = String.format(SortClearCommand.MESSAGE_EMPTY_TASKLIST);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        assertCommandSuccess(sortClearCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
    @Test
    public void execute_emptyAddressBook_successWithPrompt() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        //Sort lists using comparators to use it as an input for the test
        model.updateSortedTaskList(TASKCOMPARATOR);
        model.updateSortedPersonList(PERSONCOMPARATOR);
        SortClearCommand sortClearCommand = new SortClearCommand();
        String expectedMessage = String.format(SortClearCommand.MESSAGE_EMPTY_ADDRESSBOOK);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());
        assertCommandSuccess(sortClearCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
    @Test
    public void execute_emptyLifeBook_successWithPrompt() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        //Sort lists using comparators to use it as an input for the test
        model.updateSortedTaskList(TASKCOMPARATOR);
        model.updateSortedPersonList(PERSONCOMPARATOR);
        SortClearCommand sortClearCommand = new SortClearCommand();
        String expectedMessage = String.format(SortClearCommand.MESSAGE_EMPTY_LISTS);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        assertCommandSuccess(sortClearCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
}
