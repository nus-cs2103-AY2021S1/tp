package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_STATS;

import java.util.stream.Stream;

import seedu.address.logic.commands.StatsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class StatsCommandParser implements Parser<StatsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VIEW_STATS);

        if (!arePrefixesPresent(argMultimap, PREFIX_VIEW_STATS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        }

        boolean showCalorie;
        boolean showWeight;
        boolean showCommander;

        if (argMultimap.getValue(PREFIX_VIEW_STATS).get().equals("calorie")) {
            showCalorie = true;
            showWeight = false;
            showCommander = false;
        } else if (argMultimap.getValue(PREFIX_VIEW_STATS).get().equals("weight")) {
            showCalorie = false;
            showWeight = true;
            showCommander = false;
        } else if (argMultimap.getValue(PREFIX_VIEW_STATS).get().equals("all")) {
            showCalorie = true;
            showWeight = true;
            showCommander = false;
        } else if (argMultimap.getValue(PREFIX_VIEW_STATS).get().equals("commander")) {
            showCalorie = false;
            showWeight = false;
            showCommander = true;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE));
        }
        return new StatsCommand(showCalorie, showWeight, showCommander);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
