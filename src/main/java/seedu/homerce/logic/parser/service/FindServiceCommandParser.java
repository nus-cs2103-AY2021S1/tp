package seedu.homerce.logic.parser.service;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_TITLE;

import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.homerce.logic.commands.service.FindServiceCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.service.predicate.ServiceCodePredicate;
import seedu.homerce.model.service.predicate.ServiceTitlePredicate;

/**
 * Parses input arguments and creates a new FindServiceCommand object
 */
public class FindServiceCommandParser implements Parser<FindServiceCommand> {

    public static final String MULTIPLE_PARAMETERS = "Please only input one parameter.";
    public static final int NUM_ALLOWED_PARAMETERS = 1;

    /**
     * Parses the given {@code String} of arguments in the context of the FindServiceCommand
     * and returns a FindServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindServiceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_SERVICE_CODE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_SERVICE_CODE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindServiceCommand.MESSAGE_USAGE));
        }

        if (areMultipleParametersPresent(argMultimap, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_SERVICE_CODE)) {
            throw new ParseException(MULTIPLE_PARAMETERS);
        }

        Predicate<Service> predicate = null;
        if (argMultimap.getValue(PREFIX_SERVICE_TITLE).isPresent()) {
            predicate = new ServiceTitlePredicate(ParserUtil.parseTitle(
                argMultimap.getValue(PREFIX_SERVICE_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).isPresent()) {
            ServiceCode sc = new ServiceCode(argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE)
                .get().trim().toUpperCase());
            predicate = new ServiceCodePredicate(sc);
        }

        return new FindServiceCommand(predicate);
    }

    /**
     * * Returns true if none of the prefixes contains empty {@code Optional} values
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if there is more than one input parameters.
     */
    private static boolean areMultipleParametersPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count()
            > NUM_ALLOWED_PARAMETERS;
    }
}
