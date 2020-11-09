package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.itinerary.AddItineraryCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryDate;

/**
 * Parses input arguments and creates a new AddItineraryCommand object
 */
public class AddItineraryCommandParser implements Parser<AddItineraryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddItineraryCommand
     * and returns an AddItineraryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItineraryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_START_DATE,
                        PREFIX_END_DATE, PREFIX_BUDGET);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryCommand.MESSAGE_USAGE));
        }

        // Name is not optional
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        // Description is optional
        Description description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = new Description();
        }

        // Start date is not optional
        ItineraryDate startDate = ParserUtil.parseItineraryDate(argMultimap.getValue(PREFIX_START_DATE).get());

        // End date is not optional
        ItineraryDate endDate = ParserUtil.parseItineraryDate(argMultimap.getValue(PREFIX_END_DATE).get());

        // Budget is optional
        Budget budget;
        if (argMultimap.getValue(PREFIX_BUDGET).isPresent()) {
            budget = ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get());
        } else {
            budget = new Budget();
        }

        if (startDate.isAfter(endDate)) {
            throw new ParseException(AddItineraryCommand.MESSAGE_START_BEFORE_END_DATE);
        }

        Itinerary itinerary = new Itinerary(name, description, startDate, endDate, budget, new ArrayList<Day>());

        return new AddItineraryCommand(itinerary);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
