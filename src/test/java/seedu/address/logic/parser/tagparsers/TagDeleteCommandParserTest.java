package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tagcommands.TagDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the TagDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the TagDeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class TagDeleteCommandParserTest {
    private TagDeleteCommandParser parser = new TagDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsTagDeleteCommand() {
        assertParseSuccess(parser, "1", new TagDeleteCommand(INDEX_FIRST_TAG));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagDeleteCommand.MESSAGE_USAGE));
    }
}
