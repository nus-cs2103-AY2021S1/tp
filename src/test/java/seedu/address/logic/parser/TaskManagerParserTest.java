package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDeadlineCommand;
import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddTodoCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Event;
import seedu.address.model.task.ToDo;
import seedu.address.testutil.DeadlineBuilder;
import seedu.address.testutil.DeadlineUtil;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.EventUtil;
import seedu.address.testutil.ToDoBuilder;
import seedu.address.testutil.ToDoUtil;

public class TaskManagerParserTest {

    private final TaskManagerParser parser = new TaskManagerParser();

    // TODO: Update tests once commands are fixed.

//    @Test
//    public void parseCommand_addTodo() throws Exception {
//        ToDo todo = new ToDoBuilder().build();
//        AddTodoCommand command = (AddTodoCommand) parser.parseCommand(ToDoUtil.getTodoCommand(todo));
//        assertEquals(new AddTodoCommand(todo), command);
//    }
//
//    @Test
//    public void parseCommand_addDeadline() throws Exception {
//        Deadline deadline = new DeadlineBuilder().build();
//        AddDeadlineCommand command =
//                (AddDeadlineCommand) parser.parseCommand(DeadlineUtil.getDeadlineCommand(deadline));
//        assertEquals(new AddDeadlineCommand(deadline), command);
//    }
//
//    @Test
//    public void parseCommand_addEvent() throws Exception {
//        Event event = new EventBuilder().build();
//        AddEventCommand command = (AddEventCommand) parser.parseCommand(EventUtil.getEventCommand(event));
//        assertEquals(new AddEventCommand(event), command);
//    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " lol") instanceof ExitCommand);
    }

//    @Test
//    public void parseCommand_help() throws Exception {
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
//        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " lol") instanceof  HelpCommand);
//    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " lol") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
