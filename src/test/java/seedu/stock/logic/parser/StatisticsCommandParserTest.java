package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;
import seedu.stock.logic.commands.SourceStatisticsCommand;
import seedu.stock.logic.commands.StatisticsCommand;


public class StatisticsCommandParserTest {

    private StatisticsCommandParser parser = new StatisticsCommandParser();

    @Test
    public void parse_validArg_returnsSourceStatisticsCommand() {
        String input = " st/source";

        SourceStatisticsCommand expectedSourceStatisticsCommandCommand = new SourceStatisticsCommand();

        assertParseSuccess(parser, input, expectedSourceStatisticsCommandCommand);
    }

    @Test
    public void parse_validArg_returnsSourceQuantityDistributionStatisticsCommand() {
        String input = " st/source-qd-ntuc";
        String targetString = "ntuc";
        SourceQuantityDistributionStatisticsCommand expectedSourceQuantityDistributionStatisticsCommand =
                new SourceQuantityDistributionStatisticsCommand(targetString);

        assertParseSuccess(parser, input, expectedSourceQuantityDistributionStatisticsCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " sss/wrongarg",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
    }
}
