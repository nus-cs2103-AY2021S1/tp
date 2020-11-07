package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE;

import java.util.stream.Stream;

import seedu.address.logic.commands.CalorieMinusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calorie.Calorie;

/**
 * Parses input arguments and creates a new CalorieMinusCommand object
 */
public class CalorieMinusCommandParser implements Parser<CalorieMinusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CalorieMinusCommand
     * and returns an CalorieMinusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalorieMinusCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CALORIE);

        if (!arePrefixesPresent(argMultimap, PREFIX_CALORIE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieMinusCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_CALORIE).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CalorieMinusCommand.MESSAGE_USAGE)
            );
        }

        Calorie calorie = ParserUtil.parseCalorie(argMultimap.getValue(PREFIX_CALORIE).get());

        return new CalorieMinusCommand(calorie);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
