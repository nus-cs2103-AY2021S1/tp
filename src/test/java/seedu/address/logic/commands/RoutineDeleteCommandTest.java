package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoutines.getTypicalFitNus;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineDeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RoutineDeleteCommand}.
 */
public class RoutineDeleteCommandTest {

    private static final Index INDEX_FIRST_ROUTINE = Index.fromOneBased(1);
    private static final Index INDEX_SECOND_ROUTINE = Index.fromOneBased(2);
    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredRoutineList_success() {
        Routine routineToDelete = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        RoutineDeleteCommand routineDeleteCommand = new RoutineDeleteCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(RoutineDeleteCommand.MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete);

        ModelManager expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteRoutine(routineToDelete);

        assertCommandSuccess(routineDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredRoutineList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoutineList().size() + 1);
        RoutineDeleteCommand routineDeleteCommand = new RoutineDeleteCommand(outOfBoundIndex);

        assertCommandFailure(routineDeleteCommand, model, Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredRoutineList_success() {

        showRoutineAtIndex(model);

        //Delete this only Routine from model
        Routine routineToDelete = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        RoutineDeleteCommand routineDeleteCommand = new RoutineDeleteCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(RoutineDeleteCommand.MESSAGE_DELETE_ROUTINE_SUCCESS, routineToDelete);

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteRoutine(routineToDelete);
        showNoRoutine(expectedModel);

        assertCommandSuccess(routineDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredRoutineList_throwsCommandException() {
        showRoutineAtIndex(model);

        Index outOfBoundIndex = INDEX_SECOND_ROUTINE;

        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getRoutineList().size());

        RoutineDeleteCommand routineDeleteCommand = new RoutineDeleteCommand(outOfBoundIndex);

        assertCommandFailure(routineDeleteCommand, model, Messages.MESSAGE_INVALID_ROUTINE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RoutineDeleteCommand routineDeleteFirstCommand = new RoutineDeleteCommand(INDEX_FIRST_ROUTINE);
        RoutineDeleteCommand routineDeleteSecondCommand = new RoutineDeleteCommand(INDEX_SECOND_ROUTINE);

        // same object -> returns true
        assertTrue(routineDeleteFirstCommand.equals(routineDeleteFirstCommand));

        // same values -> returns true
        RoutineDeleteCommand routineDeleteFirstCommandCopy = new RoutineDeleteCommand(INDEX_FIRST_ROUTINE);
        assertTrue(routineDeleteFirstCommand.equals(routineDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(routineDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(routineDeleteFirstCommand.equals(null));

        // different Routine -> returns false
        assertFalse(routineDeleteFirstCommand.equals(routineDeleteSecondCommand));
    }

    private void showRoutineAtIndex(Model model) {
        //First Routine in Model
        Routine routine = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getOneBased());

        //Update filter to show only this Routine
        final String[] splitName = routine.getName().fullName.split("\\s+");
        model.updateFilteredRoutineList(new RoutineNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    }

    /**
     * Updates {@code model}'s filtered Routine list to show no one.
     */
    private void showNoRoutine(Model model) {
        model.updateFilteredRoutineList(p -> false);

        assertTrue(model.getFilteredRoutineList().isEmpty());
    }
}
