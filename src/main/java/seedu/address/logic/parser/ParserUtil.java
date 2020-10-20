package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.Type;

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
     * Parses {@code oneBasedIndexes} into an {@code Index array} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index[] parseVarargsIndex(String oneBasedIndexes) throws ParseException {
        String[] splited = oneBasedIndexes.trim().split(" ");
        Index[] indexes = new Index[splited.length];
        for (int i = 0; i < splited.length; i++) {
            if (!StringUtil.isNonZeroUnsignedInteger(splited[i])) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexes[i] = Index.fromOneBased(Integer.parseInt(splited[i]));
        }
        return indexes;
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        if (!Title.isValidTitle(trimmedTitle)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedTitle);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static DateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTime.DATETIME_MESSAGE_CONSTRAINTS);
        }
        return new DateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String time} into a {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
        if (!DateTime.isValidTime(trimmedTime)) {
            throw new ParseException(DateTime.TIME_MESSAGE_CONSTRAINTS);
        }
        return LocalTime.parse(trimmedTime, parser);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (!DateTime.isValidDate(trimmedDate)) {
            throw new ParseException(DateTime.DATE_MESSAGE_CONSTRAINTS);
        }
        return LocalDate.parse(trimmedDate, parser);
    }

    /**
     * Parses a {@code String day} into a {@code DayOfTheWeek}.
     * Leading and trailing whitespaces will be trimmed, input is case-insensitive.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static DayOfWeek parseDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        String dayOfWeek = trimmedDay.toUpperCase();
        switch (dayOfWeek) {
        case "MON":
        case "MONDAY":
            return DayOfWeek.MONDAY;
        case "TUE":
        case "TUESDAY":
            return DayOfWeek.TUESDAY;
        case "WED":
        case "WEDNESDAY":
            return DayOfWeek.WEDNESDAY;
        case "THU":
        case "THURSDAY":
            return DayOfWeek.THURSDAY;
        case "FRI":
        case "FRIDAY":
            return DayOfWeek.FRIDAY;
        case "SAT":
        case "SATURDAY":
            return DayOfWeek.SATURDAY;
        case "SUN":
        case "SUNDAY":
            return DayOfWeek.SUNDAY;
        default:
            throw new ParseException(DateTime.DAY_MESSAGE_CONSTRAINTS);
        }
    }
    /**
     * Parses a {@code String type} into an {@code Type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static Type parseType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!Type.isValidType(trimmedType)) {
            throw new ParseException(Type.MESSAGE_CONSTRAINTS);
        }
        return new Type(trimmedType);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
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
