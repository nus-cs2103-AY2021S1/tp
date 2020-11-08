package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.tag.Tag;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

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
            throw new ParseException(Name.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!Name.isValidLength(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS_LIMIT);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String day} into a {@code Day}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static Day parseDay(String day) throws ParseException {
        requireNonNull(day);
        Day dayEnum = Day.getDayEnum(day.trim().toLowerCase());
        if (Day.isUnknownDay(dayEnum)) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        return dayEnum;
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!Duration.isValidDuration(trimmedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS_FORMAT);
        }

        String[] timeSplit = trimmedDuration.split("-");

        int startHour = Integer.parseInt(timeSplit[0].substring(0, 2));
        int startMinute = Integer.parseInt(timeSplit[0].substring(2, 4));
        LocalTime startTime = LocalTime.of(startHour, startMinute);

        int endHour = Integer.parseInt(timeSplit[1].substring(0, 2));
        int endMinute = Integer.parseInt(timeSplit[1].substring(2, 4));
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        if (!Duration.isValidDuration(startTime, endTime)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS_ORDER);
        }

        return new Duration(startTime, endTime);
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
        final Set<String> lowerCaseTagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
            String lowerCaseTagName = tagName.toLowerCase();
            if (lowerCaseTagSet.contains(lowerCaseTagName)) {
                throw new ParseException("Tags are case-sensitive! Please refrain from adding duplicates.");
            }
            lowerCaseTagSet.add(lowerCaseTagName);
        }
        return tagSet;
    }

    /**
     * Parses {@code String height} into a {@code Height}.
     */
    public static Height parseHeight(String height) throws ParseException {
        requireNonNull(height);
        String trimmedHeight = height.trim();
        if (!Height.isValidHeight(trimmedHeight)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS_FORMAT);
        }

        double parsedHeight = Double.parseDouble(trimmedHeight);
        if (!Height.isValidHeight(parsedHeight)) {
            throw new ParseException(Height.MESSAGE_CONSTRAINTS_LIMIT);
        }

        return new Height(parsedHeight);
    }

    /**
     * Parses {@code String weight} into a {@code Weight}.
     */
    public static Weight parseWeight(String weight) throws ParseException {
        requireNonNull(weight);
        String trimmedWeight = weight.trim();
        if (!Weight.isValidWeight(trimmedWeight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS_FORMAT);
        }

        double parsedWeight = Double.parseDouble(trimmedWeight);
        if (!Weight.isValidWeight(parsedWeight)) {
            throw new ParseException(Weight.MESSAGE_CONSTRAINTS_LIMIT);
        }
        return new Weight(parsedWeight);
    }

    /**
     * Parses a {@code String calorie} into a Calorie.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code calorie} is invalid.
     */
    public static Calorie parseCalorie(String calorie) throws ParseException {
        requireNonNull(calorie);
        String trimmedCalorie = calorie.trim();
        if (!Calorie.isValidCalorie(trimmedCalorie)) {
            throw new ParseException(Calorie.MESSAGE_CONSTRAINTS_FORMAT);
        }

        int parsedCalorie = Integer.parseInt(trimmedCalorie);
        if (!Calorie.isValidCalorie(parsedCalorie)) {
            throw new ParseException(Calorie.MESSAGE_CONSTRAINTS_LIMIT);
        }
        return new Calorie(parsedCalorie);
    }

}
