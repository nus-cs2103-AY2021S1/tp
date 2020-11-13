package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewPastConsultationsCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewPastConsultationsCommandTest {

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
        Command command = new ViewPastConsultationsCommand();
        String expectedMessage = ViewPastConsultationsCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewPastConsultationsCommand viewPastConsultationsCommand = new ViewPastConsultationsCommand();
        assertThrows(NullPointerException.class, () -> viewPastConsultationsCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewPastConsultationsCommand_commandTargetFeatureAccurate() {
        ViewPastConsultationsCommand viewPastConsultationsCommand = new ViewPastConsultationsCommand();
        CommandResult commandResult = viewPastConsultationsCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Consultations, actualCommandTargetFeature);
    }
}
