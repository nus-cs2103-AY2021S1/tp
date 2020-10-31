package seedu.tasklist.logic.commands;

import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tasklist.testutil.TypicalAssignments.getTypicalProductiveNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;
import seedu.tasklist.model.UserPrefs;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.testutil.AssignmentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
    }

    @Test
    public void execute_newAssignment_success() {
        Assignment validAssignment = new AssignmentBuilder().build();

        Model expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
        expectedModel.addAssignment(validAssignment);

        assertCommandSuccess(new AddCommand(validAssignment), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validAssignment), expectedModel);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Assignment assignmentInList = model.getProductiveNus().getAssignmentList().get(0);
        assertCommandFailure(new AddCommand(assignmentInList), model, AddCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

}
