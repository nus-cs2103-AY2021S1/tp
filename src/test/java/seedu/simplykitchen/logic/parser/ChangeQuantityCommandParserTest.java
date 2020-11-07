package seedu.simplykitchen.logic.parser;

import static seedu.simplykitchen.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.AMOUNT_DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.AMOUNT_DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.INVALID_AMOUNT_SIZE_DESC;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_AMOUNT_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_AMOUNT_BREAD;
import static seedu.simplykitchen.logic.parser.ArgumentTokenizer.MULTIPLE_SAME_PREFIX;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.simplykitchen.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.simplykitchen.logic.parser.ParserUtil.MESSAGE_INVALID_AMOUNT;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_FIRST_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_SECOND_FOOD;
import static seedu.simplykitchen.testutil.TypicalIndexes.INDEX_THIRD_FOOD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;

public class ChangeQuantityCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangeQuantityCommand.MESSAGE_USAGE);

    private ChangeQuantityCommandParser parser = new ChangeQuantityCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_AMOUNT_APPLE_PIE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AMOUNT_DESC_APPLE_PIE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + AMOUNT_DESC_APPLE_PIE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", "Invalid prefix \"i/\" detected. "
                + "Please remove it and re-enter the command.");
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, MESSAGE_INVALID_AMOUNT); // invalid amount value
        assertParseFailure(parser, "1" + INVALID_AMOUNT_SIZE_DESC,
                MESSAGE_INVALID_AMOUNT); // invalid amount size
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_FOOD;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BREAD;
        ChangeQuantityCommand expectedCommand =
                new ChangeQuantityCommand(targetIndex, Double.parseDouble(VALID_AMOUNT_BREAD));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        Index targetIndex = INDEX_SECOND_FOOD;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_APPLE_PIE + AMOUNT_DESC_BREAD;
        assertParseFailure(parser, userInput, String.format(MULTIPLE_SAME_PREFIX, "a/"));
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_failure() {
        Index targetIndex = INDEX_THIRD_FOOD;
        String userInput = targetIndex.getOneBased() + INVALID_AMOUNT_DESC + AMOUNT_DESC_BREAD;
        assertParseFailure(parser, userInput, String.format(MULTIPLE_SAME_PREFIX, "a/"));
    }
}
