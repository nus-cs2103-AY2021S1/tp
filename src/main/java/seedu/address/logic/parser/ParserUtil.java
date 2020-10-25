package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Venue;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.price.Price;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_TIME = "Time is not in valid format.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @throws ParseException if there is an error when parsing.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String venue} into a {@code venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseMeetingVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        return new Venue(trimmedVenue);
    }

    /**
     * Parses a {@code String time} into a {@code time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Time parseMeetingTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        return new Time(trimmedTime);
    }

    /**
     * Parses a {@code String type} into a {@code type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static String parseMeetingType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        return trimmedType;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Time parseTime(String time) throws ParseException {
        String trimmedTime = time.trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = formatter.parse(trimmedTime);
            String dateString = formatter.format(date);
            return new Time(dateString);
        } catch (java.text.ParseException e) {
            throw new ParseException(MESSAGE_INVALID_TIME);
        }
    }

    /**
     * Parses a {@code String askingPrice} into an {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code askingPrice} is invalid.
     */
    public static Price parsePrice(String askingPrice) throws ParseException {
        requireNonNull(askingPrice);
        String trimmedAskingPrice = askingPrice.trim();
        try {
            double doublePrice = Double.parseDouble(trimmedAskingPrice);
            if (!Price.isValidPrice(doublePrice)) {
                throw new ParseException(Price.MESSAGE_CONSTRAINTS);
            }
            return new Price(doublePrice);
        } catch (NumberFormatException e) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String order} into a boolean option.
     * True is returned when order is ascending.
     * False is returned when order is descending or null
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Boolean parseOrder(String order) throws ParseException {
        if (order == null || order.trim().equalsIgnoreCase("dsc")) {
            return false;
        } else if (order.trim().equalsIgnoreCase("asc")) {
            return true;
        } else {
            throw new ParseException("Order can only be 'asc' or 'dsc'");
        }
    }
}
