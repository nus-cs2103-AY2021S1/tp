package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CdCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.TagName;

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
    public static TagName parseTagName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TagName.isValidTagName(trimmedName)) {
            throw new ParseException(TagName.MESSAGE_CONSTRAINTS);
        }
        return new TagName(trimmedName);
    }

    /**
     * Parses a {@code String address} into an {@code FileAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static FileAddress parseFileAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!FileAddress.isValidFileAddress(trimmedAddress)) {
            throw new ParseException(FileAddress.MESSAGE_CONSTRAINTS);
        }
        return new FileAddress(trimmedAddress);
    }

    /**
     * Parses an address string as an absolute address.
     * @param address the address string
     * @return the absolute address string
     * @throws ParseException if the given address string is blank.
     */
    public static String parseAbsoluteAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (trimmedAddress.isBlank()) {
            throw new ParseException(CdCommand.MESSAGE_BLANK_PATH);
        }
        return trimmedAddress;
    }

    /**
     * Parses an address string as a child address.
     * @param address the address string
     * @return the child address string
     * @throws ParseException if the address string given is blank.
     */
    public static String parseChildAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (trimmedAddress.isBlank()) {
            throw new ParseException(CdCommand.MESSAGE_BLANK_PATH);
        }
        return trimmedAddress;
    }

    /**
     * Parses an address string as a parent address.
     * @param address the address string
     * @return the parent address string
     * @throws ParseException if the given address string is not blank.
     */
    public static String parseParentAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!trimmedAddress.isBlank()) {
            throw new ParseException(CdCommand.MESSAGE_PARENT_PATH_NOT_BLANK);
        }
        return trimmedAddress;
    }

    /** Parses a {@code String label} into an {@code Label}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code label} is invalid.
     */
    public static Label parseLabel(String label) throws ParseException {
        requireNonNull(label);
        String trimmedLabel = label.trim();
        if (!Label.isValidLabel(trimmedLabel)) {
            throw new ParseException(Label.MESSAGE_CONSTRAINTS);
        }
        return new Label(trimmedLabel);
    }

    /**
     * Parses a {@code Collection<Label> labels} into an {@code Set<Label>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code labels} is invalid.
     */
    public static Set<Label> parseLabels(Collection<String> labels) throws ParseException {
        requireNonNull(labels);
        final Set<Label> labelSet = new HashSet<>();
        for (String label : labels) {
            labelSet.add(parseLabel(label));
        }
        return labelSet;
    }

}
