package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SalesFindCommand;
import seedu.address.model.InputContainsKeywordsPredicate;

public class SalesFindCommandParserTest {
    private SalesFindCommandParser parser = new SalesFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SalesFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSalesFindCommand() {
        // no leading and trailing whitespaces
        SalesFindCommand expectedSalesFindCommand =
                new SalesFindCommand(new InputContainsKeywordsPredicate(Arrays.asList("BSBM")));
    }
}
