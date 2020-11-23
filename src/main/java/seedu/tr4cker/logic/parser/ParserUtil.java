package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.commons.util.StringUtil;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.util.NaturalDateUtil;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_DAYS = "Number of days given is not a non-negative integer.";
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
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified task index is invalid (not non-zero unsigned integer).
     */
    public static Index parseTaskIndex(String oneBasedIndex, String invalidMsg) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (StringUtil.isInteger(trimmedIndex) && !StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, invalidMsg));
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified module index is invalid (not non-zero unsigned integer).
     */
    public static Index parseModuleIndex(String oneBasedIndex, String invalidMsg) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (StringUtil.isInteger(trimmedIndex) && !StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified event index is invalid (not non-zero unsigned integer).
     */
    public static Index parseEventIndex(String oneBasedIndex, String invalidMsg) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (StringUtil.isInteger(trimmedIndex) && !StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
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
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();

        if (NaturalDateUtil.isNaturalDeadline(trimmedDeadline)) {
            trimmedDeadline = NaturalDateUtil.convertToDateTime(trimmedDeadline);
        }
        if (!Deadline.isDeadlineWithTime(trimmedDeadline)) {
            trimmedDeadline += Deadline.DEFAULT_TIME;
        }
        if (!Deadline.isValidDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        if (!Deadline.isFutureDeadline(trimmedDeadline)) {
            throw new ParseException(Deadline.MESSAGE_FUTURE_CONSTRAINT);
        }
        return new Deadline(trimmedDeadline, true);
    }

    /**
     * Parses a {@code String tr4cker} into an {@code description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tr4cker} is invalid.
     */
    public static TaskDescription parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!TaskDescription.isValidTaskDescription(trimmedDescription)) {
            throw new ParseException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        return new TaskDescription(trimmedDescription);
    }

    /**
     * Parses a {@code String completionStatus} into an {@code CompletionStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code completionStatus} is invalid.
     */
    public static CompletionStatus parseCompletionStatus(String completionStatus) throws ParseException {
        requireNonNull(completionStatus);
        String trimmedCompletionStatus = completionStatus.trim();
        int trimmedCompletionStatusInt;
        try {
            trimmedCompletionStatusInt = Integer.parseInt(trimmedCompletionStatus);
        } catch (NumberFormatException numberFormatException) {
            throw new ParseException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        if (!CompletionStatus.isValidCompletionStatus(trimmedCompletionStatusInt)) {
            throw new ParseException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        return new CompletionStatus(trimmedCompletionStatusInt);
    }

    /**
     * Parses a {@code String name} and {@code String moduleCode} into a {@code Module}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleName} or {@code moduleCode} is invalid.
     */
    public static Module parseModule(String moduleName, String moduleCode) throws ParseException {
        requireAllNonNull(moduleName, moduleCode);
        String trimmedName = moduleName.trim();
        String trimmedModuleCode = moduleCode.trim();
        if (!Module.isValidModuleName(trimmedName)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new Module(trimmedName, new ModuleCode(trimmedModuleCode));
    }

    /**
     * Parses a {@code String moduleCode} into a {@code Set<ModuleCode>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code moduleCode} is invalid.
     */
    public static Set<ModuleCode> parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        final Set<ModuleCode> moduleCodeSet = new HashSet<>();
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        moduleCodeSet.add(new ModuleCode(moduleCode));
        return moduleCodeSet;
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return new EventName(trimmedEventName);
    }

    /**
     * Parses a {@code String eventDate} into a {@code EventDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventDate parseEventDate(String eventDate) throws ParseException {
        requireNonNull(eventDate);
        String trimmedEventDate = eventDate.trim();
        if (!EventDate.isValidDate(trimmedEventDate)) {
            throw new ParseException(EventDate.MESSAGE_CONSTRAINTS);
        }
        if (!EventDate.isFutureDate(trimmedEventDate)) {
            throw new ParseException(EventDate.MESSAGE_FUTURE_CONSTRAINT);
        }
        return new EventDate(trimmedEventDate, true);
    }

    /**
     * Parses a {@code String numDays} into an {@code int numDays}.
     * Leading and trailing whitespace will be trimmed.
     *
     * @throws ParseException if the {@code numDays} is invalid.
     */
    public static int parseQueryDays(String numDays) throws ParseException {
        requireNonNull(numDays);
        String trimmedNumDays = numDays.trim();
        int numDaysInt;
        try {
            numDaysInt = Integer.parseInt(trimmedNumDays);
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_INVALID_DAYS);
        }
        if (numDaysInt < 0) {
            throw new ParseException(MESSAGE_INVALID_DAYS);
        }
        return numDaysInt;
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
