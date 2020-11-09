package seedu.flashcard.logic.parser;

import static seedu.flashcard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashcard.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashcard.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashcard.logic.commands.ViewCommand;

class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgsWithShowAnswerFlag_returnsViewCommand() {
        assertParseSuccess(parser, "1 -a", new ViewCommand(INDEX_FIRST_FLASHCARD, true));
    }

    @Test
    public void parse_validArgsWithoutShowAnswerFlag_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_FLASHCARD, false));
    }

    @Test
    public void parse_validArgsMultipleShowAnswerFlag_returnsViewCommand() {
        assertParseSuccess(parser, "1 -a -a -a", new ViewCommand(INDEX_FIRST_FLASHCARD, true));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

}
