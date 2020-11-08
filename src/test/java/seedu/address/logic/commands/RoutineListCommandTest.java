package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoutines.getTypicalFitNus;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.routines.RoutineListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RoutineListCommand.
 */
public class RoutineListCommandTest {

    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private final Index index = Index.fromOneBased(1);

    private void showRoutineAtIndex(Model model) {
        //First Routine in Model
        Routine routine = model.getFilteredRoutineList().get(index.getOneBased());

        //Update filter to show only this Routine
        final String[] splitName = routine.getName().fullName.split("\\s+");
        model.updateFilteredRoutineList(new RoutineNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    }

    @Test
    public void execute_routineListIsNotFiltered_showsSameRoutineList() {
        assertCommandSuccess(new RoutineListCommand(), model, RoutineListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_routineListIsFiltered_showsEverything() {
        showRoutineAtIndex(model);
        assertCommandSuccess(new RoutineListCommand(), model, RoutineListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
