package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_QUANTITY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.PresetCommand;
import seedu.address.logic.commands.enums.Inequality;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.ParseIndexException;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Email;
import seedu.address.model.vendor.Name;
import seedu.address.model.vendor.Phone;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses {@code inequality} into an {@code Inequality} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the string is not recognized as an inequality symbol
     */
    public static Inequality parseInequality(String inequality) throws ParseException {
        String trimmedInequality = inequality.trim();

        if (!StringUtil.isInequality(trimmedInequality)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_INEQUALITY, trimmedInequality));
        }

        return Inequality.get(trimmedInequality);
    }

    /**
     * Parses {@code price} into a double and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the string is not a valid price
     */
    public static double parsePrice(String price) throws ParseException {
        String trimmedPrice = price.trim();
        if (!StringUtil.isNonNegativeUnsignedDouble(trimmedPrice)) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_PRICE, trimmedPrice));
        }
        return Double.parseDouble(trimmedPrice);
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
     * Parses {@code oneBasedIndex} into an {@code Index} with indexName
     * and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseIndexException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex, String indexName) throws ParseIndexException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseIndexException(String.format(MESSAGE_INVALID_INDEX, indexName));
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code quantity} into an {@code Integer} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseIndexException if the specified quantity is a zero or negative value, or not an Integer.
     */
    public static int parseQuantity(String quantity) throws ParseIndexException {
        String trimmedQuantity = quantity.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedQuantity)) {
            throw new ParseIndexException(MESSAGE_INVALID_QUANTITY);
        }
        return Integer.parseInt(trimmedQuantity);
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses and validates the string for Preset Command.
     */
    public static String[] checkPresetSyntax(String modeType) throws ParseException {
        requireNonNull(modeType);
        String trimArgs = modeType.trim();
        if (modeType.equals("")) {
            throw new ParseException(PresetCommand.MESSAGE_USAGE);
        }
        int firstSpace = trimArgs.indexOf(' ');
        String[] argsArr = new String[2];

        argsArr[0] = trimArgs;
        argsArr[1] = "";
        if (firstSpace != -1) {
            argsArr[0] = trimArgs.substring(0, firstSpace).trim();
            argsArr[1] = trimArgs.substring(firstSpace + 1).trim();
        }

        String[] commands = {
            "save",
            "load",
            "delete"
        };
        ArrayList<String> matchingCommands = new ArrayList<>(Arrays.asList(commands));
        matchingCommands.removeIf(s -> !s.startsWith(argsArr[0]));
        if (matchingCommands.size() == 0) {
            throw new ParseException(PresetCommand.MESSAGE_USAGE);
        }

        return argsArr;
    }

    /**
     * Checks whether the number of arguments are from min to max inclusive.
     * Throws a ParseException if given String is empty.
     */
    public static void checkArgsLength(String[] argsArr, String commandWord, String messageUsage,
                                       int min, int max) throws ParseException {
        // Check for empty String
        if (argsArr.length == 1 && argsArr[0].equals("") || argsArr.length < min) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(Messages.MESSAGE_INSUFFICIENT_ARGUMENTS, commandWord, min, messageUsage)));
        } else if (argsArr.length > max) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(Messages.MESSAGE_TOO_MANY_ARGUMENTS, commandWord, max, messageUsage)));
        }
    }

    /**
     * Checks whether the number of arguments are equal to argNum.
     * Throws a ParseException if given String is empty.
     */
    public static void checkArgsLength(String[] argsArr, String commandWord,
                                       String messageUsage, int argNum) throws ParseException {
        checkArgsLength(argsArr, commandWord, messageUsage, argNum, argNum);
    }

    /**
     * Checks whether the number of argument is at least min.
     */
    public static void argsLengthAtLeast(String[] argsArr, String commandWord,
                                         String messageUsage, int min) throws ParseException {
        if (argsArr.length == 1 && argsArr[0].equals("") || argsArr.length < min) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(Messages.MESSAGE_INSUFFICIENT_ARGUMENTS, commandWord, min, messageUsage)));
        }
    }
}
