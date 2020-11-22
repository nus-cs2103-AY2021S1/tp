package seedu.forgetfulnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.commands.ClearCommand;
import seedu.forgetfulnus.logic.commands.DeleteCommand;
import seedu.forgetfulnus.logic.commands.EditCommand;
import seedu.forgetfulnus.logic.commands.EndQuizCommand;
import seedu.forgetfulnus.logic.commands.ExitCommand;
import seedu.forgetfulnus.logic.commands.FindCommand;
import seedu.forgetfulnus.logic.commands.HelpCommand;
import seedu.forgetfulnus.logic.commands.ListCommand;
import seedu.forgetfulnus.logic.commands.NextCommand;
import seedu.forgetfulnus.logic.commands.ScoreCommand;
import seedu.forgetfulnus.logic.parser.exceptions.ParseException;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;
import seedu.forgetfulnus.testutil.EditFlashCardDescriptorBuilder;
import seedu.forgetfulnus.testutil.FlashCardBuilder;
import seedu.forgetfulnus.testutil.FlashCardUtil;

public class GlossaryParserTest {

    private final GlossaryParser parser = new GlossaryParser();

    @Test
    public void parseCommand_add() throws Exception {
        FlashCard flashCard = new FlashCardBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(FlashCardUtil.getAddCommand(flashCard));
        assertEquals(new AddCommand(flashCard), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_FLASHCARD.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_FLASHCARD), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        FlashCard flashCard = new FlashCardBuilder().build();
        EditCommand.EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder(flashCard).build();

        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_FLASHCARD.getOneBased() + " "
                + FlashCardUtil.getEditFlashCardDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_FLASHCARD, descriptor), command);
    }

    @Test
    public void parseCommand_end() throws Exception {
        assertTrue(parser.parseCommand(EndQuizCommand.COMMAND_WORD) instanceof EndQuizCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new GermanPhraseContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_next() throws Exception {
        assertTrue(parser.parseCommand(NextCommand.COMMAND_WORD) instanceof NextCommand);
    }

    @Test
    public void parseCommand_scores() throws Exception {
        assertTrue(parser.parseCommand(ScoreCommand.COMMAND_WORD) instanceof ScoreCommand);
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

    @Test
    public void parseCommand_clearWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("clear abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("clear 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("clear abc123"));
    }

    @Test
    public void parseCommand_endWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("end abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("end 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("end abc123"));
    }

    @Test
    public void parseCommand_exitWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("exit abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("exit 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("exit abc123"));
    }

    @Test
    public void parseCommand_helpWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("help abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("help 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("help abc123"));
    }

    @Test
    public void parseCommand_listWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("list abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("list 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("list abc123"));
    }

    @Test
    public void parseCommand_nextWithArguments_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("next abc"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("next 123"));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("next abc123"));
    }
}
