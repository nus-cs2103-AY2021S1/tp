package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortPersonCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.comparator.PersonBlackListComparator;
import seedu.address.model.information.comparator.PersonDateOfApplicationComparator;
import seedu.address.model.information.comparator.PersonExpectedSalaryComparator;
import seedu.address.model.information.comparator.PersonExperienceComparator;
import seedu.address.model.information.comparator.PersonNameComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortJobCommand.
 */
public class SortPersonCommandTest {
    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(),
            new UserPrefs());

    @Test
    public void execute_ascendingName_sortedSuccess() {
        PersonNameComparator comparator = new PersonNameComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in ascending order.";
        SortPersonCommand command = new SortPersonCommand(comparator, true);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }

    @Test
    public void execute_ascendingExpectedSalary_sortedSuccess() {
        PersonExpectedSalaryComparator comparator = new PersonExpectedSalaryComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in ascending order.";
        SortPersonCommand command = new SortPersonCommand(comparator, true);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }

    @Test
    public void execute_ascendingExperience_sortedSuccess() {
        PersonExperienceComparator comparator = new PersonExperienceComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in ascending order.";
        SortPersonCommand command = new SortPersonCommand(comparator, true);
        expectedModel.updateSortedPersonList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }

    @Test
    public void execute_descendingDateOfApplication_sortedSuccess() {
        PersonDateOfApplicationComparator comparator = new PersonDateOfApplicationComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in descending order.";
        SortPersonCommand command = new SortPersonCommand(comparator, false);
        expectedModel.updateSortedPersonList(comparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }

    @Test
    public void execute_descendingBlacklist_sortedSuccess() {
        PersonBlackListComparator comparator = new PersonBlackListComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in descending order.";
        SortPersonCommand command = new SortPersonCommand(comparator, false);
        expectedModel.updateSortedPersonList(comparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedPersonList(), model.getSortedPersonList());
    }

    @Test
    public void equals() {
        PersonBlackListComparator blackListComparator = new PersonBlackListComparator();
        PersonDateOfApplicationComparator dateOfApplicationComparator = new PersonDateOfApplicationComparator();
        PersonExperienceComparator experienceComparator = new PersonExperienceComparator();
        PersonExpectedSalaryComparator expectedSalaryComparator = new PersonExpectedSalaryComparator();
        PersonNameComparator nameComparator = new PersonNameComparator();

        SortPersonCommand sortFirstCommand = new SortPersonCommand(blackListComparator, true);
        SortPersonCommand sortSecondCommand = new SortPersonCommand(dateOfApplicationComparator, false);
        SortPersonCommand sortThirdCommand = new SortPersonCommand(experienceComparator, true);
        SortPersonCommand sortFourthCommand = new SortPersonCommand(expectedSalaryComparator, true);
        SortPersonCommand sortFifthCommand = new SortPersonCommand(nameComparator, false);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortPersonCommand sortFirstCommandCopy = new SortPersonCommand(blackListComparator, true);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
        assertFalse(sortFirstCommand.equals(sortThirdCommand));
        assertFalse(sortFirstCommand.equals(sortFourthCommand));
        assertFalse(sortFirstCommand.equals(sortFifthCommand));
    }
}
