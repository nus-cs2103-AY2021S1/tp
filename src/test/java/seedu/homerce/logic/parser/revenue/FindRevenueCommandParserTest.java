package seedu.homerce.logic.parser.revenue;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.homerce.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.revenue.FindRevenueCommand;
import seedu.homerce.model.revenue.predicate.RevenueDatePredicate;
import seedu.homerce.model.revenue.predicate.RevenueServiceCodePredicate;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

public class FindRevenueCommandParserTest {

    private FindRevenueCommandParser parser = new FindRevenueCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindRevenueCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validDateArgs_returnsFindRevenueCommand() {
        // no leading and trailing whitespaces
        FindRevenueCommand expectedFindRevenueCommand =
            new FindRevenueCommand(new RevenueDatePredicate(new Date("25-10-2020")));

        assertParseSuccess(parser, " dt/25-10-2020", expectedFindRevenueCommand);
    }

    @Test
    public void parse_validServiceCodeArgs_returnsFindRevenueCommand() {
        // no leading and trailing whitespaces
        FindRevenueCommand expectedFindRevenueCommand =
            new FindRevenueCommand(new RevenueServiceCodePredicate(new ServiceCode("SC999")));

        assertParseSuccess(parser, " s/SC999", expectedFindRevenueCommand);
    }

}
