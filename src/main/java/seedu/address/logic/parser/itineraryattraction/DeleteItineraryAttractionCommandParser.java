package seedu.address.logic.parser.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.DeleteItineraryAttractionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteItineraryAttractionCommand object
 */
public class DeleteItineraryAttractionCommandParser implements Parser<DeleteItineraryAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteItineraryAttractionCommand
     * and returns a DeleteItineraryAttractionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItineraryAttractionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DAY_VISITING);

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY_VISITING) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteItineraryAttractionCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryAttractionCommand.MESSAGE_USAGE), pe);
        }

        Index dayVisiting = ParserUtil.parseDayIndex(argMultimap.getValue(PREFIX_DAY_VISITING).get());
        return new DeleteItineraryAttractionCommand(index, dayVisiting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
