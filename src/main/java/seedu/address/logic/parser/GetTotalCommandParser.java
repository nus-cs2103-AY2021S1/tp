package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import java.util.stream.Stream;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.GetTotalCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new GetTotalCommand object.
 */
public class GetTotalCommandParser implements Parser<GetTotalCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GetTotalCommand
     * and returns a GetTotalCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public GetTotalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CATEGORY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetTotalCommand.MESSAGE_USAGE));
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        return new GetTotalCommand(category);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
