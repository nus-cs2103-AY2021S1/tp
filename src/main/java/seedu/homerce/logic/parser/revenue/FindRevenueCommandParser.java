package seedu.homerce.logic.parser.revenue;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.homerce.logic.commands.revenue.FindRevenueCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.revenue.predicate.RevenueDatePredicate;
import seedu.homerce.model.revenue.predicate.RevenueServiceCodePredicate;

/**
 * Parses input arguments and creates a new FindRevenueCommand object
 */
public class FindRevenueCommandParser implements Parser<FindRevenueCommand> {

    public static final String MULTIPLE_PARAMETERS = "Please only input one parameter.";
    public static final int NUM_ALLOWED_PARAMETERS = 1;

    @Override
    public FindRevenueCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRevenueCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_DATE, PREFIX_SERVICE_SERVICE_CODE);
        if (areMultipleParametersPresent(argMultimap, PREFIX_DATE, PREFIX_SERVICE_SERVICE_CODE)) {
            throw new ParseException(MULTIPLE_PARAMETERS);
        }
        if ((!arePrefixesPresent(argMultimap, PREFIX_DATE)
            && !arePrefixesPresent(argMultimap, PREFIX_SERVICE_SERVICE_CODE))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindRevenueCommand.MESSAGE_USAGE));
        }

        Predicate<Revenue> predicate = null;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            predicate = new RevenueDatePredicate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        } else if (argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).isPresent()) {
            predicate = new RevenueServiceCodePredicate(ParserUtil
                .parseServiceCode(argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).get()));
        }

        return new FindRevenueCommand(predicate);
    }

    /**
     * Returns true if there is more than one input parameter.
     */
    private static boolean areMultipleParametersPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count()
            > NUM_ALLOWED_PARAMETERS;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
