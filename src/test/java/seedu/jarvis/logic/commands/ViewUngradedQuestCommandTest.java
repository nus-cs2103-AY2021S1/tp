package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalQuests.CARDIOID_ARREST;
import static seedu.jarvis.testutil.TypicalQuests.CURVACEOUS_WIZARDRY;
import static seedu.jarvis.testutil.TypicalQuests.RUNIC_CARPETS;
import static seedu.jarvis.testutil.TypicalQuests.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.commands.view.ViewUngradedQuestCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewUngradedQuestCommandTest {

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
        Command command = new ViewUngradedQuestCommand();
        String expectedMessage = ViewUngradedQuestCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewUngradedQuestCommand viewUngradedQuestCommand = new ViewUngradedQuestCommand();
        assertThrows(NullPointerException.class, () -> viewUngradedQuestCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewUngradedQuestCommand_questListFiltered() {
        String expectedMessage = ViewUngradedQuestCommand.MESSAGE_SUCCESS;
        ViewUngradedQuestCommand command = new ViewUngradedQuestCommand();
        expectedModel.updateQuestsList(ViewUngradedQuestCommand.PREDICATE_SHOW_UNGRADED_QUESTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RUNIC_CARPETS, CARDIOID_ARREST, CURVACEOUS_WIZARDRY),
                model.getFilteredQuestList());
    }

    @Test
    public void execute_viewUngradedQuestCommand_commandTargetFeatureAccurate() throws CommandException {
        ViewUngradedQuestCommand viewUngradedQuestCommand = new ViewUngradedQuestCommand();
        CommandResult commandResult = viewUngradedQuestCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Quest, actualCommandTargetFeature);
    }

}
