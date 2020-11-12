package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODEL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODEL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteTaskCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteTaskCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteTaskCommandParserTest {

    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTaskCommand() {
        Index[] indexes = {INDEX_FIRST_MODEL};
        assertParseSuccess(parser, "1", new DeleteTaskCommand(indexes));
    }

    @Test
    public void parse_manyValidArgs_returnsDeleteTaskCommand() {
        Index[] indexes = {INDEX_FIRST_MODEL, INDEX_SECOND_MODEL};
        assertParseSuccess(parser, "1 2", new DeleteTaskCommand(indexes));
    }

    @Test
    public void parse_invalidIndexAmongManyValidArgs_returnsDeleteTaskCommand() {
        assertParseFailure(parser, "1 -1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ParserUtil.MESSAGE_INVALID_INDEX, DeleteTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ParserUtil.MESSAGE_INVALID_INDEX, DeleteTaskCommand.MESSAGE_USAGE));
    }
}
