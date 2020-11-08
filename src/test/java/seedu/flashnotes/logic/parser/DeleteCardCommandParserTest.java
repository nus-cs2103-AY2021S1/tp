package seedu.flashnotes.logic.parser;

import static seedu.flashnotes.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashnotes.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashnotes.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.flashnotes.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.flashnotes.logic.commands.DeleteCardCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCardCommandParserTest {

    private DeleteCardCommandParser parser = new DeleteCardCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCardCommand(INDEX_FIRST_FLASHCARD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT + "\n"
                        + MESSAGE_INVALID_INDEX,
                DeleteCardCommand.MESSAGE_USAGE));
    }
}
