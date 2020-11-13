package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalQuests.CARDIOID_ARREST;
import static seedu.jarvis.testutil.TypicalQuests.COLORFUL_CARPETS;
import static seedu.jarvis.testutil.TypicalQuests.FUNCTIONAL_EXPRESSIONISM;
import static seedu.jarvis.testutil.TypicalQuests.RUNIC_CARPETS;
import static seedu.jarvis.testutil.TypicalQuests.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewQuestDeadlineCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewQuestDeadlineCommandTest {

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
        Command command = new ViewQuestDeadlineCommand();
        String expectedMessage = ViewQuestDeadlineCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewQuestDeadlineCommand viewQuestDeadlineCommand = new ViewQuestDeadlineCommand();
        assertThrows(NullPointerException.class, () -> viewQuestDeadlineCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewQuestDeadlineCommand_questListFiltered() {
        String expectedMessage = ViewQuestDeadlineCommand.MESSAGE_SUCCESS;
        ViewQuestDeadlineCommand command = new ViewQuestDeadlineCommand();
        expectedModel.updateQuestsList(model.PREDICATE_SHOW_ALL_QUESTS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(RUNIC_CARPETS, COLORFUL_CARPETS, FUNCTIONAL_EXPRESSIONISM, CARDIOID_ARREST),
                model.getFilteredQuestList());
    }

    @Test
    public void execute_viewQuestDeadlineCommand_commandTargetFeatureAccurate() {
        ViewQuestDeadlineCommand viewQuestDeadlineCommand = new ViewQuestDeadlineCommand();
        CommandResult commandResult = viewQuestDeadlineCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Quest, actualCommandTargetFeature);
    }

}
