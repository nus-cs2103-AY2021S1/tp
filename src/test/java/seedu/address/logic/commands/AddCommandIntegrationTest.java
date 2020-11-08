package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.testutil.AssignmentBuilder;

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
