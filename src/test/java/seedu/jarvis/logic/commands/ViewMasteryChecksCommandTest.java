package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewMasteryChecksCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewMasteryChecksCommandTest {
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
        Command command = new ViewMasteryChecksCommand();
        String expectedMessage = ViewMasteryChecksCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewMasteryChecksCommand viewMasteryChecksCommand = new ViewMasteryChecksCommand();
        assertThrows(NullPointerException.class, () -> viewMasteryChecksCommand.execute(emptyModel));
    }

    @Test
    public void execute_masteryChecksListNotFiltered_commandTargetFeatureAccurate() {
        ViewMasteryChecksCommand viewMasteryChecksCommand = new ViewMasteryChecksCommand();
        CommandResult commandResult = viewMasteryChecksCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, commandTargetFeature);
    }
}
