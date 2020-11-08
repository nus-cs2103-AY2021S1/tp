package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoutines.getTypicalFitNus;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineViewCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RoutineViewCommand}.
 */
public class RoutineViewCommandTest {

    private static final Index INDEX_FIRST_ROUTINE = Index.fromOneBased(1);
    private static final Index INDEX_SECOND_ROUTINE = Index.fromOneBased(2);
    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredRoutineList_success() {
        Routine routineToView = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        RoutineViewCommand routineViewCommand = new RoutineViewCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(RoutineViewCommand.MESSAGE_SUCCESS, routineToView);

        ModelManager expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.viewRoutine(routineToView);

        assertCommandSuccess(routineViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredRoutineList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoutineList().size() + 1);
        RoutineViewCommand routineViewCommand = new RoutineViewCommand(outOfBoundIndex);

        assertCommandFailure(routineViewCommand, model, RoutineViewCommand.MESSAGE_OUT_OF_BOUNDS_ROUTINE);
    }

    @Test
    public void execute_validIndexFilteredRoutineList_success() {

        showRoutineAtIndex(model);

        //View this only Routine from model
        Routine routineToView = model.getFilteredRoutineList().get(INDEX_FIRST_ROUTINE.getZeroBased());
        RoutineViewCommand routineViewCommand = new RoutineViewCommand(INDEX_FIRST_ROUTINE);

        String expectedMessage = String.format(RoutineViewCommand.MESSAGE_SUCCESS, routineToView);

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.viewRoutine(routineToView);
        showNoRoutine(expectedModel);

        assertCommandSuccess(routineViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredRoutineList_throwsCommandException() {
        showRoutineAtIndex(model);

        Index outOfBoundIndex = INDEX_SECOND_ROUTINE;

        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getRoutineList().size());

        RoutineViewCommand routineViewCommand = new RoutineViewCommand(outOfBoundIndex);

        assertCommandFailure(routineViewCommand, model, RoutineViewCommand.MESSAGE_OUT_OF_BOUNDS_ROUTINE);
    }

    @Test
    public void equals() {
        RoutineViewCommand routineViewFirstCommand = new RoutineViewCommand(INDEX_FIRST_ROUTINE);
        RoutineViewCommand routineViewSecondCommand = new RoutineViewCommand(INDEX_SECOND_ROUTINE);

        // same object -> returns true
        assertTrue(routineViewFirstCommand.equals(routineViewFirstCommand));

        // same values -> returns true
        RoutineViewCommand routineViewFirstCommandCopy = new RoutineViewCommand(INDEX_FIRST_ROUTINE);
        assertTrue(routineViewFirstCommand.equals(routineViewFirstCommandCopy));

        // different types -> returns false
        assertFalse(routineViewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(routineViewFirstCommand.equals(null));

        // different Routine -> returns false
        assertFalse(routineViewFirstCommand.equals(routineViewSecondCommand));
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
