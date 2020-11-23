package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DAILY_DELETE;

import java.util.stream.Stream;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.DailyCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DailyCommand object.
 */
public class DailyCommandParser implements Parser<DailyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DailyCommand
     * and returns an DailyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DailyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_DAILY_DELETE);

        // user wants to go to Daily tab
        if (!arePrefixesPresent(argMultimap, PREFIX_DAILY_DELETE)) {
            String string = argMultimap.getPreamble();
            if (!string.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DailyCommand.MESSAGE_SWITCH_TAB_USAGE));
            }
            return new DailyCommand();
        }

        // user wants to delete an existing todo
        if (arePrefixesPresent(argMultimap, PREFIX_DAILY_DELETE)
                && argMultimap.getPreamble().isEmpty()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DAILY_DELETE).get());
            return new DailyCommand(index);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DailyCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
