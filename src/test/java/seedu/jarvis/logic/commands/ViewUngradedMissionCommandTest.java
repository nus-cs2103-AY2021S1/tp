package seedu.jarvis.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.FRACTAL_DIMENSIONS;
import static seedu.jarvis.testutil.TypicalMissions.STREAMS;
import static seedu.jarvis.testutil.TypicalMissions.STREAM_ANOMALY;
import static seedu.jarvis.testutil.TypicalMissions.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.logic.commands.view.ViewUngradedMissionCommand;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.ModelManager;
import seedu.jarvis.testutil.TypicalManagers;

public class ViewUngradedMissionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        expectedModel = new ModelManager(getTypicalAddressBook(), TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
    }

    @Test
    public void execute_noFilters_success() {
        Command command = new ViewUngradedMissionCommand();
        String expectedMessage = ViewUngradedMissionCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyModel_throwsNullPointerException() {
        Model emptyModel = null;
        ViewUngradedMissionCommand viewUngradedMissionCommand = new ViewUngradedMissionCommand();
        assertThrows(NullPointerException.class, () -> viewUngradedMissionCommand.execute(emptyModel));
    }

    @Test
    public void execute_viewUngradedMissionCommand_missionListFiltered() {
        String expectedMessage = ViewUngradedMissionCommand.MESSAGE_SUCCESS;
        ViewUngradedMissionCommand command = new ViewUngradedMissionCommand();
        expectedModel.updateMissionsList(ViewUngradedMissionCommand.PREDICATE_SHOW_UNGRADED_MISSIONS);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FRACTAL_DIMENSIONS, STREAMS, STREAM_ANOMALY),
                model.getFilteredMissionList());
    }

    @Test
    public void execute_viewUngradedMissionCommand_commandTargetFeatureAccurate() throws CommandException {
        ViewUngradedMissionCommand viewUngradedMissionCommand = new ViewUngradedMissionCommand();
        CommandResult commandResult = viewUngradedMissionCommand.execute(model);
        CommandTargetFeature actualCommandTargetFeature = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Missions, actualCommandTargetFeature);
    }


}
