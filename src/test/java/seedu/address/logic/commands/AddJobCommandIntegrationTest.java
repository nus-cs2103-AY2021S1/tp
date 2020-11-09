package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Job;
import seedu.address.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddJobCommand}.
 */
public class AddJobCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newJob_success() {
        Job validJob = new JobBuilder().build();

        Model expectedModel = new ModelManager(model.getPersonAddressBook(), model.getJobAddressBook(),
                new UserPrefs());
        expectedModel.addJob(validJob);

        assertCommandSuccess(new AddJobCommand(validJob), model,
                String.format(AddJobCommand.MESSAGE_SUCCESS, validJob), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Job jobInList = model.getJobAddressBook().getJobList().get(0);
        assertCommandFailure(new AddJobCommand(jobInList), model, AddJobCommand.MESSAGE_DUPLICATE_JOB);
    }

}
