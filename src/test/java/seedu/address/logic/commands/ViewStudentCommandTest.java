package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class ViewStudentCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Module moduleInView = CS2103T;

    @Test
    public void execute_invalidTutorialGroupIndex_failure() {
        model.setCurrentViewToTutorialGroup();
        ViewStudentCommand command = new ViewStudentCommand(INDEX_FIRST_PERSON);
        String expectedMessage = Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_moduleView_failure() {
        model.setCurrentViewToModule();
        ViewStudentCommand command = new ViewStudentCommand(INDEX_FIRST_PERSON);
        String expectedMessage = ViewStudentCommand.MESSAGE_WRONG_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_tutorialGroup_correctSuccessMessage() throws CommandException {
        model.setViewToTutorialGroup(moduleInView);
        TutorialGroup tutorialGroup = model.getFilteredTutorialGroupList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewStudentCommand command = new ViewStudentCommand(INDEX_FIRST_PERSON);
        String message = command.execute(model).getFeedbackToUser();
        String expectedMessage = String.format(ViewStudentCommand.MESSAGE_SUCCESS, tutorialGroup);
        assertEquals(expectedMessage, message);
    }

    @Test
    public void execute_tutorialGroupInvalidIndex_failure() {
        model.setViewToTutorialGroup(moduleInView);
        Index invalidIndex = Index.fromOneBased(6);
        ViewStudentCommand command = new ViewStudentCommand(invalidIndex);
        String expectedMessage = Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        ViewStudentCommand viewStudentCommandFirst = new ViewStudentCommand(INDEX_FIRST_PERSON);
        ViewStudentCommand viewStudentCommandSecond = new ViewStudentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewStudentCommandFirst.equals(viewStudentCommandFirst));

        // same values -> returns true
        ViewStudentCommand viewStudentCommandFirstCopy = new ViewStudentCommand(INDEX_FIRST_PERSON);
        assertTrue(viewStudentCommandFirst.equals(viewStudentCommandFirstCopy));

        // different types -> returns false
        assertFalse(viewStudentCommandFirst.equals(1));

        // null -> returns false
        assertFalse(viewStudentCommandFirst.equals(null));

        // different person -> returns false
        assertFalse(viewStudentCommandFirst.equals(viewStudentCommandSecond));
    }
}
