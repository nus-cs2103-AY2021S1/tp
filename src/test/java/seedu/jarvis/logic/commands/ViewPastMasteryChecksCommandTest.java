package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewPastMasteryChecksCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;


public class ViewPastMasteryChecksCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel = new ModelManager(model.getAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
    }

    @Test
    public void execute_noFilters_success() {
        Command command = new ViewPastMasteryChecksCommand();
        String expectedMessage = ViewPastMasteryChecksCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewPastMasteryChecksCommand viewPastMasteryChecksCommand = new ViewPastMasteryChecksCommand();
        assertThrows(NullPointerException.class, () -> viewPastMasteryChecksCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewPastMasteryChecksCommand_commandTargetFeatureAccurate() {
        ViewPastMasteryChecksCommand viewPastMasteryChecksCommand = new ViewPastMasteryChecksCommand();
        CommandResult commandResult = viewPastMasteryChecksCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, actualCommandTargetFeature);
    }
}
