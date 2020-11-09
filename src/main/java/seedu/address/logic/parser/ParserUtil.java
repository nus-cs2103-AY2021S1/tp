package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ROOM_ID = "Room Id is entered in invalid format";
    public static final String MESSAGE_INVALID_BOOKING_ID = "Booking Id is entered in invalid format";
    public static final String MESSAGE_INVALID_DATE = "Date is entered in invalid format";
    public static final String MESSAGE_INVALID_PERSON_ID = "Person Id is entered in invalid format";
    public static final String MESSAGE_INVALID_IS_ACTIVE = "Invalid archived state";

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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String person Id} into a {@code int}.
     *
     * @param id person id as entered by user
     * @return person id as an integer
     * @throws ParseException if the given {@code person Id} is invalid
     */
    public static int parsePersonId(String id) throws ParseException {
        requireNonNull(id);
        try {
            int personId = Integer.parseInt(id);
            return personId;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_PERSON_ID);
        }
    }

    /**
     * Parses a {@code String room Id} into a {@code int}.
     *
     * @param id room id as entered by user
     * @return room id as an integer
     * @throws ParseException if the given {@code room Id} is invalid
     */
    public static int parseRoomId(String id) throws ParseException {
        requireNonNull(id);
        try {
            int roomId = Integer.parseInt(id);
            return roomId;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_ROOM_ID);
        }
    }

    /**
     * Parses a {@code String booking Id} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param id booking id as entered by user
     * @return booking id as an integer
     * @throws ParseException if the given {@code booking Id} is invalid
     */
    public static int parseBookingId(String id) throws ParseException {
        requireNonNull(id);

        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_BOOKING_ID);
        }
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}
     *
     * @param date the date entered by the user
     * @return the date as a LocalDate
     * @throws ParseException if the given {@code date} is invalid
     */
    public static LocalDate parseDate(String date) throws ParseException {
        try {
            LocalDate newDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return newDate;
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Checks if the input is a valid representation of a {@code boolean}, case-insensitive.
     *
     * @param input
     * @return a boolean
     */
    private static boolean isValidBooleanString(String input) {
        return "true".equalsIgnoreCase(input) || "false".equalsIgnoreCase(input);
    }

    /**
     * Parses a {@code String date} into a {@code boolean}
     *
     * @param bool the boolean entered by the user
     * @return the boolean as a boolean
     * @throws ParseException if the given {@code date} is invalid
     */
    public static boolean parseIsActive(String bool) throws ParseException {
        if (!isValidBooleanString(bool)) {
            throw new ParseException(MESSAGE_INVALID_IS_ACTIVE);
        }

        boolean isActive = Boolean.parseBoolean(bool);
        return isActive;
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
     * Parses a {@code String room type} into a {@code int}.
     *
     * @param roomType room type as entered by user
     * @return room type as an integer
     * @throws ParseException if the given {@code room type} is invalid
     */
    public static int parseRoomType(String roomType) throws ParseException {
        requireNonNull(roomType);
        try {
            int value = Integer.parseInt(roomType.trim());

            if (value >= 1 && value <= 3) {
                return value;
            } else {
                throw new ParseException("Only 1, 2, 3 allowed.");
            }
        } catch (NumberFormatException | ParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_ROOM_TYPE);
        }
    }
}
