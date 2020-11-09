package seedu.address.logic.parser.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.AddItineraryAttractionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.itinerary.ItineraryTime;


public class AddItineraryAttractionCommandParser implements Parser<AddItineraryAttractionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddiCommand
     * and returns an AddiCommand object for execution.
     * The command is: add itinerary attraction att/2 st/1300 et/2100 day/2
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItineraryAttractionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_TIME, PREFIX_END_TIME,
                PREFIX_DAY_VISITING);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY_VISITING)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddItineraryAttractionCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddItineraryAttractionCommand.MESSAGE_USAGE), pe);
        }

        ItineraryTime startTime = ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_START_TIME).get());
        ItineraryTime endTime = ParserUtil.parseItineraryTime(argMultimap.getValue(PREFIX_END_TIME).get());
        if (!startTime.isEarlierThan(endTime)) {
            throw new ParseException(MESSAGE_INVALID_START_TIME);
        }

        Index dayVisiting = ParserUtil.parseDayIndex(argMultimap.getValue(PREFIX_DAY_VISITING).get());
        return new AddItineraryAttractionCommand(index, startTime, endTime, dayVisiting);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
