package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CalendarCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindLessonCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListLessonCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.LessonContainsKeywordsPredicate;
import seedu.address.model.task.TaskContainsKeywordsPredicate;


public class PlanusParserTest {

    private final PlanusParser parser = new PlanusParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_calendar() throws Exception {
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD) instanceof CalendarCommand);
        assertTrue(parser.parseCommand(CalendarCommand.COMMAND_WORD + " 3") instanceof CalendarCommand);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_MODEL.getOneBased());
        Index[] indexes = {INDEX_FIRST_MODEL};
        assertEquals(new DeleteTaskCommand(indexes), command);
    }

    @Test
    public void parseCommand_deleteLesson() throws Exception {
        DeleteLessonCommand command = (DeleteLessonCommand) parser.parseCommand(
                DeleteLessonCommand.COMMAND_WORD + " " + INDEX_FIRST_MODEL.getOneBased());
        Index[] indexes = {INDEX_FIRST_MODEL};
        assertEquals(new DeleteLessonCommand(indexes), command);
    }

    @Test
    public void parseCommand_done() throws Exception {
        DoneCommand command = (DoneCommand) parser.parseCommand(
                DoneCommand.COMMAND_WORD + " " + INDEX_FIRST_MODEL.getOneBased() + ":20");
        Index[] indexes = {INDEX_FIRST_MODEL};
        int[] durations = {20};
        assertEquals(new DoneCommand(indexes, durations), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("title:foo", "desc:bar");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " " + String.join(" ", keywords));

        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "foo");
        predicate.setKeyword(PREFIX_DESCRIPTION, "bar");
        assertEquals(new FindTaskCommand(predicate), command);
    }

    @Test
    public void parseCommand_findLesson() throws Exception {
        List<String> keywords = Arrays.asList("title:tutorial", "tag:cs2103");
        FindLessonCommand command = (FindLessonCommand) parser.parseCommand(
                FindLessonCommand.COMMAND_WORD + " " + String.join(" ", keywords));

        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "tutorial");
        predicate.setKeyword(PREFIX_TAG, "cs2103");
        assertEquals(new FindLessonCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_listLesson() throws Exception {
        assertTrue(parser.parseCommand(ListLessonCommand.COMMAND_WORD) instanceof ListLessonCommand);
        assertTrue(parser.parseCommand(ListLessonCommand.COMMAND_WORD + " 3") instanceof ListLessonCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, "",
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
