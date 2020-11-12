package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateTimeUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.StartDateTime;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a positive integer.";
    public static final String MESSAGE_MISSING_INDEX = "Index is not supplied in the argument.";

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
        String trimmed = oneBasedIndexes.trim();
        if (trimmed.equals("")) {
            throw new ParseException(MESSAGE_MISSING_INDEX);
        }
        String[] split = trimmed.split(" ");
        int length = split.length;
        Index[] indexes = new Index[length];
        for (int i = 0; i < length; i++) {
            if (!StringUtil.isNonZeroUnsignedInteger(split[i])) {
                throw new ParseException(MESSAGE_INVALID_INDEX);
            }
            indexes[i] = Index.fromOneBased(Integer.parseInt(split[i]));
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
    public static DeadlineDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!DeadlineDateTime.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(DateTimeUtil.DATE_TIME_CONSTRAINTS);
        }
        return new DeadlineDateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static StartDateTime parseStartDateTime(String date, String time) throws ParseException {
        requireNonNull(date, time);
        String trimmedDate = date.trim();
        String trimmedTime = time.trim();
        if (!DateTimeUtil.isValidDate(trimmedDate)) {
            throw new ParseException(DateTimeUtil.DATE_CONSTRAINTS);
        }
        if (!DateTimeUtil.isValidTime(trimmedTime)) {
            throw new ParseException(DateTimeUtil.TIME_CONSTRAINTS);
        }
        return StartDateTime.createStartDateTime(trimmedDate, trimmedTime);
    }

    /**
     * Parses a {@code String dateTime} into a {@code DateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} is invalid.
     */
    public static EndDateTime parseEndDateTime(String date, String time) throws ParseException {
        requireNonNull(date, time);
        String trimmedDate = date.trim();
        String trimmedTime = time.trim();
        if (!DateTimeUtil.isValidDate(trimmedDate)) {
            throw new ParseException(DateTimeUtil.DATE_CONSTRAINTS);
        }
        if (!DateTimeUtil.isValidTime(trimmedTime)) {
            throw new ParseException(DateTimeUtil.TIME_CONSTRAINTS);
        }
        return EndDateTime.createEndDateTime(trimmedDate, trimmedTime);
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
        DateTimeFormatter parser = DateTimeUtil.TIME_FORMATTER;
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(DateTimeUtil.TIME_CONSTRAINTS);
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
        DateTimeFormatter parser = DateTimeUtil.DATE_FORMATTER;
        if (!DateTimeUtil.isValidDate(trimmedDate)) {
            throw new ParseException(DateTimeUtil.DATE_CONSTRAINTS);
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
        DayOfWeek result; //Default value
        try {
            result = DayOfWeek.valueOf(dayOfWeek);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new ParseException(DateTimeUtil.DAY_MESSAGE_CONSTRAINTS);
        }
        return result;
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
}
