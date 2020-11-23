package seedu.tr4cker.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_EMPTY;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_DELETE_ASSIGNMENT;
import static seedu.tr4cker.logic.commands.CommandTestUtil.TAG_NEW_HOMEWORK;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.commands.DeleteCommand;
import seedu.tr4cker.logic.commands.DoneCommand;
import seedu.tr4cker.logic.commands.EditCommand;
import seedu.tr4cker.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.tr4cker.logic.commands.EditCommand.EditTodoDescriptor;
import seedu.tr4cker.logic.commands.ExitCommand;
import seedu.tr4cker.logic.commands.FindCommand;
import seedu.tr4cker.logic.commands.HelpCommand;
import seedu.tr4cker.logic.commands.ListCommand;
import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.logic.commands.ResetCommand;
import seedu.tr4cker.logic.commands.TagCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.NameContainsKeywordsPredicate;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.testutil.EditTaskDescriptorBuilder;
import seedu.tr4cker.testutil.EditTodoDescriptorBuilder;
import seedu.tr4cker.testutil.TaskBuilder;
import seedu.tr4cker.testutil.TaskUtil;

public class Tr4ckerParserTest {

    private final Tr4ckerParser parser = new Tr4ckerParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new TaskBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(TaskUtil.getAddCommand(task));
        assertEquals(new AddCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD + " 3") instanceof ResetCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new TaskBuilder().build();
        Todo todo = new Todo(task.getName(), task.getDeadline());
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder(task).build();
        EditTodoDescriptor descriptor2 = new EditTodoDescriptorBuilder(todo).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor1));
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_TASK, descriptor1, descriptor2);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_done() throws Exception {
        CompletionStatus completionStatus = new CompletionStatus(50);
        DoneCommand command = (DoneCommand) parser.parseCommand(DoneCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + "p/50");
        DoneCommand expectedCommand = new DoneCommand(INDEX_FIRST_TASK, completionStatus);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_tag() throws Exception {
        Tag tag1 = new Tag("homework");
        Tag tag2 = new Tag("assignment");
        Set<Tag> add = new HashSet<>();
        Set<Tag> delete = new HashSet<>();
        add.add(tag1);
        delete.add(tag2);
        TagCommand command = (TagCommand) parser.parseCommand(
                TagCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased()
                        + TAG_NEW_HOMEWORK + TAG_DELETE_ASSIGNMENT);
        assertEquals(new TagCommand(INDEX_FIRST_TASK, add, delete), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_planner() throws Exception {
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD) instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/today") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/tdy") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/tomorrow") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/tmr") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/Oct-2020") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/10-Oct-2020") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/10-2020") instanceof PlannerCommand);
        assertTrue(parser.parseCommand(PlannerCommand.COMMAND_WORD + " goto/10-10-2020") instanceof PlannerCommand);
    }

    @Test
    public void parseCommand_countdown() throws Exception {
        assertTrue(parser.parseCommand(CountdownCommand.COMMAND_WORD) instanceof CountdownCommand);
        assertTrue(parser.parseCommand(CountdownCommand.COMMAND_WORD + " n/Halloween Party" + " d/31-Oct-2021")
                instanceof CountdownCommand);
        assertThrows(ParseException.class, ()
            -> parser.parseCommand(CountdownCommand.COMMAND_WORD + " n/Halloween Party"));
        assertThrows(ParseException.class, ()
            -> parser.parseCommand(CountdownCommand.COMMAND_WORD + " d/31-Oct-2021"));
        assertThrows(ParseException.class, ()
            -> parser.parseCommand(CountdownCommand.COMMAND_WORD + " d/31-Oct-2021" + " del/5"));
        assertThrows(ParseException.class, ()
            -> parser.parseCommand(CountdownCommand.COMMAND_WORD + " n/Halloween Party" + " del/5"));
        assertTrue(parser.parseCommand(CountdownCommand.COMMAND_WORD + " del/1") instanceof CountdownCommand);
        assertThrows(ParseException.class, ()
            -> parser.parseCommand(CountdownCommand.COMMAND_WORD + " del/-1"));
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_EMPTY, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
