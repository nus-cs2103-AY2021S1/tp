package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleId;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
import seedu.address.model.tutorialgroup.TutorialGroupId;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_UPPER_BOUND = "Upper bound must be a positive integer.";

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

    /**
     * Parses a {@code String tutorial group id} into a {@code TutorialGroupId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorial group id} is invalid.
     */
    public static TutorialGroupId parseTutorialGroupId(String tutorialGroupId) throws ParseException {
        requireNonNull(tutorialGroupId);
        String trimmedModule = tutorialGroupId.trim();
        if (!TutorialGroupId.isValidTutorialGroupId(trimmedModule)) {
            throw new ParseException(TutorialGroupId.MESSAGE_CONSTRAINTS);
        }
        return new TutorialGroupId(tutorialGroupId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static DayOfWeek parseDayOfWeek(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        if (!DayOfWeek.isValidDayOfWeek(trimmedDay)) {
            throw new ParseException(DayOfWeek.MESSAGE_CONSTRAINTS);
        }
        return new DayOfWeek(trimmedDay);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     *
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TimeOfDay[] parseTimesOfDay(String startTime, String endTime) throws ParseException {
        requireAllNonNull(startTime, endTime);
        String trimmedStartTime = startTime.trim();
        String trimmedEndTime = endTime.trim();
        if (!TimeOfDay.isValidTimeOfDay(trimmedStartTime) || !TimeOfDay.isValidTimeOfDay(trimmedEndTime)) {
            throw new ParseException(TimeOfDay.MESSAGE_CONSTRAINTS);
        } else if (!TimeOfDay.isValidTimes(trimmedStartTime, trimmedEndTime)) {
            throw new ParseException(TimeOfDay.TIME_CONSTRAINTS);
        }
        TimeOfDay[] timeOfDays = {new TimeOfDay(trimmedStartTime), new TimeOfDay(trimmedEndTime)};
        return timeOfDays;
    }

    /**
     * Parses user input into a single TimeOfDay
     * @param startTime
     * @throws ParseException if invalid startTime String provided
     */
    public static TimeOfDay parseTimeOfDay(String startTime) throws ParseException {
        requireNonNull(startTime);
        String trimmedStartTime = startTime.trim();
        if (!TimeOfDay.isValidTimeOfDay(trimmedStartTime)) {
            throw new ParseException(TimeOfDay.MESSAGE_CONSTRAINTS);
        }
        return new TimeOfDay(trimmedStartTime);
    }

    /**
     * Parses a {@code String module id} into a {@code ModuleId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code module id} is invalid.
     */
    public static ModuleId parseModuleId(String moduleId) throws ParseException {
        requireNonNull(moduleId);
        String trimmedModule = moduleId.trim();
        if (!ModuleId.isValidModuleId(trimmedModule)) {
            throw new ParseException(ModuleId.MESSAGE_CONSTRAINTS);
        }
        return new ModuleId(trimmedModule);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static int parseUpperBound(String upperBoundString) throws ParseException {
        requireNonNull(upperBoundString);
        String trimmedUpperBound = upperBoundString.trim();
        int upperBoundInt;
        try {
            upperBoundInt = Integer.parseInt(trimmedUpperBound);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_UPPER_BOUND);
        }
        if (upperBoundInt <= 0) {
            throw new ParseException(MESSAGE_INVALID_UPPER_BOUND);
        }
        return upperBoundInt;
    }
}
