package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Job;
import seedu.address.model.information.predicate.JobCompanyNameContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobEmailContainsKeywordsPredicate;
import seedu.address.model.information.predicate.JobPhoneContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindJobCommand}.
 */
public class FindJobCommandTest {
    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(),
            new UserPrefs());

    @Test
    public void equals() {
        Predicate<Job> firstPredicate = unused -> true;
        firstPredicate.and(new JobCompanyNameContainsKeywordsPredicate(Collections.singletonList("Samsung")));
        Predicate<Job> secondPredicate = unused -> true;
        secondPredicate.and(new JobCompanyNameContainsKeywordsPredicate(Collections.singletonList("Apple")));

        FindJobCommand findFirstCommand = new FindJobCommand(firstPredicate);
        FindJobCommand findSecondCommand = new FindJobCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindJobCommand findFirstCommandCopy = new FindJobCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Predicate<Job> predicate = unused -> true;
        predicate.and(new JobCompanyNameContainsKeywordsPredicate(Collections.singletonList("Samsung")));
        FindJobCommand command = new FindJobCommand(predicate);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_predicateAcceptedByModel_findSuccessful() {
        // matching keyword found
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 6);
        Predicate<Job> firstPredicate = new JobEmailContainsKeywordsPredicate(Collections.singletonList("@"));
        FindJobCommand command = new FindJobCommand(firstPredicate);
        expectedModel.updateFilteredJobList(firstPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredJobList(), model.getFilteredJobList());

        // no matching keyword found
        expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        Predicate<Job> secondPredicate = new JobPhoneContainsKeywordsPredicate(Collections.singletonList("00000000"));
        command = new FindJobCommand(secondPredicate);
        expectedModel.updateFilteredJobList(secondPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
