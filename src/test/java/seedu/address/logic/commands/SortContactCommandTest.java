package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sort.SortContactCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonNameComparator;

public class SortContactCommandTest {
    private static final Predicate<Person> PREDICATE_SHOW_NO_PERSONS = unused -> false;
    private static final PersonNameComparator PERSONCOMPARATOR = new PersonNameComparator();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());

    @Test
    public void execute_unsortedAddressBook_success() {
        SortContactCommand sortContactCommand = new SortContactCommand();

        String expectedMessage = String.format(SortContactCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.updateSortedPersonList(PERSONCOMPARATOR);
        assertCommandSuccess(sortContactCommand, model, expectedMessage, "CONTACT", expectedModel);
    }

    @Test
    public void execute_emptyAddressBook_success() {
        model = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        SortContactCommand sortContactCommand = new SortContactCommand();
        String expectedMessage = String.format(SortContactCommand.MESSAGE_EMPTY_PERSON_LIST);
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new TaskList());
        expectedModel.updateSortedPersonList(PERSONCOMPARATOR);
        assertCommandSuccess(sortContactCommand, model, expectedMessage, "CONTACT", expectedModel);
    }

    @Test
    public void execute_emptyFilteredAddressBook_success() {
        //Filter list using predicate to use it as an input for the test
        model.updateFilteredPersonList(PREDICATE_SHOW_NO_PERSONS);
        SortContactCommand sortContactCommand = new SortContactCommand();
        String expectedMessage = String.format(SortContactCommand.MESSAGE_EMPTY_FILTERED_PERSON_LIST);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.updateSortedPersonList(PERSONCOMPARATOR);
        assertCommandSuccess(sortContactCommand, model, expectedMessage, "CONTACT", expectedModel);
    }
}
