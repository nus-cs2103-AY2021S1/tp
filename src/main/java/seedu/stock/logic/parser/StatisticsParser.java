package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.stock.logic.commands.SourceStatisticsCommand;
import seedu.stock.logic.commands.StatisticsCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class StatisticsParser implements Parser<StatisticsCommand> {

    @Override
    public StatisticsCommand parse(String userInput) throws ParseException {
        String[] trimmedStatisticsType = userInput.trim().split(" ");

        if (trimmedStatisticsType.length != 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }

        String type = trimmedStatisticsType[0].toLowerCase();

        switch (type) {

        case SourceStatisticsCommand.STATISTICS_TYPE:
            return new SourceStatisticsCommand();

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }
    }
}
