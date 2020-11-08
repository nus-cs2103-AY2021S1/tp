package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;

public class PreviousViewCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());

    @Test
    public void execute_inModelView_success() {
        PreviousViewCommand command = new PreviousViewCommand();
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        String expectedMessage = PreviousViewCommand.MESSAGE_IN_MODULE_VIEW;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_fromTutorialGroupViewToModuleView_success() {
        model.setCurrentViewToTutorialGroup();
        PreviousViewCommand command = new PreviousViewCommand();
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setCurrentViewToModule();
        String expectedMessage = PreviousViewCommand.MESSAGE_VIEWING_MODULES_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_fromStudentViewTutorialGroupView_success() {
        model.setCurrentViewToStudent();
        PreviousViewCommand command = new PreviousViewCommand();
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setCurrentViewToTutorialGroup();
        String expectedMessage = String.format(PreviousViewCommand.MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS,
                model.getCurrentModuleInView());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        PreviousViewCommand previousViewCommand = new PreviousViewCommand();

        // same object -> returns true
        assertTrue(previousViewCommand.equals(previousViewCommand));

        // same values -> returns true
        PreviousViewCommand previousViewCommandCopy = new PreviousViewCommand();
        assertTrue(previousViewCommand.equals(previousViewCommandCopy));

        // different types -> returns false
        assertFalse(previousViewCommand.equals(1));

        // null -> returns false
        assertFalse(previousViewCommand.equals(null));
    }
}
