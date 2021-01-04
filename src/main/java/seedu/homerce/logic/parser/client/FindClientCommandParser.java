package seedu.homerce.logic.parser.client;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.commons.core.Messages.MESSAGE_MULTIPLE_PARAMETERS;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.homerce.logic.commands.client.FindClientCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.predicate.ClientNamePredicate;
import seedu.homerce.model.client.predicate.ClientPhonePredicate;

/**
 * Parses input arguments and creates a new FindClientCommand object
 */
public class FindClientCommandParser implements Parser<FindClientCommand> {

    public static final int NUM_ALLOWED_PARAMETERS = 1;
    /**
     * Parses the given {@code String} of arguments in the context of the FindClientCommand
     * and returns a FindClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);
        checkIfValidPrefix(args);
        Predicate<Client> predicate = createPredicate(argMultimap);
        return new FindClientCommand(predicate);
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

    private boolean checkIfValidPrefix(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindClientCommand.MESSAGE_USAGE));
        }

        if (areMultipleParametersPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)) {
            throw new ParseException(MESSAGE_MULTIPLE_PARAMETERS);
        }
        return true;
    }

    private Predicate<Client> createPredicate(ArgumentMultimap argMultimap) throws ParseException {
        Predicate<Client> predicate = null;
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (!argMultimap.getValue(PREFIX_NAME).get().trim().isEmpty()) { // Check for empty user input
                String [] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
                predicate = new ClientNamePredicate(Arrays.asList(nameKeywords));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindClientCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate = new ClientPhonePredicate(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        return predicate;
    }
}
