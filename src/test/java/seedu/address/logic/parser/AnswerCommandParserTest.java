package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TF_ANSWER_DESC_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.TF_ANSWER_DESC_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TF_ANSWER_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TF_ANSWER_TRUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AnswerCommand;

class AnswerCommandParserTest {

    private AnswerCommandParser parser = new AnswerCommandParser();

    @Test
    public void parse_validArgs_returnsAnswerCommand() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + "1" + TF_ANSWER_DESC_TRUE,
                new AnswerCommand(INDEX_FIRST_FLASHCARD, VALID_TF_ANSWER_TRUE));
        // multiple answers - last answer accepted
        assertParseSuccess(parser, "1" + TF_ANSWER_DESC_TRUE + TF_ANSWER_DESC_FALSE,
                new AnswerCommand(INDEX_FIRST_FLASHCARD, VALID_TF_ANSWER_FALSE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnswerCommand.MESSAGE_USAGE));
        // valid index, answer without prefix
        assertParseFailure(parser, "1" + VALID_TF_ANSWER_FALSE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnswerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing index, valid answer present
        assertParseFailure(parser, TF_ANSWER_DESC_TRUE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnswerCommand.MESSAGE_USAGE));
        // valid index present, missing answer
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AnswerCommand.MESSAGE_USAGE));
    }
}
