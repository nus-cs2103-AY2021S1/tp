package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.*;
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

    public static School parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String trimmedSchool = school.trim();
        if (!Name.isValidName(trimmedSchool)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new School(trimmedSchool);
    }

    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Name.isValidName(trimmedYear)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Year(trimmedYear);
    }
    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static ClassVenue parseClassVenue(String classVenue) throws ParseException {
        requireNonNull(classVenue);
        String trimmedClassVenue = classVenue.trim();
        if (!ClassVenue.isValidClassVenue(trimmedClassVenue)) {
            throw new ParseException(ClassVenue.MESSAGE_CONSTRAINTS);
        }
        return new ClassVenue(trimmedClassVenue);
    }

    public static ClassTime parseClassTime(String classTime) throws ParseException {
        requireNonNull(classTime);
        String trimmedClassTime = classTime.trim();
        if (!Name.isValidName(trimmedClassTime)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ClassTime(trimmedClassTime);
    }

    public static AdditionalDetails parseAdditionalDetails(String additionalDetails) throws ParseException {
        requireNonNull(additionalDetails);
        String trimmedAdditionalDetails = additionalDetails.trim();
        if (!Name.isValidName(trimmedAdditionalDetails)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new AdditionalDetails(trimmedAdditionalDetails);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static MeetingLink parseMeetingLink(String meetingLink) throws ParseException {
        requireNonNull(meetingLink);
        String trimmedMeetingLink = meetingLink.trim();
        if (!MeetingLink.isValidEmail(trimmedMeetingLink)) {
            throw new ParseException(MeetingLink.MESSAGE_CONSTRAINTS);
        }
        return new MeetingLink(trimmedMeetingLink);
    }

    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Name.isValidName(trimmedSubject)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
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

    public static ClassVenue parseAddress(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Name.isValidName(trimmedSubject)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new ClassVenue(trimmedSubject);
    }
}
