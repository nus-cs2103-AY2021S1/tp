package seedu.internhunter.logic.parser.util;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;

import java.util.stream.Stream;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.commons.util.StringUtil;
import seedu.internhunter.logic.parser.ArgumentMultimap;
import seedu.internhunter.logic.parser.Prefix;
import seedu.internhunter.logic.parser.exceptions.ParseException;

/**
 * Handles the general parsing of all commands.
 */
public class GeneralParserUtil {

    private static final int ITEM_TYPE_INDEX = 0;
    private static final int COMMAND_DETAILS_INDEX = 1;
    private static final int NUMBER_OF_ARGUMENTS = 2;

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Checks if the arguments provided by the user are valid. Arguments are valid if prefixes are all present and a
     * preamble is present when {@code isPreambleNeeded} is true, or that a preamble is absent when
     * {@code isPreambleNeeded} is false.
     *
     * @param isPreambleNeeded Indicates if there should be a preamble or not.
     * @param argumentMultimap Argument multimap.
     * @param prefixes Prefixes required in the multimap.
     * @return True if and only if the prefixes are valid.
     */
    public static boolean argumentsAreValid(boolean isPreambleNeeded, ArgumentMultimap argumentMultimap,
            Prefix... prefixes) {
        boolean prefixesArePresent = arePrefixesPresent(argumentMultimap, prefixes);
        boolean preambleIsEmpty = isPreambleEmpty(argumentMultimap);
        return prefixesArePresent && (preambleIsEmpty != isPreambleNeeded);
    }

    /**
     * Returns true if the preamble of this argument multimap is empty.
     *
     * @param argumentMultimap Argument multimap.
     * @return True if and only if preamble of this argument multimap is empty.
     */
    private static boolean isPreambleEmpty(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getPreamble().isEmpty();
    }

    /**
     * Retrieves the index in the preamble.
     *
     * @param argumentMultimap Argument multimap.
     * @return The index provided by the user, if it exists.
     * @throws ParseException If the given index by the user is invalid.
     */
    public static Index getIndexInPreamble(ArgumentMultimap argumentMultimap) throws ParseException {
        return parseIndex(argumentMultimap.getPreamble());
    }

    /**
     * Checks if item type is present. If item type is not present, then throw ParseException that displays
     * the correct command format to user.
     *
     * @param args Arguments provided by the user.
     * @param messageUsage Correct message format to display.
     * @return Item type, if it exists.
     * @throws ParseException If item type is not provided.
     */
    public static String getItemType(String args, String messageUsage) throws ParseException {
        String[] argumentTypes = getArgumentsArr(args);
        String itemType = argumentTypes[ITEM_TYPE_INDEX];

        if (itemType.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, messageUsage));
        }
        return itemType;
    }

    /**
     * Obtains the command details.
     *
     * @param args Arguments provided by the user.
     * @return Details of the command.
     */
    public static String getCommandDetails(String args) {
        String[] argumentTypes = getArgumentsArr(args);
        String dummy = "";
        if (argumentTypes.length < NUMBER_OF_ARGUMENTS) {
            return dummy; // if the user only entered the command word and the item type (did not enter details),
            // then provide this dummy string so that the relevant parser will show its error message.
        } else {
            return " " + argumentTypes[COMMAND_DETAILS_INDEX];
        }
    }

    /**
     * Obtains the array containing the arguments provided by the user.
     *
     * @param args Arguments provided by the user.
     * @return Array containing the arguments provided by the user.
     */
    private static String[] getArgumentsArr(String args) {
        return args.strip().split(" ", NUMBER_OF_ARGUMENTS);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Checks if the input command details is empty. Else throws a specified message.
     *
     * @param commandDetails String representing the input command details.
     * @param message A String representing a specific error message.
     * @throws ParseException if {@code commandDetails} is blank.
     */
    public static void checkCommandDetailsIsNotBlank(String commandDetails, String message) throws ParseException {
        if (commandDetails.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, message));
        }
    }

    /**
     * Removes whitespace from both ends of a string, and split the {@code args} by white space(s) between each words.
     *
     * @param args A string representing the input argument.
     * @return An array of strings computed by splitting {@code args} around matches of the given white spaces between
     * each words.
     */
    public static String[] getTrimmedArgsKeywords(String args) {
        String trimmedArgs = args.trim();
        return trimmedArgs.split("\\s+");
    }
}
