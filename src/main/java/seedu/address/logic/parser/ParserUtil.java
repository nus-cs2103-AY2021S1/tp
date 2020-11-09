package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.StringUtil.isWithinCharacterLimit;

import java.time.format.DateTimeParseException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.InvalidIndexException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;
import seedu.address.model.friend.Mobile;
import seedu.address.model.friend.Passport;

/**
 * Contains utility methods used for parsing strings in the various *WanderlustParser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned positive integer.";
    public static final int OBJECT_TYPE_POSITION = 1;
    public static final int INDEX_POSITION = 2;
    public static final int SORT_TYPE_POSITION = 2;
    public static final int FIND_WORD_POSITION = 2;

    public static final int ACTIVITY_INDEX = 0;
    public static final int ACCOMMODATION_INDEX = 1;
    public static final int FRIEND_INDEX = 2;
    public static final int INVALID_INDEX = -1;

    // For copy and move commands
    public static final int ACTIVITY_INDEX_POSITION = 1;
    public static final int TRAVELPLAN_INDEX_POSITION = 2;


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new InvalidIndexException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX));
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
        if (!isWithinCharacterLimit(trimmedName, Name.CHARACTER_LIMIT)) {
            throw new ParseException(Name.MESSAGE_NAME_EXCEEDS_CHARACTER_LIMIT);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String location} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!isWithinCharacterLimit(trimmedLocation, Location.CHARACTER_LIMIT)) {
            throw new ParseException(Location.MESSAGE_LOCATION_EXCEEDS_CHARACTER_LIMIT);
        }
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String cost} into a {@code Cost}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cost} is invalid.
     */
    public static Cost parseCost(String cost) throws ParseException {
        requireNonNull(cost);
        String trimmedCost = cost.trim();
        if (!Cost.isValidCost(trimmedCost)) {
            throw new ParseException(Cost.MESSAGE_CONSTRAINTS);
        }
        return new Cost(trimmedCost);
    }

    /**
     * Parses a {@code String passport} into a {@code Passport}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code passport} is invalid.
     */
    public static Passport parsePassport(String passport) throws ParseException {
        requireNonNull(passport);
        String trimmedPassport = passport.trim();
        if (!Passport.isValidPassport(trimmedPassport)) {
            throw new ParseException(Passport.MESSAGE_CONSTRAINTS);
        }
        return new Passport(trimmedPassport);
    }

    /**
     * Parses a {@code String activityDateTime} into a {@code WanderlustDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code activityDateTime} is invalid.
     */
    public static WanderlustDateTime parseActivityDateTime(String activityDateTime)
            throws ParseException, DateTimeParseException {
        requireNonNull(activityDateTime);
        String trimmedActivityDateTime = activityDateTime.trim();
        if (!WanderlustDateTime.isValidWanderlustDateTime(trimmedActivityDateTime)) {
            throw new ParseException(WanderlustDateTime.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDateTime(trimmedActivityDateTime);
    }

    /**
     * Parses a {@code String importance} into a {@code Importance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code importance} is invalid.
     */
    public static Importance parseImportance(String importance) throws ParseException {
        requireNonNull(importance);
        String trimmedImportance = importance.trim();
        if (!Importance.isValidImportance(trimmedImportance)) {
            throw new ParseException(Importance.MESSAGE_CONSTRAINTS);
        }
        return new Importance(trimmedImportance);
    }


    /**
     * Parses a {@code String date} into a {@code WanderlustDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code WanderlustDate} is invalid.
     */
    public static WanderlustDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!WanderlustDate.isValidWanderlustDate(trimmedDate)) {
            throw new ParseException(WanderlustDate.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDate(trimmedDate);
    }

    /**
     * Parses a {@code String dateTime} into a {@code WanderlustDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code WanderlustDateTime} is invalid.
     */
    public static WanderlustDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!WanderlustDateTime.isValidWanderlustDateTime(trimmedDateTime)) {
            throw new ParseException(WanderlustDateTime.MESSAGE_CONSTRAINTS);
        }
        return new WanderlustDateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String mobile} into a {@code Mobile}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code mobile} is invalid.
     */
    public static Mobile parseMobile(String mobile) throws ParseException {
        requireNonNull(mobile);
        String trimmedMobile = mobile.trim();
        if (!Mobile.isValidMobile(trimmedMobile)) {
            throw new ParseException(Mobile.MESSAGE_CONSTRAINTS);
        }
        return new Mobile(trimmedMobile);
    }

    /**
     * Removes the dash character "-" from the zeroth index of the string.
     * @param str String of format "-string", with dash in the zeroth index.
     * @return str without dash character in the zeroth index.
     */
    public static String removeDash(String str, String messageUsage) throws ParseException {
        if (str.charAt(0) != '-') {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
        return str.substring(1);
    }
}
