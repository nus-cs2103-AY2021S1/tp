package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramAddress;
import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagTask;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_ALPHAINDEX = "Alphabetical index must be a single letter.";
    public static final String MESSAGE_INVALID_LOWERCASEINDEX = "Alphabetical index must be in lowercase!";
    public static final String MESSAGE_MISSING_ARGS = "Need one more argument!";

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
     * Parses {@code alphaIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified alphaIndex is not valid or is not in lower case.
     */
    public static Index parseAlphaIndex(String alphaIndex) throws ParseException {
        String trimmedIndex = alphaIndex.trim();
        if (trimmedIndex.length() > 1) {
            throw new ParseException(MESSAGE_INVALID_ALPHAINDEX);
        }
        int asciiRepresentation = trimmedIndex.charAt(0);
        if (asciiRepresentation < 97 || asciiRepresentation > 122) {
            throw new ParseException(MESSAGE_INVALID_LOWERCASEINDEX);
        }
        int convertedIndex = asciiRepresentation - 97;
        return Index.fromZeroBased(convertedIndex);
    }

    /**
     * Parses an {@code args} containing two Index into an array of two Strings and returns them.
     */
    public static String[] parseTwoIndex(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] arrOfIndex = trimmedArgs.split(" ", 2);

        if (arrOfIndex.length != 2) {
            throw new ParseException(MESSAGE_MISSING_ARGS);
        }
        return arrOfIndex;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static PersonName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!PersonName.isValidName(trimmedName)) {
            throw new ParseException(PersonName.MESSAGE_CONSTRAINTS);
        }
        return new PersonName(trimmedName);
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
     * Parses a {@code String telegramAddress} into a {@code TelegramAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static TelegramAddress parseTelegramAddress(String telegramAddress) throws ParseException {
        requireNonNull(telegramAddress);
        String trimmedTelegramAddress = telegramAddress.trim();
        if (!TelegramAddress.isValidTelegramAddress(trimmedTelegramAddress)) {
            throw new ParseException(TelegramAddress.MESSAGE_CONSTRAINTS);
        }
        return new TelegramAddress(trimmedTelegramAddress);
    }

    /**
     * Parses {@code Collection<String> tagNames} into a {@code Set<TagName>}.
     */
    public static Set<TagName> parseTagNames(Collection<String> tagNames) throws ParseException {
        requireNonNull(tagNames);
        final Set<TagName> tagNameSet = new HashSet<>();
        for (String tagName : tagNames) {
            tagNameSet.add(parseTagName(tagName));
        }
        return tagNameSet;
    }

    /**
     * Parses a {@code String tagName} into a {@code TagName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagName} is invalid.
     */
    public static TagName parseTagName(String tagName) throws ParseException {
        requireNonNull(tagName);
        String trimmedTag = tagName.trim();
        if (!TagName.isValidTagName(trimmedTag)) {
            throw new ParseException(TagName.MESSAGE_CONSTRAINTS);
        }
        return new TagName(trimmedTag);
    }

    /**
     * Parses a {@code String taskDescription} and a {@code boolean isDone} into a {@code TagTask}.
     * Leading and trailing whitespaces in {@code String taskDescription} will be trimmed.
     *
     * @throws ParseException if the given {@code taskDescription} is invalid
     */
    public static TagTask parseTagTask(String taskDescription, boolean isDone) throws ParseException {
        requireNonNull(taskDescription);
        String trimmedTaskDescription = taskDescription.trim();
        if (!TagTask.isValidTaskDescription(trimmedTaskDescription)) {
            throw new ParseException(TagTask.MESSAGE_CONSTRAINTS);
        }
        return new TagTask(taskDescription, isDone);
    }

    /**
     * Parses a {@code String link} into a {@code MeetingLink}.
     * Leading and trailing whitespaces in {@code String link} will be trimmed.
     *
     * @throws ParseException if the given {@code link} is invalid
     */
    public static Optional<MeetingLink> parseMeetingLink(String link) throws ParseException {
        requireNonNull(link);
        if (!MeetingLink.isValidMeetingLink(link)) {
            throw new ParseException(MeetingLink.MESSAGE_CONSTRAINTS);
        }
        try {
            return Optional.of(new MeetingLink(link));
        } catch (MalformedURLException e) {
            return Optional.empty();
        }
    }

}
