package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.ReduceCommand;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.tag.Tag;

class ReduceCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReduceCommand.MESSAGE_USAGE);

    private final ReduceCommandParser parser = new ReduceCommandParser();

    @Test
    void parse_validValue_success() {
        String amount = "1";
        String userInput = " " + PREFIX_AMOUNT + amount;

        ReduceCommand expectedCommand = new ReduceCommand(new Amount(amount));
        assertParseSuccess(parser, userInput, expectedCommand);

        String userInput2 = " " + PREFIX_AMOUNT + amount + TAG_DESC_FOOD;

        ReduceCommand expectedCommand2 = new ReduceCommand(new Amount(amount), new Tag(VALID_TAG_FOOD));
        assertParseSuccess(parser, userInput2, expectedCommand2);
    }

    @Test
    void parse_invalidValue_failure() {
        assertParseFailure(parser, " " + PREFIX_AMOUNT + "one", MESSAGE_INVALID_FORMAT);
    }

    @Test
    void parse_missingAmountValue_failure() {
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }
}
