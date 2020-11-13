package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.FRACTAL_DIMENSIONS;
import static seedu.jarvis.testutil.TypicalMissions.MUSICAL_NOTES;
import static seedu.jarvis.testutil.TypicalMissions.REUSE_PAIRS;
import static seedu.jarvis.testutil.TypicalMissions.STREAMS;
import static seedu.jarvis.testutil.TypicalMissions.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.view.ViewMissionDeadlineCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;


public class ViewMissionDeadlineCommandTest {

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
        Command command = new ViewMissionDeadlineCommand();
        String expectedMessage = ViewMissionDeadlineCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewMissionDeadlineCommand viewMissionDeadlineCommand = new ViewMissionDeadlineCommand();
        assertThrows(NullPointerException.class, () -> viewMissionDeadlineCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewMissionDeadlineCommand_missionListFiltered() {
        String expectedMessage = ViewMissionDeadlineCommand.MESSAGE_SUCCESS;
        ViewMissionDeadlineCommand command = new ViewMissionDeadlineCommand();
        expectedModel.updateMissionsList(model.PREDICATE_SHOW_ALL_MISSIONS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FRACTAL_DIMENSIONS, MUSICAL_NOTES, REUSE_PAIRS, STREAMS),
                model.getFilteredMissionList());
    }

    @Test
    public void execute_viewMissionDeadlineCommand_commandTargetFeatureAccurate() {
        ViewMissionDeadlineCommand viewMissionDeadlineCommand = new ViewMissionDeadlineCommand();
        CommandResult commandResult = viewMissionDeadlineCommand.execute(model);
        CommandTargetFeature commandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Missions, commandTargetFeature);
    }

}
