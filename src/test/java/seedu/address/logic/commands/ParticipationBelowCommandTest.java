package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.ParticipationBelowSpecifiedScorePredicate;

public class ParticipationBelowCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackr(), new UserPrefs());

    @Test
    public void equals() {
        int firstUpperBound = 1;
        int secondUpperBound = 101;
        ParticipationBelowSpecifiedScorePredicate firstPredicate =
                new ParticipationBelowSpecifiedScorePredicate(firstUpperBound);
        ParticipationBelowSpecifiedScorePredicate secondPredicate =
                new ParticipationBelowSpecifiedScorePredicate(secondUpperBound);

        ParticipationBelowCommand firstParticipationBelow =
                new ParticipationBelowCommand(firstPredicate, firstUpperBound);
        ParticipationBelowCommand secondParticipationBelow =
                new ParticipationBelowCommand(secondPredicate, secondUpperBound);

        // same object -> returns true
        assertTrue(firstParticipationBelow.equals(firstParticipationBelow));

        // same values -> returns true
        ParticipationBelowCommand firstParticipationBelowCommandCopy =
                new ParticipationBelowCommand(firstPredicate, firstUpperBound);
        assertTrue(firstParticipationBelow.equals(firstParticipationBelowCommandCopy));

        // different types -> returns false
        assertFalse(firstParticipationBelow.equals(1));

        // null -> returns false
        assertFalse(firstParticipationBelow.equals(null));

        // different command -> returns false
        assertFalse(firstParticipationBelow.equals(secondParticipationBelow));
    }

    @Test
    public void execute_zeroUpperBound_noModuleFound() {
        model.setViewToTutorialGroup(CS2103T);
        model.setViewToStudent(T05);
        expectedModel.setViewToTutorialGroup(CS2103T);
        expectedModel.setViewToStudent(T05);
        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS, 0);
        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(0);
        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 0);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    //    todo
    //    @Test
    //    public void execute_validUpperBound_multipleModulesFound() {
    //        model.setViewToTutorialGroup(CS2103T);
    //        model.setViewToStudent(T05);
    //        expectedModel.setViewToTutorialGroup(CS2103T);
    //        expectedModel.setViewToStudent(T05);
    //        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS, 50);
    //        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(50);
    //        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 50);
    //        expectedModel.updateFilteredStudentList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(BENG, CHARLIE, DAVID, FIONA), model.getFilteredStudentList());
    //    }
    //
    //
    //    @Test
    //    public void execute_topUpperBound_multipleModulesFound() {
    //        model.setViewToTutorialGroup(CS2103T);
    //        model.setViewToStudent(T05);
    //        expectedModel.setViewToTutorialGroup(CS2103T);
    //        expectedModel.setViewToStudent(T05);
    //        String expectedMessage = String.format(ParticipationBelowCommand.MESSAGE_PARTICIPATION_BELOW_SUCCESS,
    //                101);
    //        ParticipationBelowSpecifiedScorePredicate predicate = preparePredicate(101);
    //        ParticipationBelowCommand command = new ParticipationBelowCommand(predicate, 101);
    //        expectedModel.updateFilteredStudentList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(ALEX, BENG, CHARLIE, DAVID, ELIZABETH, FIONA), model.getFilteredStudentList());
    //    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private ParticipationBelowSpecifiedScorePredicate preparePredicate(int upperBound) {
        return new ParticipationBelowSpecifiedScorePredicate(upperBound);
    }
}
