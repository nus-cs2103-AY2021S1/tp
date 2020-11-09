package seedu.address.logic.commands.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_ROUTINES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;
import seedu.address.model.util.Name;
import seedu.address.testutil.TypicalRoutines;

public class RoutineFindCommandTest {

    private Model model = new ModelManager(TypicalRoutines.getTypicalFitNus(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalRoutines.getTypicalFitNus(), new UserPrefs());
    private Routine testRoutine = new Routine(new Name("Jump Fly Swim"));
    private Routine testRoutine1 = new Routine(new Name("Fly Swim Jump"));
    private Routine testRoutine2 = new Routine(new Name("Swim Jump Fly"));


    /**
     * Parses {@code userInput} into a {@code RoutineNameContainsKeywordsPredicate}.
     */
    private RoutineNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RoutineNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void execute_singleKeywords_multipleRoutinesFound() {
        String expectedMessage = String.format(MESSAGE_ROUTINES_LISTED_OVERVIEW, 3);
        RoutineNameContainsKeywordsPredicate predicate = preparePredicate("Fly");
        RoutineFindCommand command = new RoutineFindCommand(predicate);
        model.addRoutine(testRoutine);
        model.addRoutine(testRoutine1);
        model.addRoutine(testRoutine2);
        model.updateFilteredRoutineList(predicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(testRoutine, testRoutine1, testRoutine2), model.getFilteredRoutineList());
    }

    @Test
    public void execute_multipleKeywords_multipleRoutinesFound() {
        String expectedMessage = String.format(MESSAGE_ROUTINES_LISTED_OVERVIEW, 3);
        RoutineNameContainsKeywordsPredicate predicate = preparePredicate("Fly Jump Swim");
        RoutineFindCommand command = new RoutineFindCommand(predicate);
        model.addRoutine(testRoutine);
        model.addRoutine(testRoutine1);
        model.addRoutine(testRoutine2);
        model.updateFilteredRoutineList(predicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(testRoutine, testRoutine1, testRoutine2), model.getFilteredRoutineList());
    }

    @Test
    public void equals() {
        RoutineNameContainsKeywordsPredicate firstPredicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList("first"));
        RoutineNameContainsKeywordsPredicate secondPredicate =
                new RoutineNameContainsKeywordsPredicate(Collections.singletonList("second"));

        RoutineFindCommand routineFindFirstCommand = new RoutineFindCommand(firstPredicate);
        RoutineFindCommand routineFindSecondCommand = new RoutineFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(routineFindFirstCommand.equals(routineFindFirstCommand));

        // same values -> returns true
        RoutineFindCommand routineFindFirstCommandCopy = new RoutineFindCommand(firstPredicate);
        assertTrue(routineFindFirstCommand.equals(routineFindFirstCommandCopy));

        // different types -> returns false
        assertFalse(routineFindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(routineFindFirstCommand.equals(null));

        // different Routine -> returns false
        assertFalse(routineFindFirstCommand.equals(routineFindSecondCommand));
    }
}
