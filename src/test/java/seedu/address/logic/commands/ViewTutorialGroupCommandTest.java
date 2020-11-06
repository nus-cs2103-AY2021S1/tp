package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

public class ViewTutorialGroupCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());

    @Test
    public void execute_invalidModuleIndex_failure() {
        Index invalidIndex = Index.fromOneBased(6);
        ViewTutorialGroupCommand command = new ViewTutorialGroupCommand(invalidIndex);
        String expectedMessage = Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_studentViewp_failure() {
        model.setCurrentViewToTutorialGroup();
        model.setCurrentViewToStudent();
        ViewTutorialGroupCommand command = new ViewTutorialGroupCommand(INDEX_FIRST_PERSON);
        String expectedMessage = ViewTutorialGroupCommand.MESSAGE_NOT_IN_MODULE_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_viewTutorialGroup_correctSuccessMessage() throws CommandException {
        Module module = model.getFilteredModuleList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewTutorialGroupCommand command = new ViewTutorialGroupCommand(INDEX_FIRST_PERSON);
        String message = command.execute(model).getFeedbackToUser();
        String expectedMessage = String.format(ViewTutorialGroupCommand.MESSAGE_VIEWING_TG_SUCCESS, module);
        assertEquals(expectedMessage, message);
    }

    @Test
    public void equals() {
        ViewTutorialGroupCommand viewTutorialGroupCommandFirst = new ViewTutorialGroupCommand(INDEX_FIRST_PERSON);
        ViewTutorialGroupCommand viewTutorialGroupCommandSecond = new ViewTutorialGroupCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewTutorialGroupCommandFirst.equals(viewTutorialGroupCommandFirst));

        // same values -> returns true
        ViewTutorialGroupCommand viewTutorialGroupCommandFirstCopy = new ViewTutorialGroupCommand(INDEX_FIRST_PERSON);
        assertTrue(viewTutorialGroupCommandFirst.equals(viewTutorialGroupCommandFirstCopy));

        // different types -> returns false
        assertFalse(viewTutorialGroupCommandFirst.equals(1));

        // null -> returns false
        assertFalse(viewTutorialGroupCommandFirst.equals(null));

        // different person -> returns false
        assertFalse(viewTutorialGroupCommandFirst.equals(viewTutorialGroupCommandSecond));
    }
}
