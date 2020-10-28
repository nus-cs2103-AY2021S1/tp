package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.AppMode;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;

public class HelpCommandTest {
    private CommandHistory history = new CommandHistory();

    /* Command that is available in all modes */
    private final CommandWordEnum allModesCommand = CommandWordEnum.HELP_COMMAND;
    /* Command that is only avaiable when the app is in {@code AppMode.NEW} */
    private final CommandWordEnum newModeOnlyCommand = CommandWordEnum.ADD_ROOM_COMMAND;
    /* Command that is only available when the app is in {@code AppMode.NORMAL} */
    private final CommandWordEnum normalModeOnlyCommand = CommandWordEnum.ADD_COMMAND;

    /**
     * Verify the correctness of {@code allModesCommand}, {@code newModeOnlyCommand} and {@code normalModeOnlyCommand}.
     */
    @Test
    public void testSetup() {
        assertEquals(Arrays.asList(AppMode.values()), allModesCommand.getAppModes());

        assertTrue(newModeOnlyCommand.getAppModes().contains(AppMode.NEW));
        assertFalse(newModeOnlyCommand.getAppModes().contains(AppMode.NORMAL));

        assertFalse(normalModeOnlyCommand.getAppModes().contains(AppMode.NEW));
        assertTrue(normalModeOnlyCommand.getAppModes().contains(AppMode.NORMAL));
    }

    @Test
    public void executeWithNoCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        HelpCommand helpCommand = new HelpCommand("");
        HelpCommand helpCommandWithSpace = new HelpCommand(" ");

        // during new mode
        assertCommandSuccess(helpCommand, model, history,
                HelpCommand.getGeneralHelpMessage(expectedModel.getAppMode()), expectedModel);
        assertCommandSuccess(helpCommandWithSpace, model, history,
                HelpCommand.getGeneralHelpMessage(expectedModel.getAppMode()), expectedModel);

        // during normal mode
        model.finalizeRooms();
        expectedModel.finalizeRooms();
        assertCommandSuccess(helpCommand, model, history,
                HelpCommand.getGeneralHelpMessage(expectedModel.getAppMode()), expectedModel);

        assertCommandSuccess(helpCommandWithSpace, model, history,
                HelpCommand.getGeneralHelpMessage(expectedModel.getAppMode()), expectedModel);

    }

    /**
     * Check help for {@code allModesCommand}, {@code newModeOnlyCommand} and {@code normalModeOnlyCommand}
     * when the app is in {@code AppMode.NEW}.
     */
    @Test
    public void executeInNewMode() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new HelpCommand(allModesCommand.getCommandWord()), model, history,
                allModesCommand.getHelp().getFullMessage(), expectedModel);
        assertCommandSuccess(new HelpCommand(newModeOnlyCommand.getCommandWord()), model, history,
                newModeOnlyCommand.getHelp().getFullMessage(), expectedModel);
        assertCommandFailure(new HelpCommand(normalModeOnlyCommand.getCommandWord()), model, history,
                String.format(HelpCommand.MESSAGE_UNKNOWN_COMMAND, normalModeOnlyCommand.getCommandWord()));
    }

    /**
     * Check help for {@code allModesCommand}, {@code newModeOnlyCommand} and {@code normalModeOnlyCommand}
     * when the app is in {@code AppMode.NORMAL}.
     */
    @Test
    public void executeInNormalMode() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        model.finalizeRooms();
        expectedModel.finalizeRooms();
        assertCommandSuccess(new HelpCommand(allModesCommand.getCommandWord()), model, history,
                allModesCommand.getHelp().getFullMessage(), expectedModel);
        assertCommandSuccess(new HelpCommand(normalModeOnlyCommand.getCommandWord()), model, history,
                normalModeOnlyCommand.getHelp().getFullMessage(), expectedModel);
        assertCommandFailure(new HelpCommand(newModeOnlyCommand.getCommandWord()), model, history,
                String.format(HelpCommand.MESSAGE_UNKNOWN_COMMAND, newModeOnlyCommand.getCommandWord()));
    }

    @Test
    public void executeWithInvalidCommand_failure() {
        Model model = new ModelManager();

        String invalidCommand = "nonsenseCommand";
        HelpCommand helpCommand = new HelpCommand(invalidCommand);
        assertCommandFailure(helpCommand, model, history,
                String.format(HelpCommand.MESSAGE_UNKNOWN_COMMAND, invalidCommand));
    }

}
