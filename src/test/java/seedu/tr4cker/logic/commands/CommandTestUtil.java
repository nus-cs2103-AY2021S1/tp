package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DATE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_NEW;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DAILY_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DELETE_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.task.NameContainsKeywordsPredicate;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.testutil.EditTaskDescriptorBuilder;
import seedu.tr4cker.testutil.EditTodoDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_1 = "CS2100S Assignment";
    public static final String VALID_NAME_2 = "CS1010E Midterm QuizS";
    public static final String VALID_DEADLINE_1 = "20-Oct-2021 1800";
    public static final String VALID_DEADLINE_2 = "24-Sep-2021 2359";
    public static final int VALID_COMPLETION_STATUS_1 = 0;
    public static final int VALID_COMPLETION_STATUS_2 = 0;
    public static final String VALID_DESCRIPTION_1 = "description 1";
    public static final String VALID_DESCRIPTION_2 = "description 2";
    public static final String VALID_MODULE_NAME_1 = "Module 1";
    public static final String VALID_MODULE_NAME_2 = "Module 2";
    public static final String VALID_MODULE_CODE_1 = "CS1111";
    public static final String VALID_MODULE_CODE_2 = "CS2222";
    public static final String VALID_EVENT_NAME_1 = "event 1";
    public static final String VALID_EVENT_NAME_2 = "event 2";
    public static final String VALID_EVENT_DATE_1 = "10-10-2021";
    public static final String VALID_EVENT_DATE_2 = "20-10-2021";
    public static final String VALID_TAG_URGENT = "urgent";
    public static final String VALID_TAG_HELP = "help";
    public static final String VALID_TAG_HOMEWORK = "homework";
    public static final String VALID_TAG_WORK = "work";
    public static final String VALID_TAG_ASSIGNMENT = "assignment";
    public static final String VALID_TAG_MISSION = "mission";

    public static final String NAME_DESC_1 = " " + PREFIX_NAME + VALID_NAME_1;
    public static final String NAME_DESC_2 = " " + PREFIX_NAME + VALID_NAME_2;
    public static final String DEADLINE_DESC_1 = " " + PREFIX_DEADLINE + VALID_DEADLINE_1;
    public static final String DEADLINE_DESC_2 = " " + PREFIX_DEADLINE + VALID_DEADLINE_2;
    public static final String COMPLETION_STATUS_DESC_1 = " " + PREFIX_COMPLETION_STATUS + VALID_COMPLETION_STATUS_1;
    public static final String COMPLETION_STATUS_DESC_2 = " " + PREFIX_COMPLETION_STATUS + VALID_COMPLETION_STATUS_2;
    public static final String DESCRIPTION_DESC_1 = " " + PREFIX_TASK_DESCRIPTION + VALID_DESCRIPTION_1;
    public static final String DESCRIPTION_DESC_2 = " " + PREFIX_TASK_DESCRIPTION + VALID_DESCRIPTION_2;
    public static final String MODULE_NAME_DESC_1 = " " + PREFIX_MODULE_NAME + VALID_MODULE_NAME_1;
    public static final String MODULE_NAME_DESC_2 = " " + PREFIX_MODULE_NAME + VALID_MODULE_NAME_2;
    public static final String MODULE_CODE_DESC_1 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_1;
    public static final String MODULE_CODE_DESC_2 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_2;
    public static final String MODULE_DELETE_DESC = " " + PREFIX_MODULE_DELETE + "1";
    public static final String EVENT_NAME_DESC_1 = " " + PREFIX_COUNTDOWN_NEW + VALID_EVENT_NAME_1;
    public static final String EVENT_NAME_DESC_2 = " " + PREFIX_COUNTDOWN_NEW + VALID_EVENT_NAME_2;
    public static final String EVENT_DATE_DESC_1 = " " + PREFIX_COUNTDOWN_DATE + VALID_EVENT_DATE_1;
    public static final String EVENT_DATE_DESC_2 = " " + PREFIX_COUNTDOWN_DATE + VALID_EVENT_DATE_2;
    public static final String EVENT_DELETE_DESC_1 = " " + PREFIX_COUNTDOWN_DELETE + "1";
    public static final String DAILY_DELETE_DESC_1 = " " + PREFIX_DAILY_DELETE + "1";
    public static final String TAG_DESC_HELP = " " + PREFIX_TAG + VALID_TAG_HELP;
    public static final String TAG_DESC_URGENT = " " + PREFIX_TAG + VALID_TAG_URGENT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "CS2103&"; // '&' not allowed in names
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2020-09-a"; // 'a' not allowed
    // in deadlines
    public static final String INVALID_COMPLETION_STATUS = " " + PREFIX_COMPLETION_STATUS + 1000; // >100 not allowed
    public static final String INVALID_DESCRIPTION_DESC =
            " " + PREFIX_TASK_DESCRIPTION; // empty string not allowed for addresses
    public static final String INVALID_MODULE_NAME_DESC = " " + PREFIX_MODULE_NAME + "!Discrete Structures";
    // '!' not allowed
    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE + "#CS3241"; // '#' not allowed
    // in module codes
    public static final String INVALID_MODULE_DELETE_DESC = " " + PREFIX_MODULE_DELETE + "-1"; // '#' not allowed
    // in module codes
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "tag*"; // '*' not allowed in tags
    public static final String INVALID_NEW_TAG = " " + PREFIX_NEW_TAG + "tag*"; // '*' not allowed in tags
    public static final String INVALID_DELETE_TAG = " " + PREFIX_DELETE_TAG + "tag*"; // '*' not allowed in tags

    public static final String INVALID_DAILY_DELETE_DESC = " " + PREFIX_DAILY_DELETE + "-1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_1;
    public static final EditCommand.EditTaskDescriptor DESC_2;
    public static final EditCommand.EditTodoDescriptor DESC_3;
    public static final EditCommand.EditTodoDescriptor DESC_4;

    public static final String TAG_NEW_HOMEWORK = " " + PREFIX_NEW_TAG + VALID_TAG_HOMEWORK;
    public static final String TAG_NEW_WORK = " " + PREFIX_NEW_TAG + VALID_TAG_WORK;
    public static final String TAG_DELETE_ASSIGNMENT = " " + PREFIX_DELETE_TAG + VALID_TAG_ASSIGNMENT;
    public static final String TAG_DELETE_MISSION = " " + PREFIX_DELETE_TAG + VALID_TAG_MISSION;
    public static final String INVALID_NO_NEW_TAG = " " + PREFIX_NEW_TAG;
    public static final String INVALID_NO_DELETE_TAG = " " + PREFIX_DELETE_TAG;

    static {
        DESC_1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_1)
                .withDeadline(VALID_DEADLINE_1).withTaskDescription(VALID_DESCRIPTION_1)
                .withTags(VALID_TAG_HELP).build();
        DESC_2 = new EditTaskDescriptorBuilder().withName(VALID_NAME_2)
                .withDeadline(VALID_DEADLINE_2).withTaskDescription(VALID_DESCRIPTION_2)
                .withTags(VALID_TAG_URGENT, VALID_TAG_HELP).build();
        DESC_3 = new EditTodoDescriptorBuilder().withName(VALID_NAME_1)
                .withDeadline(VALID_DEADLINE_1).build();
        DESC_4 = new EditTodoDescriptorBuilder().withName(VALID_NAME_2)
                .withDeadline(VALID_DEADLINE_2).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
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
     * - the Tr4cker, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Tr4cker expectedTr4cker = new Tr4cker(actualModel.getTr4cker());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTr4cker, actualModel.getTr4cker());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s Tr4cker.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().taskName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
