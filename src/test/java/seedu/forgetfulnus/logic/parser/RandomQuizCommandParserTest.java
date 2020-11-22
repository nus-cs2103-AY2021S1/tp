package seedu.forgetfulnus.logic.parser;

import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.forgetfulnus.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.forgetfulnus.testutil.TypicalIndexes.INDEX_FIVE_FLASHCARDS;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.logic.commands.RandomQuizCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RandomQuizCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RandomQuizCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RandomQuizCommandParserTest {

    private RandomQuizCommandParser parser = new RandomQuizCommandParser();

    @Test
    public void parse_validArgs_returnsRandomQuizCommand() {
        assertParseSuccess(parser, "5", new RandomQuizCommand(INDEX_FIVE_FLASHCARDS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RandomQuizCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseZeroException() {
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX,
                RandomQuizCommand.MESSAGE_USAGE));
    }
}
