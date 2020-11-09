package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_BIN_ITEM;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.RestoreCommand;

public class RestoreCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, RestoreCommand.HELP.getFullMessage());

    private RestoreCommandParser parser = new RestoreCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 j/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BIN_ITEM;
        String userInput = targetIndex.getOneBased() + "";

        RestoreCommand expectedCommand = new RestoreCommand(targetIndex);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
