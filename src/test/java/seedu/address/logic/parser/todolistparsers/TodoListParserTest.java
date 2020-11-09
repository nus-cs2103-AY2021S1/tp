package seedu.address.logic.parser.todolistparsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.todolistcommands.AddTaskCommand;
import seedu.address.logic.commands.todolistcommands.ClearTaskCommand;
import seedu.address.logic.commands.todolistcommands.CompleteTaskCommand;
import seedu.address.logic.commands.todolistcommands.DeleteTaskCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskCommand;
import seedu.address.logic.commands.todolistcommands.EditTaskDescriptor;
import seedu.address.logic.commands.todolistcommands.FindTaskCommand;
import seedu.address.logic.commands.todolistcommands.ListTaskCommand;
import seedu.address.logic.commands.todolistcommands.ResetTaskCommand;
import seedu.address.logic.commands.todolistcommands.SortTaskCommand;
import seedu.address.logic.parser.TodoListParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.todolist.EditTaskDescriptorBuilder;
import seedu.address.testutil.todolist.TaskBuilder;
import seedu.address.testutil.todolist.TaskUtil;

public class TodoListParserTest {

    private final TodoListParser parser = new TodoListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearTaskCommand.COMMAND_WORD) instanceof ClearTaskCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
            DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_complete() throws Exception {
        CompleteTaskCommand command = (CompleteTaskCommand) parser.parseCommand(
            CompleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new CompleteTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_reset() throws Exception {
        ResetTaskCommand command = (ResetTaskCommand) parser.parseCommand(
            ResetTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new ResetTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        Command command = (SortTaskCommand) parser.parseCommand(
            SortTaskCommand.COMMAND_WORD + " PRIORITY");
        assertNotNull(command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD + " "
            + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
            FindTaskCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        FindTaskCriteria criteria = new FindTaskCriteria();
        criteria.addPredicate(new TaskNameContainsKeywordsPredicate(keywords));
        assertEquals(new FindTaskCommand(criteria), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void singleWordCommandTest_valid_success() throws ParseException {
        Command command = parser.parseCommand(ClearTaskCommand.COMMAND_WORD);
        assertTrue(command instanceof ClearTaskCommand);
    }

    @Test
    public void singleWordCommandTest_invalid_failure() throws ParseException {
        assertThrows(ParseException.class, () -> parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 123"));
    }
}
