package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteJobCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteJobCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteJobCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteJobCommandParserTest {

    private DeleteJobCommandParser parser = new DeleteJobCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteJobCommand(INDEX_FIRST_JOB));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteJobCommand.MESSAGE_USAGE));
    }
}
