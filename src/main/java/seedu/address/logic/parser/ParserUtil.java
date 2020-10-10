package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calendar.CalendarBidderId;
import seedu.address.model.calendar.CalendarPropertyId;
import seedu.address.model.calendar.CalendarTime;
import seedu.address.model.calendar.CalendarVenue;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * trims off any excess white spaces for a given string
     * @param propertyId string to trim
     * @return a string that has no white spaces on the sides
     * @throws ParseException
     */
    public static String parsePropertyId(String propertyId) throws ParseException {
        requireNonNull(propertyId);
        String trimmedPropertyId = propertyId.trim();
        return trimmedPropertyId;
    }

    /**
     * trims off any excess white spaces for a given string
     * @param bidderId string to trim
     * @return a string that has no white spaces on the sides
     * @throws ParseException
     */
    public static String parseBidderId(String bidderId) throws ParseException {
        requireNonNull(bidderId);
        String trimmedBidderId = bidderId.trim();
        return trimmedBidderId;
    }

    /**
     *  trims off any excess white spaces for a given string
     * @param bidAmount string to trim
     * @return a string that has no white spaces on the sides
     * @throws ParseException
     */
    public static double parseBidAmount(String bidAmount) throws ParseException {
        requireNonNull(bidAmount);
        String trimmedBidAmount = bidAmount.trim();
        double numericalBidAmount = Double.parseDouble(trimmedBidAmount);
        return numericalBidAmount;
    }

    /**
     * Parses a {@code String venue} into a {@code venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static CalendarVenue parseCalendarVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!Name.isValidName(trimmedVenue)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new CalendarVenue(trimmedVenue);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static CalendarTime parseCalendarTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Phone.isValidPhone(trimmedTime)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new CalendarTime(trimmedTime);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static CalendarPropertyId parseCalendarPropertyId(String propertyId) throws ParseException {
        requireNonNull(propertyId);
        String trimmedpropertyId = propertyId.trim();
        if (!Phone.isValidPhone(trimmedpropertyId)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new CalendarPropertyId(trimmedpropertyId);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static CalendarBidderId parseCalendarBidderId(String bidderId) throws ParseException {
        requireNonNull(bidderId);
        String trimmedbidderId = bidderId.trim();
        if (!Phone.isValidPhone(trimmedbidderId)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new CalendarBidderId(trimmedbidderId);
    }

}
