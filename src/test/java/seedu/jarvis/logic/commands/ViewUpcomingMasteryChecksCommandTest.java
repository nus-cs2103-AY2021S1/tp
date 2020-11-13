package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewUpcomingMasteryChecksCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewUpcomingMasteryChecksCommandTest {

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
        Command command = new ViewUpcomingMasteryChecksCommand();
        String expectedMessage = ViewUpcomingMasteryChecksCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewUpcomingMasteryChecksCommand viewUpcomingMasteryChecksCommand = new ViewUpcomingMasteryChecksCommand();
        assertThrows(NullPointerException.class, () -> viewUpcomingMasteryChecksCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewUpcomingMasteryChecksCommand_commandTargetFeatureAccurate() {
        ViewUpcomingMasteryChecksCommand viewUpcomingMasteryChecksCommand = new ViewUpcomingMasteryChecksCommand();
        CommandResult commandResult = viewUpcomingMasteryChecksCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, actualCommandTargetFeature);
    }
}
