package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.stock.logic.commands.SourceStatisticsCommand;
import seedu.stock.logic.commands.StatisticsCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class StatisticsCommandParser implements Parser<StatisticsCommand> {

    @Override
    public StatisticsCommand parse(String userInput) throws ParseException {
        String[] trimmedStatisticsType = userInput.trim().split("st/");

        //a valid input will lead to an array of index 0 as "", index 1 as the statistics type
        if (trimmedStatisticsType.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }

        String type = trimmedStatisticsType[1].toLowerCase();

        switch (type) {

        case SourceStatisticsCommand.STATISTICS_TYPE:
            return new SourceStatisticsCommand();

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }
    }
}
