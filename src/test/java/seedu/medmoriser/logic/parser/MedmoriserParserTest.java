package seedu.medmoriser.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medmoriser.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medmoriser.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.medmoriser.testutil.Assert.assertThrows;
import static seedu.medmoriser.testutil.TypicalIndexes.INDEX_FIRST_QANDA;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.medmoriser.logic.commands.AddCommand;
import seedu.medmoriser.logic.commands.ClearCommand;
import seedu.medmoriser.logic.commands.DeleteCommand;
import seedu.medmoriser.logic.commands.EditCommand;
import seedu.medmoriser.logic.commands.ExitCommand;
import seedu.medmoriser.logic.commands.FindCommand;
import seedu.medmoriser.logic.commands.HelpCommand;
import seedu.medmoriser.logic.commands.ListCommand;
import seedu.medmoriser.logic.parser.exceptions.ParseException;
import seedu.medmoriser.model.qanda.QAndA;
import seedu.medmoriser.model.qanda.QAndAContainsKeywordsPredicate;
//import seedu.medmoriser.model.qanda.QuestionContainsKeywordsPredicate;
import seedu.medmoriser.testutil.EditQAndADescriptorBuilder;
import seedu.medmoriser.testutil.QAndABuilder;
import seedu.medmoriser.testutil.QAndAUtil;

public class MedmoriserParserTest {

    private final MedmoriserParser parser = new MedmoriserParser();

    @Test
    public void parseCommand_add() throws Exception {
        QAndA qAndA = new QAndABuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(QAndAUtil.getAddCommand(qAndA));
        assertEquals(new AddCommand(qAndA), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_QANDA.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_QANDA), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        QAndA qAndA = new QAndABuilder().build();
        EditCommand.EditQAndADescriptor descriptor = new EditQAndADescriptorBuilder(qAndA).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_QANDA.getOneBased() + " "
                + QAndAUtil.getEditQAndADescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_QANDA, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(", ")));
        assertEquals(new FindCommand(new QAndAContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " questions") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
            parser.parseCommand("unknownCommand"));
    }
}
