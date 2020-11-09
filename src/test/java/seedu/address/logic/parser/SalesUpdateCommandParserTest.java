package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBBT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BSBM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SalesUpdateCommand;
import seedu.address.model.Drink;

public class SalesUpdateCommandParserTest {
    private SalesUpdateCommandParser parser = new SalesUpdateCommandParser();

    @Test
    public void parse_salesItemSpecified_success() {
        // set up
        int numBsbmSold = 80;
        int numBsbbtSold = 20;
        HashMap<Drink, Integer> sales = new HashMap<>();
        sales.put(Drink.BSBM, numBsbmSold);

        // have one item
        String userInput = " " + PREFIX_BSBM.toString() + numBsbmSold;
        SalesUpdateCommand expectedCommand = new SalesUpdateCommand(sales);
        assertParseSuccess(parser, userInput, expectedCommand);

        // have two item
        sales.put(Drink.BSBBT, numBsbbtSold);
        userInput = " " + PREFIX_BSBM.toString() + numBsbmSold
                + " " + PREFIX_BSBBT.toString() + numBsbbtSold;
        expectedCommand = new SalesUpdateCommand(sales);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingSalesItem_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalesUpdateCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_negativeSalesNumber_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SalesUpdateCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, "BSBM/-100", expectedMessage);
    }

    @Test
    public void parse_exceedRecordLimit_failure() {
        String expectedMessage = SalesUpdateCommand.MESSAGE_MAX_NUM_ALLOWED_EXCEEDED;
        Integer maxExceeded = SalesUpdateCommand.MAX_NUM_ALLOWED + 1;
        // input does not exceed max value of Integer, but exceeds allowed limit
        assertParseFailure(parser, String.format(" BSBM/%d", maxExceeded), expectedMessage);
        // input exceeds max value of Integer
        assertParseFailure(parser, " BSBM/99999999999999", expectedMessage);
    }
}
