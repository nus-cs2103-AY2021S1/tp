package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortJobCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.comparator.JobCompanyComparator;
import seedu.address.model.information.comparator.JobPriorityComparator;
import seedu.address.model.information.comparator.JobTitleComparator;
import seedu.address.model.information.comparator.JobVacancyComparator;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortJobCommand.
 */
public class SortJobCommandTest {

    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(),
        new UserPrefs());

    @Test
    public void execute_ascendingVacancy_jobsSorted() {
        JobVacancyComparator comparator = new JobVacancyComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in ascending order.";
        SortJobCommand command = new SortJobCommand(comparator, true);
        expectedModel.updateSortedJobList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedJobList(), model.getSortedJobList());
    }

    @Test
    public void execute_ascendingTitle_jobsSorted() {
        JobTitleComparator comparator = new JobTitleComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in ascending order.";
        SortJobCommand command = new SortJobCommand(comparator, true);
        expectedModel.updateSortedJobList(comparator);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedJobList(), model.getSortedJobList());
    }

    @Test
    public void execute_descendingPriority_jobsSorted() {
        JobPriorityComparator comparator = new JobPriorityComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in descending order.";
        SortJobCommand command = new SortJobCommand(comparator, false);
        expectedModel.updateSortedJobList(comparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedJobList(), model.getSortedJobList());
    }

    @Test
    public void execute_descendingCompanyName_jobsSorted() {
        JobCompanyComparator comparator = new JobCompanyComparator();
        String expectedMessage = MESSAGE_SUCCESS + comparator.toString() + "in descending order.";
        SortJobCommand command = new SortJobCommand(comparator, false);
        expectedModel.updateSortedJobList(comparator.reversed());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getSortedJobList(), model.getSortedJobList());
    }

    @Test
    public void equals() {
        JobVacancyComparator vacancyComparator = new JobVacancyComparator();
        JobPriorityComparator priorityComparator = new JobPriorityComparator();

        SortJobCommand sortFirstCommand = new SortJobCommand(vacancyComparator, true);
        SortJobCommand sortSecondCommand = new SortJobCommand(priorityComparator, false);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortJobCommand sortFirstCommandCopy = new SortJobCommand(vacancyComparator, true);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different commands -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }
}
