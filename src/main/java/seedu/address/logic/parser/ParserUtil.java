package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Priority;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

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
     * Parses {@code multipleIndexes} into many {@code Index} and returns a list of indexes. Leading and trailing
     * whitespaces will be trimmed.
     * @throws ParseException if any of the specified indexes are invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseIndexes(String multipleIndexes) throws ParseException {
        String trimmedIndexes = multipleIndexes.trim();
        String[] indexes = trimmedIndexes.split("\\s+");
        List<Index> parsedIndexes = new ArrayList<>();

        for (String s : indexes) {
            if (!s.isEmpty()) {
                Index index = parseIndex(s);
                parsedIndexes.add(index);
            }
        }

        return parsedIndexes;
    }

    /**
     * Parses {@code zeroBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseListIndex(String zeroBasedIndex) throws ParseException {
        String trimmedIndex = zeroBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        return Index.fromZeroBased(Integer.parseInt(trimmedIndex));
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
     * Parses a {@code String deadline} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given deadline is invalid.
     */
    public static Time parseDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        if (!Time.isValidTime(trimmedDeadline)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedDeadline);
    }

    /**
     * Parses a {@code String num} into a int.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given int is invalid.
     */
    public static int parseExpectedTime(String num) throws ParseException {
        requireNonNull(num);
        String trimmedNum = num.trim();
        try {
            int n = Integer.parseInt(trimmedNum);
            if (!isValidExpectedTime(n)) {
                throw new ParseException("Invalid expected time");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid expected time");
        }
    }

    private static boolean isValidExpectedTime(int n) {
        return (ScheduleCommand.MIN_HOURS <= n && n <= ScheduleCommand.MAX_HOURS);
    }

    /**
     * Parses a {@code String moduleCode} into an {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ModuleCode} is invalid.
     */
    public static ModuleCode parseModuleCode(String moduleCode) throws ParseException {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String priority} into an {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String priorityLevel = priority.trim().toUpperCase();
        if (priorityLevel.equals(Priority.LOW_PRIORITY)) {
            return new Priority(Priority.LOW_PRIORITY);
        }
        if (priorityLevel.equals(Priority.MEDIUM_PRIORITY)) {
            return new Priority(Priority.MEDIUM_PRIORITY);
        }
        if (priorityLevel.equals(Priority.HIGH_PRIORITY)) {
            return new Priority(Priority.HIGH_PRIORITY);
        }
        throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
    }

}
