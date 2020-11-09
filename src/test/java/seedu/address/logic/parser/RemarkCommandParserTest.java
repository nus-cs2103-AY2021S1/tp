package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.person.Remark;

class RemarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private RemarkCommandParser parser = new RemarkCommandParser();


    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "likes fries", MESSAGE_INVALID_FORMAT);

        // no prefix specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no remark specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + REMARK_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicatePrefix_failure() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + REMARK_DESC_BOB + REMARK_DESC_AMY;

        assertParseFailure(parser, userInput, String.format(MESSAGE_DUPLICATE_PREFIX, PREFIX_REMARK));
    }

    @Test
    public void parse_setRemark_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + REMARK_DESC_BOB;
        Remark remark = new Remark(VALID_REMARK_BOB);
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, remark);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_removeRemark_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " r/";

        Remark remark = new Remark("");
        RemarkCommand expectedCommand = new RemarkCommand(targetIndex, remark);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
