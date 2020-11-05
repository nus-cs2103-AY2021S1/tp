package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
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
    public static final String MESSAGE_INVALID_DATE = "Meeting Date is not in valid format. dd-MM-yyy";
    public static final String MESSAGE_INVALID_DATE_CURRENT = "Meeting Date keyed in has passed or is invalid.";
    public static final String MESSAGE_INVALID_STARTTIME = "Start time is not in valid format. HH:mm";
    public static final String MESSAGE_INVALID_ENDTIME = "End time is not in valid format. HH:mm";

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
        if (!Name.isValidLength(name)) {
            throw new ParseException(Name.NAME_LENGTH_EXCEED);
        }
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
        if (!Phone.isValidPhone(trimmedPhone) || !Phone.isValidLength(trimmedPhone)) {
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
     * Parses {@code meetingDate} into an {@code MeetingDate} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the format of meetingDate is wrong.
     */
    public static MeetingDate parseDate(String date) throws ParseException {
        String trimmedTime = date.trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateObject = formatter.parse(trimmedTime);
            Date currentActualDate = Calendar.getInstance().getTime();
            if (dateObject.before(currentActualDate)) {
                throw new ParseException(MeetingDate.MESSAGE_CONSTRAINTS_PAST_DATE);
            }
            String dateString = formatter.format(dateObject);
            return new MeetingDate(dateString);
        } catch (java.text.ParseException e) {
            throw new ParseException(MeetingDate.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code startTime} into an {@code StartTime} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the start time is of the wrong format.
     */
    public static StartTime parseStartTime(String startTime) throws ParseException {
        String trimmedTime = startTime.trim();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            java.util.Date startTimeObject = formatter.parse(trimmedTime);
            String startString = formatter.format(startTimeObject);
            return new StartTime(startString);
        } catch (java.text.ParseException e) {
            throw new ParseException(StartTime.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code endTime} into an {@code Endtime} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the end time is of the wrong format.
     */
    public static EndTime parseEndTime(String endTime) throws ParseException {
        String trimmedTime = endTime.trim();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            java.util.Date endTimeObject = formatter.parse(trimmedTime);
            String endString = formatter.format(endTimeObject);
            return new EndTime(endString);
        } catch (java.text.ParseException e) {
            throw new ParseException(EndTime.MESSAGE_CONSTRAINTS);
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
            throw new ParseException(Price.MESSAGE_NOT_NUMERIC);
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
