package tp.acecs2103.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tp.acecs2103.testutil.Assert.assertThrows;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.UiTaskList;
import tp.acecs2103.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_WEEK_1 = "1";
    public static final String VALID_WEEK_2 = "2";
    public static final String VALID_DESCRIPTION_1 = "Description1";
    public static final String VALID_DESCRIPTION_2 = "Description2";
    public static final String VALID_REMARK_1 = "Remark 1";
    public static final String VALID_REMARK_2 = "Remark 2";
    public static final String VALID_DDL_1 = "2020-10-02";
    public static final String VALID_DDL_2 = "2020-11-07";

    public static final EditTaskDescriptorBuilder DESC_A;
    public static final EditTaskDescriptorBuilder DESC_B;

    static {
        DESC_A = new EditTaskDescriptorBuilder().withWeekNumber(VALID_WEEK_1)
                .withDescription(VALID_DESCRIPTION_1).withRemark(VALID_REMARK_1).withCustomizedDeadline(VALID_DDL_1);
        DESC_B = new EditTaskDescriptorBuilder().withWeekNumber(VALID_WEEK_2)
                .withDescription(VALID_DESCRIPTION_2).withRemark(VALID_REMARK_2).withCustomizedDeadline(VALID_DDL_2);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        CommandResult result = null;
        try {
            result = command.execute(actualModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(expectedCommandResult, result);
        assertEquals(expectedModel, actualModel);
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
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
     * - the address book, filtered Task list and selected Task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskList expectedTaskList = new TaskList(actualModel.getTaskList());
        UiTaskList expectedUiTaskList = new UiTaskList(actualModel.getUiTaskList().getCombinedTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskList, actualModel.getTaskList());
        assertEquals(expectedUiTaskList, actualModel.getUiTaskList());
    }
}
