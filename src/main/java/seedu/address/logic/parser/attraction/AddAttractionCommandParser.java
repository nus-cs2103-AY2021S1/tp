package seedu.address.logic.parser.attraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISITED;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.attraction.AddAttractionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddAttractionCommand object
 */
public class AddAttractionCommandParser implements Parser<AddAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAttractionCommand
     * and returns an AddAttractionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAttractionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_DESCRIPTION, PREFIX_LOCATION, PREFIX_OPENING_HOURS,
                        PREFIX_PRICE_RANGE, PREFIX_RATING, PREFIX_VISITED, PREFIX_TAG);

        // Only check for name and location of the attraction, the rest are optional.
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttractionCommand.MESSAGE_USAGE));
        }

        // Name is not optional
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        // Phone is optional
        Phone phone;
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        } else {
            phone = new Phone();
        }

        // Email is optional
        Email email;
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        } else {
            email = new Email();
        }

        // Address is optional
        Address address;
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        } else {
            address = new Address();
        }

        // Description is optional
        Description description;
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } else {
            description = new Description();
        }

        // Location is not optional
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get());

        // Opening Hours is optional
        OpeningHours openingHours;
        if (argMultimap.getValue(PREFIX_OPENING_HOURS).isPresent()) {
            openingHours = ParserUtil.parseOpeningHours(argMultimap.getValue(PREFIX_OPENING_HOURS).get());
        } else {
            openingHours = new OpeningHours();
        }

        // Price Range is optional
        PriceRange priceRange;
        if (argMultimap.getValue(PREFIX_PRICE_RANGE).isPresent()) {
            priceRange = ParserUtil.parsePriceRange(argMultimap.getValue(PREFIX_PRICE_RANGE).get());
        } else {
            priceRange = new PriceRange();
        }

        // Rating is optional
        Rating rating;
        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        } else {
            rating = new Rating();
        }

        // Visited is optional
        Visited visited;
        if (argMultimap.getValue(PREFIX_VISITED).isPresent()) {
            visited = ParserUtil.parseVisited(argMultimap.getValue(PREFIX_VISITED).get());
        } else {
            visited = new Visited();
        }

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Attraction attraction = new Attraction(name, phone, email, address, description,
                location, openingHours, priceRange, rating, visited, tagList);

        return new AddAttractionCommand(attraction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
