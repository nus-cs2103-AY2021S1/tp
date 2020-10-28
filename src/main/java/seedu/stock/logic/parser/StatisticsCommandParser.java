package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_STATISTICS_TYPE;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;
import seedu.stock.logic.commands.SourceStatisticsCommand;
import seedu.stock.logic.commands.StatisticsCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class StatisticsCommandParser implements Parser<StatisticsCommand> {

    private static final Logger logger = LogsCenter.getLogger(StatisticsCommandParser.class);

    @Override
    public StatisticsCommand parse(String userInput) throws ParseException {
        //invalid input if it does not start with "st/" as it is a confirmed invalid header.
        if (!userInput.trim().startsWith("st/")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STATISTICS_TYPE);

        List<String> values = argMultimap.getAllValues(PREFIX_STATISTICS_TYPE);

        //correct input only has one value
        if (values.size() != 1) {
            logger.log(Level.WARNING, "Invalid format");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }

        String input = values.get(0).toLowerCase();
        String type = getStatisticsType(input);

        switch (type) {

        case SourceStatisticsCommand.STATISTICS_TYPE:
            return new SourceStatisticsCommand();

        case SourceQuantityDistributionStatisticsCommand.STATISTICS_TYPE:
            return new SourceQuantityDistributionStatisticsCommand(getSourceCompany(input));

        default:
            logger.log(Level.WARNING, "Statistics type not found!");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }

    }

    /**
     * To obtain the type of statistics command.
     *
     * @param input The user input.
     * @return The statistics type.
     * @throws ParseException if no such type of statistics command is found.
     */
    public String getStatisticsType(String input) throws ParseException {
        if (input.length() >= 10 && input.startsWith("source-qd-")) {
            return "source-qd-";
        } else if (input.equals("source")) {
            return "source";
        } else {
            logger.log(Level.WARNING, "Statistics type not found!");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }
    }

    //for extracting target company from source-qd types
    public String getSourceCompany(String input) throws ParseException {
        String targetSourceCompany = input.substring(10);

        //exception thrown here if the target source company is missing
        if (targetSourceCompany.length() == 0) {
            logger.log(Level.WARNING, "Source company not found!");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatisticsCommand.MESSAGE_USAGE));
        }
        return targetSourceCompany;
    }
}
