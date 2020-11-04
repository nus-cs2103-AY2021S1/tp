package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Link;
import seedu.address.model.task.MeetingLink;

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
     * Validates a {@code String url} an returns a {@Code Boolean}
     *
     * @throws ParseException if the given {@code String url} is invalid.
     */
    public static boolean validateLink(String url) throws ParseException {
        requireNonNull(url);
        String trimmedUrl = url.trim();
        boolean isValid = Link.isValidUrl(trimmedUrl);
        if (!isValid) {
            throw new ParseException((Link.MESSAGE_CONSTRAINTS));
        }
        return true;
    }


    /**
     * Returns a Collaborative Link that is guaranteed to have a valid URL.
     *
     * @param description The description of the Collaborative Link
     * @param url The url to the Collaborative Folder
     * @return A Collaborative Link object with description and url
     */
    public static CollaborativeLink parseCollaborativeLink(String description, String url) {
        requireNonNull(description);
        String trimmedUrl = url.trim();
        return new CollaborativeLink(description, trimmedUrl);
    }

    /**
     * Returns a Meeting Link that is guaranteed to have a valid URL.
     *
     * @param description The description of the Meeting Link
     * @param url The url to the Meeting
     * @param meetingTime The meeting time
     * @return A Meeting Link object with description, url, and meeting time
     */
    public static MeetingLink parseMeetingLink(String description, String url, String meetingTime) {
        requireNonNull(description, meetingTime);
        String trimmedUrl = url.trim();
        return new MeetingLink(description, trimmedUrl, meetingTime);
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
     * Returns true if date is valid.
     * @param date input by user
     * @return boolean
     */
    public static boolean checkDateValidity(String date) {
        String[] dateSplit = date.split("-");
        String strDay = dateSplit[0];
        String strMonth = dateSplit[1];
        String strYear = dateSplit[2];

        Integer day = Integer.parseInt(strDay);
        Integer month = Integer.parseInt(strMonth);
        Integer year = Integer.parseInt(strYear);

        boolean checkLength = strDay.length() == 2 && strMonth.length() == 2 && strYear.length() == 4;
        boolean checkDay = day <= 31 && day > 0;
        boolean checkMonth = month > 0 && month <= 12;
        boolean checkYear = year > 1970;

        return checkLength && checkDay && checkMonth && checkYear;
    }

    /**
     * Returns true if time is valid.
     * @param time input by user
     * @return boolean
     */
    public static boolean checkTimeValidity(String time) {
        boolean checkLength = time.length() == 4;
        if (checkLength) {
            Integer hour = Integer.parseInt(time.substring(0, 2));
            Integer minute = Integer.parseInt(time.substring(2, 4));

            boolean checkHour = hour >= 0 && hour <= 23;
            boolean checkMinute = minute >= 0 && minute <= 59;
            return checkHour && checkMinute;
        } else {
            return false;
        }
    }
}
