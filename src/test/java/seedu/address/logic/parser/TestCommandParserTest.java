package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ANSWER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ANSWER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPTION_NON_ALPHANUMERIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.OPTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPTION_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MCQ_FLASHCARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OPENENDED_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.flashcard.Answer;
import seedu.address.flashcard.Option;
import seedu.address.logic.commands.TestCommand;

class TestCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TestCommand.MESSAGE_USAGE);

    private TestCommandParser parser = new TestCommandParser();

    @Test
    public void parse_noInput_failure() {
        // no index specified
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);

        // no index and no option/answer specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no answer specified
        assertParseFailure(parser, "1", TestCommand.MESSAGE_NO_OPTION_OR_ANSWER_PROVIDED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ANSWER_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ANSWER_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty answer
        assertParseFailure(parser, "1" + INVALID_ANSWER_DESC, Answer.MESSAGE_CONSTRAINTS);

        // empty option
        assertParseFailure(parser, "1" + INVALID_OPTION_DESC, Option.MESSAGE_CONSTRAINTS);
        // non-alphanumeric option
        assertParseFailure(parser, "1" + INVALID_OPTION_NON_ALPHANUMERIC_DESC, Option.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_answerField_success() {
        Index targetIndex = INDEX_FIRST_OPENENDED_FLASHCARD;
        String userInput = targetIndex.getOneBased() + ANSWER_DESC_AMY;
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setAnswer(new Answer(VALID_ANSWER_AMY));
        TestCommand expectedCommand = new TestCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_optionField_sucesss() {
        Index targetIndex = INDEX_FIRST_MCQ_FLASHCARD;
        String userInput = targetIndex.getOneBased() + OPTION_DESC_AMY;
        TestCommand.TestAnswerDescriptor descriptor = new TestCommand.TestAnswerDescriptor();
        descriptor.setOption(new Option(VALID_OPTION_AMY));
        TestCommand expectedCommand = new TestCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}