package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_FORCE_DELETE_USAGE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MODULE_CODE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ForceDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ForceDeleteCommand code. For example, inputs "cs2103t" and "cs2103t abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ForceDeleteCommandParserTest {

    private ForceDeleteCommandParser parser = new ForceDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsForceDeleteCommand() {
        assertParseSuccess(parser, "cs2103t", new ForceDeleteCommand(CODE_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_MODULE_CODE, "A"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            MESSAGE_FORCE_DELETE_USAGE));
    }
}
