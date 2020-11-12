package seedu.address.logic.parser;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.MultipleAttributesException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException, MultipleAttributesException;

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if the {@code ArgumentMultimap} contains multiple inputs of the same attributes.
     * @return true if it contains multiple attributes.
     */
    static boolean argMultimapHasRepeatedAttributes(ArgumentMultimap argMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argMultimap.hasMultipleValues(prefix)) {
                return true;
            }
        }
        return false;
    }
}
