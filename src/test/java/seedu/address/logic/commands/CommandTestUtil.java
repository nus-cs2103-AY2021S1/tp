package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Nusave;
import seedu.address.model.Renderable;


public class CommandTestUtil {

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * @param command
     * @param actualModel
     * @param expectedResult
     * @param expectedModel
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     * @param command
     * @param actualModel
     * @param expectedMessage
     * @param expectedModel
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * @param command
     * @param actualModel
     * @param expectedMessage
     */
    public static void assertCommandFailure(Command command, Model actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Nusave expectedNusave = new Nusave(actualModel.getNusave());
        List<Renderable> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRenderableList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedNusave, actualModel.getNusave());
        assertEquals(expectedFilteredList, actualModel.getFilteredRenderableList());
    }
}
