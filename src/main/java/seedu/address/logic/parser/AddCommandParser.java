package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public abstract class AddCommandParser<T extends AddCommand> implements Parser<T> {


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
