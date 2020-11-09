package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.room.RoomCliSyntax;
import seedu.address.logic.parser.task.TaskCliSyntax;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_UNSIGNED_INT = "Please ensure that the value "
            + "for the field \"%1$s\" is a number between 1 and " + Integer.MAX_VALUE + "."
            + "\nYour current input: %2$s";

    //@author LeeMingDe
    /**
     * Parses {@code roomNumber} into an {@code Integer} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified room number is invalid (not non-zero unsigned integer).
     */
    public static Integer parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedRoomNumber)) {
            throw new ParseException(String.format(MESSAGE_INVALID_UNSIGNED_INT,
                    RoomCliSyntax.PREFIX_ROOM_NUMBER, roomNumber));
        }
        return Integer.parseInt(trimmedRoomNumber);
    }
    //@author LeeMingDe

    /**
     * Parses {@code taskIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified task index is invalid (not non-zero unsigned integer).
     */
    public static Index parseTaskIndex(String taskIndex) throws ParseException {
        requireNonNull(taskIndex);
        String trimmedIndex = taskIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(String.format(MESSAGE_INVALID_UNSIGNED_INT,
                    TaskCliSyntax.PREFIX_TASK_NUMBER, taskIndex));
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if exactly one prefix contains a non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean isExactlyOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .count() == 1;
    }
}
