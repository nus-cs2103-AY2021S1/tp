package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME;

import java.util.stream.Stream;

import seedu.stock.logic.commands.PrintCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PrintCommand object
 */
public class PrintCommandParser implements Parser<PrintCommand> {

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String INVALID_PRINT_ARGUMENT = "File name is invalid. File name should only contain"
            + " alphanumeric characters and should not be empty.";

    /**
     * Parses the given {@code String} of arguments in the context of the PrintCommand
     * and returns an PrintCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrintCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILE_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_FILE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintCommand.MESSAGE_USAGE));
        }

        // Checks if all the prefixes only appear once in the given command.
        if (!doesPrefixesAppearOnce(argMultimap, PREFIX_FILE_NAME)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD, PrintCommand.MESSAGE_USAGE));
        }

        String fileName = argMultimap.getValue(PREFIX_FILE_NAME).get();

        // Checks if the input is in the valid format
        if (!fileName.matches(VALIDATION_REGEX)) {
            throw new ParseException(String.format(INVALID_PRINT_ARGUMENT, PrintCommand.MESSAGE_USAGE));
        }

        return new PrintCommand(fileName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes appears only once in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean doesPrefixesAppearOnce(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }
}
