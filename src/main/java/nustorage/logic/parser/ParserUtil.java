package nustorage.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import nustorage.commons.core.index.Index;
import nustorage.commons.util.StringUtil;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.person.Email;
import nustorage.model.person.Name;
import nustorage.model.person.Phone;
import nustorage.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_AMOUNT = "Amount if not a decimal value.";
    public static final String MESSAGE_INVALID_DATETIME = "Date must be of the format yyyy-mm-dd HH:mm";
    public static final String MESSAGE_INVALID_QUANITY = "Quantity is not a non-zero integer.";

    /**
     * Parses {@code itemDescription} into an String and returns it. Leading and trailing whitespaces will be
     * trimmed.
     */
    public static String parseItemDescription(String itemDescription) {
        return itemDescription.trim();
    }

    /**
     * Parses {@code quantity} into an int and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified quantity is invalid
     */
    public static int parseQuantity(String quantity) throws ParseException {
        String trimmedQuantity = quantity.trim();
        try {
            return Integer.parseInt(trimmedQuantity);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_QUANITY);
        }
    }

    /**
     * Parses {@code amount} into an {@code double} and returns it.
     * @throws ParseException if the specified amount is invalid (non-double value).
     */
    public static double parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        try {
            return Double.parseDouble(trimmedAmount);
        } catch (NumberFormatException ex) {
            throw new ParseException(MESSAGE_INVALID_AMOUNT);
        }
    }

    /**
     * Parses {@code datetimeList} into an {@code LocalDateTime} and returns it.
     * @throws ParseException if the specified input is invalid (not correctly formatted).
     */
    public static LocalDateTime parseDatetime(Optional<String> datetime) throws ParseException {
        requireNonNull(datetime);

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        if (datetime.isPresent()) {
            String[] datetimeArray = datetime.get().split(" ");

            if (datetimeArray.length == 1) {

                try {
                    date = LocalDate.parse(datetimeArray[0]);
                } catch (DateTimeParseException ex1) {
                    try {
                        time = LocalTime.parse(datetimeArray[0]);
                    } catch (DateTimeParseException ex2) {
                        throw new ParseException(MESSAGE_INVALID_DATETIME);
                    }
                }

            } else if (datetimeArray.length > 1) {

                try {
                    date = LocalDate.parse(datetimeArray[0]);
                    time = LocalTime.parse(datetimeArray[1]);
                } catch (DateTimeParseException ex1) {
                    try {
                        date = LocalDate.parse(datetimeArray[1]);
                        time = LocalTime.parse(datetimeArray[0]);
                    } catch (DateTimeParseException ex2) {
                        throw new ParseException(MESSAGE_INVALID_DATETIME);
                    }
                }
            }
        }

        return LocalDateTime.of(date, time);
    }

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
}
